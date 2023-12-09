package edu.hw6;

import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task1Test {
    private static final String MAP_PATH = "src/main/java/edu/hw6/Task1/Map.txt";

    private Task1Test() {
    }

    @BeforeEach
    @AfterEach
    void deleteMap() throws IOException {
        Files.deleteIfExists(Path.of(MAP_PATH));
    }

    @Test
    @DisplayName("right map path test")
    void rightMapPathTest() throws IOException {
        assertEquals(Path.of(MAP_PATH), new DiskMap(MAP_PATH).getMapPath());
    }

    @Test
    @DisplayName("put test")
    void putTest() throws IOException {
        DiskMap diskMap = new DiskMap(MAP_PATH);
        diskMap.put("1", "1");
        assertEquals("{1=1}", diskMap.toString());
    }

    @Test
    @DisplayName("overwrite test")
    void overwriteTest() throws IOException {
        DiskMap diskMap = new DiskMap(MAP_PATH);
        diskMap.put("1", "2");
        diskMap.put("1", "1");
        assertEquals("{1=1}", diskMap.toString());
    }

    @Test
    @DisplayName("remove from map test")
    void removeFromMapTest() throws IOException {
        DiskMap diskMap = new DiskMap(MAP_PATH);
        diskMap.put("2", "2");
        diskMap.put("1", "1");
        diskMap.remove("2");
        assertEquals("{1=1}", diskMap.toString());
    }

    @Test
    @DisplayName("remove from empty map test")
    void removeFromEmptyMapTest() throws IOException {
        DiskMap diskMap = new DiskMap(MAP_PATH);
        assertNull(diskMap.remove("1"));
    }
}
