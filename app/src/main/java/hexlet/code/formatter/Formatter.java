package hexlet.code.formatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Formatter {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String STYLISH_FORMAT = "stylish";
    private static final String PLAIN_FORMAT = "plain";
    private static final String STATUS_ADDED = "added";
    private static final String STATUS_DELETED = "deleted";
    private static final String STATUS_UPDATED = "updated";
    private static final String STATUS_UNMODIFIED = "unmodified";
    private static final String PLUS_PREFIX = "  + ";
    private static final String MINUS_PREFIX = "  - ";
    private static final String NEUTRAL_PREFIX = "    ";
    private static final String SPLITTER = ": ";

    public static String getFormattedView(Map<String, Map<String, Object>> map, String format) {
        // TODO (?) Check "" AND null ?
        String resultView;
        if (format.equalsIgnoreCase(STYLISH_FORMAT)) {
            resultView = getStylishView(map);
        } else if (format.equalsIgnoreCase(PLAIN_FORMAT)) {
            resultView = getPlainView(map);
        } else {
            throw new UnsupportedOperationException("View format '%s' not supported!".formatted(format));
        }
        return resultView;
    }

    private static String getStylishView(Map<String, Map<String, Object>> map) {
        // TODO (?) Check "" AND null ?
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append(NEW_LINE);
        map.entrySet().forEach(entry -> stringBuilder.append(buildStylishLine(entry)));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static String buildStylishLine(Map.Entry<String, Map<String, Object>> entry) {
        StringBuilder stringBuilder = new StringBuilder();
        String key = entry.getKey();
        List<Object[]> keyData = getKeyData(entry.getValue());
        for (Object[] data : keyData) {
            Object prefix = data[0];
            Object value = data[1];
            stringBuilder.append(prefix)
                         .append(key)
                         .append(SPLITTER)
                         .append(value)
                         .append(NEW_LINE);
        }
        return stringBuilder.toString();
    }

    private static List<Object[]> getKeyData(Map<String, Object> valueMap) {
        List<Object[]> keyData = new ArrayList<>(2);
        Object keyStatus = valueMap.get("status");
        if (keyStatus.equals(STATUS_ADDED)) {
            keyData.add(new Object[] {PLUS_PREFIX, valueMap.get("newValue")});
        } else if (keyStatus.equals(STATUS_DELETED)) {
            keyData.add(new Object[] {MINUS_PREFIX, valueMap.get("oldValue")});
        } else if (keyStatus.equals(STATUS_UPDATED)) {
            keyData.add(new Object[] {MINUS_PREFIX, valueMap.get("oldValue")});
            keyData.add(new Object[] {PLUS_PREFIX, valueMap.get("newValue")});
        } else if (keyStatus.equals(STATUS_UNMODIFIED)) {
            keyData.add(new Object[] {NEUTRAL_PREFIX, valueMap.get("oldValue")});
        }
        return keyData;
    }

    private static String getPlainView(Map<String, Map<String, Object>> map) {
        // TODO (?) Check "" AND null ?
        StringJoiner stringJoiner = new StringJoiner(NEW_LINE);
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            String key = entry.getKey();
            Map<String, Object> valueMap = entry.getValue();
            Object keyStatus = valueMap.get("status");
            if (keyStatus.equals(STATUS_ADDED)) {
                stringJoiner.add("Property '%s' was added with value: %s".formatted(key, valueMap.get("newValue")));
            } else if (keyStatus.equals(STATUS_DELETED)) {
                stringJoiner.add("Property '%s' was removed".formatted(key));
            } else if (keyStatus.equals(STATUS_UPDATED)) {
                stringJoiner.add("Property '%s' was updated. From %s to %s".formatted(key, valueMap.get("oldValue"),
                                                                                           valueMap.get("newValue")));
            }
            // TODO (?) Return a message if unmodified ?
        }
        return stringJoiner.toString();
    }
}
