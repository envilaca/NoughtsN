package noughtsn;

import java.util.*;

import static noughtsn.Grid.CLS;
import static noughtsn.Grid.RWS;

public class Computer {

    @SuppressWarnings("StatementWithEmptyBody")
    int choose() {
        int last;
        if (grid.getHistory().isEmpty()) last = 1;
        else last = grid.getHistory().getLast();

        int[] sq;
        if      (grid.dexter(last) && grid.sameIn(sq = grid.dig(Grid.DEX)) == 2);
        else if (grid.sinstr(last) && grid.sameIn(sq = grid.dig(Grid.SIN)) == 2);
        else if (grid.sameIn(sq = grid.col(last % CLS)) == 2);
        else if (grid.sameIn(sq = grid.row(last / RWS)) == 2);
        else return free.toArray(new Integer[0])[new Random().nextInt(free.size())];
        return getFree(sq);
    }

    int getFree(int[] squares) {
        for (int i : squares) if (free.contains(i)) return i;
        throw new IllegalArgumentException();
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
        free = grid.getFree();
    }

    private Grid grid;
    private Set<Integer> free;

}
