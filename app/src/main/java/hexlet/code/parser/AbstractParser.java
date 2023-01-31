package hexlet.code.parser;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public abstract class AbstractParser {

    public abstract Map<String, Object> parse(String string, String extension) throws JsonProcessingException;
}
