package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.Test;

public class Tests {

    @Test
    public void testOpeningFile() {
        File file = new File("D:\\Pati\\03 Life Is a Beat.mp3");
        String fileName = new String();

            if (file != null)
                fileName = file.toString();

        assertEquals(fileName, "D:\\Pati\\03 Life Is a Beat.mp3");
    }



}