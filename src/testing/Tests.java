package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Base64;


import application.Actions;
import application.ChooseFile;
import application.Main;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
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
        //STACK OVERFLOW//
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
    public void testID3TagRead() { // test if a known file with a working ID3 tag returns an expected title
        File file = new File("src\\data\\02 Weak Fantasy.mp3");
        String title = new String();
        try {
            int size = (int)file.length();
            InputStream in = new FileInputStream(file);
            in.skip(size - 128);
            byte[] last128 = new byte[128];
            System.out.println(in.read(last128));
            String id3 = last128.toString();
           // String id3 = new String(last128);
            //String id3 = new String(Base64.getEncoder().encode(last128));

            /* for (byte b: last128) System.out.println(b);
            // put data into this char array
            char[] cbuf = new char[last128.length];
            for (int i = 0; i < last128.length; i++) {
                cbuf[i] = (char) last128[i];
            }

            String id3 = new String(cbuf);*/

            System.out.println(id3);

            title = id3.substring(3, 10);
            in.close();
        } catch(Exception e) {
            System.out.println("Error in test 'ID3TagRead' - " + e.toString());
        }
        System.out.print(title);
        Assert.assertEquals(title, "Weak Fantasy"); // assuming there is such file in the project folder!!!
    }

}