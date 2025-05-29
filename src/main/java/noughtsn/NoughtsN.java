package noughtsn;

import java.io.InputStream;
import java.util.Scanner;

public class NoughtsN implements Runnable {

    private final Grid grid;
    private final Computer computer;

    @Override
    public void run() {
        String input = "";

        do {
            System.out.println(grid);
            if (grid.getTurn() % 2 == 0) if ((input = scanner.nextLine()).equals("exit")) break;
        } while (!grid.play(grid.getTurn() % 2 == 0 ? Integer.parseInt(input) - 1 : computer.choose()));
        System.out.println(grid.getTurn() == 9 ? "Draw." : "You " + (grid.getTurn() % 2 == 0 ? "lose" : "win") + "!");

        scanner.close();
    }

    public NoughtsN(InputStream input) {
        scanner = new Scanner(input);
        grid = new Grid();
        computer = new Computer();
        computer.setGrid(grid);
    }

    public NoughtsN(String input) {
        scanner = new Scanner(input);
        grid = new Grid();
        computer = new Computer();
        computer.setGrid(grid);
    }

    public NoughtsN(String input, Computer computer) {
        scanner = new Scanner(input);
        grid = new Grid();
        this.computer = computer;
        this.computer.setGrid(grid);
    }

    private final Scanner scanner;

}
