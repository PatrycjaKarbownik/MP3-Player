package application;

import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.net.MalformedURLException;

public class ChooseFile {
    static File file;
    private static String audioFile = null;
    private static Boolean fileInUse = false;

    private static MediaPlayer player;

    private static Duration startTimeAfterPause;
    public static Duration d = new Duration(1000); // 1 sekunda

   // private static double tempRate = 1.0; // do zapamietania szybkosci przed PAUSE
    private static Boolean pauseFlag = false; // ustawiony, aby pokazac, ze w skutek nacisniecia przycisku PAUSE, zostal zmodyfikowany startTime
  //  private static Boolean wasPausedFlag = false; // ustawiony, aby pokazac czy kiedykolwiek byl wcisniety przycisk PAUSE
    private static String fileName;
    private static String filePath;
    private static String title;
    private static String artist;
    private static String album;
    private static String trackLength;

    public ChooseFile() {
        try{
            chooseFile();
        } catch (Exception e){
            System.out.println("Error in ChooseFile class constructor: " + e);
        }
    }


    public static void chooseFile() throws MalformedURLException {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select your mp3 file", "*.mp3");
            chooser.getExtensionFilters().add(filter);
        file = chooser.showOpenDialog(Main.getPrimaryStage());
        filePath = file.getPath();

        // jesli muzyka jest odtwarzana, a uzytkownik wybierze nowy utwor, obecny nalezy przerwac
        if (fileInUse == true) {
            player.stop();
            fileInUse = false;
            //fileNotInUseButIsHere = false;
        }
        
        if (file != null && file.exists() && (file.toString().substring(file.toString().length()-4).equals(".mp3")
                || file.toString().substring(file.toString().length()-4).equals(".MP3")) ){
            fileName = file.toString();
            fileName = fileName.substring(fileName.lastIndexOf('\\')+1);
            audioFile = file.toURI().toURL().toString();

            Actions.playMusic();
        } else { // incorrect file chosen
            fileName = "Wybrano zly plik! Sprobuj jeszcze raz";
            audioFile = null;
        }
    }

    /*public static double getTempRate() {
        return tempRate;
    }*/

    /*public static void setTempRate(double value) {
        tempRate = value;
    }*/

    public static File getFile() {
        return file;
    }

    public static String getAudioFile() {
        return audioFile;
    }

    public static void setAudioFile(String aFile){
        audioFile = aFile;
    }

    public static Boolean getFileInUse() {
        return fileInUse;
    }

    public static void setFileInUse(Boolean bool){
        fileInUse = bool;
    }


    public static Boolean getPauseFlag() {
        return pauseFlag;
    }

    public static void setPauseFlag(Boolean bool) {
        pauseFlag = bool;
    }
/*
    public static Boolean getWasPausedFlag() {
        return wasPausedFlag;
    }

    public static void setWasPausedFlag(Boolean bool) {
        wasPausedFlag = bool;
    }*/

    public static MediaPlayer getPlayer() {
        return player;
    }

    public static void setPlayer(MediaPlayer mp) {
        player = mp;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String name) {
        fileName = name;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String path) {
        filePath = path;
    }

    public static String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public static String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public static Duration getStartTimeAfterPause() {
        return startTimeAfterPause;
    }

    public static void setStartTimeAfterPause(Duration time) {
        startTimeAfterPause = time;
    }

}
