package noughtsn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.abs;

public class Grid {

    protected static final boolean DEX = true;  // Diagonal from upper left to bottom right.
    protected static final boolean SIN = false; // Diagonal from upper right to bottom left.

    protected static final int RWS = 3; // No. of rows.
    protected static final int CLS = 3; // No. of columns.

    boolean play(int index) {
        if (grid[index] != 0) throw new IllegalArgumentException("Square already played.");
        grid[index] = (byte) (turn ? +1 : -1);
        turn = !turn;
        history.add(index);
        free.remove(index);

        return index - (CLS - 1) - (index / RWS) * (CLS - 1) == 0 && sameInADig(SIN) == 3
                || index % (RWS * CLS / 2) == 0 && sameInADig(DEX) == 3
                || sameInACol(index % CLS) == 3
                || sameInARow(index / RWS) == 3;
    }

    public String toString() {
        return getRow(0) +
                "────┼────┼────" +
                getRow(1) +
                "────┼────┼────" +
                getRow(2);
    }

    // Makes a row string.
    private String getRow(int row) {
        return "\n " +
                toTwo(grid[row * RWS]) +
                " │ " +
                toTwo(grid[row * RWS + 1]) +
                " │ " +
                toTwo(grid[row * RWS + 2]) +
                "\n";
    }

    // Converts bytes from grid storage to a string of length two.
    private String toTwo(byte symb) {
        return switch (symb) {
            case  0 -> "  ";
            case -1 -> "⟩⟨";
            case +1 -> "()";
            default -> throw new IllegalStateException("Unexpected value: " + symb);
        };
    }

    public byte sameInARow(int row) {
        return (byte) abs(grid[row * RWS] + grid[row * RWS + 1] + grid[row * RWS + 2]);
    }

    public byte sameInACol(int col) {
        return (byte) abs(grid[col] + grid[CLS + col] + grid[CLS * 2 + col]);
    }

    public byte sameInADig(boolean dig) {
        return (byte) (dig ? abs(grid[0] + grid[4] + grid[8]) : abs(grid[2] + grid[4] + grid[6]));
    }


    public Grid() {
        grid = new byte[RWS * CLS];
        history = new ArrayList<>();
        free = new HashSet<>(Set.of(0, 1, 2, 3, 4, 5, 6, 7, 8));
    }

    private final byte[] grid;  // Initialised with 0s.
    private boolean turn; // X or O.

    public List<Integer> getHistory() {
        return history;
    }

    private final List<Integer> history;

    public Set<Integer> getFree() {
        return free;
    }

    private final Set<Integer> free;

}
