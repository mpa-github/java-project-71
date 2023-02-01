package hexlet.code.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    private static final String JSON_EXTENSION = "json";
    private static final String YAML_EXTENSION = "yml";

    public static Map<String, Object> parse(String string, String extension) throws JsonProcessingException {
        // TODO (?) Check "" AND null ?
        Map<String, Object> map = new HashMap<>();
        if (extension.equalsIgnoreCase(JSON_EXTENSION)) {
            map = parseJson(string);
        } else if (extension.equalsIgnoreCase(YAML_EXTENSION)) {
            map = parseYaml(string);
        } else {
            throw new UnsupportedOperationException("File extension '%s' not supported!".formatted(extension));
        }
        return map;
    }

    private static Map<String, Object> parseJson(String jsonString) throws JsonProcessingException {
        // TODO (?) Check "" AND null ?
        ObjectMapper jsonMapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        map = jsonMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() { });
        return map;
    }

    private static Map<String, Object> parseYaml(String yamlString) throws JsonProcessingException {
        // TODO (?) Check "" AND null ?
        ObjectMapper yamlMapper = new YAMLMapper();
        Map<String, Object> map = new HashMap<>();
        map = yamlMapper.readValue(yamlString, new TypeReference<Map<String, Object>>() { });
        return map;
    }
}
