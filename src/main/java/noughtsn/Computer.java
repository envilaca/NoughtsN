package noughtsn;

import java.util.*;

public class Computer {

    int choose() {
        int[] sq = new int[]{free.toArray(new Integer[0])[new Random().nextInt(free.size())]};
        for (int i : trimmedHist()) {
            System.out.println("Doing this thing, i is " + i);
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

    // Get an array of the moves that need checking. 🦎🌳
    private int[] trimmedHist() {
        List<Integer> hist = grid.getHistory();

        int[] moves = new int[hist.size() / 2 + 1];
        if (hist.isEmpty()) moves = new int[0];
        else moves[0] = hist.getLast();
        for (int i = 1; i < moves.length; i++) moves[i] = hist.get(hist.size() - i * 2);
        return moves;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
        free = grid.getFree();
    }

    private Grid grid;
    private Set<Integer> free;

}
