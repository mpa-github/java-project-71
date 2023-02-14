package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {

    private static final String STR_JSON_PATH_1 = "src/test/resources/json/json1.json";
    private static final String STR_JSON_PATH_2 = "src/test/resources/json/json2.json";
    private static final String STR_YAML_PATH_1 = "src/test/resources/yaml/yaml1.yml";
    private static final String STR_YAML_PATH_2 = "src/test/resources/yaml/yaml2.yml";
    private static final String EXPECTED_STYLISH_STR_PATH = "src/test/resources/expected/stylish.txt";
    private static final String EXPECTED_PLAIN_STR_PATH = "src/test/resources/expected/plain.txt";
    private static final String EXPECTED_JSON_STR_PATH = "src/test/resources/expected/json.txt";
    private static final String UNSUPPORTED_FORMAT = "unsupported";
    private static final String STYLISH_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";
    private static final String JSON_FORMAT = "json";
    private static String expectedStylishString;
    private static String expectedPlainString;
    private static String expectedJsonString;

    @BeforeAll
    static void init() throws IOException {
        Path stylishFilePath = Paths.get(EXPECTED_STYLISH_STR_PATH).toAbsolutePath().normalize();
        Path plainFilePath = Paths.get(EXPECTED_PLAIN_STR_PATH).toAbsolutePath().normalize();
        Path jsonFilePath = Paths.get(EXPECTED_JSON_STR_PATH).toAbsolutePath().normalize();
        expectedStylishString = Files.readString(stylishFilePath);
        expectedPlainString = Files.readString(plainFilePath);
        expectedJsonString = Files.readString(jsonFilePath);
    }

    @Test
    void testDiffsWhenInputJsonFiles() throws IOException {
        String actualStylishString = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2, STYLISH_FORMAT);
        String actualPlainString = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2, PLAIN_FORMAT);
        String actualJsonString = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2, JSON_FORMAT);
        assertEquals(expectedStylishString, actualStylishString);
        assertEquals(expectedPlainString, actualPlainString);
        assertEquals(expectedJsonString, actualJsonString);
    }

    @Test
    void testDiffsWhenInputYamlFiles() throws IOException {
        String actualStylishString = Differ.generate(STR_YAML_PATH_1, STR_YAML_PATH_2, STYLISH_FORMAT);
        String actualPlainString = Differ.generate(STR_YAML_PATH_1, STR_YAML_PATH_2, PLAIN_FORMAT);
        String actualJsonString = Differ.generate(STR_YAML_PATH_1, STR_YAML_PATH_2, JSON_FORMAT);
        assertEquals(expectedStylishString, actualStylishString);
        assertEquals(expectedPlainString, actualPlainString);
        assertEquals(expectedJsonString, actualJsonString);
    }

    @Test
    void testExceptionWhenFileExtensionsAreDifferent() {
        assertThrows(UnsupportedOperationException.class, () ->
                Differ.generate(STR_JSON_PATH_1, STR_YAML_PATH_1, STYLISH_FORMAT));
    }

    @Test
    void testExceptionWhenFormatDoesNotExist() {
        assertThrows(RuntimeException.class, () ->
                Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_1, UNSUPPORTED_FORMAT));
    }
}
