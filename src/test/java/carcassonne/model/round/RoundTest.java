package carcassonne.model.round;

import carcassonne.model.Round;
import carcassonne.model.grid.Grid;
import carcassonne.model.tile.TileStack;
import carcassonne.settings.GameSettings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RoundTest {

    @Mock
    private TileStack tileStack;

    @Mock
    private Grid grid;

    @Mock
    private GameSettings gameSettings;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void cannotCreateRoundWithMoreThan5Players() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Round(6, tileStack, grid, gameSettings);
        });
        assertEquals("6 is not a valid players count", exception.getMessage());
    }

    @Test
    public void cannotCreateRoundWith1Player() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Round(1, tileStack, grid, gameSettings);
        });
        assertEquals("1 is not a valid players count", exception.getMessage());
    }

    @Test
    public void cannotCreateRoundWithNoPlayers() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Round(0, tileStack, grid, gameSettings);
        });
        assertEquals("0 is not a valid players count", exception.getMessage());
    }

    @Test
    public void cannotCreateRoundWithNegativePlayers() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Round(-1, tileStack, grid, gameSettings);
        });
        assertEquals("-1 is not a valid players count", exception.getMessage());
    }

    @Test
    public void canCreateRoundWith5Players() {
        // Arrange
        Round round = new Round(5, tileStack, grid, gameSettings);

        // Act, Assert
        assertEquals(5, round.getPlayerCount());
    }
}