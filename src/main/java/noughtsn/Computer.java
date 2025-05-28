package noughtsn;

import java.util.*;

import static noughtsn.Grid.CLS;
import static noughtsn.Grid.RWS;

public class Computer {

    int choose() {
        Set<Integer> free = grid.getFree();

        try {
            int last = grid.getHistory().getLast();

            if (last - (CLS - 1) - (last / RWS) * (CLS - 1) == 0 && grid.sameInADig(Grid.SIN) == 2)
                for (int i = 2; i <  8; i += 2) if (free.contains(i)) return i;
            if (last % (RWS * CLS / 2) == 0 && grid.sameInADig(Grid.DEX) == 2)
                for (int i = 0; i < 12; i += 4) if (free.contains(i)) return i;
            if (grid.sameInACol(last % CLS) == 2)
                for (int i = last % CLS; i < RWS * CLS; i += CLS) if (free.contains(i))  return i;
            if (grid.sameInARow(last / RWS) == 2)
                for (int i = last / RWS * RWS; i < last / RWS * RWS + CLS; i++) if (free.contains(i)) return i;
        } catch (NoSuchElementException ignored) {
            // Computer is going first.
        }
        return grid.getFree().toArray(new Integer[0])[new Random().nextInt(free.size())];
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    private Grid grid;

}
