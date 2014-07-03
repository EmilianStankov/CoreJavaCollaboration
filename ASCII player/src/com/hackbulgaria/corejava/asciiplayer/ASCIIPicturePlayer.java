package com.hackbulgaria.corejava.asciiplayer;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ASCIIPicturePlayer implements ASCIIPlayer {
    private final static int columns = 100;
    private BufferedImage image;
    private int scale;

    public ASCIIPicturePlayer(File file) throws IOException {
        image = ImageIO.read(file);
        if (image.getWidth() > columns) {
            scale = image.getWidth() / columns + 1;
        } else {
            scale = 1;
        }
    }
    
    public ASCIIPicturePlayer(BufferedImage image) throws IOException {
        this.image = image;
        if (image.getWidth() > columns) {
            scale = image.getWidth() / columns + 1;
        } else {
            scale = 1;
        }
    }
    
    private int getBlockIntensity(int x, int y) {
        int averageIntensity = 0;
        for (int i = x; i < scale + x; i++) {
            for (int j = y; j < scale + y; j++) {
                averageIntensity += getIntensity(i, j);
            }
        }
        return averageIntensity / (scale * scale);
    }

    private int getIntensity(int x, int y) {
        Color color = new Color(this.image.getRGB(x, y), false);
        int intensity = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        return intensity;
    }

    private String pixelToChar(int intensity) {
        if (intensity >= 240) {
            return "   ";
        } else if (intensity >= 200 && intensity < 240) {
            return "...";
        } else if (intensity >= 160 && intensity < 200) {
            return "~~~";
        } else if (intensity >= 120 && intensity < 160) {
            return "+++";
        } else if (intensity >= 80 && intensity < 120) {
            return "===";
        } else if (intensity >= 40 && intensity < 80) {
            return "###";
        } else {
            return "@@@";
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int y = 0; y < this.image.getHeight() - scale; y += scale) {
            for (int x = 0; x < this.image.getWidth() - scale; x += scale) {
                result += pixelToChar(getBlockIntensity(x, y));
            }
            result += "\n";
        }
        return result;
    }
    
    @Override
    public void play() {
        System.out.println(toString());
    }
}
