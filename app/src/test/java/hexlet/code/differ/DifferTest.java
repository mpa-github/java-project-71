package hexlet.code.differ;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DifferTest {

    private static final String STR_PATH_1 = "src/test/resources/json1.json";
    private static final String STR_PATH_2 = "src/test/resources/json2.json";
    private static final String NEW_LINE = System.lineSeparator();

    @Test
    void generate_shouldReturnCorrectDiffString_whenInputCorrectStringPaths() throws IOException {
        String actual = Differ.generate(STR_PATH_1, STR_PATH_2);
        String expected = "{" + NEW_LINE +
                          "  - follow: false" + NEW_LINE +
                          "    host: hexlet.io" + NEW_LINE +
                          "  - proxy: 123.234.53.22" + NEW_LINE +
                          "  - timeout: 50" + NEW_LINE +
                          "  + timeout: 20" + NEW_LINE +
                          "  + verbose: true" + NEW_LINE +
                          "}";
        assertEquals(expected, actual);
    }
}