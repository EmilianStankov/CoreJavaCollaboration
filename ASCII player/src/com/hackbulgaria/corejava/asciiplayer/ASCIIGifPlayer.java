package com.hackbulgaria.corejava.asciiplayer;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;

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
        
        ArrayList<ASCIIPlayer> frames = new ArrayList<>();
        int num = decoder.getFrameCount();
        ConsoleReader reader = new ConsoleReader();
        JFrame fr = new JFrame();
        JTextArea text = new JTextArea();
        
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLocationByPlatform(true);
        fr.pack();
        fr.setVisible(true);
        fr.setSize(1000, 600);
        fr.setLocationRelativeTo(null);
        text.setSize(fr.getWidth(), fr.getHeight());
        fr.add(text);
        
        
        Font f = new Font(Font.MONOSPACED, 10, 4);
        text.setFont(f);
         
        for (int i = 0; i<num; i++) {
            frames.add(new ASCIIPicturePlayer(decoder.getFrame(i)));
            }
        
        while(true) {
        for (int i = 0; i<num; i++) {
            //long t = decoder.getDelay(i);
            //frame.play();
            text.setText(frames.get(i).toString());
            //Thread.sleep(t);
            reader.clearScreen();
            }
        
        }
        
//        while(true) {
//        for (int i = 0; i<num; i++) {
//            frame = new ASCIIPicturePlayer(decoder.getFrame(i));
//            long t = decoder.getDelay(i);
//            text.setText(frame.toString());
//            //frame.play();        
//            //Thread.sleep(1);
//            //reader.clearScreen();
//            }
//        }
    }
}
