package SA.exercise13;

import java.util.Random;

public class Data {
    private static final Random random = new Random();
    private final int[] values;
    private final long timestamp;

    public  Data(int size) {
        this.values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = random.nextInt(1000);
        }
        this.timestamp = System.currentTimeMillis();
    }

    public int[] get() {
        return values;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i]);
            if (i < values.length - 1) sb.append(", ");
        }
        sb.append("]");
        return String.format("Data@%d %s", timestamp, sb);
    }
}
