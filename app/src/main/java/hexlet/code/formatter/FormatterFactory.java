package hexlet.code.formatter;

public class FormatterFactory {

    private static final String STYLISH_NAME = "stylish";
    private static final String PLAIN_NAME = "plain";
    private static final String JSON_NAME = "json";

    public IFormatter getFormatter(String format) {
        IFormatter formatter;
        formatter = switch (format) {
            case STYLISH_NAME -> new StylishFormatter();
            case PLAIN_NAME -> new PlainFormatter();
            case JSON_NAME -> new JsonFormatter();
            default -> throw new RuntimeException("Format to '%s' is not supported!".formatted(format));
        };
        return formatter;
    }
}
