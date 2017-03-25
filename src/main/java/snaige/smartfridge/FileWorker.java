package snaige.smartfridge;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileWorker {

    public static List<String> readAllLines(String file) {
        try {
            return Files.readAllLines(FileSystems.getDefault().getPath(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean write(String filepath, List<String> lines) {
        Path path = FileSystems.getDefault().getPath(filepath);
        try {
            Files.write(path, lines);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static boolean delete(String path) {
        File file = new File(path);
        return file.delete();
    }

}