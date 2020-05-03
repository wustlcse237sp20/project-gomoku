package com.homework.gomoku.controller;

import com.homework.gomoku.game.*;
import com.homework.gomoku.gui.EntryFrame;
import com.homework.gomoku.gui.GamePage;
import com.homework.gomoku.gui.TutorialPane;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class Controller {

    EntryFrame mainFrame;
    JPanel p;
    Game game;
    GamePage gp;
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
            mainFrame.setContentPane(gp);
            mainFrame.revalidate();
            mainFrame.repaint();
        });
        mainFrame.getEntryPage().getLoadBut().addActionListener(e -> {
            makeLoadPane();
        });

        mainFrame.getEntryPage().getTutorialBut().addActionListener(e -> {
            Tutorial[] tuts = generateTutorials();
            TutorialPane tp = new TutorialPane(tuts);
            for (Tutorial t : tuts) {
                JButton tutBut = new JButton(t.getTutName());
                tp.add(tutBut);
                tutBut.addActionListener(e1 -> {
                    game = new Game(new TwoPlayerBoard(15), t.getPlyr1(), t.getPlyr2(), new TwoPlayerRule());
                    makeTutGame(t);
                    mainFrame.setContentPane(gp);
                    mainFrame.revalidate();
                    mainFrame.repaint();
                    tp.dispose();
                });
            }
            tp.setVisible(true);
        });
    }

    private int gridRound(int length, int loc, int numInt) {
        int rem = length % numInt;
        int gap = length / numInt;
        return loc > rem * (gap + 1) ? (int) Math.round((double) loc / (gap + 1)) :
                (int) Math.round((double) (loc - rem * (gap + 1)) / gap) + rem;
    }

    private void makeTutGame(Tutorial tut) {
        gp = new GamePage(game);
        gp.getSaveBut().addActionListener(e -> {
            gp.getPromptText().setText("Your can't save a tutorial game");
            gp.repaint();
        });
        gp.getExitBut().addActionListener(e -> {
            mainFrame.dispose();
        });
        gp.getMainBut().addActionListener(e -> {
            mainFrame.setContentPane(mainFrame.getEntryPage());
            mainFrame.revalidate();
            mainFrame.repaint();
        });
        gp.getPromptText().setText(tut.getStartMessage());
        JPanel boardArea = gp.getBoardArea();
        boardArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = gridRound(boardArea.getHeight(), e.getY(), game.getBoard().getBoardSize());
                int colNum = gridRound(boardArea.getWidth(), e.getX(), game.getBoard().getBoardSize());
                Move expectedMove = game.getCurrentPlayer().getMove(game);
                if (rowNum == expectedMove.getRow() && colNum == expectedMove.getCol()) {
                    if (game.isEnd(expectedMove)) {
                        game.getBoard().placeMove(expectedMove);
                        gp.getPromptText().setText("You win");
                        gp.repaint();
                        boardArea.removeMouseListener(this);
                    } else {
                        game.getBoard().placeMove(expectedMove);
                        gp.getPromptText().setText(tut.getInGameMessage(game.getNumTurn() / 2));
                        game.nextTurn();
                        expectedMove = game.getCurrentPlayer().getMove(game);
                        game.getBoard().placeMove(expectedMove);
                        game.nextTurn();
                        gp.repaint();
                    }
                } else {
                    gp.getPromptText().setText("You placed on (" + rowNum + "," + colNum + "), place on (" + expectedMove.getRow() + "," + expectedMove.getCol() + ") to win");
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
        gp = new GamePage(game);
        gp.getSaveBut().addActionListener(e -> {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
                FileOutputStream fos = new FileOutputStream("./saves/" + sdf.format(System.currentTimeMillis()) + ".ser");
                ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(fos);
                myObjectOutputStream.writeObject(game);
                myObjectOutputStream.close();
                mainFrame.setContentPane(mainFrame.getEntryPage());
                mainFrame.revalidate();
                mainFrame.repaint();
            } catch (Exception ioe) {
                System.out.println(ioe.getMessage());
            }
        });
        gp.getExitBut().addActionListener(e -> {
            mainFrame.dispose();
        });
        gp.getMainBut().addActionListener(e -> {
            mainFrame.setContentPane(mainFrame.getEntryPage());
            mainFrame.revalidate();
            mainFrame.repaint();
        });
        gp.getPromptText().setText("Enjoy your game");
        JPanel boardArea = gp.getBoardArea();
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
                        gp.getPromptText().setText(winSide + " wins");
                        gp.repaint();
                        boardArea.removeMouseListener(this);
                    } else {
                        game.getBoard().placeMove(cMove);
                        gp.getPromptText().setText("");
                        gp.repaint();
                        game.nextTurn();
                    }
                } else {
                    gp.getPromptText().setText("Invalid move");
                    gp.repaint();
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
                mainFrame.setContentPane(gp);
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
