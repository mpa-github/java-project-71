package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.parser.Parser;
import hexlet.code.parser.ParserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    private static final String JSON_INVALID_CONTENT = "json invalid string";
    private static final String YAML_INVALID_CONTENT = "yaml invalid string";
    private static final String JSON_EXTENSION = "json";
    private static final String YAML_EXTENSION = "yml";
    private static final String INVALID_EXTENSION = "txt";
    private static final ParserFactory PARSER_FACTORY = new ParserFactory();

    @Test
    void testExceptionIfCanNotParseFile() {
        Parser jsonParser = PARSER_FACTORY.getParser(JSON_EXTENSION);
        Parser yamlParser = PARSER_FACTORY.getParser(YAML_EXTENSION);
        assertThrows(JsonProcessingException.class, () -> jsonParser.parse(JSON_INVALID_CONTENT));
        assertThrows(JsonProcessingException.class, () -> yamlParser.parse(YAML_INVALID_CONTENT));
        //assertThrows(JsonProcessingException.class, () -> Parser.parse(JSON_INVALID_CONTENT, JSON_EXTENSION));
        //assertThrows(JsonProcessingException.class, () -> Parser.parse(YAML_INVALID_CONTENT, YAML_EXTENSION));
    }

    /*@Test
    void testExceptionIfExtensionIsNotSupported() {
        String content = "{\"field\":\"value\"}";
        assertThrows(RuntimeException.class, () -> Parser.parse(content, INVALID_EXTENSION));
    }*/
}
