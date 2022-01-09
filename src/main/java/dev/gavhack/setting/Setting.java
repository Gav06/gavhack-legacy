package dev.gavhack.setting;

public class Setting<T> {
    private final String name;
    private T value;

    private final Number min, max;

    public Setting(String name, T value) {
        this(name, value, null, null);
    }

    public Setting(String name, T value, Number min, Number max) {
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Number getMin() {
        return min;
    }

    public Number getMax() {
        return max;
    }
}
