import java.awt.*;

public class Tile2 extends MapTile {
    //keyhex: top left
    public Tile2(char letter, int row, int col, Scenario scenario, int rotation) {
        super(row, col, letter, scenario, rotation, new Point(-50, -87), new Point[]{new Point(0, 0),
                new Point(-278, 3), new Point(-429, -131), new Point(-300, -264), new Point(-203, -394), new Point(-52, -260)}, 25.99090318);
    }

    public void getHexes() {
        int even = (row % 2 == 0) ? -1 : 0;
        int[][] offsets = switch (rotation) {
            case 0 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2}, {0, 3},
                    {1, even + 1}, {1, even + 2}, {1, even + 3},
                    {2, 0}, {2, 1}, {2, 2}, {2, 3},
                    {3, even + 1}, {3, even + 2}, {3, even + 3}
            };
            case 60 -> new int[][]{
                    {0, 0},
                    {1, even - 1}, {1, even}, {1, even + 1},
                    {2, -2}, {2, -1}, {2, 0}, {2, 1},
                    {3, even - 1}, {3, even}, {3, even + 1}, {3, even + 2},
                    {4, -1}, {4, 0}
            };
            case 120 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2}, {0, -3},
                    {-1, even - 1}, {-1, even - 2},
                    {1, even}, {1, even - 1}, {1, even - 2}, {1, even - 3},
                    {2, -1}, {2, -2}, {2, -3},
                    {3, even - 1}
            };
            case 180 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2}, {0, -3},
                    {-1, even}, {-1, even - 1}, {-1, even - 2},
                    {-2, 0}, {-2, -1}, {-2, -2}, {-2, -3},
                    {-3, even}, {-3, even - 1}, {-3, even - 2}
            };
            case 240 -> new int[][]{
                    {0, 0},
                    {-1, even}, {-1, even + 1}, {-1, even + 2},
                    {-2, -1}, {-2, 0}, {-2, 1}, {-2, 2},
                    {-3, even - 1}, {-3, even}, {-3, even + 1}, {-3, even + 2},
                    {-4, 0}, {-4, 1}
            };
            case 300 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2}, {0, 3},
                    {1, even + 2}, {1, even + 3},
                    {-1, even + 1}, {-1, even + 2}, {-1, even + 3}, {-1, even + 4},
                    {-2, 1}, {-2, 2}, {-2, 3},
                    {-3, even + 2}
            };
            default -> new int[0][0];
        };

        for (int[] offset : offsets) {
            hexes.add(scenario.grid.hexes.get(row + offset[0]).get(col + offset[1]));
        }
    }

    @Override
    public String toString() {
        return "02-" + letter;
    }
}