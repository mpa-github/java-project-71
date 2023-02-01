package hexlet.code.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    private static final String JSON_EXTENSION = "json";
    private static final String YAML_EXTENSION = "yml";
    private static final String NEW_LINE = System.lineSeparator();

    @Test
    void parseShouldReturnCorrectMapWhenInputJsonString() throws JsonProcessingException {
        String jsonContent = "{" + NEW_LINE
                           + "  \"host\": \"hexlet.io\"," + NEW_LINE
                           + "  \"timeout\": 50," + NEW_LINE
                           + "  \"proxy\": \"123.234.53.22\"," + NEW_LINE
                           + "  \"follow\": false" + NEW_LINE
                           + "}";
        Map<String, Object> actual = Parser.parse(jsonContent, JSON_EXTENSION);
        Map<String, Object> expected = Map.of("host", "hexlet.io",
                                              "timeout", 50,
                                              "proxy", "123.234.53.22",
                                              "follow", false);
        assertEquals(expected, actual);
    }

    @Test
    void parseShouldReturnCorrectMapWhenInputYamlString() throws JsonProcessingException {
        String yamlContent = "---" + NEW_LINE
                           + "host: hexlet.io" + NEW_LINE
                           + "timeout: 50" + NEW_LINE
                           + "proxy: 123.234.53.22" + NEW_LINE
                           + "follow: false";
        Map<String, Object> actual = Parser.parse(yamlContent, YAML_EXTENSION);
        Map<String, Object> expected = Map.of("host", "hexlet.io",
                                              "timeout", 50,
                                              "proxy", "123.234.53.22",
                                              "follow", false);
        assertEquals(expected, actual);
    }
}
