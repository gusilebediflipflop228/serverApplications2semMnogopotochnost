package SA.exercise14;

import java.util.Random;

public class MathTask extends Task {
    private static final Random random = new Random();
    private final int a, b;

    public MathTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void execute() {
        int result = a + b;
        System.out.println(a + " + " + b + " = " + result + " (Поток: " + Thread.currentThread().getName() + ")");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
