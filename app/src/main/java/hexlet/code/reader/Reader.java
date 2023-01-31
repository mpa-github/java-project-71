package hexlet.code.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Reader {

    private static final String EMPTY_STRING = "";

    public String readFile(String pathString) throws IOException {
        if (pathString == null || pathString.equals(EMPTY_STRING)) {
            return EMPTY_STRING;
        }
        // TODO: can throw Unchecked exception - InvalidPathException
        Path path = Paths.get(pathString).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new FileNotFoundException("File '%s' does not exist!".formatted(path));
        }
        // TODO: can throw Checked exception - IOException OR Unchecked - OutOfMemoryError
        return Files.readString(path, StandardCharsets.UTF_8);
    }
}
