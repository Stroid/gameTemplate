package com.company;

import com.company.GameMecanics.GameObjectHandler;
import com.company.input.*;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class GameEngine extends Canvas implements Runnable {
    GameWindow gameWindow;
    private final double UPDATES_PER_SECOND = 60.0;
    private final String title;
    public boolean gameActive = false;
    private int frameRate;
    Keyboard keyboard = new Keyboard();
    Mouse mouse = new Mouse();
    GameObjectHandler handler = new GameObjectHandler();
    Thread thread;

    public GameEngine(String title) {
        this.title = title;
        gameWindow = new GameWindow(this);
        addListeners();
    }

    private void addListeners() {
        this.addKeyListener(keyboard);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        gameActive = true;
    }

    public synchronized void stop() {
        gameWindow.dispatchEvent(new WindowEvent(gameWindow, WindowEvent.WINDOW_CLOSING));
        try {
            thread.join();
            gameActive = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update() {
        gameWindow.setTitle(String.format("%s frames per second: %d", this.title, frameRate));
    }

    private void draw() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        Graphics graphics;
        try {
            graphics = bufferStrategy.getDrawGraphics();
            enableAntialiasing((Graphics2D) graphics);
            drawBackground(Color.black, graphics);
            handler.display(graphics);
            graphics.dispose();
            bufferStrategy.show();
        } catch (IllegalStateException e) {
            this.gameActive = false;
        }
    }

    private void enableAntialiasing(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void drawBackground(Color color, Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(0, 0, getWidth(), getHeight());
    }

    public void run() {
        this.createBufferStrategy(3);
        long lastTime = System.nanoTime();
        double nanoSecondsPerFrame = 1000000000 / UPDATES_PER_SECOND;
        double delta = 0;
        while (gameActive) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSecondsPerFrame;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            if (gameActive) {
                draw();
            }
            frameRate = (int) (1000000000 / (System.nanoTime() - now));
        }
        this.stop();
    }
}
