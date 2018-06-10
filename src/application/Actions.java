package application;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

public class Actions {

    public static void openFile() throws MalformedURLException {
        System.out.println("open");

        ChooseFile.chooseFile();
        MyWindow.setNowPlayingLabel(ChooseFile.getFileName());
        MusicFileInfo.setInfo();
        MyWindow.setTitleLabel(MusicFileInfo.getTitleInfo());
        MyWindow.setArtistLabel(MusicFileInfo.getArtistInfo());
        MyWindow.setAlbumLabel(MusicFileInfo.getAlbumInfo());
        MyWindow.setLengthLabel(MusicFileInfo.getLengthInfo());
        MyWindow.setYearLabel(MusicFileInfo.getYearInfo());


        Timer timer = new Timer();
        timer.schedule(new SongProgress(), 0, 1000);

        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                if(ChooseFile.getPlayer().getStatus() == MediaPlayer.Status.PLAYING){
                    Platform.runLater(() -> MyWindow.setCurrentTime(SongProgress.getCurrentTime()));
                }
            }
        }, 0, 1000);

        Timer timer3 = new Timer();
        timer3.schedule(new TimerTask() {
            @Override
            public void run() {
                if(ChooseFile.getPlayer().getStatus() == MediaPlayer.Status.PLAYING){
                    Platform.runLater(() -> MyWindow.setProgressBar(SongProgress.getSongProgress()));
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
                ChooseFile.setPlayer(new MediaPlayer(pick));// = new MediaPlayer(pick);
                playFunction();
            }


        } catch (Exception e) {
            System.out.println("Blad w playMusic: " + e);
        }
    }

    private static void playFunction() {
        System.out.println("play function");
            if (ChooseFile.getPauseFlag() == true) {
                ChooseFile.getPlayer().setStartTime(ChooseFile.getStartTimeAfterPause());
                ChooseFile.setPauseFlag(false);
            }

            Thread playThread = new Thread(() -> {
                /*public void run() */{
                    ChooseFile.getPlayer().play();
                    ChooseFile.setFileInUse(true);
                }
            });
            playThread.start();
    }

    public static void pauseMusic() {
        System.out.println("pause");
        ChooseFile.setPauseFlag(true);
        ChooseFile.setStartTimeAfterPause(ChooseFile.getPlayer().getCurrentTime());
        ChooseFile.getPlayer().pause();
    }

    public static void stopMusic() {
        System.out.println("stop");
        ChooseFile.setPauseFlag(false);
        ChooseFile.getPlayer().setStartTime(new Duration(0));
        ChooseFile.getPlayer().stop();
    }

    public static void addToPlaylistFunction() {
        System.out.println("add to playlist");
    }

    public static void openPlaylistFunction() {
        System.out.println("open playlist");
    }

    public static void volumeChange(/*Number value*/){
        System.out.println("volume Change");
        ChooseFile.getPlayer().setVolume(MyWindow.getVolumeSlider()/100);

        //ChooseFile.getPlayer().setVolume(value.doubleValue()/100);
    }

    public static void balanceChange(){
        System.out.println("balance Change");
        ChooseFile.getPlayer().setBalance(MyWindow.getBalanceSlider()/100);
    }
}
