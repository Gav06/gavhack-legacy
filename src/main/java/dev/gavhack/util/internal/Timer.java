package dev.gavhack.util.internal;

public class Timer {
    private long time = -1L;

    public void reset() {
        time = System.currentTimeMillis();
    }

    public boolean passed(long ms) {
        return System.currentTimeMillis() - time >= ms;
    }

    public long getTimeMs() {
        return System.currentTimeMillis() - time;
    }
}
