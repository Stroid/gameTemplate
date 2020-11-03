package com.company.GameMecanics;

import com.company.Game;
import com.company.GameObjectHandler;
import com.company.input.*;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class GameEngine extends Canvas implements Runnable {
    GameWindow gameWindow;
    private final double UPDATES_PER_SECOND = 60.0;
    private final String title;
    private boolean gameActive = false;
    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;
    private int frameRate;
    private int updateRate;
    Game game;
    Keyboard keyboard = new Keyboard();
    Mouse mouse = new Mouse();
    GameObjectHandler handler = new GameObjectHandler();
    Thread thread;
    private Color backgroundColor = Color.black;

    public GameEngine(String title, int window_width, int window_height) {
        this.title = title;
        WINDOW_WIDTH = window_width;
        WINDOW_HEIGHT = window_height;
        this.game = new Game();
        createGameWindow();
        addListeners();
    }

    private void createGameWindow() {
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setFocusable(true);
        gameWindow = new GameWindow(this);
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
        gameWindow.setTitle(String.format("%s | FPS: %d | UPS: %d", this.title, this.frameRate, this.updateRate));
        game.update();
    }

    private void draw() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        Graphics graphics;
        try {
            graphics = bufferStrategy.getDrawGraphics();
            enableAntialiasing((Graphics2D) graphics);
            drawBackground(backgroundColor, graphics);
            game.draw(graphics);
            graphics.dispose();
            bufferStrategy.show();
        } catch (IllegalStateException e) {
            this.gameActive = false;
        }
    }

    private void enableAntialiasing(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    private void drawBackground(Color color, Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(0, 0, getWidth(), getHeight());
    }

    public void run() {
        game.setup();
        this.createBufferStrategy(3);
        long lastTime = System.nanoTime();
        long lastUpdateTime = System.nanoTime();
        double nanoSecondsPerFrame = 1000000000 / UPDATES_PER_SECOND;
        double delta = 0;
        while (gameActive) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSecondsPerFrame;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
                updateRate = (int) (1000000000 / (System.nanoTime() - lastUpdateTime));
                lastUpdateTime = System.nanoTime();
            }
            if (gameActive) {
                draw();
            }
            frameRate = (int) (1000000000 / (System.nanoTime() - now));

        }
        this.stop();
    }
}
