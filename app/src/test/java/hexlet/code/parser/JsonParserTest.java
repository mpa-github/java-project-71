package hexlet.code.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonParserTest {

    private static final String NEXT_LINE = System.lineSeparator();
    private static final AbstractParser JSON_PARSER = new JsonParser();

    @Test
    void parseShouldReturnCorrectMapWhenInputJsonString() throws JsonProcessingException {
        String json = "{" + NEXT_LINE
                    + "  \"host\": \"hexlet.io\"," + NEXT_LINE
                    + "  \"timeout\": 50," + NEXT_LINE
                    + "  \"proxy\": \"123.234.53.22\"," + NEXT_LINE
                    + "  \"follow\": false" + NEXT_LINE
                    + "}";
        Map<String, String> actual = JSON_PARSER.parse(json);
        Map<String, String> expected = Map.of("host", "hexlet.io",
                                              "timeout", "50",
                                              "proxy", "123.234.53.22",
                                              "follow", "false");
        assertEquals(actual, expected);
    }
}
