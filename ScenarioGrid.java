import java.util.ArrayList;

public class ScenarioGrid {
    public ArrayList<ArrayList<Hex>> hexes;
    public ArrayList<Hex> validHexes;


    public ScenarioGrid() {
        hexes = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            hexes.add(new ArrayList<>());
            for (int j = 0; j < 25; j++) {
                hexes.get(i).add(new Hex(i, j));
            }
        }
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                hexes.get(i).get(j).getSurroundingHexes(this);
                hexes.get(i).get(j).adjacentHexes = hexes.get(i).get(j).surroundingHexes;
            }
        }
        validHexes = new ArrayList<>();
    }

    public boolean inGrid(int row, int col) {
        return row > 0 && row < hexes.size() && col > 0 && col < hexes.get(row).size();
    }
}