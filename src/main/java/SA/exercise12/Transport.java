package SA.exercise12;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Transport {
    private static final String OUTPUT_FILE = "sent_emails.log";
    private static final Object fileLock = new Object();

    public void send(Message message) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        synchronized (fileLock) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE, true))) {
                String logEntry = String.format("[%s] SENT: %s\n",
                        java.time.LocalDateTime.now(), message.getEmailAddress());
                writer.write(logEntry);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Отправлено: " + message.getEmailAddress() +
                " (Поток: " + Thread.currentThread().getName() + ")");
    }
}
