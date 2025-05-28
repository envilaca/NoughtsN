package noughtsn;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AppTests {

    @BeforeEach
    public void beforeEach() {
        readableOut = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(readableOut));
    }

    @Test
    public void gridPreview() {
        NoughtsN crosses = new NoughtsN("exit\n");
        crosses.run();

        Assertions.assertEquals(emptyGrid, readableOut.toString());
    }

    @Test
    public void gridEntry() {
        NoughtsN crosses = new NoughtsN("1\nexit\n");
        crosses.run();

        Assertions.assertEquals(emptyGrid + topLeft, readableOut.toString());
    }

    @AfterEach
    public void afterEach() {
        System.setOut(originalOut);
    }

    private PrintStream originalOut;
    private ByteArrayOutputStream readableOut;

    private static final String emptyGrid = """
            
                │    │  \s
            ────┼────┼────
                │    │  \s
            ────┼────┼────
                │    │  \s
            """;
    private static final String topLeft = """
            
             ⟩⟨ │    │  \s
            ────┼────┼────
                │    │  \s
            ────┼────┼────
                │    │  \s
            """;

}
