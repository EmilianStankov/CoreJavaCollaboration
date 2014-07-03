package com.hackbulgaria.corejava.paint.sprites;

import java.awt.Color;
import java.awt.Graphics;

public class LineSprite extends Sprite {

    private int x2;
    private int y2;

    public LineSprite(int x, int y, int x2, int y2, int size, Color color) {
        super(x, y, size, color);
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.drawLine(x, y, x2, y2);
    }

}
