package noughtsn;

import java.util.Scanner;

public class TestComputer extends Computer {

    @Override
    int choose() {
        return Integer.parseInt(scanner.nextLine()) - 1;
    }

    public TestComputer(String input) {
        scanner = new Scanner(input);
    }

    private final Scanner scanner;

}
