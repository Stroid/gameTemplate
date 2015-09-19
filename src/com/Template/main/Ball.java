package com.Template.main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends gameObject{

	public Ball(int x, int y,int sizeX, int sizeY, ID id) {
		super(x, y,sizeX, sizeY, id);
		
		velX = 1;
		velY = 0;
		
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		
		if(x < 0 || x > Game.WIDTH - 32) velX *= -1;
		if(y < 0 || y > Game.HEIGHT) velY *= -1;
		
	}

	
	public void render(Graphics g) {
	
		g.setColor(Color.red);
		
		g.fillRect(x, y, sizeX, sizeY);
	}

}
