package hexlet.code.formatter;

import java.util.List;
import java.util.Map;

public class Formatter {

    private static final String STYLISH_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";
    private static final String JSON_FORMAT = "json";

    public static String getView(List<Map<String, Object>> map, String format) {
        return switch (format) {
            case STYLISH_FORMAT -> StylishFormatter.getStylishView(map);
            case PLAIN_FORMAT -> PlainFormatter.getPlainView(map);
            case JSON_FORMAT -> JsonFormatter.getJsonView(map);
            default -> throw new RuntimeException("View format '%s' is not supported!".formatted(format));
        };
    }
}
