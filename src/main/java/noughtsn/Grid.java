package noughtsn;

public class Grid {

    protected static int RWS = 3; // No. of rows.
    protected static int CLS = 3; // No. of columns.

    boolean play(int index) {
        if (grid[index] != '\0') throw new IllegalArgumentException("Square already played.");
        grid[index] = turn ? 'O' : 'X'; turn = !turn; Computer.free.remove(index);
        return hasThreeInADig(index)
            || hasThreeInACol(index % RWS)
            || hasThreeInARow(index / CLS);
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

    // Converts chars from grid storage to a string of length two.
    private String toTwo(char symb) {
        return switch (symb) {
            case '\0' -> "  ";
            case 'X'  -> "⟩⟨";
            case 'O'  -> "()";
            default -> throw new IllegalStateException("Unexpected value: " + symb);
        };
    }

    private boolean hasThreeInARow(int row) {
        return grid[row * RWS] == grid[row * RWS + 1] && grid[row * RWS + 1] == grid[row * RWS + 2];
    }

    private boolean hasThreeInACol(int col) {
        return grid[col] == grid[CLS + col] && grid[CLS + col] == grid[CLS * 2 + col];
    }

    private boolean hasThreeInADig(int ind) {
        return ind % (RWS * CLS / 2) == 0                     && grid[0] == grid[4] && grid[4] == grid[8]
            || ind - (CLS - 1) - (ind / RWS) * (CLS - 1) == 0 && grid[2] == grid[4] && grid[4] == grid[6];
    }

    protected void clear() {
        for (int i = 0; i < RWS * CLS; i++) grid[i] = '\0';
    }

    public Grid() {
        grid = new char[RWS * CLS];
    }

    char[] grid;
    boolean turn; // X or O.

}
