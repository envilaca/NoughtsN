package noughtsn;

import java.util.*;

public class Computer {

    int choose() {
        return free.toArray(new Integer[0])[new Random().nextInt(free.size())];
    }

    public static Set<Integer> free = new HashSet<>(Set.of(0, 1, 2, 3, 4, 5, 6, 7, 8));

}
