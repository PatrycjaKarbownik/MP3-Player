package application;

import main.com.mpatric.mp3agic.Mp3File;

public class MusicFileInfo {

    private static String titleInfo;
    private static String artistInfo;
    private static String albumInfo;
    private static String trackNumberInfo;
    private static String lengthInfo;

    public static String getTitleInfo() {
        return titleInfo;
    }

    public static String getArtistInfo() {
        return artistInfo;
    }

    public static String getAlbumInfo() {
        return albumInfo;
    }

    public static String getTrackNumberInfo() {
        return trackNumberInfo;
    }

    public static String getLengthInfo() {
        return lengthInfo;
    }

    public static void setInfo() {
        Mp3File mp3File;

        try {
            mp3File = new Mp3File(ChooseFile.getFilePath());



        } catch (Exception e) {

        }
    }

}
