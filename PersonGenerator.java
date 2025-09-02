import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> people = new ArrayList<>();

        System.out.println("=== Person Generator ===");
        boolean more = true;

        while (more) {
            String id = SafeInput.getNonZeroLenString(in, "Enter ID");
            String firstName = SafeInput.getNonZeroLenString(in, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter Last Name");
            String title = SafeInput.getNonZeroLenString(in, "Enter Title (Mr., Ms., Dr., etc.)");
            int yob = SafeInput.getInt(in, "Enter Year of Birth");

            String record = id + ", " + firstName + ", " + lastName + ", " + title + ", " + yob;
            people.add(record);

            more = SafeInput.getYNConfirm(in, "Add another person?");
        }

        String fileName = SafeInput.getNonZeroLenString(in, "Enter file name to save (e.g., PersonTestData.txt)");
        Path file = Paths.get(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            for (String p : people) {
                writer.write(p);
                writer.newLine();
            }
            System.out.println("File saved: " + file.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
