package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public class Formatter {

    private static final String STYLISH_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";
    private static final String JSON_FORMAT = "json";

    public static String format(List<Map<String, Object>> map, String format)
            throws RuntimeException, JsonProcessingException {
        return switch (format) {
            case STYLISH_FORMAT -> StylishFormatter.format(map);
            case PLAIN_FORMAT -> PlainFormatter.format(map);
            case JSON_FORMAT -> JsonFormatter.format(map);
            default -> throw new RuntimeException("Format to '%s' is not supported!".formatted(format));
        };
    }
}
