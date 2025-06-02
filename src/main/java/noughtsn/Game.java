package noughtsn;

import noughtsn.grid.Grid;
import noughtsn.grid.SlimGrid;

import java.io.InputStream;
import java.util.Scanner;

public class Game implements Runnable {

    private final Grid grid;
    private final Computer computer;

    private static final int INVALID_INPUT = -1;

    @SuppressWarnings("AssignmentUsedAsCondition")
    @Override
    public void run() {
        boolean isWon = false, turn; // turn: true = X. false = O.

        do {
            System.out.print(grid);

            int index = 0;
            if (turn = grid.getTurn() % 2 == 0) {
                do {
                    System.out.print(index == INVALID_INPUT
                            ? "\nSorry, that is not a valid square. Please try again: "
                            : "\nYou play: ");
                    index = extractIndex(scanner.nextLine().toLowerCase().trim());
                } while (index == INVALID_INPUT);
                if (index == 9) break;
            } else index = computer.choose();
            try {
                isWon = grid.play(index);
            } catch (IllegalArgumentException ignored) {
                System.out.println("That square has already been played.");
            }
        } while (!isWon);
        System.out.println();
        System.out.println(grid.getTurn() == 9 ? "Draw." : "You " + (turn ? "win!" : "lose!"));

        scanner.close();
    }

    private int extractIndex(String input) {
        int index;
        try {
            index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < 9) return index;
        } catch (NumberFormatException ignored) {
            if (input.matches("^(t(op)?|m(id(dle)?)?|b(ot(tom)?)?) ?" +
                              "(l(eft)?|m(id(dle)?)?|r(ight)?)$")) {
                char[] sqs = input.toCharArray(); // Square selection.
                // Accounting for edge cases involving the ‘l’ in ‘middle’, or the ‘m’ in ‘bottom’.
                int i = 1;
                if      (sqs[0] == 'm' && sqs.length > 6 && sqs[4] == 'l' && sqs[3] == 'd') i = 5;
                else if (sqs[0] == 'b' && sqs.length > 6 && sqs[5] == 'm' && sqs[4] == 'o') i = 6;
                while (sqs[i] != 'r' && sqs[i] != 'm' && sqs[i] != 'l') i++;

                int row = sqs[0] == 't' ? 0 : sqs[0] == 'm' ? 1 : 2;
                int col = sqs[i] == 'l' ? 0 : sqs[i] == 'm' ? 1 : 2;

                return row * Grid.RWS + col;
            } else if (input.equals("exit")) {
                return 9;
            }
        }
        return -1;
    }

    public Game(String[] args, InputStream input) {
        scanner = new Scanner(input);
        if (args.length > 0 && args[0].equals("-slim")) grid = new SlimGrid();
        else grid = new Grid();
        computer = new Computer();
        computer.setGrid(grid);
    }

    public Game(String input, Computer computer) {
        scanner = new Scanner(input);
        grid = new Grid();
        this.computer = computer;
        this.computer.setGrid(grid);
    }

    private final Scanner scanner;

}
