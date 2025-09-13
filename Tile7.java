import java.awt.*;

public class Tile7 extends MapTile {
    //keyhex: top left
    public Tile7(char letter, int row, int col, Scenario scenario, int rotation) {
        super(row, col, letter, scenario, rotation, new Point(-50, -87), new Point[]{new Point(0, 0),
                new Point(-200, 0), new Point(-549, -87), new Point(-700, -172), new Point(-400, -694), new Point(-49, -607)}, 25.70177905);
    }

    @Override
    public void getHexes() {
        int even = (row % 2 == 0) ? -1 : 0;

        int[][] offsets = switch (rotation) {
            case 0 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7},
                    {1, even + 1}, {1, even + 2}, {1, even + 3}, {1, even + 4}, {1, even + 5}, {1, even + 6}, {1, even + 7},
                    {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6}, {2, 7}
            };
            case 60 -> new int[][]{
                    {0, 0}, {1, even + 1}, {1, even}, {1, even - 1},
                    {2, 1}, {2, 0}, {2, -1},
                    {3, even + 2}, {3, even + 1}, {3, even},
                    {4, 2}, {4, 1}, {4, 0},
                    {5, even + 3}, {5, even + 2}, {5, even + 1},
                    {6, 3}, {6, 2}, {6, 1},
                    {7, even + 4}, {7, even + 3}, {7, even + 2},
                    {8, 2}
            };
            case 120 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2},
                    {-1, even - 1},
                    {1, even}, {1, even - 1}, {1, even - 2},
                    {2, -1}, {2, -2}, {2, -3},
                    {3, even - 1}, {3, even - 2}, {3, even - 3},
                    {4, -2}, {4, -3}, {4, -4},
                    {5, even - 2}, {5, even - 3}, {5, even - 4},
                    {6, -3}, {6, -4}, {6, -5},
                    {7, even - 3}
            };
            case 180 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2}, {0, -3}, {0, -4}, {0, -5}, {0, -6}, {0, -7},
                    {-1, even}, {-1, even - 1}, {-1, even - 2}, {-1, even - 3},
                    {-1, even - 4}, {-1, even - 5}, {-1, even - 6},
                    {-2, 0}, {-2, -1}, {-2, -2}, {-2, -3}, {-2, -4}, {-2, -5}, {-2, -6}, {-2, -7}
            };
            case 240 -> new int[][]{
                    {0, 0}, {-1, even}, {-1, even + 1}, {-1, even + 2},
                    {-2, -1}, {-2, 0}, {-2, 1},
                    {-3, even - 1}, {-3, even}, {-3, even + 1},
                    {-4, -2}, {-4, -1}, {-4, 0},
                    {-5, even - 2}, {-5, even - 1}, {-5, even},
                    {-6, -3}, {-6, -2}, {-6, -1},
                    {-7, even - 3}, {-7, even - 2}, {-7, even - 1},
                    {-8, -2}
            };
            case 300 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2},
                    {1, even + 2},
                    {-1, even + 1}, {-1, even + 2}, {-1, even + 3},
                    {-2, 1}, {-2, 2}, {-2, 3},
                    {-3, even + 2}, {-3, even + 3}, {-3, even + 4},
                    {-4, 2}, {-4, 3}, {-4, 4},
                    {-5, even + 3}, {-5, even + 4}, {-5, even + 5},
                    {-6, 3}, {-6, 4}, {-6, 5},
                    {-7, even + 4}
            };
            default -> new int[0][0];
        };

        for (int[] offset : offsets) {
            hexes.add(scenario.grid.hexes.get(row + offset[0]).get(col + offset[1]));
        }
    }

    @Override
    public String toString() {
        return "07-" + letter;
    }
}