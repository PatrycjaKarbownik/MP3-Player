package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
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
    public void testHasID3TagTrue() throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3File = new Mp3File("src\\data\\02 Weak Fantasy.mp3");
        assertEquals(true, mp3File.hasId3v1Tag());
    }

    @Test
    public void testHasID3TagFalse() throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3File = new Mp3File("src\\data\\Airplanes.mp3");
        assertEquals(false, mp3File.hasId3v1Tag());
    }

    @Test
    public void testID3TagRead() {
        // File file = new File("src\\data\\Thousand Foot Krutch  Light Up The Sky (Official Audio).mp3");
        File file = new File("src\\data\\02 Weak Fantasy.mp3");
        String title = new String();
        try {
            int size = (int) file.length();
            InputStream in = new FileInputStream(file);
            in.skip(size - 128);
            byte[] last128 = new byte[128];
            in.read(last128);
            String id3 = new String(last128);

            System.out.println(id3);

            title = id3.substring(3, 15);
            in.close();
        } catch (Exception e) {
            System.out.println("Blad w tescie 'ID3TagRead' - " + e.toString());
        }
        System.out.print(title);
        Assert.assertEquals("Weak Fantasy", title);
    }

    @Test
    public void testAddToPlaylist() throws IOException {
        //write
        String absolutePath = "D:\\Pati\\AA SERWER\\Documents\\Ważne rzeczy\\STUDIA\\" +
                "4 semestr\\PROZ\\MyMP3Player\\src\\data\\playlists\\do_testow.txt";
        File playlist = new File(absolutePath);

        String filePath = "D:\\Pati\\AA SERWER\\Documents\\Ważne rzeczy\\STUDIA\\" +
                "4 semestr\\PROZ\\MyMP3Player\\src\\data\\Jimi Hendrix - Little Wing.txt";

        FileWriter fw = new FileWriter(playlist.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);

        // Write in file
        bw.write(filePath);
        bw.newLine();

        // Close connection
        bw.close();


        //read
        String line = null;
        try (BufferedReader br = new BufferedReader(new FileReader(playlist))) {
            line = br.readLine();
            String next_line = br.readLine();

            while(next_line != null) {
                line = br.readLine();
                next_line = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

     Assert.assertEquals(filePath, line);
    }

}