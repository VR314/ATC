package app;

public class Time {
    public final int multiplier; //used by thread.sleeps
    private final long offset;

    public Time(int m) {
        offset = System.nanoTime();
        this.multiplier = m;
    }

    public long getMins() {
        long x = ((System.nanoTime() - offset));
        return (x / (3000000000L / multiplier) / 60);
    }

    public String format() {
        long tot = getMins();
        int hours = (int) (tot / 60);
        int mins = (int) (tot - (hours * 60));
        return String.valueOf(tot);
    }
}