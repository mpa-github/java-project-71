package hexlet.code;

import hexlet.code.formatter.Formatter;
import hexlet.code.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Differ {

    private static final String STATUS_ADDED = "added";
    private static final String STATUS_DELETED = "deleted";
    private static final String STATUS_UPDATED = "updated";
    private static final String STATUS_UNMODIFIED = "unmodified";

    public static String generate(String pathString1, String pathString2, String format) {
        String extension1 = FileUtils.getFileExtension(pathString1);
        String extension2 = FileUtils.getFileExtension(pathString2);
        if (!extension1.equals(extension2)) {
            throw new UnsupportedOperationException("Unable to match files with different extensions!");
        }
        String content1 = FileReader.readFile(pathString1);
        String content2 = FileReader.readFile(pathString2);
        Map<String, Object> map1 = Parser.parse(content1, extension1);
        Map<String, Object> map2 = Parser.parse(content2, extension2);
        List<Map<String, Object>> diffList = buildDiffList(map1, map2);
        return Formatter.getView(diffList, format);
    }

    private static List<Map<String, Object>> buildDiffList(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> diffList = new ArrayList<>();
        Set<String> keys = new HashSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());
        keys.stream()
            .sorted()
            .forEachOrdered(key -> diffList.add(getKeyStatusMap(key, map1, map2)));
        return diffList;
    }

    private static Map<String, Object> getKeyStatusMap(String key, Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        if (map1.containsKey(key) && map2.containsKey(key)) {
            Object oldValue = map1.get(key);
            Object newValue = map2.get(key);
            if (Objects.equals(oldValue, newValue)) {
                resultMap.put("key", key);
                resultMap.put("status", STATUS_UNMODIFIED);
                resultMap.put("value", oldValue);
            } else {
                resultMap.put("key", key);
                resultMap.put("status", STATUS_UPDATED);
                resultMap.put("oldValue", oldValue);
                resultMap.put("newValue", newValue);
            }
        } else if (map1.containsKey(key) && !map2.containsKey(key)) {
            Object oldValue = map1.get(key);
            resultMap.put("key", key);
            resultMap.put("status", STATUS_DELETED);
            resultMap.put("value", oldValue);
        } else if (!map1.containsKey(key) && map2.containsKey(key)) {
            Object newValue = map2.get(key);
            resultMap.put("key", key);
            resultMap.put("status", STATUS_ADDED);
            resultMap.put("value", newValue);
        }
        return resultMap;
    }
}
