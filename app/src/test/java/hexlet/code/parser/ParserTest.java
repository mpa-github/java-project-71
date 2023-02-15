package hexlet.code.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ParserTest {

    private static final String JSON_INVALID_CONTENT = "json invalid string";
    private static final String YAML_INVALID_CONTENT = "yaml invalid string";
    private static final String JSON_EXTENSION = "json";
    private static final String YAML_EXTENSION = "yml";

    @ParameterizedTest
    @MethodSource("getParserForContent")
    void testExceptionIfCanNotParseFile2(Parser parser, String content) {
        assertThrows(JsonProcessingException.class, () -> parser.parse(content));
    }

    private static Stream<Arguments> getParserForContent() {
        return Stream.of(
                arguments(ParserFactory.getParser(JSON_EXTENSION), JSON_INVALID_CONTENT),
                arguments(ParserFactory.getParser(YAML_EXTENSION), YAML_INVALID_CONTENT)
        );
    }
}
