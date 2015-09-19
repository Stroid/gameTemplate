package com.Template.main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends gameObject{

	public Player(int x, int y,int sizeX,int sizeY, ID id) {
		super(x, y, sizeX, sizeY, id);
		
	}

	public void tick(){
		x += velX;
		y += velY;
	}

	public void render(Graphics g) {
		if(id == ID.Player){ g.setColor(Color.white); }
		else if(id == ID.Player2){ g.setColor(Color.blue); }
		else if(id == ID.Enemy){ g.setColor(Color.red); }
		
		g.fillRect(x, y, sizeX, sizeY);
	}

}
