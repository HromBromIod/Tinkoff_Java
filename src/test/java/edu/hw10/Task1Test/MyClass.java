package edu.hw10.Task1Test;

import edu.hw10.Task1.Max;
import edu.hw10.Task1.NotNull;

public class MyClass {

    public int x;
    public String string;
    public Boolean is;

    public MyClass create(@Max(1000) int x, @NotNull String string, @NotNull Boolean is) {
        this.x = x;
        this.string = string;
        this.is = is;
        return this;
    }
}
