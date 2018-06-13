package application;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.EqualizerBand;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
                System.out.println("halo");
                playFunction();
            }


        } catch (NullPointerException e) {
            System.out.println("Blad w playMusic: " + e);

            /*Stage errorStage = */MyWindow.errorStage("Choose a file which you want to play.");
        }
    }

    /*private*/ public static void playFunction() {
        System.out.println("play function");
            if(ChooseFile.getPlayer().getStatus() != MediaPlayer.Status.PLAYING){
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
      //  System.out.println(ChooseFile.getPlayer().getStatus());
    }

    public static void pauseMusic() {
        System.out.println("pause");
        try {
            ChooseFile.setPauseFlag(true);
            ChooseFile.setStartTimeAfterPause(ChooseFile.getPlayer().getCurrentTime());
            ChooseFile.getPlayer().pause();
        } catch (NullPointerException e) {
            System.out.println("Blad w pauseMusic: " + e);

            MyWindow.errorStage("Cannot pause track. File not chosen.");
        }
    }

    public static void stopMusic() {
        System.out.println("stop");
        try {
            ChooseFile.setPauseFlag(false);
            ChooseFile.getPlayer().setStartTime(new Duration(0));
            ChooseFile.getPlayer().stop();
        }
        catch (NullPointerException e) {
            System.out.println("Blad w stopMusic: " + e);

            MyWindow.errorStage("Cannot stop track. File not chosen.");
        }
    }

    public static void addToPlaylist() {
        System.out.println("playlist selection");

        try {
      //      String path = new String(ChooseFile.getFilePath());
            MyWindow.playlistSelectionStage();
        }
        catch (NullPointerException e) {
            System.out.println("Blad w addToPlaylist: " + e);

            MyWindow.errorStage("Cannot add track. File not chosen.");
        }

    }

    public static void addToPlaylistFunction() {
        System.out.println("add to playlist");


    }

    public static void openPlaylistFunction() {
        System.out.println("open playlist");
    }

    public static void volumeChange(/*Number value*/){
        System.out.println("volume change");
        ChooseFile.getPlayer().setVolume(MyWindow.getVolumeSlider()/100);

        //ChooseFile.getPlayer().setVolume(value.doubleValue()/100);
    }

    public static void balanceChange(){
        System.out.println("balance change");
        ChooseFile.getPlayer().setBalance(MyWindow.getBalanceSlider()/100);
    }

    public static void equalizerBandChange(EqualizerBand eq, Slider freqSlider) {
        System.out.println("gain freq change: " + freqSlider.getValue());
        eq.setGain(freqSlider.getValue());
    }
}
