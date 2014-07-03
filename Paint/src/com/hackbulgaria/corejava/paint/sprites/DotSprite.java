package com.hackbulgaria.corejava.paint.sprites;

import java.awt.Color;
import java.awt.Graphics;

public class DotSprite extends Sprite {

    public DotSprite(int x, int y, int size, Color color) {
        super(x, y, size, color);
    }

    @Override
    public void draw(Graphics graphics) {
        if (color != null) {
            graphics.setColor(color); //set Color to use for FUTURE draw and fill calls
        }
        graphics.fillOval(x, y, size, size);
    }

}
