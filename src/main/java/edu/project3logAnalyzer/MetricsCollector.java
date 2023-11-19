package edu.project3logAnalyzer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MetricsCollector {
    private final static int MAX_COUNT = 3;
    private final Set<String> sources = new HashSet<>();
    private final Map<Integer, Integer> statuses = new HashMap<>();
    private BigDecimal bytes = new BigDecimal(0);
    private final Map<String, Integer> requests = new HashMap<>();
    private int requestsCount = 0;
    private LocalDateTime from;
    private LocalDateTime to;

    public void collect(LogString logString) {
        sources.add(logString.source());
        statuses.put(logString.status(), statuses.getOrDefault(logString.status(), 0) + 1);
        bytes = bytes.add(BigDecimal.valueOf(logString.bodyBytesSent()));
        String request = logString.request().split(" ")[1];
        requests.put(request, requests.getOrDefault(request, 0) + 1);
        requestsCount++;

        if (from == null || logString.timeLocal().isBefore(from)) {
            from = logString.timeLocal();
        }

        if (to == null || logString.timeLocal().isAfter(to)) {
            to = logString.timeLocal();
        }
    }

    public Metrics getMetrics() {
        return new Metrics(
            sources,
            statuses.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()).limit(MAX_COUNT)
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                )),
            bytes.equals(BigDecimal.valueOf(0))
                ? 0
                : bytes.divide(BigDecimal.valueOf(requestsCount), RoundingMode.CEILING).intValue(),
            requests.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(MAX_COUNT)
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                )),
            requestsCount,
            from,
            to
        );
    }
}
