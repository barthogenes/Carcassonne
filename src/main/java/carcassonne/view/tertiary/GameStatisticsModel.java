package carcassonne.view.tertiary;

import javax.swing.table.AbstractTableModel;

import carcassonne.model.Round;
import carcassonne.model.terrain.TerrainType;
import carcassonne.view.util.GameMessage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Model class for the game statistics view. Acts as an adapter that offers the data of the round to the game statistics
 * view.
 * @author Timur Saglam
 */
public class GameStatisticsModel extends AbstractTableModel {

    private static final long serialVersionUID = -7138458001360243937L; // generated UID
    private final Round round;
    private static final String[] HEADER = {"Player", "Castle", "Road", "Monastery", "Field", "SCORE"};

    /**
     * Creates the game statistics model with the current round.
     * @param round is the current round.
     */
    public GameStatisticsModel(Round round) {
        super();
        this.round = round;
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public String getColumnName(int column) {
        return HEADER[column];
    }

    @Override
    public int getRowCount() {
        return round.getPlayerCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return round.getPlayer(rowIndex).getName();
        } else if (columnIndex == 5) {
            return round.getPlayer(rowIndex).getScore();
        } else {
            return round.getPlayer(rowIndex).getTerrainScore(TerrainType.values()[columnIndex - 1]);
        }
    }

    /**
     * Writes the game statistics to a csv file.
     * Uses a tab as separator.
     * @param file The file to write to.
     */
    public void exportToCsv(File file) {
        exportToCsv(file, '\t');
    }


    /**
     * Writes the game statistics to a csv file.
     * @param file The file to write to.
     * @param separator The separator to use.
     */
    public void exportToCsv(File file, Character separator) {
        try (FileWriter fw = new FileWriter(file)) {
            for (int i = 0; i < this.getColumnCount(); i++) {
                fw.write(this.getColumnName(i) + separator);
            }
            fw.write("\n");
            for (int i = 0; i < this.getRowCount(); i++) {
                for (int j = 0; j < this.getColumnCount(); j++) {
                    fw.write(this.getValueAt(i, j).toString() + separator);
                }
                fw.write("\n");
            }
            fw.close();
        } catch (IOException exception) {
            GameMessage.showError("Failed to export game statistics!" + exception.getCause().getMessage());
        }
    }
}
