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
        grid[index] = (byte) (turn++ % 2 == 0 ? -1 : +1);
        history.add(index);
        free.remove(index);

        return turn == 9
                || dexter(index) && sameIn(dig(DEX)) == 3
                || sinstr(index) && sameIn(dig(SIN)) == 3
                || sameIn(col(index % CLS)) == 3
                || sameIn(row(index / RWS)) == 3;
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

    public byte sameIn(int[] squares) {
        return (byte) abs(grid[squares[0]] + grid[squares[1]] + grid[squares[2]]);
    }

    public boolean sinstr(int index) {
        return index - (CLS - 1) - (index / RWS) * (CLS - 1) == 0;
    }

    public boolean dexter(int index) {
        return index % (RWS * CLS / 2) == 0;
    }

    public int[] row(int row) {
        return new int[]{row * RWS, row * RWS + 1, row * RWS + 2};
    }

    public int[] col(int col) {
        return new int[]{col,       CLS + col,     CLS * 2 + col};
    }

    public int[] dig(boolean dig) {
        return dig ? new int[]{0, 4, 8} : new int[]{2, 4, 6};
    }


    public Grid() {
        grid = new byte[RWS * CLS];
        history = new ArrayList<>();
        free = new HashSet<>(Set.of(0, 1, 2, 3, 4, 5, 6, 7, 8));
    }

    public int getTurn() {
        return turn;
    }

    private int turn;
    private final byte[] grid; // Initialised with 0s.

    public List<Integer> getHistory() {
        return history;
    }

    private final List<Integer> history;

    public Set<Integer> getFree() {
        return free;
    }

    private final Set<Integer> free;

}
