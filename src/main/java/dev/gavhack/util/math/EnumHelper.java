package dev.gavhack.util.math;

@SuppressWarnings("rawtypes")
public class EnumHelper {

    public static Enum getEnumFromName(String name, Class<? extends Enum> enumClass) {
        final Enum[] constants = enumClass.getEnumConstants();

        for (Enum enumObj : constants) {
            if (enumObj.name().equals(name))
                return enumObj;
        }

        return null;
    }
}
