package com.hackbulgaria.corejava.paint.controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

import com.hackbulgaria.corejava.paint.sprites.DotSprite;
import com.hackbulgaria.corejava.paint.sprites.LineSprite;
import com.hackbulgaria.corejava.paint.sprites.SpraySprite;
import com.hackbulgaria.corejava.paint.sprites.Sprite;
import com.hackbulgaria.corejava.paint.tools.Tool;
import com.hackbulgaria.corejava.paint.view.PaintWindow;

public class PaintController {
    public class MouseListener extends MouseAdapter {

        private int x1;
        private int y1;

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                Sprite sprite = null;
                if (currentTool == Tool.SPRAY) {
                    super.mouseClicked(e);
                    sprite = new SpraySprite(e.getX(), e.getY(), 1, 100, Color.MAGENTA);
                } else if (currentTool == Tool.DOT) {
                    sprite = new DotSprite(e.getX(), e.getY(), 100, Color.MAGENTA);
                }
                if (sprite != null) {
                    window.addSprite(sprite);
                    window.repaint();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1 && currentTool == Tool.LINE) {
                x1 = e.getX();
                y1 = e.getY();
            } else if (e.getButton() == MouseEvent.BUTTON2) {
                window.removeSprites();
                window.repaint();
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                window.undoSprites();
                window.repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1 && currentTool == Tool.LINE) {
                Sprite sprite = new LineSprite(x1, y1, e.getX(), e.getY(), 10, Color.CYAN);
                window.addSprite(sprite);
                window.repaint();
            }
        }

    }

    public class MouseWheel implements MouseWheelListener {

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int notches = e.getWheelRotation();
            if (notches > 0) {
                if (currentTool == Tool.DOT) {
                    currentTool = Tool.SPRAY;
                } else if (currentTool == Tool.SPRAY) {
                    currentTool = Tool.LINE;
                } else {
                    currentTool = Tool.DOT;
                }
            } else {
                if (currentTool == Tool.SPRAY) {
                    currentTool = Tool.DOT;
                } else if (currentTool == Tool.LINE) {
                    currentTool = Tool.SPRAY;
                } else {
                    currentTool = Tool.LINE;
                }
            }
        }
    }

    private final PaintWindow window;

    private Tool currentTool;

    public PaintController() {
        this.window = new PaintWindow();
        window.setTitle("Paint!");
        currentTool = Tool.DOT;
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setFocusable(true);
        window.setVisible(true);
        window.setSize(800, 600);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.addMouseListener(new MouseListener());
        window.addMouseWheelListener(new MouseWheel());
    }

}
