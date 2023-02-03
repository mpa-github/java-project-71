package hexlet.code.formatter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class PlainFormatter {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String STATUS_ADDED = "added";
    private static final String STATUS_DELETED = "deleted";
    private static final String STATUS_UPDATED = "updated";

    public static String getPlainView(List<Map<String, Object>> mapList) {
        StringJoiner stringJoiner = new StringJoiner(NEW_LINE);
        for (Map<String, Object> map : mapList) {
            Object key = map.get("key");
            Object keyStatus = map.get("status");
            if (keyStatus.equals(STATUS_ADDED)) {
                Object value = map.get("value");
                stringJoiner.add("Property '%s' was added with value: %s".formatted(key, format(value)));
            } else if (keyStatus.equals(STATUS_DELETED)) {
                stringJoiner.add("Property '%s' was removed".formatted(key));
            } else if (keyStatus.equals(STATUS_UPDATED)) {
                Object oldValue = map.get("oldValue");
                Object newValue = map.get("newValue");
                stringJoiner.add("Property '%s' was updated. From %s to %s".formatted(key, format(oldValue),
                                                                                           format(newValue)));
            }
            // TODO (?) Return a message if unmodified ?
        }
        return stringJoiner.toString();
    }

    private static Object format(Object object) {
        if (object instanceof String) {
            return "'%s'".formatted(object);
        }
        if (object instanceof ArrayList<?> || object instanceof LinkedHashMap<?, ?>) {
            return "[complex value]";
        }
        return object;
    }
}
