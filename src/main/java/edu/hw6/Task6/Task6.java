package edu.hw6.Task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"UncommentedMain", "MagicNumber"}) public class Task6 {
    private Task6() {
    }

    private final static Logger logger = LogManager.getLogger();

    private static final String TCP_PROTOCOL = "TCP";

    private static final String UDP_PROTOCOL = "UDP";

    private static final Map<Integer, String> MAP_OF_CONNECTION = new HashMap<>();

    private static final String OUTPUT_FORMAT = "%-10s%-7s%-10s";

    private static final int MAX_PORT_NUMBER = 49151;

    static {
        setConnections();
        scanPort();
    }

    public static void setConnections() {
        MAP_OF_CONNECTION.put(23, "Telnet");
        MAP_OF_CONNECTION.put(110, "POP3 ");
        MAP_OF_CONNECTION.put(143, "IMAP");
        MAP_OF_CONNECTION.put(123, "NTP ");
        MAP_OF_CONNECTION.put(445, "SMB");
        MAP_OF_CONNECTION.put(548, "AFP");
        MAP_OF_CONNECTION.put(8080, "HTTP Proxy");
        MAP_OF_CONNECTION.put(1080, "SOCKS");
        MAP_OF_CONNECTION.put(1723, "PPTP ");
        MAP_OF_CONNECTION.put(3128, "Squid");
        MAP_OF_CONNECTION.put(80, "HTTP");
        MAP_OF_CONNECTION.put(21, "FTP");
        MAP_OF_CONNECTION.put(25, "SMTP");
        MAP_OF_CONNECTION.put(22, "SSH");
        MAP_OF_CONNECTION.put(443, "HTTPS");
        MAP_OF_CONNECTION.put(53, "DNS");
        MAP_OF_CONNECTION.put(3306, "MySQL");
        MAP_OF_CONNECTION.put(5432, "PostgreSQL");
        MAP_OF_CONNECTION.put(3389, "RDP");
        MAP_OF_CONNECTION.put(27017, "MongoDB");
    }

    public static void scanPort() {
        logger.info(OUTPUT_FORMAT.formatted("Протокол", "Порт", "Сервис"));
        for (int portNumber = 0; portNumber < MAX_PORT_NUMBER; ++portNumber) {
            try {
                ServerSocket serverSocket = new ServerSocket(portNumber);
                serverSocket.close();
            } catch (IOException ignoreException) {
                logger.info(OUTPUT_FORMAT.formatted(
                    TCP_PROTOCOL,
                    portNumber,
                    MAP_OF_CONNECTION.getOrDefault(portNumber, "")
                ));
            }
            try {
                DatagramSocket datagramSocket = new DatagramSocket(portNumber);
                datagramSocket.close();
            } catch (IOException ignoreException) {
                logger.info(OUTPUT_FORMAT.formatted(
                    UDP_PROTOCOL,
                    portNumber,
                    MAP_OF_CONNECTION.getOrDefault(portNumber, "")
                ));
            }
        }
    }
}
