package edu.project3logAnalyzer;

import java.time.LocalDateTime;

public record LogString(
    String source,
    String remoteAddress,
    String remoteUser,
    LocalDateTime timeLocal,
    String request,
    int status,
    int bodyBytesSent,
    String httpReferer,
    String httpUser
) {
}
