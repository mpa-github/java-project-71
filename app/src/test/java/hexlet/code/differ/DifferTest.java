package hexlet.code.differ;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private static final String STR_JSON_PATH_1 = "src/test/resources/json1.json";
    private static final String STR_JSON_PATH_2 = "src/test/resources/json2.json";
    private static final String STR_JSON_PATH_3 = "src/test/resources/json3.json";
    private static final String STR_JSON_PATH_4 = "src/test/resources/json4.json";
    private static final String STR_YAML_PATH_1 = "src/test/resources/yaml1.yml";
    private static final String STR_YAML_PATH_2 = "src/test/resources/yaml2.yml";
    private static final String NEW_LINE = System.lineSeparator();

    @Test
    void generateShouldReturnCorrectDiffStringWhenInputCorrectJsonStringPaths() throws IOException {
        String actual1 = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2);
        String expected1 = "{" + NEW_LINE
                         + "  - follow: false" + NEW_LINE
                         + "    host: hexlet.io" + NEW_LINE
                         + "  - proxy: 123.234.53.22" + NEW_LINE
                         + "  - timeout: 50" + NEW_LINE
                         + "  + timeout: 20" + NEW_LINE
                         + "  + verbose: true" + NEW_LINE
                         + "}";
        assertEquals(expected1, actual1);

        String actual2 = Differ.generate(STR_JSON_PATH_3, STR_JSON_PATH_4);
        String expected2 = "{" + NEW_LINE
                         + "    chars1: [a, b, c]" + NEW_LINE
                         + "  - chars2: [d, e, f]" + NEW_LINE
                         + "  + chars2: false" + NEW_LINE
                         + "  - checked: false" + NEW_LINE
                         + "  + checked: true" + NEW_LINE
                         + "  - default: null" + NEW_LINE
                         + "  + default: [value1, value2]" + NEW_LINE
                         + "  - id: 45" + NEW_LINE
                         + "  + id: null" + NEW_LINE
                         + "  - key1: value1" + NEW_LINE
                         + "  + key2: value2" + NEW_LINE
                         + "    numbers1: [1, 2, 3, 4]" + NEW_LINE
                         + "  - numbers2: [2, 3, 4, 5]" + NEW_LINE
                         + "  + numbers2: [22, 33, 44, 55]" + NEW_LINE
                         + "  - numbers3: [3, 4, 5]" + NEW_LINE
                         + "  + numbers4: [4, 5, 6]" + NEW_LINE
                         + "  + obj1: {nestedKey=value, isNested=true}" + NEW_LINE
                         + "  - setting1: Some value" + NEW_LINE
                         + "  + setting1: Another value" + NEW_LINE
                         + "  - setting2: 200" + NEW_LINE
                         + "  + setting2: 300" + NEW_LINE
                         + "  - setting3: true" + NEW_LINE
                         + "  + setting3: none" + NEW_LINE
                         + "}";
        assertEquals(expected2, actual2);
    }

    @Test
    void generateShouldReturnCorrectDiffStringWhenInputCorrectYamlStringPaths() throws IOException {
        String actual1 = Differ.generate(STR_YAML_PATH_1, STR_YAML_PATH_2);
        String expected1 = "{" + NEW_LINE
                         + "    chars1: [a, b, c]" + NEW_LINE
                         + "  - chars2: [d, e, f]" + NEW_LINE
                         + "  + chars2: false" + NEW_LINE
                         + "  - checked: false" + NEW_LINE
                         + "  + checked: true" + NEW_LINE
                         + "  - default: null" + NEW_LINE
                         + "  + default: [value1, value2]" + NEW_LINE
                         + "  - id: 45" + NEW_LINE
                         + "  + id: null" + NEW_LINE
                         + "  - key1: value1" + NEW_LINE
                         + "  + key2: value2" + NEW_LINE
                         + "    numbers1: [1, 2, 3, 4]" + NEW_LINE
                         + "  - numbers2: [2, 3, 4, 5]" + NEW_LINE
                         + "  + numbers2: [22, 33, 44, 55]" + NEW_LINE
                         + "  - numbers3: [3, 4, 5]" + NEW_LINE
                         + "  + numbers4: [4, 5, 6]" + NEW_LINE
                         + "  + obj1: {nestedKey=value, isNested=true}" + NEW_LINE
                         + "  - setting1: Some value" + NEW_LINE
                         + "  + setting1: Another value" + NEW_LINE
                         + "  - setting2: 200" + NEW_LINE
                         + "  + setting2: 300" + NEW_LINE
                         + "  - setting3: true" + NEW_LINE
                         + "  + setting3: none" + NEW_LINE
                         + "}";
        assertEquals(expected1, actual1);
    }
}
