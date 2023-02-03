package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    private static final String JSON_EXTENSION = "json";
    private static final String YAML_EXTENSION = "yml";

    public static Map<String, Object> parse(String string, String extension) {
        Map<String, Object> map = new HashMap<>();
        map = switch (extension) {
            case JSON_EXTENSION -> parseJson(string);
            case YAML_EXTENSION -> parseYaml(string);
            default -> throw new RuntimeException("File extension '%s' is not supported!".formatted(extension));
        };
        return map;
    }

    private static Map<String, Object> parseJson(String jsonString) {
        ObjectMapper jsonMapper = new ObjectMapper();
        return getStringObjectMap(jsonString, jsonMapper);
    }

    private static Map<String, Object> parseYaml(String yamlString) {
        ObjectMapper yamlMapper = new YAMLMapper();
        return getStringObjectMap(yamlString, yamlMapper);
    }

    private static Map<String, Object> getStringObjectMap(String string, ObjectMapper mapper) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = mapper.readValue(string, new TypeReference<Map<String, Object>>() { });
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return map;
    }
}
