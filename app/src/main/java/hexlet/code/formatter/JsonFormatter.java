package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.Map;

public class JsonFormatter {

    public static String getJsonView(List<Map<String, Object>> mapList) {
        ObjectMapper mapper = new ObjectMapper();
        String resultView;
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
            prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
            resultView = mapper.writer(prettyPrinter).writeValueAsString(mapList);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return resultView;
    }
}
