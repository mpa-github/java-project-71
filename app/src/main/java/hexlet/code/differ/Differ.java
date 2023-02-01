package hexlet.code.differ;

import hexlet.code.formatter.Formatter;
import hexlet.code.parser.Parser;
import hexlet.code.reader.Reader;
import hexlet.code.utils.FileUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Differ {

    private static final String STATUS_ADDED = "added";
    private static final String STATUS_DELETED = "deleted";
    private static final String STATUS_UPDATED = "updated";
    private static final String STATUS_UNMODIFIED = "unmodified";

    public static String generate(String pathString1, String pathString2, String format) throws IOException {
        // TODO Check if the file extensions match.
        String content1 = Reader.readFile(pathString1);
        String content2 = Reader.readFile(pathString2);
        Map<String, Object> map1 = Parser.parse(content1, FileUtils.getFileExtension(pathString1));
        Map<String, Object> map2 = Parser.parse(content2, FileUtils.getFileExtension(pathString1));
        Map<String, Map<String, Object>> diffMap = buildDiffMap(map1, map2);
        return Formatter.getFormattedView(diffMap, format);
    }

    private static Map<String, Map<String, Object>> buildDiffMap(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Map<String, Object>> differMap = new LinkedHashMap<>();
        Set<String> keys = new HashSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());
        keys.stream()
            .sorted()
            .forEachOrdered(key -> differMap.put(key, getKeyStatusMap(key, map1, map2)));
        return differMap;
    }

    private static Map<String, Object> getKeyStatusMap(String key, Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> resultMap = new HashMap<>();
        if (map1.containsKey(key) && map2.containsKey(key)) {
            Object oldValue = map1.get(key);
            Object newValue = map2.get(key);
            if (Objects.equals(oldValue, newValue)) {
                resultMap.put("status", STATUS_UNMODIFIED);
                resultMap.put("oldValue", oldValue);
            } else {
                resultMap.put("status", STATUS_UPDATED);
                resultMap.put("oldValue", oldValue);
                resultMap.put("newValue", newValue);
            }
        } else if (map1.containsKey(key) && !map2.containsKey(key)) {
            Object oldValue = map1.get(key);
            resultMap.put("status", STATUS_DELETED);
            resultMap.put("oldValue", oldValue);
        } else if (!map1.containsKey(key) && map2.containsKey(key)) {
            Object newValue = map2.get(key);
            resultMap.put("status", STATUS_ADDED);
            resultMap.put("newValue", newValue);
        }
        return resultMap;
    }
}
