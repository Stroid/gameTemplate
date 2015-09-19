package com.Template.main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends gameObject{

	public Ball(int x, int y,int sizeX, int sizeY, ID id) {
		super(x, y,sizeX, sizeY, id);
		
		velX = 5;
		velY = 5;
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0 || x >= Game.WIDTH - sizeX) velX *= -1;
		if(y <= 0 || y >= Game.HEIGHT - sizeY) velY *= -1;
		
	}

	
	public void render(Graphics g) {
	
		g.setColor(Color.red);
		
		g.fillRect(x, y, sizeY, sizeX);
		
	}

}
