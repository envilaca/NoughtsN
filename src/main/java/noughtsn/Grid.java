package noughtsn;

public class Grid {

    boolean play(int index) {
        if (grid[index] != '\0') throw new IllegalArgumentException("Square already played.");
        grid[index] = turn ? 'O' : 'X';
        turn = !turn;
        return index == 4 && hasThreeInADig(0)
            || index == 4 && hasThreeInADig(2)
            || index % 2 == 0 && hasThreeInADig(index)
            || hasThreeInACol(index % 3)
            || hasThreeInARow(index / 3);
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
                toTwo(grid[row * 3]) +
                " │ " +
                toTwo(grid[row * 3 + 1]) +
                " │ " +
                toTwo(grid[row * 3 + 2]) +
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
        return grid[row * 3] == grid[row * 3 + 1] && grid[row * 3 + 1] == grid[row * 3 + 2];
    }

    private boolean hasThreeInACol(int col) {
        return grid[col] == grid[3 + col] && grid[3 + col] == grid[6 + col];
    }

    private boolean hasThreeInADig(int dig) {
        return grid[dig] == grid[4] && grid[4] == grid[8 - dig];
    }

    public Grid() {
        grid = new char[9];
    }

    char[] grid;
    boolean turn;

}
