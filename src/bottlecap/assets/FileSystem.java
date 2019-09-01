package bottlecap.assets;

import bottlecap.states.Handler;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileSystem {

    private Handler handler;
    private File saveFile;
    private File newFolder;
    private String fileName = FileSystemView.getFileSystemView().getDefaultDirectory().getAbsolutePath() + ("\\Bottlecap\\saveFile.txt");
    private String makeDir = FileSystemView.getFileSystemView().getDefaultDirectory().getAbsolutePath() + ("\\Bottlecap");
    public ArrayList<String> charaterSlots = new ArrayList<>();
    public ArrayList<String> readInfo = new ArrayList<>();

    public FileSystem(Handler handler) {
        this.handler = handler;
        createSaveFile();
    }

    public String pullCharacterInfo() {
        return "";
    }

    public void deleteSave() {
        try {
            FileWriter fileWriter = new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write("");
            bufferedWriter.newLine();

            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");
        }
    }

    public void initReadFromFile() {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String temp = contentBuilder.toString();
        String tempArray[] = temp.split("\\r?\\n");
        for (String s : tempArray) {
            if (s.startsWith("CHARSLOT"))
                charaterSlots.add(s);
        }
    }

    public void writeToFile(String message) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(message);
            bufferedWriter.newLine();

            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");
        }
    }


    private void createSaveFile() {
        new File(makeDir).mkdir();
        saveFile = new File(fileName);
        try {
            if (saveFile.createNewFile()) {
                System.out.println("Save File Made at " + fileName);
            } else {
                System.out.println("Save Filed Detected at " + fileName);
                initReadFromFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
