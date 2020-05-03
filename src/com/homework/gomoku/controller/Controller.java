package com.homework.gomoku.controller;

import com.homework.gomoku.game.*;
import com.homework.gomoku.gui.EntryFrame;
import com.homework.gomoku.gui.GamePage;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Timer;

import javax.swing.*;

public class Controller {

    EntryFrame mainFrame;
    JPanel p;
    Game game;
    GamePage gp;
    JDialog loadOptions;
    File saveDir;
    Move saverMove;
    public int movesCounter = 0;
    public int totalMovesPossible = 15*15;
    public int binaryFlagforCurrenPlayer = 1;

    public Controller(){
        mainFrame = new EntryFrame("New Game");
    }

    public void launch(){

    	
        saveDir = new File("./saves");
        saveDir.mkdir();
        System.out.println(saveDir.getAbsolutePath());
        mainFrame.setVisible(true);
        mainFrame.getEntryPage().getExitBut().addActionListener(e->mainFrame.dispose());
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
    }

    private int gridRound(int length, int loc, int numInt){
        int rem = length % numInt;
        int gap = length / numInt;
        return loc > rem * (gap+1)? (int)Math.round((double)loc/(gap+1)):
                (int)Math.round((double)(loc-rem*(gap+1))/gap)+rem;
    }

    private void makeNewGame(){

    	
        gp = new GamePage(game);
        gp.getSaveBut().addActionListener(e -> {
            try{
                FileOutputStream fos = new FileOutputStream("./saves/game.ser");
                ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(fos);
                myObjectOutputStream.writeObject(game);
                myObjectOutputStream.close();
                mainFrame.setContentPane(mainFrame.getEntryPage());
                mainFrame.revalidate();
                mainFrame.repaint();
            }
            catch(Exception ioe){
                System.out.println(ioe.getMessage());
            }
        });
        //complete the undo action
        gp.getUndoBut().addActionListener(e->{
        	try {
        		game.getBoard().undoMove(saverMove);
        		gp.repaint();
                game.nextTurn();
                movesCounter = movesCounter -1;
                totalMovesPossible = totalMovesPossible +1;
                String castedMovesCounter = Integer.toString(movesCounter);
                gp.movesUpdate.setText(castedMovesCounter);
                String castedMovesLeftCounter = Integer.toString(totalMovesPossible);
                gp.totalMovesUpdate.setText(castedMovesLeftCounter);
                gp.totalMovesUpdate.setForeground(Color.red);
                gp.movesUpdate.setForeground(Color.red);
                
                
                if(binaryFlagforCurrenPlayer==1) {
                    binaryFlagforCurrenPlayer = 0;
                    gp.currentPlayerStatus.setText("WHITE");
                    gp.currentPlayerStatus.setForeground(Color.WHITE);
                }
                else if(binaryFlagforCurrenPlayer==0) {
                    binaryFlagforCurrenPlayer = 1;
                    gp.currentPlayerStatus.setText("BLACK");
                    gp.currentPlayerStatus.setForeground(Color.BLACK);
                }
                
                
        	}
        	catch(Exception ioe) {
        		System.out.println(ioe.getMessage());
        	}
        });
        
        //complete the color change function
        gp.getColorChangeBut().addActionListener(e->{
        	try {
        		double switchSelector = 1*Math.random();
        		if(switchSelector<0.33) {
            		gp.leftWing.setBackground(Color.GRAY);
            		gp.rightWing.setBackground(Color.GRAY);
        		}
        		if(switchSelector>=0.33) {
        			if(switchSelector<0.66) {
                		gp.leftWing.setBackground(Color.GREEN);
                		gp.rightWing.setBackground(Color.GREEN);
        			}
        			else {
                		gp.leftWing.setBackground(Color.YELLOW);
                		gp.rightWing.setBackground(Color.YELLOW);
        			}
        		}

        	}
        	catch(Exception ioe) {
        		System.out.println(ioe.getMessage());
        	}
        });
        
        
    	Timer timer = new Timer();
    	TimeCount tc = new TimeCount();
    	timer.schedule(tc, 0, 1000);
    	Runnable helloRunnable = new Runnable() {
    	    public void run() {
    			if (tc.secondsD<10) {
    				gp.tickerUpdate.setText(tc.minD + ": 0" + tc.secondsD);
    				}
    			else {
    				gp.tickerUpdate.setText(tc.minD + ": " + tc.secondsD );
    				}
    	    }
    	};
    	
    	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    	executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);
        
        JPanel boardArea = gp.getBoardArea();

        boardArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = gridRound(boardArea.getHeight(), e.getY(), game.getBoard().getBoardSize());
                int colNum = gridRound(boardArea.getWidth(), e.getX(), game.getBoard().getBoardSize());
                Move cMove = new PlayerMove(rowNum, colNum, game.getCurrentPlayer());
                System.out.println(cMove);
                saverMove = cMove;
                //Move tMove = new TMove(tp.setMove())
                if(game.isValidMove(cMove)){
                    if(game.isEnd(cMove)){
                        game.getBoard().placeMove(cMove);
                        gp.repaint();
                        JDialog d = new JDialog();
                        d.add(new JLabel("GameEnd"));
                        d.setSize(200, 200);
                        d.setVisible(true);
                        boardArea.removeMouseListener(this);
                    }
                    else{
                        game.getBoard().placeMove(cMove);
                        gp.repaint();
                        game.nextTurn();
                        movesCounter = movesCounter +1;
                        String castedMovesCounter = Integer.toString(movesCounter);
                        gp.movesUpdate.setText(castedMovesCounter);

                        totalMovesPossible = totalMovesPossible -1;
                        String castedMovesLeftCounter = Integer.toString(totalMovesPossible);
                        gp.totalMovesUpdate.setText(castedMovesLeftCounter);
                        gp.totalMovesUpdate.setForeground(Color.red);
                        gp.movesUpdate.setForeground(Color.red);
                        
                        if(binaryFlagforCurrenPlayer==1) {
                            binaryFlagforCurrenPlayer = 0;
                            gp.currentPlayerStatus.setText("WHITE");
                            gp.currentPlayerStatus.setForeground(Color.WHITE);
                        }
                        else if(binaryFlagforCurrenPlayer==0) {
                            binaryFlagforCurrenPlayer = 1;
                            gp.currentPlayerStatus.setText("BLACK");
                            gp.currentPlayerStatus.setForeground(Color.BLACK);
                        }

                    }
                }
                else{
                    JDialog d = new JDialog();
                    d.add(new JLabel("Invalid Move"));
                    d.setSize(200, 200);
                    d.setVisible(true);
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
        String[] fileNames = null;
        if(listSavefile != null){
            fileNames = new String[listSavefile.length];
            for (int i = 0; i<listSavefile.length;i++) {
                fileNames[i] = listSavefile[i].getName();
            }
        }
        JList<String> localFiles = new JList<>(fileNames);
        localFiles.setFixedCellWidth(300);
        localFiles.setVisibleRowCount(10);
        localFiles.setPreferredSize(new Dimension(300, 250));
        JButton ldb = new JButton("load");
        ldb.addActionListener(e -> {
            String fileName = localFiles.getSelectedValue();
            try
            {
                FileInputStream myFileInputStream = new FileInputStream("./saves/"+fileName);
                ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
                game = (Game) myObjectInputStream.readObject();
                myObjectInputStream.close();
                makeNewGame();
                mainFrame.setContentPane(gp);
                mainFrame.revalidate();
                mainFrame.repaint();
                loadOptions.dispose();
            }
            catch (Exception ioe)
            {
                System.out.println(ioe.getMessage());
            }
        });
        loadPanel.add(localFiles, BorderLayout.CENTER);
        loadPanel.add(ldb, BorderLayout.LINE_END);
        loadOptions.setContentPane(loadPanel);
        loadOptions.setSize(400,400);
        loadOptions.setVisible(true);
    }
}



