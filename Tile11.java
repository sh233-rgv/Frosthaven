import java.awt.*;

public class Tile11 extends MapTile {
    //keyhex: leftmost
    public Tile11(char letter, int row, int col, Scenario scenario, int rotation) {
        super(row, col, letter, scenario, rotation, new Point(-100, -174), new Point[]{new Point(0, 0),
                new Point(-400, 0), new Point(-751, -172), new Point(-702, -345), new Point(-450, -780), new Point(-100, -607)}, 26.37749121);
    }

    @Override
    public void getHexes() {
        int even = (row % 2 == 0) ? -1 : 0;

        int[][] offsets = switch (rotation) {
            case 0 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2},
                    {-1, even + 2},
                    {1, even + 1}, {1, even + 2}, {1, even + 3},
                    {2, 1}, {2, 2}, {2, 3},
                    {3, even + 2}, {3, even + 3}, {3, even + 4}, {3, even + 5}, {3, even + 6}, {3, even + 7}, {3, even + 8},
                    {4, 2}, {4, 3}, {4, 4}, {4, 5}, {4, 6}, {4, 7},
                    {5, even + 4}, {5, even + 5}, {5, even + 6}, {5, even + 7}, {5, even + 8}
            };
            case 60 -> new int[][]{
                    {0, 0},
                    {1, even}, {1, even + 1}, {1, even + 2},
                    {2, -1}, {2, 0}, {2, 1},
                    {3, even - 1}, {3, even}, {3, even + 1},
                    {4, -2}, {4, -1}, {4, 0},
                    {5, even - 1}, {5, even},
                    {6, -2}, {6, -1}, {6, 0},
                    {7, even - 1}, {7, even}, {7, even + 1},
                    {8, -1}, {8, 0}, {8, 1},
                    {9, even}, {9, even + 1}, {9, even + 2},
                    {10, 0}
            };
            case 120 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2}, {0, -3}, {0, -4},
                    {1, even}, {1, even - 1}, {1, even - 2}, {1, even - 3}, {1, even - 4}, {1, even - 5},
                    {2, 0}, {2, -1}, {2, -2}, {2, -3}, {2, -4}, {2, -5}, {2, -6},
                    {3, even - 4}, {3, even - 5}, {3, even - 6},
                    {4, -5}, {4, -6}, {4, -7},
                    {5, even - 5}, {5, even - 6}, {5, even - 7},
                    {6, -6}
            };
            case 180 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2},
                    {1, even - 1},
                    {-1, even}, {-1, even - 1}, {-1, even - 2},
                    {-2, -1}, {-2, -2}, {-2, -3},
                    {-3, even - 1}, {-3, even - 2}, {-3, even - 3}, {-3, even - 4}, {-3, even - 5}, {-3, even - 6}, {-3, even - 7},
                    {-4, -2}, {-4, -3}, {-4, -4}, {-4, -5}, {-4, -6}, {-4, -7},
                    {-5, even - 3}, {-5, even - 4}, {-5, even - 5}, {-5, even - 6}, {-5, even - 7}
            };
            case 240 -> new int[][]{
                    {0, 0},
                    {-1, even + 1}, {-1, even}, {-1, even - 1},
                    {-2, 1}, {-2, 0}, {-2, -1},
                    {-3, even + 2}, {-3, even + 1}, {-3, even},
                    {-4, 2}, {-4, 1}, {-4, 0},
                    {-5, even + 2}, {-5, even + 1},
                    {-6, 2}, {-6, 1}, {-6, 0},
                    {-7, even + 2}, {-7, even + 1}, {-7, even},
                    {-8, 1}, {-8, 0}, {-8, -1},
                    {-9, even + 1}, {-9, even}, {-9, even - 1},
                    {-10, 0}
            };
            case 300 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4},
                    {-1, even + 1}, {-1, even + 2}, {-1, even + 3}, {-1, even + 4}, {-1, even + 5}, {-1, even + 6},
                    {-2, 0}, {-2, 1}, {-2, 2}, {-2, 3}, {-2, 4}, {-2, 5}, {-2, 6},
                    {-3, even + 5}, {-3, even + 6}, {-3, even + 7},
                    {-4, 5}, {-4, 6}, {-4, 7},
                    {-5, even + 6}, {-5, even + 7}, {-5, even + 8},
                    {-6, 6}
            };
            default -> new int[0][0];
        };

        for (int[] offset : offsets) {
            hexes.add(scenario.grid.hexes.get(row + offset[0]).get(col + offset[1]));
        }
    }

    @Override
    public String toString() {
        return "11-" + letter;
    }
}