import java.awt.*;
import java.util.ArrayList;

public class Hex {
    public int row;
    public int col;
    public boolean validHex;
    public ArrayList<Hex> adjacentHexes;
    public ArrayList<Hex> surroundingHexes;
    public ArrayList<OverlayTile> overlayTiles;
    public Figure figure;

    public Hex(int x, int y) {
        row = x;
        col = y;
        adjacentHexes = new ArrayList<>();
        surroundingHexes = new ArrayList<>();
        overlayTiles = new ArrayList<>();
        validHex = false;
        figure = null;
    }

    public void getSurroundingHexes(ScenarioGrid grid) {
        int even = 0;
        if (row % 2 == 0) {
            even = -1;
        }
        addHex(grid, row - 1, col + even);
        addHex(grid, row - 1, col + 1 + even);
        addHex(grid, row, col - 1);
        addHex(grid, row, col + 1);
        addHex(grid, row + 1, col + even);
        addHex(grid, row + 1, col + 1 + even);
    }

    public Point getPosition() {
        int even = 0;
        if (row % 2 == 0) {
            even = 1;
        }
        return new Point(col * 100 + 75 - 50 * even, 38 + 87 * row);
    }

    public Point getPositionLOS() {
        int even = 0;
        if (row % 2 == 0) {
            even = 1;
        }
        return new Point(col * 104 + 71 - 52 * even, 35 + 90 * row);
    }

    private void addHex(ScenarioGrid grid, int rowNum, int colNum) {
        if (rowNum >= 0 && rowNum < grid.hexes.size()) {
            if (colNum >= 0 && colNum < grid.hexes.getFirst().size()) {
                surroundingHexes.add(grid.hexes.get(rowNum).get(colNum));
            }
        }
    }

    public boolean isFeatureless() {
        return overlayTiles.isEmpty();
    }

    public boolean isUnoccupied() {
        return figure == null;
    }

    public boolean isEmpty() {
        return isFeatureless() && isUnoccupied();
    }

    @Override
    public String toString() {
        return row + ", " + col;
    }
}