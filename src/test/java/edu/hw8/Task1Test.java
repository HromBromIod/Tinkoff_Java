package edu.hw8;

import edu.hw8.Task1.Client;
import edu.hw8.Task1.Quotes;
import edu.hw8.Task1.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import static edu.hw8.Task1.Quotes.QUOTES;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    private Task1Test() {
    }

    private List<String> list = List.of("личности", "оскорбления", "глупый", "интеллект");

    @RepeatedTest(10)
    @DisplayName("Сервер и клиенты общаются")
    void serverResponseTest() throws InterruptedException {

        Server server = new Server(6);
        Thread thread = new Thread(() -> {
            try {
                server.start(18080);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Thread.sleep(1000);

        int listIndex = ThreadLocalRandom.current().nextInt(4);
        Client client = new Client("Ваня", "localhost", 18080);
        String in = client.sendToServer(list.get(listIndex));
        String out = client.readFromServer();
        try {
            client.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Thread.sleep(1000);
        try {
            server.close();
            thread.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals("Ваня: " + list.get(listIndex), in);
        assertEquals("Сервер: " + QUOTES.get(list.get(listIndex)), out);
    }
}
