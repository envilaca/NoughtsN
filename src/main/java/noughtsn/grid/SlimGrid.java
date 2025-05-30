package noughtsn.grid;

public class SlimGrid extends Grid {

    @Override
    public String toString() {
        return getRow(0) +
                "───┼───┼───" +
                getRow(1) +
                "───┼───┼───" +
                getRow(2);
    }

    @Override
    protected String toSymb(byte b) {
        return switch (b) {
            case  0 -> " ";
            case -1 -> "X";
            case +1 -> "O";
            default -> throw new IllegalStateException("Unexpected value: " + b);
        };
    }

}
