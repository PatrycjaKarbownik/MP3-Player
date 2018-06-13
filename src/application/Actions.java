package application;

import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.media.EqualizerBand;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
               // System.out.println("halo");
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
            String path = new String(ChooseFile.getFilePath());
            MyWindow.playlistSelectionStage();
        }
        catch (NullPointerException e) {
            System.out.println("Blad w addToPlaylist: " + e);

            MyWindow.errorStage("Cannot add track. File not chosen.");
        }

    }

    public static void addToPlaylistFunction() {
        System.out.println("add to playlist");
        String valueComboBox = MyWindow.getComboBox().getValue().toString();

        if(valueComboBox == "<create a new playlist>"){
            System.out.println("jestem przed?");
            MyWindow.newPlaylistNameStage();
        }
        else {
            openPlaylistFile();
        }
        System.out.println("jestem za?");

    }

    public static void createNewPlaylist() {
        System.out.println("create a new playlist");
        try {
            String nameOfPlaylist = MyWindow.getPlaylistName().getText();

            /*for (String name : PlaylistFolder.getFileNamesList()) {
                if(name == nameOfPlaylist)
                {
                    MyWindow.errorStage("Playlist exists. Change name!");
                    break;
                }
            }*/

            File newPlaylist = new File("src\\data\\playlists\\" + nameOfPlaylist + ".txt");

            if (newPlaylist.exists()) {
                MyWindow.errorStage("Playlist exists. Change name!");
                return;
            }

            newPlaylist.createNewFile();
            MyWindow.getComboBox().setValue(nameOfPlaylist);

            addToOpenFile(true);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addToOpenFile(boolean create)
    {
        System.out.println("add to open file");
        addMP3fileToPlaylist();
        if(create)
            MyWindow.getNewPlaylistNameSt().close();
        MyWindow.getPlaylistSelectionStage().close();
    }

    public static void addMP3fileToPlaylist() {
        try {
            System.out.println("add Mp3 file to playlist");
            String nameOfPlaylist = MyWindow.getComboBox().getValue().toString();

            if(ChooseFile.getFilePath() != null){
                File playlist = new File("src\\data\\playlists\\" + nameOfPlaylist + ".txt");

                FileWriter fw = new FileWriter(playlist.getAbsoluteFile(), true);
                BufferedWriter bw = new BufferedWriter(fw);

                // Write in file
                bw.write(ChooseFile.getFilePath());
                bw.newLine();

                // Close connection
                bw.close();
            }
            else {
                MyWindow.errorStage("Cannot add track. File not chosen.");
                return;
            }

        }catch(Exception e){
            System.out.println(e);
        }

    }

    private static void openPlaylistFile(){
        System.out.println("open a playlist");
        try {
            System.out.println("tu?");
            String nameOfPlaylist = MyWindow.getComboBox().getValue().toString();

            System.out.println("jestem przed");
            File newPlaylist = new File("src\\data\\playlists\\" + nameOfPlaylist + ".txt");
            System.out.println("jestesm za");

            addToOpenFile(false);

        } catch (Exception e) {
            System.out.println(e);
        }
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
