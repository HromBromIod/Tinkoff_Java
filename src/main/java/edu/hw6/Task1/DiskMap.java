package edu.hw6.Task1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class DiskMap extends AbstractMap<String, String> {
    private final String mapPath;

    public DiskMap(String filePath) throws IOException {
        Path currentMap = Path.of(filePath);
        if (Files.isDirectory(currentMap)) {
            throw new IllegalArgumentException("Передан несуществующий файл или директория");
        }
        this.mapPath = filePath;
        if (!Files.exists(currentMap)) {
            Files.createFile(currentMap);
        }
        this.clear();
    }

    public void write(Set<Entry<String, String>> entrySet) {
        try (FileOutputStream output = new FileOutputStream(mapPath)) {
            for (Entry<String, String> entry : entrySet) {
                String entryString = entry.getKey() + ':' + entry.getValue();
                output.write(entryString.getBytes(), 0, entryString.getBytes().length);
                output.write('\n');
            }
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    public Path getMapPath() {
        return Path.of(mapPath);
    }

    public void clear() {
        write(new HashSet<>());
    }

    public String remove(String key) {
        Set<Entry<String, String>> entrySet = entrySet();
        String value = null;
        for (Entry<String, String> entry : entrySet) {
            if (entry.getKey().equals(key)) {
                value = entry.getValue();
                if (value != null) {
                    entrySet.remove(entry);
                    write(entrySet);
                    return value;
                }
            }
        }
        return value;
    }

    public String put(String key, String value) {
        Set<Entry<String, String>> entrySet = entrySet();
        AbstractMap.SimpleEntry<String, String> newEntry = new SimpleEntry<>(key, value);
        for (Entry<String, String> entry : entrySet) {
            if (entry.getKey().equals(key)) {
                entrySet.remove(entry);
                entrySet.add(newEntry);
            }
        }
        if (!entrySet.contains(newEntry)) {
            entrySet.add(newEntry);
        }
        write(entrySet);
        return value;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> entrySet = new HashSet<>();
        try (FileInputStream input = new FileInputStream(mapPath)) {
            byte[] buffer = new byte[256];
            int count;
            while ((count = input.read(buffer)) != -1) {
                String entryString = new String(buffer, count);
                SimpleEntry<String, String> entry = new SimpleEntry<>(entryString.substring(0, entryString.indexOf(':')), entryString.substring(entryString.indexOf(':') + 1, entryString.length() - 1));
                entrySet.add(entry);
            }
        } catch (IOException e) {
            entrySet = new HashSet<>();
        }
        return entrySet;
    }
}
