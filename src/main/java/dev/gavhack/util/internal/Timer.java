package dev.gavhack.util.internal;

public class Timer {
    private long time = -1L;

    public void reset() {
        time = System.nanoTime();
    }

    public boolean passed(long ms) {
        return System.nanoTime() - time >= ms * 1000000L;
    }
}
