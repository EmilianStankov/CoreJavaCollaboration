package com.hackbulgaria.corejava.asciiplayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jline.ConsoleReader;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;

public class ASCIIVideoPlayer implements ASCIIPlayer {
    private List<ASCIIPicturePlayer> frames = new ArrayList<ASCIIPicturePlayer>();

    public ASCIIVideoPlayer(File file) throws IOException, JCodecException {
        // for testing purposes only get 5 frames
        // change to
        // while(FrameGrab.getFrame(file, frameNumber) != null)
        // for complete video
        int frameNumber = 40;
        while (frameNumber < 45) {
            frames.add(new ASCIIPicturePlayer(FrameGrab.getFrame(file, frameNumber)));
            frameNumber++;
        }
    }

    @Override
    public void play() throws IOException, InterruptedException {
        ConsoleReader reader = new ConsoleReader();
        for (ASCIIPicturePlayer frame : frames) {
            frame.play();
            Thread.sleep(10);
            reader.clearScreen();
        }
    }

}
