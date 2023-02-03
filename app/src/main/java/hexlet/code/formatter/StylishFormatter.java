package hexlet.code.formatter;

import java.util.List;
import java.util.Map;

public class StylishFormatter {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String STATUS_ADDED = "added";
    private static final String STATUS_DELETED = "deleted";
    private static final String STATUS_UPDATED = "updated";
    private static final String STATUS_UNMODIFIED = "unmodified";
    private static final String PLUS_PREFIX = "  + ";
    private static final String MINUS_PREFIX = "  - ";
    private static final String NEUTRAL_PREFIX = "    ";
    private static final String SPLITTER = ": ";

    public static String getStylishView(List<Map<String, Object>> mapList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append(NEW_LINE);
        mapList.forEach(map -> stringBuilder.append(buildStylishLine(map)));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private static String buildStylishLine(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        Object key = map.get("key");
        String keyStatus = (String) map.get("status");
        switch (keyStatus) {
            case STATUS_ADDED -> {
                Object value = map.get("value");
                sb.append(PLUS_PREFIX).append(key).append(SPLITTER).append(value).append(NEW_LINE);
            }
            case STATUS_DELETED -> {
                Object value = map.get("value");
                sb.append(MINUS_PREFIX).append(key).append(SPLITTER).append(value).append(NEW_LINE);
            }
            case STATUS_UPDATED -> {
                Object oldValue = map.get("oldValue");
                Object newValue = map.get("newValue");
                sb.append(MINUS_PREFIX).append(key).append(SPLITTER).append(oldValue).append(NEW_LINE);
                sb.append(PLUS_PREFIX).append(key).append(SPLITTER).append(newValue).append(NEW_LINE);
            }
            case STATUS_UNMODIFIED -> {
                Object value = map.get("value");
                sb.append(NEUTRAL_PREFIX).append(key).append(SPLITTER).append(value).append(NEW_LINE);
            }
            default -> throw new UnsupportedOperationException();
        }
        return sb.toString();
    }

    /*private static String buildStylishLine2(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        Object key = map.get("key");
        Object keyStatus = map.get("status");
        if (keyStatus.equals(STATUS_ADDED)) {
            Object value = map.get("value");
            sb.append(PLUS_PREFIX).append(key).append(SPLITTER).append(value).append(NEW_LINE);
        } else if (keyStatus.equals(STATUS_DELETED)) {
            Object value = map.get("value");
            sb.append(MINUS_PREFIX).append(key).append(SPLITTER).append(value).append(NEW_LINE);
        } else if (keyStatus.equals(STATUS_UPDATED)) {
            Object oldValue = map.get("oldValue");
            Object newValue = map.get("newValue");
            sb.append(MINUS_PREFIX).append(key).append(SPLITTER).append(oldValue).append(NEW_LINE);
            sb.append(PLUS_PREFIX).append(key).append(SPLITTER).append(newValue).append(NEW_LINE);
        } else if (keyStatus.equals(STATUS_UNMODIFIED)) {
            Object value = map.get("value");
            sb.append(NEUTRAL_PREFIX).append(key).append(SPLITTER).append(value).append(NEW_LINE);
        }
        return sb.toString();
    }*/

    /*private static String buildStylishLine3(Map<String, Object> map) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Object[]> keyData = getKeyData(map);
        for (Object[] data : keyData) {
            Object prefix = data[0];
            Object key = data[1];
            Object value = data[2];
            stringBuilder.append(prefix)
                         .append(key)
                         .append(SPLITTER)
                         .append(value)
                         .append(NEW_LINE);
        }
        return stringBuilder.toString();
    }*/

    /*private static List<Object[]> getKeyData(Map<String, Object> map) {
        List<Object[]> keyData = new ArrayList<>(2);
        Object keyStatus = map.get("status");
        if (keyStatus.equals(STATUS_ADDED)) {
            keyData.add(new Object[] {PLUS_PREFIX, map.get("key"), map.get("value")});
        } else if (keyStatus.equals(STATUS_DELETED)) {
            keyData.add(new Object[] {MINUS_PREFIX, map.get("key"), map.get("value")});
        } else if (keyStatus.equals(STATUS_UPDATED)) {
            keyData.add(new Object[] {MINUS_PREFIX, map.get("key"), map.get("oldValue")});
            keyData.add(new Object[] {PLUS_PREFIX, map.get("key"), map.get("newValue")});
        } else if (keyStatus.equals(STATUS_UNMODIFIED)) {
            keyData.add(new Object[] {NEUTRAL_PREFIX, map.get("key"), map.get("value")});
        }
        return keyData;
    }*/
}
