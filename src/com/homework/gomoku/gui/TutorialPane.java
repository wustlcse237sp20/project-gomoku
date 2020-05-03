package com.homework.gomoku.gui;

import com.homework.gomoku.game.Tutorial;

import javax.swing.*;
import java.awt.*;

public class TutorialPane extends JDialog {
    Tutorial[] tutorials;
    public TutorialPane(Tutorial[] tuts){
        super();
        this.tutorials = tuts;
        init();
    }

    private void init() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setMinimumSize(new Dimension(300, 500));
        this.add(new JLabel("Choose Your Tutorial"));
    }
}
