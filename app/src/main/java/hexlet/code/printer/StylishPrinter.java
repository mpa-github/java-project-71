package hexlet.code.printer;

import java.util.Map;

public class StylishPrinter extends AbstractPrinter {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String SPLITTER = ": ";

    @Override
    public String getStringForPrint(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append(NEW_LINE);
        map.forEach((key, value) -> stringBuilder.append(key).append(SPLITTER).append(value).append(NEW_LINE));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
