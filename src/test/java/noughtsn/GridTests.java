package noughtsn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static noughtsn.Grid.CLS;
import static noughtsn.Grid.RWS;
import static noughtsn.NoughtsN.GRID;

public class GridTests {

    @BeforeEach
    void beforeEach() {
        GRID.clear();
    }

    @Test
    void occupiedSquareUnwriteable() {
        // Act
        GRID.play(0);

        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> GRID.play(0));
    }

    @Test
    void boundsApply() {
        // Assert
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> GRID.play(9));
    }

    @Test
    void horizontalWinWins() {
        for (int i = 0; i < RWS * 3; i += RWS, GRID.clear()) {
            // Act
            GRID.play(i);
            GRID.play(i < RWS * 2 ? i + 3 : i - 3);
            GRID.play(i + 1);
            GRID.play(i < RWS * 2 ? i + 4 : i - 2);

            // Assert
            Assertions.assertTrue(GRID.play(i + 2));
        }
    }

    @Test
    void verticalWinWins() {
        for (int i = 0; i < CLS; i++, GRID.clear()) {
            // Act
            GRID.play(i);
            GRID.play(i < 2 ? i + 1 : i - 1);
            GRID.play(i + CLS);
            GRID.play(i < 2 ? i + CLS + 1 : i + CLS - 1);

            // Assert
            Assertions.assertTrue(GRID.play(i + CLS * 2));
        }

    }

    @Test
    void diagonalWinWins() {
        for (int i = 0; i != 4 && i < 10; i += 2, GRID.clear()) {
            // Act
            GRID.play(i);
            GRID.play(1);
            GRID.play((RWS * CLS) / 2);
            GRID.play(3);

            // Assert
            Assertions.assertTrue(GRID.play(RWS * CLS - 1 - i));
        }
        for (int i = 0; i != 4 && i < 10; i += 2, GRID.clear()) {
            // Act
            GRID.play(i);
            GRID.play(1);
            GRID.play(RWS * CLS - 1 - i);
            GRID.play(3);

            // Assert
            Assertions.assertTrue(GRID.play((RWS * CLS) / 2));
        }
    }

    @Test
    void thisDoesNotWin() {
        // Arrange
        GRID.play(0);
        GRID.play(1);
        GRID.play(2);
        GRID.play(3);

        // Assert
        Assertions.assertFalse(GRID.play(4));
    }

    @Test
    void gridLooksRight() {
        // Arrange
        GRID.play(0);
        GRID.play(4);
        GRID.play(8);
        GRID.play(7);
        GRID.play(1);
        GRID.play(2);
        GRID.play(6);
        GRID.play(3);
        GRID.play(5);

        // Assert
        Assertions.assertEquals("""
                        
                         ⟩⟨ │ ⟩⟨ │ ()
                        ────┼────┼────
                         () │ () │ ⟩⟨
                        ────┼────┼────
                         ⟩⟨ │ () │ ⟩⟨
                        """,
                GRID.toString()
        );
    }

}

//  ⟩⟨ │ ⟩⟨ │ ()
// ────┼────┼────
//  () │ () │ ⟩⟨
// ────┼────┼────
//  ⟩⟨ │ () │ ⟩⟨