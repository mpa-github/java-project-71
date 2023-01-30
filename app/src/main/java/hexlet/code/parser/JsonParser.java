package hexlet.code.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonParser extends AbstractParser {

    @Override
    public Map<String, String> parse(String jsonString) throws JsonProcessingException {
        // TODO (?) Check "" AND null ?
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        map = objectMapper.readValue(jsonString, new TypeReference<>() {});
        return map;
    }
}
