import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Coordinates {

    private File file;

    public Coordinates(File file) {
        this.file = file;
    }

    public Map<String, Float> readCSVFile(String city) {

        Map<String, Float> map = new HashMap<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(";");
                if (tokens[0].equals(city)) {
                    map.put("Latitude", Float.valueOf(tokens[1]));
                    map.put("Longitude", Float.valueOf(tokens[2]));
                    break;
                }

            }
            scanner.close();
            return map;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
