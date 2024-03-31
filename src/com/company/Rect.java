package com.company;

import java.awt.*;

public class Rect {
    int valueOf, originalValue;
    double posX,posY;
    Color color;
    public Rect (int x, int y, int score) {
        this.posX = x;
        this.posY = y;
        originalValue = score;
        valueOf = score;
    }

    public double getX() {
        return posX;
    }

    public void setX(int x) {
        this.posX = (double) x;
    }

    public double getY() {
        return posY;
    }

    public void setY(int y) {
        this.posY = (double) y;
    }

    public int getValueOf() {
        return valueOf;
    }

    public void setValueOf(int valueOf) {
        this.valueOf = valueOf;
    }

    public int getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(int originalValue) {
        this.originalValue = originalValue;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    void printDetails(){
        System.out.print("value:"+valueOf+"...posX:"+posX+"...PosY:"+posY+".....");
    }
}
