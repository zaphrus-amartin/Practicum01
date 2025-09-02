import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PersonReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser(".");
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            Path file = chooser.getSelectedFile().toPath();

            System.out.printf("%-8s %-12s %-12s %-6s %-6s\n", "ID#", "FirstName", "LastName", "Title", "YOB");
            System.out.println("===========================================================");

            try (BufferedReader reader = Files.newBufferedReader(file)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",");
                    if (fields.length == 5) {
                        System.out.printf("%-8s %-12s %-12s %-6s %-6s\n",
                                fields[0].trim(), fields[1].trim(), fields[2].trim(), fields[3].trim(), fields[4].trim());
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("No file selected.");
        }
    }
}
