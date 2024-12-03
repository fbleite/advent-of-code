import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

public class FileUtil {

    public static String getFileString (String filename) throws URISyntaxException, IOException {
        URL resource = Day3.class.getClassLoader().getResource(filename);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + filename);
        }

        File file = new File(resource.toURI());
        return Files.readString(file.toPath());
    }
}
