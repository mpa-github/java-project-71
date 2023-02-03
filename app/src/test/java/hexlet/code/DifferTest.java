package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {

    private static final String STR_JSON_PATH_1 = "src/test/resources/json/json1.json";
    private static final String STR_JSON_PATH_2 = "src/test/resources/json/json2.json";
    private static final String STR_YAML_PATH_1 = "src/test/resources/yaml/yaml1.yml";
    private static final String STR_YAML_PATH_2 = "src/test/resources/yaml/yaml2.yml";
    private static final String UNSUPPORTED_FORMAT = "unsupported";
    private static final String STYLISH_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";
    private static final String JSON_FORMAT = "json";
    private static final String NEW_LINE = System.lineSeparator();
    private String expectedStylishView;
    private String expectedPlainView;
    private String expectedJsonView;

    @BeforeEach
    void initView1() {
        expectedStylishView = "{" + NEW_LINE
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

        expectedPlainView = "Property 'chars2' was updated. From [complex value] to false" + NEW_LINE
                + "Property 'checked' was updated. From false to true" + NEW_LINE
                + "Property 'default' was updated. From null to [complex value]" + NEW_LINE
                + "Property 'id' was updated. From 45 to null" + NEW_LINE
                + "Property 'key1' was removed" + NEW_LINE
                + "Property 'key2' was added with value: 'value2'" + NEW_LINE
                + "Property 'numbers2' was updated. From [complex value] to [complex value]" + NEW_LINE
                + "Property 'numbers3' was removed" + NEW_LINE
                + "Property 'numbers4' was added with value: [complex value]" + NEW_LINE
                + "Property 'obj1' was added with value: [complex value]" + NEW_LINE
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'" + NEW_LINE
                + "Property 'setting2' was updated. From 200 to 300" + NEW_LINE
                + "Property 'setting3' was updated. From true to 'none'";
    }

    @BeforeEach
    void initView2() {
        expectedJsonView = "[" + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"chars1\"," + NEW_LINE
                + "    \"status\" : \"unmodified\"," + NEW_LINE
                + "    \"value\" : [" + NEW_LINE
                + "      \"a\"," + NEW_LINE
                + "      \"b\"," + NEW_LINE
                + "      \"c\"" + NEW_LINE
                + "    ]" + NEW_LINE
                + "  }," + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"chars2\"," + NEW_LINE
                + "    \"status\" : \"updated\"," + NEW_LINE
                + "    \"oldValue\" : [" + NEW_LINE
                + "      \"d\"," + NEW_LINE
                + "      \"e\"," + NEW_LINE
                + "      \"f\"" + NEW_LINE
                + "    ]," + NEW_LINE
                + "    \"newValue\" : false" + NEW_LINE
                + "  }," + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"checked\"," + NEW_LINE
                + "    \"status\" : \"updated\"," + NEW_LINE
                + "    \"oldValue\" : false," + NEW_LINE
                + "    \"newValue\" : true" + NEW_LINE
                + "  }," + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"default\"," + NEW_LINE
                + "    \"status\" : \"updated\"," + NEW_LINE
                + "    \"oldValue\" : null," + NEW_LINE
                + "    \"newValue\" : [" + NEW_LINE
                + "      \"value1\"," + NEW_LINE
                + "      \"value2\"" + NEW_LINE
                + "    ]" + NEW_LINE
                + "  }," + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"id\"," + NEW_LINE
                + "    \"status\" : \"updated\"," + NEW_LINE
                + "    \"oldValue\" : 45," + NEW_LINE
                + "    \"newValue\" : null" + NEW_LINE
                + "  }," + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"key1\"," + NEW_LINE
                + "    \"status\" : \"deleted\"," + NEW_LINE
                + "    \"value\" : \"value1\"" + NEW_LINE
                + "  }," + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"key2\"," + NEW_LINE
                + "    \"status\" : \"added\"," + NEW_LINE
                + "    \"value\" : \"value2\"" + NEW_LINE
                + "  }," + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"numbers1\"," + NEW_LINE
                + "    \"status\" : \"unmodified\"," + NEW_LINE
                + "    \"value\" : [" + NEW_LINE
                + "      1," + NEW_LINE
                + "      2," + NEW_LINE
                + "      3," + NEW_LINE
                + "      4" + NEW_LINE
                + "    ]" + NEW_LINE
                + "  }," + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"numbers2\"," + NEW_LINE
                + "    \"status\" : \"updated\"," + NEW_LINE
                + "    \"oldValue\" : [" + NEW_LINE
                + "      2," + NEW_LINE
                + "      3," + NEW_LINE
                + "      4," + NEW_LINE
                + "      5" + NEW_LINE
                + "    ]," + NEW_LINE
                + "    \"newValue\" : [" + NEW_LINE
                + "      22," + NEW_LINE
                + "      33," + NEW_LINE
                + "      44," + NEW_LINE
                + "      55" + NEW_LINE
                + "    ]" + NEW_LINE
                + "  }," + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"numbers3\"," + NEW_LINE
                + "    \"status\" : \"deleted\"," + NEW_LINE
                + "    \"value\" : [" + NEW_LINE
                + "      3," + NEW_LINE
                + "      4," + NEW_LINE
                + "      5" + NEW_LINE
                + "    ]" + NEW_LINE
                + "  }," + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"numbers4\"," + NEW_LINE
                + "    \"status\" : \"added\"," + NEW_LINE
                + "    \"value\" : [" + NEW_LINE
                + "      4," + NEW_LINE
                + "      5," + NEW_LINE
                + "      6" + NEW_LINE
                + "    ]" + NEW_LINE
                + "  }," + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"obj1\"," + NEW_LINE
                + "    \"status\" : \"added\"," + NEW_LINE
                + "    \"value\" : {" + NEW_LINE
                + "      \"nestedKey\" : \"value\"," + NEW_LINE
                + "      \"isNested\" : true" + NEW_LINE
                + "    }" + NEW_LINE
                + "  }," + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"setting1\"," + NEW_LINE
                + "    \"status\" : \"updated\"," + NEW_LINE
                + "    \"oldValue\" : \"Some value\"," + NEW_LINE
                + "    \"newValue\" : \"Another value\"" + NEW_LINE
                + "  }," + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"setting2\"," + NEW_LINE
                + "    \"status\" : \"updated\"," + NEW_LINE
                + "    \"oldValue\" : 200," + NEW_LINE
                + "    \"newValue\" : 300" + NEW_LINE
                + "  }," + NEW_LINE
                + "  {" + NEW_LINE
                + "    \"key\" : \"setting3\"," + NEW_LINE
                + "    \"status\" : \"updated\"," + NEW_LINE
                + "    \"oldValue\" : true," + NEW_LINE
                + "    \"newValue\" : \"none\"" + NEW_LINE
                + "  }" + NEW_LINE
                + "]";
    }

    @Test
    void generateShouldReturnCorrectDiffViewWhenInputCorrectJsonFilePaths() {
        String actualStylishView = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2, STYLISH_FORMAT);
        String actualPlainView = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2, PLAIN_FORMAT);
        String actualJsonView = Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_2, JSON_FORMAT);
        assertEquals(expectedStylishView, actualStylishView);
        assertEquals(expectedPlainView, actualPlainView);
        assertEquals(expectedJsonView, actualJsonView);
    }

    @Test
    void generateShouldReturnCorrectDiffViewWhenInputCorrectYamlFilePaths() {
        String actualStylishView = Differ.generate(STR_YAML_PATH_1, STR_YAML_PATH_2, STYLISH_FORMAT);
        String actualPlainView = Differ.generate(STR_YAML_PATH_1, STR_YAML_PATH_2, PLAIN_FORMAT);
        String actualJsonView = Differ.generate(STR_YAML_PATH_1, STR_YAML_PATH_2, JSON_FORMAT);
        assertEquals(expectedStylishView, actualStylishView);
        assertEquals(expectedPlainView, actualPlainView);
        assertEquals(expectedJsonView, actualJsonView);
    }

    @Test
    void generateShouldThrowUnsupportedOperationExceptionWhenFileExtensionsAreDifferent() {
        assertThrows(UnsupportedOperationException.class, () ->
                Differ.generate(STR_JSON_PATH_1, STR_YAML_PATH_1, STYLISH_FORMAT));
    }

    @Test
    void generateShouldThrowRuntimeExceptionWhenViewFormatDoesNotExist() {
        assertThrows(RuntimeException.class, () ->
                Differ.generate(STR_JSON_PATH_1, STR_JSON_PATH_1, UNSUPPORTED_FORMAT));
    }
}
