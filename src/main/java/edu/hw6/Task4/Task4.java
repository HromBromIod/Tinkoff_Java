package edu.hw6.Task4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class Task4 {
    private Task4() {
    }

    public static void main(String[] args) {
        try (
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(new CheckedOutputStream(
                Files.newOutputStream(Path.of("src/main/java/edu/hw6/Task4/Brian Kernighan.txt")),
                new Adler32()
            )), StandardCharsets.UTF_8))) {
            printWriter.write("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
}
