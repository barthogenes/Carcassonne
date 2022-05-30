package carcassonne.model.tile.tilestack;

import carcassonne.model.grid.Grid;
import carcassonne.model.grid.GridSpot;
import carcassonne.model.tile.Tile;
import carcassonne.model.tile.TileDistribution;
import carcassonne.model.tile.TileStack;
import carcassonne.model.tile.TileType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultTileDistributionTest {

    @Mock
    private TileDistribution tileDistribution;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Simuleer een gevulde standaard TileStack.
        when(tileDistribution.getQuantity(any(TileType.class))).thenReturn(1);
    }

    @Test
    public void initialSizeAndActualSizeShouldBeEqualOnInitialization() {
        // Arrange
        TileStack tileStack = new TileStack(tileDistribution, 1);

        // Act, Assert
        assertEquals(tileStack.getInitialSize(), tileStack.getSize());
    }

    @Test
    public void drawTileShouldReturnRandomTiles() {
        // Arrange
        TileStack tileStack1 = new TileStack(tileDistribution, 1);
        TileStack tileStack2 = new TileStack(tileDistribution, 1);

        // Act
        List<Tile> tiles1 = new ArrayList<>();
        for (int i = 0; i < tileStack1.getInitialSize(); i++) {
            tiles1.add(tileStack1.drawTile());
        }
        List<Tile> tiles2 = new ArrayList<>();
        for (int i = 0; i < tileStack2.getInitialSize(); i++) {
            tiles2.add(tileStack2.drawTile());
        }

        // Assert
        assertNotEquals(tiles1.toString(), tiles2.toString());
    }

    @Test
    public void shouldUseProvidedSeedForShufflingStack() {
        // Arrange
        TileStack tileStack1 = new TileStack(tileDistribution, 1, (long) 1234567890);
        TileStack tileStack2 = new TileStack(tileDistribution, 1, (long) 1234567890);

        // Act
        List<Tile> tiles1 = new ArrayList<>();
        for (int i = 0; i < tileStack1.getInitialSize(); i++) {
            tiles1.add(tileStack1.drawTile());
        }
        List<Tile> tiles2 = new ArrayList<>();
        for (int i = 0; i < tileStack2.getInitialSize(); i++) {
            tiles2.add(tileStack2.drawTile());
        }

        // Assert
        assertEquals(tiles1.toString(), tiles2.toString());
    }

    @Test
    public void isEmptyShouldBeFalse() {
        // Arrange
        TileStack tileStack = new TileStack(tileDistribution, 1);

        // Act, Assert
        assertFalse(tileStack.isEmpty());
    }

    @Test
    public void putBackShouldPutTileAtTheBottomOfTheStack() {
        // Arrange
        TileStack tileStack = new TileStack(tileDistribution, 1);

        // Act
        Tile tileThatWasPutBack = tileStack.drawTile();
        tileStack.putBack(tileThatWasPutBack);
        for (int i = 0; i < tileStack.getInitialSize() - 1; i++) {
            tileStack.drawTile();
        }
        Tile tileAtBottom = tileStack.drawTile();

        // Assert
        assertEquals(tileThatWasPutBack, tileAtBottom);
    }

    @Test
    public void cannotPutBackTheSameTileTwice() {
        // Arrange
        TileStack tileStack = new TileStack(tileDistribution, 1);

        // Act
        Tile tile = tileStack.drawTile();
        tileStack.putBack(tile);
        tileStack.putBack(tile);

        // Assert
        assertEquals(tileStack.getInitialSize(), tileStack.getSize());
    }

    @Test
    public void cannotPutBackTileThatWasAlreadyPlaced() {
        // Arrange
        TileStack tileStack = new TileStack(tileDistribution, 1);
        Tile tile = tileStack.drawTile();
        tile.setPosition(new GridSpot(new Grid(5, 5, false), 1, 1));

        // Act, Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tileStack.putBack(tile);
        });
        assertEquals("Cannot return a placed tile!", exception.getMessage());
    }
}