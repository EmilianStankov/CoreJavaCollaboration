package com.hackbulgaria.corejava.asciiplayer;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ASCIIPicturePlayer viewer = new ASCIIPicturePlayer(new File(args[0]));
        viewer.play();
    }
}
