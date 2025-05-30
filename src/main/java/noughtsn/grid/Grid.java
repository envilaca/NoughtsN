package noughtsn.grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.abs;

public class Grid {

    public static final boolean DEX = true;  // Diagonal from upper left to bottom right.
    public static final boolean SIN = false; // Diagonal from upper right to bottom left.

    public static final int RWS = 3; // No. of rows.
    public static final int CLS = 3; // No. of columns.

    public boolean play(int index) {
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
    protected String getRow(int row) {
        return "\n " +
                toSymb(grid[row * CLS]) +
                " │ " +
                toSymb(grid[row * CLS + 1]) +
                " │ " +
                toSymb(grid[row * CLS + 2]) +
                "\n";
    }

    // Converts bytes from grid storage to a string.
    protected String toSymb(byte b) {
        return switch (b) {
            case  0 -> "  ";
            case -1 -> "⟩⟨";
            case +1 -> "()";
            default -> throw new IllegalStateException("Unexpected value: " + b);
        };
    }

    public byte sameIn(int[] squares) {
        return (byte) abs(grid[squares[0]] + grid[squares[1]] + grid[squares[2]]);
    }

    public boolean sinstr(int index) {
        return index - (CLS - 1) - (index / RWS) * (CLS - 1) == 0;
    }

    public boolean dexter(int index) {
        return index % (CLS + 1) == 0;
    }

    public int[] row(int row) {
        return new int[]{row * CLS, row * CLS + 1, row * CLS + 2};
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

    protected int turn;
    protected final byte[] grid; // Initialised with 0s.

    public List<Integer> getHistory() {
        return history;
    }

    protected final List<Integer> history;

    public Set<Integer> getFree() {
        return free;
    }

    protected final Set<Integer> free;

}
