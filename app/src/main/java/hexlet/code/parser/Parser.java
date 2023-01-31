package hexlet.code.parser;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;
import java.util.Map;

public class Parser extends AbstractParser {

    private static final String JSON_EXTENSION = "json";
    private static final String YAML_EXTENSION = "yml";

    @Override
    public Map<String, Object> parse(String string, String extension) throws JsonProcessingException {
        // TODO (?) Check "" AND null ?
        Map<String, Object> map = new HashMap<>();
        if (extension.equals(JSON_EXTENSION)) {
            map = parseJson(string);
        } else if (extension.equals(YAML_EXTENSION)) {
            map = parseYaml(string);
        } else {
            throw new UnsupportedOperationException("File extension '%s' not supported!".formatted(extension));
        }
        return map;
    }

    private Map<String, Object> parseJson(String jsonString) throws JsonProcessingException {
        // TODO (?) Check "" AND null ?
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        Map<String, Object> map = new HashMap<>();
        map = jsonMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() { });
        return map;
    }

    private Map<String, Object> parseYaml(String yamlString) throws JsonProcessingException {
        // TODO (?) Check "" AND null ?
        ObjectMapper yamlMapper = new YAMLMapper();
        Map<String, Object> map = new HashMap<>();
        map = yamlMapper.readValue(yamlString, new TypeReference<Map<String, Object>>() { });
        return map;
    }
}
