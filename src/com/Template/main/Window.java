package com.Template.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{
	
	private static final long serialVersionUID = 1L;
	
	public Window(int width, int height, String title, Game game){
		JFrame Frame = new JFrame(title);
		
		Frame.setPreferredSize(new Dimension(width,height));
		Frame.setMaximumSize(new Dimension(width,height));
		Frame.setMinimumSize(new Dimension(width,height));
		
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setResizable(false);
		Frame.add(game);
		Frame.setLocationRelativeTo(null);
		Frame.setFocusable(true);
		Frame.setVisible(true);
		game.start();
	}
}
