import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> products = new ArrayList<>();

        System.out.println("=== Product Writer ===");
        boolean more = true;

        while (more) {
            String id = SafeInput.getNonZeroLenString(in, "Enter Product ID");
            String name = SafeInput.getNonZeroLenString(in, "Enter Product Name");
            String description = SafeInput.getNonZeroLenString(in, "Enter Product Description");
            double cost = SafeInput.getDouble(in, "Enter Product Cost");

            String record = id + ", " + name + ", " + description + ", " + cost;
            products.add(record);

            more = SafeInput.getYNConfirm(in, "Add another product?");
        }

        String fileName = SafeInput.getNonZeroLenString(in, "Enter file name to save (e.g., ProductTestData.txt)");
        Path file = Paths.get(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            for (String p : products) {
                writer.write(p);
                writer.newLine();
            }
            System.out.println("File saved: " + file.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
