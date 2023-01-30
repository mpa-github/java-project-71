package hexlet.code.parser;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public abstract class AbstractParser {

    public abstract Map<String, String> parse(String string) throws JsonProcessingException;
}
