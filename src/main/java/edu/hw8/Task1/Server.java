package edu.hw8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import static java.nio.charset.StandardCharsets.UTF_8;

@SuppressWarnings("MagicNumber")
public class Server implements AutoCloseable {
    private ServerSocketChannel serverSocket;
    private Selector selector;
    private final ExecutorService executorService;
    private final BlockingQueue<SocketChannel> blockingQueue;

    public Server(int countOfThreads) {
        executorService = Executors.newFixedThreadPool(countOfThreads);
        blockingQueue = new LinkedBlockingDeque<>(countOfThreads);
    }

    public void start(int port) throws IOException, InterruptedException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", port));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer byteBuffer = ByteBuffer.allocate(256);

        while (selector.isOpen()) {
            selector.select();
            if (selector.isOpen()) {
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (!key.isValid()) {
                        iterator.remove();
                        blockingQueue.poll();
                        continue;
                    }

                    if (key.isAcceptable()) {
                        register(selector, serverSocket);
                    }

                    if (key.isReadable()) {
                        executorService.submit(() -> {
                            try {
                                answer(byteBuffer, key);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        Thread.sleep(1000);
                    }
                }
            }
        }
    }

    public void register(Selector selector, ServerSocketChannel serverSocket) throws IOException, InterruptedException {
        SocketChannel client = serverSocket.accept();
        if (client != null) {
            blockingQueue.put(client);
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
        }
    }

    public void answer(ByteBuffer byteBuffer, SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        int r = client.read(byteBuffer);
        while (r != -1) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                client.write(getAnswer(byteBuffer));
            }
            byteBuffer.clear();
            r = client.read(byteBuffer);
        }
        client.close();
        blockingQueue.remove(client);
    }

    private ByteBuffer getAnswer(ByteBuffer byteBuffer) {
        String currentData = UTF_8.decode(byteBuffer).toString();
        String answer = "Error\n";
        if (Quotes.QUOTES.containsKey(currentData)) {
            answer = Quotes.QUOTES.get(currentData) + '\n';
        }
        return ByteBuffer.wrap(answer.getBytes(UTF_8));
    }

    @Override public void close() throws Exception {
        executorService.shutdown();
        serverSocket.close();
        selector.close();
    }
}
