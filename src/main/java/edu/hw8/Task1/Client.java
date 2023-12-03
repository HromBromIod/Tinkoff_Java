package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client implements AutoCloseable {
    private final String clientName;
    private final Socket client;
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;

    public Client(String name, String host, int port) {
        try {
            clientName = name;
            client = new Socket(InetAddress.getByName(host), port);
            bufferedWriter =
                new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8));
            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String sendToServer(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.flush();
            System.out.println(clientName + ": " + message);
            return clientName + ": " + message;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readFromServer() {
        try {
            String response = bufferedReader.readLine();
            System.out.println("Сервер: " + response);
            return "Сервер: " + response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        client.close();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
