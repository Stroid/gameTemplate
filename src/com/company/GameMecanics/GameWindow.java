package com.company.GameMecanics;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    public GameWindow(GameEngine game) {
        this.add(game);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}