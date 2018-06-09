package application;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.MalformedURLException;

public class Actions {

    public static void openFile() throws MalformedURLException {
        System.out.println("open");

        ChooseFile.chooseFile();
        MyWindow.setNowPlayingLabel(ChooseFile.getFileName());

    }

    public static void playMusic() {
        System.out.println("play");

        try {
            if (ChooseFile.getFileInUse() == true) {
                //playFunction();
            } else {
                Media pick = new Media(ChooseFile.getAudioFile());
                ChooseFile.player = new MediaPlayer(pick);
                playFunction();
            }


        } catch (Exception e) {
            System.out.println("Blad w playMusic: " + e);
        }
    }

    private static void playFunction() {
        System.out.println("play function");
        if(ChooseFile.getPauseFlag() == true) {
            ChooseFile.player.setStartTime(ChooseFile.startTimeAfterPause);
            ChooseFile.setPauseFlag(false);
        }

        Thread playThread = new Thread() {
            public void run() {
                ChooseFile.player.play();
                ChooseFile.setFileInUse(true);
            }
        };
        playThread.start();
    }

    public static void pauseMusic() {
        System.out.println("pause");
    }

    public static void stopMusic() {
        System.out.println("stop");
    }

    public static void addToPlaylistFunction() {
        System.out.println("add to playlist");
    }

    public static void openPlaylistFunction() {
        System.out.println("open playlist");
    }
}
