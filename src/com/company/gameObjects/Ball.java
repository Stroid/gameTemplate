package com.company.gameObjects;

import com.company.GameObjectHandler;

import java.awt.*;

public class Ball extends GameObject{

    public Ball(double x, double y, GameObjectHandler handler) {
        super(x, y, 20, ID.Ball, handler);
    }

    @Override
    public void update() {

    }

    @Override
    public void display(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillOval((int) this.position.x, (int) this.position.y, this.size, this.size);
    }

    @Override
    public void detectEdge() {
    }
}
