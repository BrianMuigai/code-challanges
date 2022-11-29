package turing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        int x = 9, y = 10, z;
        z = ++x + y++;
        System.out.println("" + x + ", " + y + ", " + z);
        System.out.println(10 / 0);
    }
}
