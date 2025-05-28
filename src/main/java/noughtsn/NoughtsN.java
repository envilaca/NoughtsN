package noughtsn;

import java.io.InputStream;
import java.util.Scanner;

public class NoughtsN implements Runnable {

    private final Grid grid;
    private static final Computer COMPUTER = new Computer();

    @Override
    public void run() {
        String input;

        System.out.print(grid);
        while (!(input = scanner.nextLine()).equals("exit")) {
            grid.play(Integer.parseInt(input) - 1);
            System.out.print(grid);
        }
        scanner.close();
    }

    public NoughtsN(InputStream input) {
        scanner = new Scanner(input);
        grid = new Grid();
    }

    public NoughtsN(String input) {
        scanner = new Scanner(input);
        grid = new Grid();
    }

    private final Scanner scanner;

}
