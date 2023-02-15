package hexlet.code;

import hexlet.code.formatter.FormatterFactory;
import hexlet.code.formatter.Formatter;
import hexlet.code.parser.Parser;
import hexlet.code.parser.ParserFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Differ {

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
        Formatter formatter = FormatterFactory.getFormatter(format);
        return formatter.format(diffList);
    }

    public static String generate(String pathString1, String pathString2) throws IOException {
        return generate(pathString1, pathString2, DEFAULT_FORMAT);
    }

    private static Map<String, Object> getFileData(String pathString, String extension) throws IOException {
        String content = FileReader.read(pathString);
        Parser parser = ParserFactory.getParser(extension);
        return parser.parse(content);
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
                resultMap.put("status", ParamStatus.UNMODIFIED.getName());
                resultMap.put("value", oldValue);
            } else {
                resultMap.put("key", key);
                resultMap.put("status", ParamStatus.UPDATED.getName());
                resultMap.put("oldValue", oldValue);
                resultMap.put("newValue", newValue);
            }
        } else if (map1.containsKey(key) && !map2.containsKey(key)) {
            Object oldValue = map1.get(key);
            resultMap.put("key", key);
            resultMap.put("status", ParamStatus.DELETED.getName());
            resultMap.put("value", oldValue);
        } else if (!map1.containsKey(key) && map2.containsKey(key)) {
            Object newValue = map2.get(key);
            resultMap.put("key", key);
            resultMap.put("status", ParamStatus.ADDED.getName());
            resultMap.put("value", newValue);
        }
        return resultMap;
    }
}
