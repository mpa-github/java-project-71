package hexlet.code.reader;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReaderTest {

    private static final String STR_PATH_1 = "src/test/resources/json1.json";
    private static final String STR_PATH_2 = "src/test/resources/json2.json";
    private static final String NOT_A_FILE_PATH = "src/test/resources/file.file";
    private static final String NEW_LINE = "\r\n"; // (?) In WSL tests failed with -> System.lineSeparator()
    //private static final String NEW_LINE = System.getProperty("line.separator");
    //private static final String NEW_LINE = System.lineSeparator();
    private static final String EMPTY_STRING = "";
    private static final Reader READER = new Reader();

    @Test
    void readFile_shouldReturnCorrectString_whenReadJsonFile() throws IOException {
        String actual1 = READER.readFile(STR_PATH_1);
        String expected1 = "{" + NEW_LINE +
                           "  \"host\": \"hexlet.io\"," + NEW_LINE +
                           "  \"timeout\": 50," + NEW_LINE +
                           "  \"proxy\": \"123.234.53.22\"," + NEW_LINE +
                           "  \"follow\": false" + NEW_LINE +
                           "}";
        assertEquals(expected1, actual1);

        String actual2 = READER.readFile(STR_PATH_2);
        String expected2 = "{" + NEW_LINE +
                           "  \"timeout\": 20," + NEW_LINE +
                           "  \"verbose\": true," + NEW_LINE +
                           "  \"host\": \"hexlet.io\"" + NEW_LINE +
                           "}";
        assertEquals(expected2, actual2);
    }

    @Test
    void readFile_shouldReturnEmptyString_whenInputIsEmptyStringOrNull() throws IOException {
        String actual1 = READER.readFile(EMPTY_STRING);
        assertEquals(EMPTY_STRING, actual1);

        String actual2 = READER.readFile(null);
        assertEquals(EMPTY_STRING, actual2);
    }

    @Test
    void readFile_shouldThrowFileNotFoundException_whenInputNotAFilePath() {
        assertThrows(FileNotFoundException.class, () -> READER.readFile(NOT_A_FILE_PATH));
    }
}