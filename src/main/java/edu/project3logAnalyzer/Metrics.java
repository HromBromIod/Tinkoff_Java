package edu.project3logAnalyzer;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public record Metrics(
    Set<String> files,
    Map<Integer, Integer> statuses,
    int bytes,
    Map<String, Integer> requests,
    int requestsCount,
    LocalDateTime from,
    LocalDateTime to
) {
}
