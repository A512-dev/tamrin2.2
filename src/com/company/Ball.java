package com.company;

import java.awt.*;

public class Ball {
    int ballposX, ballposY, ballLastX, ballLastY;
    double ballDirX,ballDirY;
    Color color;
    boolean isMovable;
    int speed;
    public Ball (int currentX, int currentY,Color color) {
        isMovable = true;
        this.ballposX = currentX;
        this.ballposY = currentY;
        this.ballDirY = 0;
        this.ballDirX = 0;
        this.color = color;
        speed  = 5;
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void drawBalls(Graphics2D g) {
        g.setColor(this.color);
        g.fillOval(ballposX, ballposY, 20, 20);
    }
}
