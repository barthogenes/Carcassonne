package carcassonne.model.round;

import carcassonne.model.Player;
import carcassonne.model.Round;
import carcassonne.model.grid.Grid;
import carcassonne.model.terrain.TerrainType;
import carcassonne.model.tile.Tile;
import carcassonne.model.tile.TileStack;
import carcassonne.model.tile.TileType;
import carcassonne.settings.GameSettings;
import carcassonne.settings.PlayerColor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwoPlayerRoundTest {

    @Mock
    private TileStack tileStack;

    @Mock
    private Grid grid;

    @Mock
    private GameSettings gameSettings;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Simuleer een spel met twee spelers.
        // Speler 1 { Naam: Jan, Kleur: Rood }
        // Speler 2 { Naam: Piet, Kleur Blauw }
        when(gameSettings.getPlayerName(0)).thenReturn("Jan");
        when(gameSettings.getPlayerColor(0)).thenReturn(new PlayerColor(PlayerColor.RED));
        when(gameSettings.getPlayerName(1)).thenReturn("Piet");
        when(gameSettings.getPlayerColor(1)).thenReturn(new PlayerColor(PlayerColor.BLUE));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void hasHumanPlayersShouldReturnFalseIfEveryPlayerIsComputerControlled() {
        // Arrange
        when(gameSettings.isPlayerComputerControlled(anyInt())).thenReturn(true);
        Round round = new Round(2, tileStack, grid, gameSettings);

        // Act, Assert
        assertFalse(round.hasHumanPlayers());
    }

    @Test
    public void hasHumanPlayersShouldReturnTrueIfAtLeast1PlayerIsNotComputerControlled() {
        // Arrange
        when(gameSettings.isPlayerComputerControlled(anyInt())).thenReturn(false).thenReturn(true);
        Round round = new Round(2, tileStack, grid, gameSettings);

        // Act, Assert
        assertTrue(round.hasHumanPlayers());
    }

    @Test
    public void getPlayerShouldReturnCorrectPlayerAccordingToPlayerNumber() {
        // Arrange
        Round round = new Round(2, tileStack, grid, gameSettings);

        // Act
        Player player1 = round.getPlayer(0);
        Player player2 = round.getPlayer(1);

        // Assert
        assertEquals("Jan", player1.getName());
        assertEquals(PlayerColor.RED, player1.getColor());
        assertEquals("Piet", player2.getName());
        assertEquals(PlayerColor.BLUE, player2.getColor());
    }

    @Test
    public void nextTurnShouldPassTurnToNextPlayer() {
        // Arrange
        Round round = new Round(2, tileStack, grid, gameSettings);

        // Act
        Player startingPlayer = round.getActivePlayer();
        round.nextTurn();
        Player nextPlayer = round.getActivePlayer();

        // Assert
        assertEquals("Jan", startingPlayer.getName());
        assertEquals(PlayerColor.RED, startingPlayer.getColor());
        assertEquals("Piet", nextPlayer.getName());
        assertEquals(PlayerColor.BLUE, nextPlayer.getColor());
    }

    @Test
    public void winningPlayersShouldReturnMultipleWinningPlayersIfTheirScoresAreTheSame() {
        // Arrange
        Round round = new Round(2, tileStack, grid, gameSettings);

        // Act
        String winningPlayers = round.winningPlayers();

        // Assert
        assertEquals("Jan, Piet", winningPlayers);
    }

    @Test
    public void winningPlayersShouldReturnPlayerNameWithHighestScore() {
        // Arrange
        Round round = new Round(2, tileStack, grid, gameSettings);
        round.getPlayer(0).addPoints(4, TerrainType.ROAD);
        round.getPlayer(1).addPoints(5, TerrainType.ROAD);

        // Act, Assert
        assertEquals("Piet", round.winningPlayers());

        // Arrange
        round.getPlayer(0).addPoints(6, TerrainType.ROAD);
        round.getPlayer(1).addPoints(4, TerrainType.ROAD);

        // Act, Assert
        assertEquals("Jan", round.winningPlayers());
    }

    @Test
    public void roundEndsWhenGridIsFull() {
        // Arrange
        when(grid.isFull()).thenReturn(true);
        Round round = new Round(2, tileStack, grid, gameSettings);

        // Act, Assert
        assertTrue(round.isOver());
    }

    @Test
    public void roundEndsWhenTileStackIsEmptyAndAllPlayersHaveNoTiles() {
        // Arrange
        when(grid.isFull()).thenReturn(false);
        when(tileStack.isEmpty()).thenReturn(true);
        Round round = new Round(2, tileStack, grid, gameSettings);

        // Act, Assert
        assertTrue(round.isOver());
    }

    @Test
    public void roundContinuesWhenTileStackIsEmptyAndPlayer1Has1Tile() {
        // Arrange
        when(grid.isFull()).thenReturn(false);
        when(tileStack.isEmpty()).thenReturn(true);
        when(gameSettings.getTilesPerPlayer()).thenReturn(1);
        Round round = new Round(2, tileStack, grid, gameSettings);
        round.getPlayer(0).addTile(new Tile(TileType.Road));

        // Act, Assert
        assertFalse(round.isOver());
    }

    @Test
    public void roundContinuesWhenTileStackIsNotEmptyAndAllPlayersHaveNoTiles() {
        // Arrange
        when(grid.isFull()).thenReturn(false);
        when(tileStack.isEmpty()).thenReturn(false);
        Round round = new Round(2, tileStack, grid, gameSettings);
        round.getPlayer(0).addTile(new Tile(TileType.Road));

        // Act, Assert
        assertFalse(round.isOver());
    }
}