package edu.hw10.Task1Test;

import edu.hw10.Task1.Min;
import edu.hw10.Task1.NotNull;

public record MyRecord(@Min(1000) int x, @NotNull String string, @NotNull Boolean is) {
}
