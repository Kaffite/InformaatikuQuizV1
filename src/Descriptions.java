import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

// Class that helps with student type descriptions
public class Descriptions {
    HashMap<Character, String> descriptions; // Symbol, type name and description
    HashMap<Character, String> types = new HashMap<>(); // Symbol, Type name

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
                String descAndType [] = parts[1].substring(1).split("-");
                String type = descAndType[0];
                type = type.substring(0, type.length()-1); // removes extra space
                String description = descAndType[1].substring(1);
                descriptions.put(symbol, description);
                types.put(symbol, type);
                System.out.println(types.toString());
            }
        }
        return descriptions;
    }
}