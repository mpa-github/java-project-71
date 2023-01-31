package hexlet.code.differ;

import hexlet.code.parser.AbstractParser;
import hexlet.code.parser.Parser;
import hexlet.code.printer.AbstractPrinter;
import hexlet.code.printer.StylishPrinter;
import hexlet.code.reader.Reader;
import hexlet.code.utils.FileUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Differ {

    private static final String PLUS_PREFIX = "+ ";
    private static final String MINUS_PREFIX = "- ";
    private static final String NEUTRAL_PREFIX = "  ";
    private static final Reader READER = new Reader();
    private static final AbstractParser PARSER = new Parser();
    private static final AbstractPrinter PRINTER = new StylishPrinter();

    public static String generate(String pathString1, String pathString2) throws IOException {
        // TODO Check if the file extensions match.
        String content1 = READER.readFile(pathString1);
        String content2 = READER.readFile(pathString2);
        Map<String, Object> map1 = PARSER.parse(content1, FileUtils.getFileExtension(pathString1));
        Map<String, Object> map2 = PARSER.parse(content2, FileUtils.getFileExtension(pathString1));
        Map<String, Object> diffMap = buildDiffMap(map1, map2);
        return PRINTER.getStringView(diffMap);
    }

    private static Map<String, Object> buildDiffMap(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> differMap = new LinkedHashMap<>();
        Set<String> keys = new HashSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());
        keys.stream()
            .sorted()
            .forEachOrdered(key -> {
                if (map1.containsKey(key) && map2.containsKey(key)) {
                    Object oldValue = map1.get(key);
                    Object newValue = map2.get(key);
                    if (Objects.equals(oldValue, newValue)) {
                        differMap.put(NEUTRAL_PREFIX + key, oldValue);
                    } else {
                        differMap.put(MINUS_PREFIX + key, oldValue);
                        differMap.put(PLUS_PREFIX + key, newValue);
                    }
                } else if (map1.containsKey(key) && !map2.containsKey(key)) {
                    Object oldValue = map1.get(key);
                    differMap.put(MINUS_PREFIX + key, oldValue);
                } else {
                    Object newValue = map2.get(key);
                    differMap.put(PLUS_PREFIX + key, newValue);
                }
            });
        return differMap;
    }
}
