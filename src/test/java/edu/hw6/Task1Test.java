package edu.hw6;

import edu.hw6.Task1.DiskMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {
    private Task1Test() {
    }

    private final static String DIRECTORY_PATH = "src/main/java/edu/hw6/Task1/NewDirectory";

    @BeforeEach
    @AfterEach
    void deleteIfExist() throws Exception {
        deleteDirectoryOrFile(new File(DIRECTORY_PATH));
    }

    private static void deleteDirectoryOrFile(File directory) throws Exception {
        if (!directory.exists()) {
            return;
        }
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    deleteDirectoryOrFile(file);
                }
            }
        }
        if (!directory.delete()) {
            throw new Exception("Не удалось удалить файл");
        }
    }

    @Test
    @DisplayName("Успешное создание директории")
    void successfulConstructor() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        assertThat(newDiskMap).isNotNull();
    }

    @Test
    @DisplayName("Директория уже существует")
    void existDirectory() throws IOException {
        Files.createDirectories(Path.of(DIRECTORY_PATH));
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        assertThat(newDiskMap).isNotNull();
    }

    @Test
    @DisplayName("По пути к директории муществует файл")
    void existFile() {
        try {
            Files.createFile(Path.of(DIRECTORY_PATH));
            assertThrows(
                IllegalArgumentException.class,
                () -> new DiskMap(DIRECTORY_PATH)
            );
        } catch (IOException e) {
            assertThat(e).isNull();
        }
    }

    @Test
    @DisplayName("Успешно добавляет файл")
    void successPut() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        newDiskMap.put("test", "test");
        Path test = Path.of(DIRECTORY_PATH, "test");
        assertTrue(Files.exists(test));
        assertThat(Files.readString(test)).isEqualTo("test");
    }

    @Test
    @DisplayName("Добавляемый файл уже существует")
    void putToExistingFile() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Path test = Path.of(DIRECTORY_PATH, "test");
        Files.createDirectories(test.getParent());
        Files.createFile(test);
        newDiskMap.put("test", "test");
        assertTrue(Files.exists(test));
        assertThat(Files.readString(test)).isEqualTo("test");
    }

    @Test
    @DisplayName("Удаление файла")
    void removeFile() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Path test = Path.of(DIRECTORY_PATH, "test");
        Files.createDirectories(test.getParent());
        Files.createFile(test);
        assertThat(Files.exists(test)).isTrue();
        newDiskMap.remove("test");
        assertThat(Files.exists(test)).isFalse();
    }

    @Test
    @DisplayName("Удаление несуществующего файла")
    void removeNonExistingFile() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Path test = Path.of(DIRECTORY_PATH, "test");
        assertThat(newDiskMap.remove("test")).isNull();
        assertThat(Files.exists(test)).isFalse();
    }

    @Test
    @DisplayName("Количество ключей")
    void getSize() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Files.createDirectories(Path.of(DIRECTORY_PATH));
        Files.createFile(Path.of(DIRECTORY_PATH, "first"));
        Files.createFile(Path.of(DIRECTORY_PATH, "second"));
        Files.createFile(Path.of(DIRECTORY_PATH, "third"));
        assertThat(newDiskMap.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Количество ключей при отсутствии файлов")
    void getZeroSize() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Files.createDirectories(Path.of(DIRECTORY_PATH));
        assertThat(newDiskMap.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Проверка на пустоту")
    void isEmpty() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Files.createDirectories(Path.of(DIRECTORY_PATH));
        assertThat(newDiskMap.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Проверка на отсутствие пустоты")
    void isNotEmpty() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Files.createDirectories(Path.of(DIRECTORY_PATH));
        Files.createFile(Path.of(DIRECTORY_PATH, "first"));
        Files.createFile(Path.of(DIRECTORY_PATH, "second"));
        Files.createFile(Path.of(DIRECTORY_PATH, "third"));
        assertThat(newDiskMap.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Ключ существует")
    void keyExist() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Files.createDirectories(Path.of(DIRECTORY_PATH));
        Files.createFile(Path.of(DIRECTORY_PATH, "test"));
        assertThat(newDiskMap.containsKey("test")).isTrue();
    }

    @Test
    @DisplayName("Ключ не существует")
    void keyNotExist() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Files.createDirectories(Path.of(DIRECTORY_PATH));
        assertThat(newDiskMap.containsKey("test")).isFalse();
    }

    @Test
    @DisplayName("Значение существует")
    void valueExist() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Path first = Path.of(DIRECTORY_PATH, "first");
        Path second = Path.of(DIRECTORY_PATH, "second");
        Files.createDirectories(Path.of(DIRECTORY_PATH));
        Files.createFile(first);
        Files.createFile(second);
        Files.writeString(first, "first");
        Files.writeString(second, "second");
        assertThat(newDiskMap.containsValue("first")).isTrue();
    }

    @Test
    @DisplayName("Значение не существует")
    void valueNotExist() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Files.createDirectories(Path.of(DIRECTORY_PATH));
        assertThat(newDiskMap.containsValue("test")).isFalse();
    }

    @Test
    @DisplayName("Получение значения по ключу")
    void getByKey() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Path first = Path.of(DIRECTORY_PATH, "first");
        Files.createDirectories(Path.of(DIRECTORY_PATH));
        Files.createFile(first);
        Files.writeString(first, "first");
        assertThat(newDiskMap.get("first")).isEqualTo("first");
    }

    @Test
    @DisplayName("Получение несуществующего значения по ключу")
    void getNull() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Files.createDirectories(Path.of(DIRECTORY_PATH));
        assertThat(newDiskMap.get("test")).isNull();
    }

    @Test
    @DisplayName("Очистка директории")
    void clear() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Files.createDirectories(Path.of(DIRECTORY_PATH));
        Files.createFile(Path.of(DIRECTORY_PATH, "first"));
        Files.createFile(Path.of(DIRECTORY_PATH, "second"));
        Files.createFile(Path.of(DIRECTORY_PATH, "third"));
        newDiskMap.clear();
        assertThat(Files.list(Path.of(DIRECTORY_PATH)).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Получение списка ключей")
    void getKeys() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Path first = Path.of(DIRECTORY_PATH, "first");
        Path second = Path.of(DIRECTORY_PATH, "second");
        Files.createDirectories(first.getParent());
        Files.createFile(first);
        Files.createFile(second);
        assertThat(newDiskMap.keySet()).isEqualTo(Set.of("first", "second"));
    }

    @Test
    @DisplayName("Получение списка значений")
    void getValues() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Path first = Path.of(DIRECTORY_PATH, "first");
        Path second = Path.of(DIRECTORY_PATH, "second");
        Files.createDirectories(first.getParent());
        Files.createFile(first);
        Files.createFile(second);
        Files.writeString(first, "first");
        Files.writeString(second, "second");
        assertThat(newDiskMap.values().stream().sorted().toList()).isEqualTo(List.of("first", "second"));
    }

    @Test
    @DisplayName("Получение \"сущностей\"")
    void getEntrySet() throws IOException {
        DiskMap newDiskMap = new DiskMap(DIRECTORY_PATH);
        Path first = Path.of(DIRECTORY_PATH, "first");
        Path second = Path.of(DIRECTORY_PATH, "second");
        Files.createDirectories(first.getParent());
        Files.createFile(first);
        Files.createFile(second);
        Files.writeString(first, "first");
        Files.writeString(second, "second");
        assertThat(newDiskMap.entrySet()).isEqualTo(Map.of(
            "first", "first",
            "second", "second"
        ).entrySet());
    }
}
