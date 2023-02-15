package hexlet.code.parser;

public class ParserFactory {

    private static final String JSON_NAME = "json";
    private static final String YAML_NAME = "yml";

    public static Parser getParser(String extension) {
        Parser parser;
        parser = switch (extension) {
            case JSON_NAME -> new JsonParser();
            case YAML_NAME -> new YamlParser();
            default -> throw new RuntimeException("File extension '%s' is not supported!".formatted(extension));
        };
        return parser;
    }
}
