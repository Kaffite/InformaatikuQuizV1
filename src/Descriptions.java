import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Descriptions {
    HashMap<Character, String> descriptions;
    HashMap<Character, String> types = new HashMap<>();

    public Descriptions(String filename) throws IOException {
        descriptions = readDescriptions(filename);
    }

    public HashMap<Character, String> getDescriptions() {
        return descriptions;
    }

    public HashMap<Character, String> getTypes() {
        return types;
    }

    // Reads Descriptions from a .txt file
    private HashMap<Character, String> readDescriptions(String filename) throws IOException {
        descriptions = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                Character symbol = (Character) parts[0].charAt(0);
                // substring removes the extra space
                String description = parts[1].substring(1);
                String type = description.split("-")[0];
                descriptions.put(symbol, description);
                types.put(symbol, type);
                System.out.println(types.toString());
            }
        }
        return descriptions;
    }
}