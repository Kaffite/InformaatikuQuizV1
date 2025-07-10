import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Descriptions {
    HashMap<Character, String> descriptions;

    public Descriptions(String filename) throws IOException {
        descriptions = readDescriptions(filename);
    }

    public HashMap<Character, String> getDescriptions() {
        return descriptions;
    }

    private HashMap<Character, String> readDescriptions(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String[] line = br.readLine().split(";");
            Character symbol = (Character) line[0].charAt(0);
            String description = line[1].substring(1); // substring removes the extra space
            descriptions.put(symbol, description);
        }
        return descriptions;
    }
}
