package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private HackerNews() {
    }

    private static final int BEGIN_TITLE_INDEX = 9;
    private static final int BEGIN_PARENT_INDEX = 9;

    public static long[] hackerNewsTopStories() {
        long[] data;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request =
                HttpRequest.newBuilder().uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String[] splitResponseBody = response.body().split("[,\\[\\]]");
            int splitSize = splitResponseBody.length;
            data = new long[splitSize - 1];
            for (int i = 1; i < splitSize; i++) {
                data[i - 1] = Long.parseLong(splitResponseBody[i]);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public static String news(long id) {
        String newsTitle = null;
        StringBuilder uriId = new StringBuilder();
        uriId.append("https://hacker-news.firebaseio.com/v0/item/").append(id).append(".json");
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uriId.toString())).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Matcher matcher = Pattern.compile("(\"title\":\"[\\w* ]*)").matcher(response.body());
            if (matcher.find()) {
                newsTitle = matcher.group().substring(BEGIN_TITLE_INDEX);
            } else {
                matcher = Pattern.compile("(\"parent\":\\d+)").matcher(response.body());
                if (matcher.find()) {
                    newsTitle = news(Long.parseLong(matcher.group().substring(BEGIN_PARENT_INDEX)));
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return newsTitle;
    }
}
