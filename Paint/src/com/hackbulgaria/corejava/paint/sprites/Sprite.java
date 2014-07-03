package com.hackbulgaria.corejava.paint.sprites;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Sprite {
    
    int x;
    int y; //position
    int size; //in pixels
    Color color; //By default this should be Color.RED;
    
    public Sprite(int x, int y, int size) {
        super();
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = Color.RED;
    }
    
    public Sprite(int x, int y, int size, Color color) {
        super();
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }    
    
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    
    public abstract void draw(Graphics graphics);
}
