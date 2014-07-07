package com.hackbulgaria.corejava.asciiplayer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import jline.ConsoleReader;

public class ASCIIGifPlayer implements ASCIIPlayer {
    
    int scale;
    final int DELAY = 10;
    ASCIIPicturePlayer frame;
    GifDecoder decoder;
    ArrayList<ASCIIPlayer> frames;
    
    public ASCIIGifPlayer (File file) throws IOException {        
        BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
        decoder = new GifDecoder();
        decoder.read(stream);
    }
    
    @Override
    public void play() throws IOException, InterruptedException {
        
        frames = new ArrayList<>();
        int num = decoder.getFrameCount();

        for (int i = 0; i<num; i++) {
            frames.add(new ASCIIPicturePlayer(decoder.getFrame(i)));
            }
        
        showInWindow();
    }
    
    public void showInConsole () throws InterruptedException, IOException {        
        ConsoleReader reader = new ConsoleReader();
        while(true) {
            for (int i = 0; i<frames.size(); i++) {
                System.out.println(frames.get(i).toString());
                Thread.sleep(DELAY);
                reader.clearScreen();
            }
        }
    }
    
    public void showInWindow () throws InterruptedException {     
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
        
        while(true) {
        for (int i = 0; i<frames.size(); i++) {
            text.setText(frames.get(i).toString());
            Thread.sleep(DELAY);
            }
        }
    }
    
    public void export() throws IOException {
        List<BufferedImage> images = new ArrayList<>();

        Font f = new Font(Font.MONOSPACED, 10, 4);

        for (int i = 0; i < frames.size(); i++) {
            BufferedImage image = new BufferedImage(decoder.getFrame(0).getWidth(), decoder.getFrame(0).getHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D graph = image.createGraphics();
            graph.setFont(f);
            graph.setColor(Color.WHITE);
            graph.fillRect(0, 0, image.getWidth(), image.getHeight());
            graph.setColor(Color.BLACK);
            String[] rows = frames.get(i).toString().split("[\n]");
            int y = 0;
            for (int j = 0; j < rows.length; j++) {
                graph.drawString(rows[j], 0, y);
                y += 6;
            }
            images.add(image);
        }

        AnimatedGifEncoder e = new AnimatedGifEncoder();
        e.start(new FileOutputStream(new File("C:\\Users\\RUSHI\\Desktop\\Child2.gif")));
        e.setDelay(50);

        for (int k = 0; k < 10; k++) {
            for (int i = 0; i < frames.size(); i++) {
                e.addFrame(images.get(i));
            }
        }
        e.finish();
    }  
    
}
