package com.homework.gomoku.controller;

import com.homework.gomoku.game.*;
import com.homework.gomoku.gui.EntryFrame;
import com.homework.gomoku.gui.GamePage;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Controller {

    EntryFrame mainFrame;
    JPanel p;
    Game game;
    GamePage gp;
    JDialog loadOptions;
    File saveDir;

    public Controller(){
        mainFrame = new EntryFrame("new game");
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
        JPanel boardArea = gp.getBoardArea();
        boardArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = gridRound(boardArea.getHeight(), e.getY(), game.getBoard().getBoardSize());
                int colNum = gridRound(boardArea.getWidth(), e.getX(), game.getBoard().getBoardSize());
                Move cMove = new PlayerMove(rowNum, colNum, game.getCurrentPlayer());
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
