package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.Task5.HackerNews.news;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Test {
    private Task5Test() {
    }

    @Test
    @DisplayName("parent id title = kid id title")
    void testTitlesEquals() {
        String parentTitle = news(37570037);
        String kidTitle = news(37571340);
        assertEquals(parentTitle, kidTitle);
    }
}
