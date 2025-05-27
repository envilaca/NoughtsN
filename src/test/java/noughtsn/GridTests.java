package noughtsn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        // Act
        grid.play(3);
        grid.play(0);
        grid.play(4);
        grid.play(1);

        // Assert
        Assertions.assertTrue(grid.play(5));
    }

    @Test
    void verticalWinWins() {
        // Act
        grid.play(1);
        grid.play(2);
        grid.play(4);
        grid.play(5);

        // Assert
        Assertions.assertTrue(grid.play(7));
    }

    @Test
    void diagonalWinWins() {
        // Act
        grid.play(2);
        grid.play(0);
        grid.play(4);
        grid.play(8);

        // Assert
        Assertions.assertTrue(grid.play(6));
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
        Assertions.assertEquals("""
            
             ⟩⟨ │ ⟩⟨ │ ()
            ────┼────┼────
             () │ () │ ⟩⟨
            ────┼────┼────
             ⟩⟨ │ () │ ⟩⟨
            """,
                grid.toString()
        );
    }

    Grid grid;

}

//  ⟩⟨ │ ⟩⟨ │ ()
// ────┼────┼────
//  () │ () │ ⟩⟨
// ────┼────┼────
//  ⟩⟨ │ () │ ⟩⟨