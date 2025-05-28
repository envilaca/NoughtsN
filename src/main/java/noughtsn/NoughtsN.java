package noughtsn;

import java.io.InputStream;
import java.util.Scanner;

public class NoughtsN implements Runnable {

    private static final Grid grid = new Grid();
    private static final Computer COMPUTER = new Computer();

    @Override
    public void run() {
        System.out.print(grid);
        scanner.close();
    }

    public NoughtsN(InputStream input) {
        scanner = new Scanner(input);
    }

    public NoughtsN(String input) {
        scanner = new Scanner(input);
    }

    private final Scanner scanner;

}
