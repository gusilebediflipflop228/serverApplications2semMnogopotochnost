package SA.exercise12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailSender {
    private final Transport transport;
    private final ExecutorService executor;

    public EmailSender(Transport transport, int threadCount) {
        this.transport = transport;
        this.executor = Executors.newFixedThreadPool(threadCount);
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public void sendBulk(String emailFile, Message messageTemplate) {
        List<String> emails = readEmailsFromFile(emailFile);
        System.out.println("Загрузка завершена. Писем: " + emails.size());

        for (String email : emails) {
            Message msg = new Message(
                    email,
                    messageTemplate.getSender(),
                    messageTemplate.getSubject(),
                    messageTemplate.getBody()
            );
            executor.submit(() -> transport.send(msg));
        }
        executor.shutdown();
    }

    private List<String> readEmailsFromFile(String filename) {
        List<String> emails = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        getClass().getClassLoader().getResourceAsStream(filename)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    emails.add(trimmed);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emails;
    }
}
