package com.Template.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 480, WIDTH = HEIGHT*4/3; 
	private static final String title = "Template";
	
	
	Thread thread;
	private static boolean running = false;
	
	private Handeler handeler; 
	 
	public Game(){
		handeler = new Handeler();
		this.addKeyListener(new KeyInput(handeler));
		
		new Window(WIDTH,HEIGHT,title,this);
		
		handeler.addObject(new Player(WIDTH/2, HEIGHT/2-32/2, 32, 32, ID.Player));
		handeler.addObject(new Ball(WIDTH/2, HEIGHT/2-32/2, 32, 32, ID.Ball));
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0; 
		long timer = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running){
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("Fps:" + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	private void tick() {
		handeler.tick();
		
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		handeler.render(g);
		
		
		
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Game();

	}

}
