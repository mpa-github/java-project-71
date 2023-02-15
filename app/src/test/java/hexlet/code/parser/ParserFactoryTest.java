package hexlet.code.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserFactoryTest {

    private static final String INVALID_EXTENSION = "txt";

    @Test
    void testExceptionIfExtensionIsNotSupported() {
        assertThrows(RuntimeException.class, () -> ParserFactory.getParser(INVALID_EXTENSION));
    }
}
