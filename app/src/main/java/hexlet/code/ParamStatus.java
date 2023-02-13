package hexlet.code;

public enum ParamStatus {

    ADDED("added"),
    DELETED("deleted"),
    UPDATED("updated"),
    UNMODIFIED("unmodified");

    public final String name;

    ParamStatus(String name) {
        this.name = name;
    }

    public static ParamStatus getParamStatus(String name) {
        return ParamStatus.valueOf(name);
    }

    /*public static ParamStatus getByName(String name) {
        for (ParamStatus status : ParamStatus.values()) {
            if (status.name.equalsIgnoreCase(name)) {
                return status;
            }
        }
        return null;
    }*/
}
