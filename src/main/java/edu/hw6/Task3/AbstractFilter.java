package edu.hw6.Task3;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    default AbstractFilter and(AbstractFilter abstractFilter) {
        return (t) -> accept(t) && abstractFilter.accept(t);
    }
}
