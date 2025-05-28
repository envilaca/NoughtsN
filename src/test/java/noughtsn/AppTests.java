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
    public void boardPreview() {
        NoughtsN crosses = new NoughtsN("");
        crosses.run();
        Assertions.assertEquals("""
                       \s
                            │    │  \s
                        ────┼────┼────
                            │    │  \s
                        ────┼────┼────
                            │    │  \s
                       \s""", readableOut.toString());
    }

    @AfterAll
    public static void afterAll() {
        System.setOut(originalOut);
    }

    private static PrintStream originalOut;
    private static ByteArrayOutputStream readableOut;

}
