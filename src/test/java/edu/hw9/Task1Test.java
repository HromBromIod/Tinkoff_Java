package edu.hw9;

import edu.hw9.Task1.StatsCollector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Task1Test {
    private Task1Test() {
    }

    private final List<String> metricNames = List.of(
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"
    );
    private final Logger logger = LogManager.getLogger();

    @Test
    @DisplayName("Программа справляется с задачей")
    void test1() throws InterruptedException {
        int collectorSize = 6;
        int countOfMetrics = 10;
        StatsCollector collector = new StatsCollector(collectorSize);

        var send = Stream.generate(() -> CompletableFuture.runAsync(() ->
            {
                try {
                    collector.put(
                        metricNames.get(ThreadLocalRandom.current().nextInt(10)),
                        new double[] {ThreadLocalRandom.current().nextDouble(-5, 5),
                            ThreadLocalRandom.current().nextDouble(-5, 5),
                            ThreadLocalRandom.current().nextDouble(-5, 5),
                            ThreadLocalRandom.current().nextDouble(-5, 5)}
                    );
                } catch (InterruptedException e) {
                    logger.info(e);
                }
            }, Executors.newFixedThreadPool(collectorSize)))
            .limit(countOfMetrics)
            .toArray(CompletableFuture[]::new);
        logger.info("name        summ                avg                max                min");
        var answer = Stream.generate(() -> CompletableFuture.runAsync(
                () ->
                {
                    try {
                        logger.info(collector.collectStats());
                    } catch (InterruptedException e) {
                        logger.info(e);
                    }
                },
                Executors.newFixedThreadPool(collectorSize)
            ))
            .limit(countOfMetrics)
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(send).join();
        CompletableFuture.allOf(answer).join();
    }
}
