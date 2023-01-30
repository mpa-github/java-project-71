package hexlet.code.differ;

import hexlet.code.parser.AbstractParser;
import hexlet.code.parser.JsonParser;
import hexlet.code.printer.AbstractPrinter;
import hexlet.code.printer.StylishPrinter;
import hexlet.code.reader.Reader;

import java.io.IOException;
import java.util.*;

public class Differ {

    private final static String PLUS_PREFIX = "  + ";
    private final static String MINUS_PREFIX = "  - ";
    private final static String NEUTRAL_PREFIX = "    ";
    private final static Reader READER = new Reader();
    private final static AbstractParser PARSER = new JsonParser();
    private final static AbstractPrinter PRINTER = new StylishPrinter();

    public static String generate(String pathString1, String pathString2) throws IOException {
        String json1 = READER.readFile(pathString1);
        String json2 = READER.readFile(pathString2);
        Map<String, String> map1 = PARSER.parse(json1);
        Map<String, String> map2 = PARSER.parse(json2);
        Map<String, String> differMap = buildDiffMap(map1, map2);
        return PRINTER.getStringForPrint(differMap);
    }

    private static Map<String, String> buildDiffMap(Map<String, String> map1, Map<String, String> map2) {
        Map<String, String> differMap = new LinkedHashMap<>();
        Set<String> keys = new HashSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());
        keys.stream()
            .sorted()
            .forEachOrdered(key -> {
                if (map1.containsKey(key) && map2.containsKey(key)) {
                    String oldValue = map1.get(key);
                    String newValue = map2.get(key);
                    if (oldValue.equals(newValue)) {
                        differMap.put(NEUTRAL_PREFIX + key, oldValue);
                    } else {
                        differMap.put(MINUS_PREFIX + key, oldValue);
                        differMap.put(PLUS_PREFIX + key, newValue);
                    }
                } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                    String oldValue = map1.get(key);
                    differMap.put(MINUS_PREFIX + key, oldValue);
                } else {
                    String newValue = map2.get(key);
                    differMap.put(PLUS_PREFIX + key, newValue);
                }
            });
        return differMap;
    }
}
