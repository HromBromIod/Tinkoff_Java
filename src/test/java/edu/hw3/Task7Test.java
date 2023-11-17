package edu.hw3;

import edu.hw3.Task7.MyComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    private Task7Test() {
    }

    @Test
    @DisplayName("\"null\" test")
    void task7Test1() {
        TreeMap<String, String> tree = new TreeMap<>(new MyComparator());
        tree.put("a", "a");
        tree.put("b", "b");
        tree.put(null, "n");
        tree.put("c", "c");
        assertThat(tree.containsKey(null)).isTrue();
    }

    @Test
    @DisplayName("no \"null\" test")
    void task7Test2() {
        TreeMap<String, String> tree = new TreeMap<>(new MyComparator());
        tree.put("a", "a");
        tree.put("b", "b");
        tree.put("c", "c");
        tree.put("d", "d");
        assertThat(tree.containsKey(null)).isFalse();
    }
}
