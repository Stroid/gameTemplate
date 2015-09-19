package com.Template.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	private Handeler handeler;
	
	public KeyInput(Handeler handeler){
		this.handeler = handeler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		
		
		for(int i = 0; i<handeler.object.size(); i++){
			gameObject tempObject	= handeler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				//check keyPress for Player 1
				
				if(key == KeyEvent.VK_W) tempObject.setVelY(-5);
				if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
				if(key == KeyEvent.VK_S) tempObject.setVelY(5);
				if(key == KeyEvent.VK_D) tempObject.setVelX(5);
			}
			else if(tempObject.getId() == ID.Player2){
				//check keyPress for Player 2
				
				if(key == KeyEvent.VK_UP) tempObject.setVelY(-5);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(-5);
				if(key == KeyEvent.VK_DOWN) tempObject.setVelY(5);
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(5);
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i<handeler.object.size(); i++){
			gameObject tempObject	= handeler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				//check keyReleased for Player 1
				
				if(key == KeyEvent.VK_W) tempObject.setVelY(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
				if(key == KeyEvent.VK_S) tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
			}
			else if(tempObject.getId() == ID.Player2){
				//check keyReleased for Player 2
				
				if(key == KeyEvent.VK_UP) tempObject.setVelY(0);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
				if(key == KeyEvent.VK_DOWN) tempObject.setVelY(0);
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
			}	
		}
	}
	
}
