package SA.exercise14;

public class PrintTask extends Task {
    private final String message;

    public PrintTask(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        System.out.println(message + " (Поток: " + Thread.currentThread().getName() + ")");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
