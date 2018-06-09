package application;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

public class Actions {

    public static void openFile() throws MalformedURLException {
        System.out.println("open");

        ChooseFile.chooseFile();
        MyWindow.setNowPlayingLabel(ChooseFile.getFileName());

        Timer timer = new Timer();
        timer.schedule(new SongProgress(), 0, 1000);

        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                if(ChooseFile.getPlayer().getStatus() == MediaPlayer.Status.PLAYING){
                   // MyWindow.setProgressBar(SongProgress.getSongProgress());
                    Platform.runLater(() -> MyWindow.setCurrentTime(SongProgress.getCurrentTime()));
                }
            }
        }, 0, 1000);

    }

    public static void playMusic() {
        System.out.println("play");

        try {
            if (ChooseFile.getFileInUse() == true) {
                playFunction();
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
            if (ChooseFile.getPauseFlag() == true) {
                ChooseFile.player.setStartTime(ChooseFile.getStartTimeAfterPause());
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
        ChooseFile.setPauseFlag(true);
        ChooseFile.setStartTimeAfterPause(ChooseFile.getPlayer().getCurrentTime());
     //   ChooseFile.setFileInUseAndPause(true);
        ChooseFile.getPlayer().pause();
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
