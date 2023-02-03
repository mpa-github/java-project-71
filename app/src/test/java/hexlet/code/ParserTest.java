package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    private static final String JSON_INVALID_CONTENT = "json invalid string";
    private static final String YAML_INVALID_CONTENT = "yaml invalid string";
    private static final String INVALID_EXTENSION = "txt";

    @Test
    void parseShouldThrowRuntimeExceptionIfCanNotParseFile() {
        assertThrows(RuntimeException.class, () -> Parser.parse(JSON_INVALID_CONTENT, "json"));
        assertThrows(RuntimeException.class, () -> Parser.parse(YAML_INVALID_CONTENT, "yml"));
    }

    @Test
    void parseShouldThrowRuntimeExceptionIfExtensionIsNotSupported() {
        String content = "{\"field\":\"value\"}";
        assertThrows(RuntimeException.class, () -> Parser.parse(content, INVALID_EXTENSION));
    }
}
