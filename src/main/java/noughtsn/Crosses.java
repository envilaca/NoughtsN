package noughtsn;

import noughtsn.grid.Grid;
import noughtsn.grid.SlimGrid;

import java.io.InputStream;
import java.util.Scanner;

public class Crosses implements Runnable {

    private final Grid grid;
    private final Computer computer;

    @SuppressWarnings("AssignmentUsedAsCondition")
    @Override
    public void run() {
        String in = "";
        boolean turn; // true = X. false = O.

        do {
            System.out.println(grid);
            if (turn = grid.getTurn() % 2 == 0) if ((in = scanner.nextLine()).equals("exit")) break;
        } while (!grid.play(turn ? Integer.parseInt(in) - 1 : computer.choose()));
        System.out.println(grid.getTurn() == 9 ? "Draw." : "You " + (turn ? "win" : "lose") + "!");

        scanner.close();
    }

    public Crosses(String[] args, InputStream input) {
        scanner = new Scanner(input);
        if (args.length > 0 && args[0].equals("-slim")) grid = new SlimGrid();
        else grid = new Grid();
        computer = new Computer();
        computer.setGrid(grid);
    }

    public Crosses(String input, Computer computer) {
        scanner = new Scanner(input);
        grid = new Grid();
        this.computer = computer;
        this.computer.setGrid(grid);
    }

    private final Scanner scanner;

}
