package noughtsn;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AppTests {

    @BeforeAll
    public static void beforeAll() {
        readableOut = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(readableOut));
    }

    @Test
    public void gridPreview() {
        NoughtsN crosses = new NoughtsN("");
        crosses.run();

        Assertions.assertEquals(emptyGrid, readableOut.toString());
    }

    @Test
    public void gridEntry() {
        NoughtsN crosses = new NoughtsN("1\n");
        crosses.run();

        Assertions.assertEquals(topLeft.lines(), readableOut.toString().lines().skip(6));
    }

    @AfterAll
    public static void afterAll() {
        System.setOut(originalOut);
    }

    private static PrintStream originalOut;
    private static ByteArrayOutputStream readableOut;

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
