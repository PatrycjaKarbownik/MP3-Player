package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import application.Actions;
import application.ChooseFile;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.com.mpatric.mp3agic.InvalidDataException;
import main.com.mpatric.mp3agic.Mp3File;
import main.com.mpatric.mp3agic.UnsupportedTagException;
import org.junit.Assert;
import org.junit.Test;

public class Tests {

   /* @Test
    public void testOpeningFile() {
        File file = new File("\\data\\03 Life Is a Beat.mp3");
        String fileName = new String();

            if (file != null)
                fileName = file.toString();

        assertEquals(fileName, "\\data\\03 Life Is a Beat.mp3");
    }*/

    /*@Test
    public void testPlay() throws MalformedURLException {
        final JFXPanel fxPanel = new JFXPanel();
        File file = new File("src\\data\\03 Life Is a Beat.mp3");
        ChooseFile.setFilePath(file.getPath());
        String fileName = file.toString();
        ChooseFile.setFileName(fileName.substring(fileName.lastIndexOf('\\')+1));
        System.out.println(ChooseFile.getFileName());

        String audioFile = file.toURI().toURL().toString();
        ChooseFile.setAudioFile(audioFile);

        System.out.println(ChooseFile.getAudioFile());

        Media pick = new Media(ChooseFile.getAudioFile());
        ChooseFile.setPlayer(new MediaPlayer(pick));// = new MediaPlayer(pick);
        System.out.println("halo");

        Actions.playFunction();
       // Actions.pauseMusic();
        Actions.playFunction();

        System.out.println(ChooseFile.getPlayer().getStatus());
        //assertEquals(MediaPlayer.Status.PLAYING, ChooseFile.getPlayer().getStatus());
    }*/

    @Test
    public void testHasID3Tag() throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3File = new Mp3File("src\\data\\Thousand Foot Krutch  Light Up The Sky (Official Audio).mp3");
        assertEquals(true, mp3File.hasId3v2Tag());
    }

    @Test
    public void testID3TagRead() {
        File file = new File("src\\data\\Thousand Foot Krutch  Light Up The Sky (Official Audio).mp3");
        String title = new String();
        try {
            int size = (int)file.length();
            InputStream in = new FileInputStream(file);
            in.skip(size - 128);
            byte[] last128 = new byte[128];
            in.read(last128);
            String id3 = new String(last128);

            System.out.println(id3);

            title = id3.substring(3, 19);
            in.close();
        } catch(Exception e) {
            System.out.println("Blad w tescie 'ID3TagRead' - " + e.toString());
        }
        System.out.print(title);
        Assert.assertEquals("Light Up The Sky", title);
    }

}