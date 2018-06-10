package application;

import main.com.mpatric.mp3agic.ID3v2;
import main.com.mpatric.mp3agic.InvalidDataException;
import main.com.mpatric.mp3agic.Mp3File;
import main.com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;

public class MusicFileInfo {

    private static String titleInfo;
    private static String artistInfo;
    private static String albumInfo;
    private static String yearInfo;
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

    public static String getYearInfo() {
        return yearInfo;
    }

    public static String getLengthInfo() {
        return lengthInfo;
    }

    public static void setInfo() {
        Mp3File mp3File;

        try {
            mp3File = new Mp3File(ChooseFile.getFilePath());
            if (mp3File.hasId3v2Tag()) {
                ID3v2 tag = mp3File.getId3v2Tag();
                String title = tag.getTitle();
                String album = tag.getAlbum();
                String artist = tag.getArtist();
                String year = tag.getYear();
                if (title != null)
                    titleInfo = title;
                else
                    titleInfo = "N/A";
                if (album != null)
                    albumInfo = album;
                else
                    albumInfo = "N/A";
                if (artist != null)
                    artistInfo = artist;
                else
                    artistInfo = "N/A";
                if (year != null)
                    yearInfo = year;
                else
                    yearInfo = "N/A";

                int length = (int) mp3File.getLengthInSeconds();

                if (length / 60 >= 10)
                    lengthInfo = String.valueOf(length / 60) + ":";
                else
                    lengthInfo = "0" + String.valueOf(length / 60) + ":";
                if (length % 60 >= 10)
                    lengthInfo += String.valueOf(length % 60);
                else
                    lengthInfo += "0" + String.valueOf(length % 60);

            }


        } catch (UnsupportedTagException | InvalidDataException | IOException e) {
            e.printStackTrace();
            System.out.println("Blad w funkcji setInfo" + e.toString());
        }
    }

}
