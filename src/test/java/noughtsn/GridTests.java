package noughtsn;

import noughtsn.grid.Grid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static noughtsn.grid.Grid.CLS;
import static noughtsn.grid.Grid.RWS;

public class GridTests {

    @BeforeEach
    void beforeEach() {
        grid = new Grid();
    }

    @Test
    void occupiedSquareUnwriteable() {
        // Act
        grid.play(0);

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> grid.play(0));
    }

    @Test
    void boundsApply() {
        // Assert
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> grid.play(9));
    }

    @Test
    void horizontalWinWins() {
        for (int i = 0; i < RWS * 3; i += RWS, grid = new Grid()) {
            // Act
            grid.play(i);
            grid.play(i < RWS * 2 ? i + 3 : i - 3);
            grid.play(i + 1);
            grid.play(i < RWS * 2 ? i + 4 : i - 2);

            // Assert
            Assertions.assertTrue(grid.play(i + 2));
        }
    }

    @Test
    void verticalWinWins() {
        for (int i = 0; i < CLS; i++, grid = new Grid()) {
            // Act
            grid.play(i);
            grid.play(i < 2 ? i + 1 : i - 1);
            grid.play(i + CLS);
            grid.play(i < 2 ? i + CLS + 1 : i + CLS - 1);

            // Assert
            Assertions.assertTrue(grid.play(i + CLS * 2));
        }

    }

    @Test
    void diagonalWinWins() {
        for (int i = 0; i != 4 && i < 10; i += 2, grid = new Grid()) {
            // Act
            grid.play(i);
            grid.play(1);
            grid.play((RWS * CLS) / 2);
            grid.play(3);

            // Assert
            Assertions.assertTrue(grid.play(RWS * CLS - 1 - i));
        }
        for (int i = 0; i != 4 && i < 10; i += 2, grid = new Grid()) {
            // Act
            grid.play(i);
            grid.play(1);
            grid.play(RWS * CLS - 1 - i);
            grid.play(3);

            // Assert
            Assertions.assertTrue(grid.play((RWS * CLS) / 2));
        }
    }

    @Test
    void thisDoesNotWin() {
        // Arrange
        grid.play(0);
        grid.play(1);
        grid.play(2);
        grid.play(3);

        // Assert
        Assertions.assertFalse(grid.play(4));
    }

    @Test
    void gridLooksRight() {
        // Arrange
        grid.play(0);
        grid.play(4);
        grid.play(8);
        grid.play(7);
        grid.play(1);
        grid.play(2);
        grid.play(6);
        grid.play(3);
        grid.play(5);

        // Assert
        Assertions.assertEquals(fullGrid, grid.toString()
        );
    }

    @Test
    public void slimGridWorks() {
        grid = new SlimGrid();

        Assertions.assertEquals(emptySlimGrid, grid.toString());
    }

    private Grid grid;

    private static final String fullGrid = """
            
             ⟩⟨ │ ⟩⟨ │ ()
            ────┼────┼────
             () │ () │ ⟩⟨
            ────┼────┼────
             ⟩⟨ │ () │ ⟩⟨
            """;
    private static final String emptySlimGrid = """
           
              │   │ \s
           ───┼───┼───
              │   │ \s
           ───┼───┼───
              │   │ \s
           """;

}