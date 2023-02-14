package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FileReaderTest {

    private static final String NOT_A_FILE_PATH = "src/test/resources/file.file";

    @Test
    void testExceptionWhenInputNotAFilePath() {
        assertThrows(RuntimeException.class, () -> FileReader.read(NOT_A_FILE_PATH));
    }
}
