package com.hackbulgaria.corejava.asciiplayer;

import java.io.File;
import java.io.IOException;

import org.jcodec.api.JCodecException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, JCodecException {
        ASCIIPlayer player = PlayerFactory.newAsciiPlayer(new File(args[0]));
        player.play();
    }
}
