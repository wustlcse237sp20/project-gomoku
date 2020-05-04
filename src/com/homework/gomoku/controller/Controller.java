package com.homework.gomoku.controller;

import com.homework.gomoku.game.*;
import com.homework.gomoku.gui.EntryFrame;
import com.homework.gomoku.gui.GamePage;
import com.homework.gomoku.gui.TutorialPane;

import java.awt.event.*;
import java.awt.*;
import java.io.*;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Timer;


import java.text.SimpleDateFormat;
import javax.swing.*;

public class Controller {

    EntryFrame mainFrame;
    Game game;
    GamePage gamePage;
    JDialog loadOptions;
    File saveDir;


    public Controller() {
        mainFrame = new EntryFrame("new game");
    }

    public void launch() {
        saveDir = new File("./saves");
        saveDir.mkdir();
        mainFrame.setVisible(true);
        mainFrame.getEntryPage().getExitBut().addActionListener(e -> mainFrame.dispose());
        mainFrame.getEntryPage().getNewGameBut().addActionListener(e -> {
            game = new Game(new TwoPlayerBoard(15), new HumanPlayer(true), new HumanPlayer(false), new TwoPlayerRule());
            makeNewGame();
            mainFrame.setContentPane(gamePage);
            mainFrame.revalidate();
            mainFrame.repaint();
        });
        mainFrame.getEntryPage().getLoadBut().addActionListener(e -> {
            makeLoadPane();
        });

        mainFrame.getEntryPage().getTutorialBut().addActionListener(e -> {
            Tutorial[] tuts = generateTutorials();
            TutorialPane tutorialDialog = new TutorialPane(tuts);
            for (Tutorial t : tuts) {
                JButton tutBut = new JButton(t.getTutName());
                tutorialDialog.add(tutBut);
                tutBut.addActionListener(e1 -> {
                    game = new Game(new TwoPlayerBoard(15), t.getPlyr1(), t.getPlyr2(), new TwoPlayerRule());
                    makeTutGame(t);
                    mainFrame.setContentPane(gamePage);
                    mainFrame.revalidate();
                    mainFrame.repaint();
                    tutorialDialog.dispose();
                });
            }
            JButton aiBut = new JButton("Defensive AI");
            tutorialDialog.add(aiBut);
            aiBut.addActionListener(e1 -> {
                game = new Game(new TwoPlayerBoard(15), new HumanPlayer(true), null, new TwoPlayerRule());
                makeAiGame();
                mainFrame.setContentPane(gamePage);
                mainFrame.revalidate();
                mainFrame.repaint();
                tutorialDialog.dispose();
            });
            tutorialDialog.setVisible(true);
        });
    }

    private int gridRound(int length, int loc, int numInt) {
        int rem = length % numInt;
        int gap = length / numInt;
        return loc > rem * (gap + 1) ? (int) Math.round((double) loc / (gap + 1)) :
                (int) Math.round((double) (loc - rem * (gap + 1)) / gap) + rem;
    }

    public class TimerUpdater extends TimerTask {
        @Override
        public void run() {
            game.timeElapsed(1);
            int timeElapsed = game.getTimeSecondElapsed();
            int seconds = timeElapsed % 60;
            int minutes = timeElapsed / 60;
            if (seconds < 10) {
                gamePage.getTickerUpdate().setText(minutes + ": 0" + seconds);
            } else {
                gamePage.getTickerUpdate().setText(minutes + ": " + seconds);
            }
        }
    }

    private void makeAiGame() {
        gamePage = new GamePage(game);

        Timer gameTimer = new Timer();
        TimerUpdater timerUpdater = new TimerUpdater();

        gameTimer.scheduleAtFixedRate(timerUpdater, 0, 1000);
        gamePage.getSaveBut().addActionListener(e -> {
            gamePage.getPromptText().setText("Your can't save a tutorial game");
            gamePage.repaint();
        });

        //complete the undo action
        gamePage.getUndoBut().addActionListener(e -> {
            Move lastMove = game.getBoard().undoMove();
            if (lastMove != null) {
                Move last2Move = game.getBoard().undoMove();
                if(last2Move!=null) {
                    game.withDraw();
                    game.withDraw();
                    gamePage.repaint();
                    gamePage.updateInfoBar();
                }
            }
        });
        setDefaultButtons(gameTimer);
        JPanel boardArea = gamePage.getBoardArea();

        boardArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = gridRound(boardArea.getHeight(), e.getY(), game.getBoard().getBoardSize());
                int colNum = gridRound(boardArea.getWidth(), e.getX(), game.getBoard().getBoardSize());
                Move cMove = new PlayerMove(rowNum, colNum, game.getCurrentPlayer());
                if (game.isValidMove(cMove)) {
                    if (game.isEnd(cMove)) {
                        game.getBoard().placeMove(cMove);
                        String winSide = game.getCurrentPlayer().getColor() ? "Black" : "White";
                        gamePage.getPromptText().setText(winSide + " wins");
                        gameTimer.cancel();
                        gamePage.repaint();
                        boardArea.removeMouseListener(this);
                    } else {
                        game.getBoard().placeMove(cMove);
                        gamePage.getPromptText().setText("");
                        game.nextTurn();
                        Move aiMove = game.getCurrentPlayer().getMove(game);
                        game.getBoard().placeMove(aiMove);
                        game.nextTurn();
                        gamePage.repaint();
                    }
                } else {
                    gamePage.getPromptText().setText("Invalid move");
                    gamePage.repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void setDefaultButtons(Timer gameTimer) {
        gamePage.getExitBut().addActionListener(e -> {
            gameTimer.cancel();
            mainFrame.dispose();
        });
        gamePage.getMainBut().addActionListener(e -> {
            gameTimer.cancel();
            mainFrame.setContentPane(mainFrame.getEntryPage());
            mainFrame.revalidate();
            mainFrame.repaint();
        });
        gamePage.getPromptText().setText("Enjoy your game");

    }

    private void makeTutGame(Tutorial tut) {
        gamePage = new GamePage(game);
        gamePage.getSaveBut().addActionListener(e -> {
            gamePage.getPromptText().setText("Your can't save a tutorial game");
            gamePage.repaint();
        });
        gamePage.getExitBut().addActionListener(e -> {
            mainFrame.dispose();
        });
        gamePage.getMainBut().addActionListener(e -> {
            mainFrame.setContentPane(mainFrame.getEntryPage());
            mainFrame.revalidate();
            mainFrame.repaint();
        });
        gamePage.getPromptText().setText(tut.getStartMessage());
        JPanel boardArea = gamePage.getBoardArea();
        boardArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = gridRound(boardArea.getHeight(), e.getY(), game.getBoard().getBoardSize());
                int colNum = gridRound(boardArea.getWidth(), e.getX(), game.getBoard().getBoardSize());
                Move expectedMove = game.getCurrentPlayer().getMove(game);
                if (rowNum == expectedMove.getRow() && colNum == expectedMove.getCol()) {
                    if (game.isEnd(expectedMove)) {
                        game.getBoard().placeMove(expectedMove);
                        gamePage.getPromptText().setText("You win");
                        gamePage.repaint();
                        boardArea.removeMouseListener(this);
                    } else {
                        game.getBoard().placeMove(expectedMove);
                        gamePage.getPromptText().setText(tut.getInGameMessage(game.getNumTurn() / 2));
                        game.nextTurn();
                        expectedMove = game.getCurrentPlayer().getMove(game);
                        game.getBoard().placeMove(expectedMove);
                        game.nextTurn();
                        gamePage.repaint();
                    }
                } else {
                    gamePage.getPromptText().setText("You placed on (" + rowNum + "," + colNum + "), place on (" + expectedMove.getRow() + "," + expectedMove.getCol() + ") to win");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void makeNewGame() {
        gamePage = new GamePage(game);

        Timer gameTimer = new Timer();
        TimerUpdater timerUpdater = new TimerUpdater();

        gameTimer.scheduleAtFixedRate(timerUpdater, 0, 1000);
        gamePage.getSaveBut().addActionListener(e -> {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                FileOutputStream fos = new FileOutputStream("./saves/" + sdf.format(System.currentTimeMillis()) + ".ser");
                ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(fos);
                myObjectOutputStream.writeObject(game);
                myObjectOutputStream.close();
                gameTimer.cancel();
                mainFrame.setContentPane(mainFrame.getEntryPage());
                mainFrame.revalidate();
                mainFrame.repaint();
            } catch (Exception ioe) {
                System.out.println(ioe.getMessage());
            }
        });

        //complete the undo action
        gamePage.getUndoBut().addActionListener(e -> {
            Move lastMove = game.getBoard().undoMove();
            if (lastMove != null) {
                game.withDraw();
                gamePage.repaint();
                gamePage.updateInfoBar();
            }
        });
        setDefaultButtons(gameTimer);
        JPanel boardArea = gamePage.getBoardArea();
        boardArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = gridRound(boardArea.getHeight(), e.getY(), game.getBoard().getBoardSize());
                int colNum = gridRound(boardArea.getWidth(), e.getX(), game.getBoard().getBoardSize());
                Move cMove = new PlayerMove(rowNum, colNum, game.getCurrentPlayer());
                if (game.isValidMove(cMove)) {
                    if (game.isEnd(cMove)) {
                        game.getBoard().placeMove(cMove);
                        String winSide = game.getCurrentPlayer().getColor() ? "Black" : "White";
                        gamePage.getPromptText().setText(winSide + " wins");
                        gameTimer.cancel();
                        gamePage.repaint();
                        boardArea.removeMouseListener(this);
                    } else {
                        game.getBoard().placeMove(cMove);
                        gamePage.getPromptText().setText("");
                        gamePage.repaint();
                        game.nextTurn();
                        gamePage.updateInfoBar();
                    }
                } else {
                    gamePage.getPromptText().setText("Invalid move");
                    gamePage.repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void makeLoadPane() {
        loadOptions = new JDialog(mainFrame, "Load Game");
        JPanel loadPanel = new JPanel();
        File[] listSavefile = saveDir.listFiles();
        JList<File> localFiles = new JList<>(listSavefile);
        localFiles.setFixedCellWidth(300);
        localFiles.setVisibleRowCount(10);
        localFiles.setPreferredSize(new Dimension(300, 250));
        JButton ldb = new JButton("load");
        ldb.addActionListener(e -> {
            String fileName = localFiles.getSelectedValue().getName();
            try {
                FileInputStream myFileInputStream = new FileInputStream("./saves/" + fileName);
                ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
                game = (Game) myObjectInputStream.readObject();
                myObjectInputStream.close();
                makeNewGame();
                mainFrame.setContentPane(gamePage);
                mainFrame.revalidate();
                mainFrame.repaint();
                loadOptions.dispose();
            } catch (Exception ioe) {
                System.out.println(ioe.getMessage());
            }
        });
        loadPanel.add(localFiles, BorderLayout.CENTER);
        loadPanel.add(ldb, BorderLayout.LINE_END);
        loadOptions.setContentPane(loadPanel);
        loadOptions.setSize(400, 400);
        loadOptions.setVisible(true);
    }

    private Tutorial[] generateTutorials() {
        Tutorial[] tuts = new Tutorial[1];
        Tutorial tut = new Tutorial("How to win the game?");
        TutorialPlayer tp1 = new TutorialPlayer(true);
        TutorialPlayer tp2 = new TutorialPlayer(false);
        tp1.setMoves(new int[]{7, 8, 9, 10, 11}, new int[]{7, 7, 7, 7, 7});
        tp2.setMoves(new int[]{7, 8, 9, 10}, new int[]{8, 8, 8, 8});
        tut.setPlayers(tp1, tp2);
        tut.setGameMessage(new String[]{"Place move on (8,7)",
                "Place move on (9,7)",
                "Place move on (10,7)",
                "Place move on (11,7)"
        });
        tut.setStartMessage("Place move on (7,7) to get started");
        tuts[0] = tut;
        return tuts;
    }
}



