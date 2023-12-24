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

@SuppressWarnings({"uncommentedmain", "NestedTryDepth"})
public class Task4 {
    private Task4() {
    }

    public static void main(String[] args) throws IOException {
        try (var fos = Files.newOutputStream(Path.of("src/main/java/edu/hw6/Task4/Brian Kernighan.txt"));
             var cos = new CheckedOutputStream(fos, new Adler32());
             var bos = new BufferedOutputStream(cos);
             var osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8);
             var printWriter = new PrintWriter(osw)) {
            printWriter.write("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
