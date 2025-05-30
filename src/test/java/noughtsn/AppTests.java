package noughtsn;

import noughtsn.grid.Grid;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AppTests {

    @BeforeEach
    public void beforeEach() {
        readableOut = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(readableOut));

        grid = new Grid();
    }

    @Test
    public void gridPreview() {
        Game game = new Game("exit\n", new Computer());
        game.run();

        Assertions.assertEquals(grid.toString(), lastGrid(readableOut));
    }

    @Test
    public void gridEntry() {
        Computer computer = new TestComputer("2\n");

        Game game = new Game("1\nexit\n", computer);
        game.run();

        grid.play(0);
        grid.play(1);

        Assertions.assertEquals(grid.toString(), lastGrid(readableOut));
    }

    @Test
    public void playerCanWin() {
        Computer computer = new TestComputer("4\n5\n");

        Game game = new Game("1\n2\n3\n", computer);
        game.run();

        Assertions.assertEquals("You win!", (lastLine(readableOut)).trim());
    }

    @Test
    public void playerCanLose() {
        Computer computer = new TestComputer("3\n5\n7\n");

        Game game = new Game("1\n6\n8\n", computer);
        game.run();

        Assertions.assertEquals("You lose!", (lastLine(readableOut)).trim());
    }

    @Test
    public void playerCanDraw() {
        Computer computer = new TestComputer("4\n5\n3\n8\n");

        Game game = new Game("1\n9\n6\n7\n2\n", computer);
        game.run();

        Assertions.assertEquals("Draw.", (lastLine(readableOut)).trim());
    }

    @AfterEach
    public void afterEach() {
        System.setOut(originalOut);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private String lastGrid(ByteArrayOutputStream output) {
        String outStr = output.toString();

        int i = outStr.length() - 1;
        for (; i >= 0 && outStr.charAt(i) != '│' ; i--);
        int endex = i + 5;

        for (int j = 0; i >= 0 && j < 5 ; i--) if (outStr.charAt(i) == '\n') j++;

        return outStr.substring(i + 1, endex);
    }

    private String lastLine(ByteArrayOutputStream output) {
        String outStr = output.toString();

        int i = outStr.length() - 2;
        while (i >= 0 && outStr.charAt(i) != '\n') i--;

        return outStr.substring(i + 1);
    }

    private PrintStream originalOut;
    private ByteArrayOutputStream readableOut;
    private Grid grid;

}
