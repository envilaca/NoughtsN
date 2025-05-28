package noughtsn;

import static java.lang.Math.abs;

public class Grid {

    protected static int RWS = 3; // No. of rows.
    protected static int CLS = 3; // No. of columns.

    boolean play(int index) {
        if (grid[index] != 0) throw new IllegalArgumentException("Square already played.");
        grid[index] = (byte) (turn ? +1 : -1); turn = !turn; Computer.free.remove(index);
        return index % 2 == 0 && sameInADig() == 3
                || sameInACol(index % RWS)    == 3
                || sameInARow(index / CLS)    == 3;
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

    private byte sameInARow(int row) {
        return (byte) abs(grid[row * RWS] + grid[row * RWS + 1] + grid[row * RWS + 2]);
    }

    private byte sameInACol(int col) {
        return (byte) abs(grid[col]       + grid[CLS + col]     + grid[CLS * 2 + col]);
    }

    // I used to check here if the square being passed in was a diagonal, using the following:
    //                       index % (RWS * CLS / 2) == 0    for the dexter diagonal
    // index - (CLS - 1) - (index / RWS) * (CLS - 1) == 0    for the sinister diagonal
    // However, this is probably more intensive than just actually checking them every time, so I'm
    // not going to do that anymore.
    private byte sameInADig() {
        return (byte) Math.max(abs(grid[0] + grid[4] + grid[8]), abs(grid[2] + grid[4] + grid[6]));
    }

    protected void clear() {
        for (int i = 0; i < RWS * CLS; i++) grid[i] = '\0';
    }

    public Grid() {
        grid = new byte[RWS * CLS];
    }

    byte[] grid;
    boolean turn; // X or O.

}
