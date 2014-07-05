package com.hackbulgaria.corejava.asciiplayer;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        ASCIIPicturePlayer viewer = new ASCIIPicturePlayer(new File(args[0]));
//        viewer.play();
        ASCIIPlayer gif = PlayerFactory.newAsciiPlayer(new File(args[0]));
        gif.play();
        
        //create utility method
        //crate BufferedImage and Graphics, draw picture with text
    }
}
