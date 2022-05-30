package carcassonne.model.tile.tilestack;

import carcassonne.model.tile.Tile;
import carcassonne.model.tile.TileDistribution;
import carcassonne.model.tile.TileStack;
import carcassonne.model.tile.TileType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.describedAs;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmptyTileDistributionTest {

    @Mock
    private TileDistribution tileDistribution;

    private TileStack tileStack;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Simuleer dat de TileStack leeg is.
        when(tileDistribution.getQuantity(any(TileType.class))).thenReturn(0);
        this.tileStack = new TileStack(tileDistribution, 1);
    }

    @After
    public void tearDown() throws Exception {
        // Maakt niet uit wat er gebeurd,
        // de initiele grootte van een lege TileStack moet aan het einde van de tests altijd 0 zijn.
        assertThat(tileStack.getInitialSize(), describedAs("initialSize is changed somehow!", equalTo(0)));
    }

    @Test
    public void initialSizeAndActualSizeShouldBeEqualOnInitialization() {
        assertThat(tileStack.getInitialSize(), is(equalTo(tileStack.getSize())));
    }

    @Test
    public void drawTileShouldReturnNull() {
        // Act
        Tile tile = tileStack.drawTile();

        // Assert
        assertThat(tile, is(nullValue()));
    }

    @Test
    public void isEmptyShouldBeTrue() {
        assertThat(tileStack.isEmpty(), is(true));
    }

    @Test
    public void isEmptyShouldBeFalseWhenATileIsReturned() {
        // Act
        tileStack.putBack(new Tile(TileType.Road));

        // Assert
        assertThat(tileStack.isEmpty(), is(not(true)));
    }
}