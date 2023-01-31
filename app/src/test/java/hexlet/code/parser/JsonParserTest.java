package hexlet.code.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonParserTest {

    private static final String NEW_LINE = System.lineSeparator();
    private static final AbstractParser JSON_PARSER = new JsonParser();

    @Test
    void parseShouldReturnCorrectMapWhenInputJsonString() throws JsonProcessingException {
        String json = "{" + NEW_LINE
                    + "  \"host\": \"hexlet.io\"," + NEW_LINE
                    + "  \"timeout\": 50," + NEW_LINE
                    + "  \"proxy\": \"123.234.53.22\"," + NEW_LINE
                    + "  \"follow\": false" + NEW_LINE
                    + "}";
        Map<String, String> actual = JSON_PARSER.parse(json);
        Map<String, String> expected = Map.of("host", "hexlet.io",
                                              "timeout", "50",
                                              "proxy", "123.234.53.22",
                                              "follow", "false");
        assertEquals(actual, expected);
    }
}
