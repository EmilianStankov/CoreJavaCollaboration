package com.hackbulgaria.corejava.asciiplayer;

import java.io.File;
import java.io.IOException;

import org.jcodec.api.JCodecException;

public class PlayerFactory {

    public static ASCIIPlayer newAsciiPlayer(File file) throws IOException, JCodecException {

        String fileName = file.getName();
        String extention = fileName.substring(fileName.lastIndexOf(".") + 1);
        ASCIIPlayer result;

        switch (extention) {
            case "jpg":
                result = new ASCIIPicturePlayer(file);
                break;
            case "gif":
                result = new ASCIIGifPlayer(file);
                break;
            case "mp4":
                result = new ASCIIVideoPlayer(file);
                break;
            default:
                result = new ASCIIPicturePlayer(file);
        }
        return result;
    }
}
