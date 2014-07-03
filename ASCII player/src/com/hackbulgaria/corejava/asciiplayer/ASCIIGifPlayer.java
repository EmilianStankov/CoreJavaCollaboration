package com.hackbulgaria.corejava.asciiplayer;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jline.ConsoleReader;


public class ASCIIGifPlayer implements ASCIIPlayer {
    
    int scale;
    ASCIIPicturePlayer frame;
    GifDecoder decoder;
    
    public ASCIIGifPlayer (File file) throws IOException {        
        BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
        decoder = new GifDecoder();
        decoder.read(stream);
    }
    
    @Override
    public void play() throws IOException, InterruptedException {
        int num = decoder.getFrameCount();
        ConsoleReader reader = new ConsoleReader();
        
        for (int i = 0; i<num; i++) {
            frame = new ASCIIPicturePlayer(decoder.getFrame(i));
            long t = decoder.getDelay(i);
            frame.play();
            Thread.sleep(t);
            reader.clearScreen();
        }
    }
}
