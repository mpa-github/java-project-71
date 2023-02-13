package hexlet.code;

import hexlet.code.formatter.Formatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Differ {

    //private static final String STATUS_ADDED = "added";
    //private static final String STATUS_DELETED = "deleted";
    //private static final String STATUS_UPDATED = "updated";
    //private static final String STATUS_UNMODIFIED = "unmodified";
    private static final String DEFAULT_FORMAT = "stylish";

    public static String generate(String pathString1, String pathString2, String format)
            throws IOException, UnsupportedOperationException {
        String extension1 = FileReader.getFileExtension(pathString1);
        String extension2 = FileReader.getFileExtension(pathString2);
        if (!extension1.equals(extension2)) {
            throw new UnsupportedOperationException("Unable to match files with different extensions!");
        }
        Map<String, Object> map1 = getFileData(pathString1, extension1);
        Map<String, Object> map2 = getFileData(pathString2, extension2);
        List<Map<String, Object>> diffList = buildDiffList(map1, map2);
        return Formatter.format(diffList, format);
    }

    public static String generate(String pathString1, String pathString2) throws IOException {
        return generate(pathString1, pathString2, DEFAULT_FORMAT);
    }

    // TODO (?) Should this method be in Differ.class?
    private static Map<String, Object> getFileData(String pathString, String extension) throws IOException {
        String content = FileReader.read(pathString);
        return Parser.parse(content, extension);
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
                resultMap.put("status", ParamStatus.UNMODIFIED.name);
                resultMap.put("value", oldValue);
            } else {
                resultMap.put("key", key);
                resultMap.put("status", ParamStatus.UPDATED.name);
                resultMap.put("oldValue", oldValue);
                resultMap.put("newValue", newValue);
            }
        } else if (map1.containsKey(key) && !map2.containsKey(key)) {
            Object oldValue = map1.get(key);
            resultMap.put("key", key);
            resultMap.put("status", ParamStatus.DELETED.name);
            resultMap.put("value", oldValue);
        } else if (!map1.containsKey(key) && map2.containsKey(key)) {
            Object newValue = map2.get(key);
            resultMap.put("key", key);
            resultMap.put("status", ParamStatus.ADDED.name);
            resultMap.put("value", newValue);
        }
        return resultMap;
    }
}
