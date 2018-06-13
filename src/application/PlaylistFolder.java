package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class PlaylistFolder {

    private String path = "src\\data\\playlists";
    private static List<String> fileNamesList;
    private File directory;

    PlaylistFolder() {
        try {
            directory = downloadDirectory(path);
        } catch (NotDirectoryException e) {
            System.out.println("path does not exist or is not a directory");
        }

        fileNamesList = downloadFileNamesList(directory);
    }

    private File downloadDirectory(String path) throws NotDirectoryException {
        File directory = new File(path);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new NotDirectoryException();
        }

        return directory;
    }

    private List<String> downloadFileNamesList(File directory) {
        List<String> fileNamesList = new ArrayList<>();
        String[] list = directory.list();
        int size = list.length;

        for (int i = 0; i < size; ++i) {
            String name = list[i].substring(0, list[i].length() - 4);
            fileNamesList.add(name);

            System.out.println(list[i]);
            System.out.println(name);
        }

        return fileNamesList;
    }

    public File getDirectory() {
        return directory;
    }

    public static List<String> getFileNamesList() {
        return fileNamesList;
    }
}