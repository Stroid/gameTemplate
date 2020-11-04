package com.company;

import com.company.GameMecanics.GameEngine;
import com.company.gameObjects.Ball;

import java.awt.*;
import java.sql.Time;

public class Game{
    GameObjectHandler handler = new GameObjectHandler();
    Ball ball;

    public void setup() {
        int x = 200;
        int y = 200;

        ball = new Ball(x,y, handler);
    }

    public void update() {
    }

    public void draw(Graphics graphics) {
        ball.display(graphics);
    }

    public static void main(String[] args) {
        GameEngine game = new GameEngine("Title", 800, 600);
        game.setBackgroundColor(new Color(0, 2, 125, 255));
        game.start();
    }
}
