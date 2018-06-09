package application;

import javafx.scene.media.MediaPlayer;

import java.util.TimerTask;

public class SongProgress extends TimerTask {

    private static double songProgress;
    private static String currentTime;

    public static double getSongProgress() {
        return songProgress;
    }

    public static String getCurrentTime() {
        int temp = (int)ChooseFile.getPlayer().getCurrentTime().toSeconds();
        if(temp / 60 >= 10)
            currentTime = String.valueOf(temp/60) + ":";
        else
            currentTime = "0" + String.valueOf(temp/60) + ":";
        if(temp % 60 >= 10)
            currentTime += String.valueOf(temp%60);
        else
            currentTime += "0" + String.valueOf(temp%60);

        return currentTime;
    }

    @Override
    public void run() {
        if(ChooseFile.getPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
            songProgress = (ChooseFile.getPlayer().getCurrentTime().toSeconds()) / (ChooseFile.getPlayer().getStopTime().toSeconds());
        }
    }
}
