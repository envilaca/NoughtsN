package noughtsn;

import java.util.Arrays;

public class Help implements Runnable {

    public void run() {
        System.out.println("""
                
                NoughtsN is a simple implementation of noughts 'n' crosses. The opponent will
                attempt to win, but does not employ optimal strategy. To place a cross in a
                square, either enter its corresponding number as shown below:
                                                  1 | 2 | 3
                                                  4 | 5 | 6
                                                  7 | 8 | 9
                or its row (top, middle, bottom) then column (left, middle, right). MOBA-style
                abbreviations (mid, bot) and single letters are also allowed.
                """);
        System.out.println("""
                Examples:
                  3,  5,   4,     8,      1
                  tr, m m, mid l, botmid, top left
                """);
        System.out.println("""    
                Commands:
                  slim - makes the grid slimmer; may look more natural in some shells
                  help - displays this help message""");
    }

    public Help(String[] args) {
        this.args = Arrays.copyOfRange(args, 1, args.length);
    }

    String[] args;

}
