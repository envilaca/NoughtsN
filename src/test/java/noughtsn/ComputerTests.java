package noughtsn;

import noughtsn.grid.Grid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComputerTests {

    @BeforeEach
    public void beforeEach() {
        grid = new Grid();
        computer.setGrid(grid);
    }

    @Test
    public void computerCanPlay() {
        Assertions.assertDoesNotThrow(() -> grid.play(computer.choose()));
    }

    @Test
    public void computerPlaysFree() {
        grid.play(0);
        grid.play(4);
        grid.play(8);
        grid.play(7);
        grid.play(1);
        grid.play(2);
        grid.play(6);
        grid.play(3);

        Assertions.assertEquals(5, computer.choose());
    }

    @Test
    public void computerBlocksWins() {
        grid.play(0);
        grid.play(4);
        grid.play(1);

        Assertions.assertEquals(2, computer.choose());
    }

    @Test
    public void computerTriesWins() {
        grid.play(0);
        grid.play(3);
        grid.play(1);
        grid.play(6);

        Assertions.assertEquals(2, computer.choose());
    }

    @Test
    public void computerPrioritisesWins() {
        grid.play(0);
        grid.play(1);
        grid.play(8);
        grid.play(4);
        grid.play(6);

        Assertions.assertEquals(7, computer.choose());
    }

    @Test
    public void computerDoesNotOverwriteFreeMoves() {
        grid.play(6);
        grid.play(4);
        grid.play(0);
        grid.play(3);
        grid.play(5);

        Assertions.assertDoesNotThrow(computer::choose);
    }

    private final Computer computer = new Computer();
    private Grid grid = new Grid();

}
