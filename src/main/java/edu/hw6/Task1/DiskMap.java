package edu.hw6.Task1;

import org.jetbrains.annotations.NotNull;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Set;

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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(mapPath))) {
            for (Entry<String, String> entry : entrySet) {
                oos.writeObject(entry);
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
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(mapPath))) {
            boolean isReadable = true;
            while (isReadable) {
                try {
                    AbstractMap.SimpleEntry<String, String> entry =
                        (AbstractMap.SimpleEntry<String, String>) inputStream.readObject();
                    entrySet.add(entry);
                } catch (EOFException | ClassNotFoundException e) {
                    isReadable = false;
                }
            }
        } catch (IOException e) {
            entrySet = new HashSet<>();
        }
        return entrySet;
    }
}
