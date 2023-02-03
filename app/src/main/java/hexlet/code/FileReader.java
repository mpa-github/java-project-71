package hexlet.code;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {

    public static String readFile(String pathString) {
        Path path = Paths.get(pathString).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new RuntimeException("The file '%s' does not exist!".formatted(path));
        }
        try {
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new RuntimeException("The file '%s' cannot be read!".formatted(path), ex);
        }
    }
}
