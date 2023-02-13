package hexlet.code.formatter;

import hexlet.code.ParamStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class PlainFormatter {

    private static final String NEW_LINE = System.lineSeparator();
    //private static final String STATUS_ADDED = "added";
    //private static final String STATUS_DELETED = "deleted";
    //private static final String STATUS_UPDATED = "updated";

    public static String format(List<Map<String, Object>> mapList) {
        StringJoiner stringJoiner = new StringJoiner(NEW_LINE);
        for (Map<String, Object> map : mapList) {
            Object key = map.get("key");
            Object keyStatus = map.get("status");
            if (keyStatus.equals(ParamStatus.ADDED.name)) {
                Object value = map.get("value");
                stringJoiner.add("Property '%s' was added with value: %s".formatted(key, stringify(value)));
            } else if (keyStatus.equals(ParamStatus.DELETED.name)) {
                stringJoiner.add("Property '%s' was removed".formatted(key));
            } else if (keyStatus.equals(ParamStatus.UPDATED.name)) {
                Object oldValue = map.get("oldValue");
                Object newValue = map.get("newValue");
                stringJoiner.add("Property '%s' was updated. From %s to %s".formatted(key, stringify(oldValue),
                                                                                           stringify(newValue)));
            }
            // TODO (?) Return a message if unmodified ?
        }
        return stringJoiner.toString();
    }

    private static Object stringify(Object object) {
        if (object instanceof String) {
            return "'%s'".formatted(object);
        }
        if (object instanceof ArrayList<?> || object instanceof LinkedHashMap<?, ?>) {
            return "[complex value]";
        }
        return object;
    }
}
