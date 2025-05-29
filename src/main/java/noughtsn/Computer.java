package noughtsn;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Computer {

    int choose() {
        int[] sq = new int[]{free.toArray(new Integer[0])[new Random().nextInt(free.size())]};
        for (int i : trimmedHist()) {
            if      (grid.dexter(i) && grid.sameIn(sq = grid.dig(Grid.DEX)) == 2) break;
            else if (grid.sinstr(i) && grid.sameIn(sq = grid.dig(Grid.SIN)) == 2) break;
            else if (grid.sameIn(sq = grid.col(i % Grid.CLS)) == 2) break;
            else if (grid.sameIn(sq = grid.row(i / Grid.RWS)) == 2) break;
        }
        return getFree(sq);
    }

    private int getFree(int[] squares) {
        for (int i : squares) if (free.contains(i)) return i;
        throw new IllegalArgumentException();
    }

    // Get an array of the last chosen move and last player move (winning > not losing). 🦎🌳
    private int[] trimmedHist() {
        List<Integer> hist = grid.getHistory();
        return hist.size() < 2 ? new int[0] : new int[]{hist.get(hist.size() - 2), hist.getLast()};
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
        free = grid.getFree();
    }

    private Grid grid;
    private Set<Integer> free;

}
