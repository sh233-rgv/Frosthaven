import java.awt.*;

public class Tile12 extends MapTile {
    //keyhex: Top left
    public Tile12(char letter, int row, int col, Scenario scenario, int rotation) {
        super(row, col, letter, scenario, rotation, new Point(-300, -87), new Point[]{new Point(0, 0),
                new Point(-299, -219), new Point(-449, -435), new Point(-300, -433), new Point(-74, -695), new Point(77, -477)}, 25.55366269);
    }

    @Override
    public void getHexes() {
        int even = (row % 2 == 0) ? -1 : 0;
        int[][] offsets = switch (rotation) {
            case 0 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2}, {0, 3},
                    {1, even}, {1, even + 1}, {1, even + 2}, {1, even + 3}, {1, even + 4},
                    {2, -1}, {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4},
                    {3, even - 1}, {3, even}, {3, even + 1}, {3, even + 3}, {3, even + 4}, {3, even + 5},
                    {4, -2}, {4, -1}, {4, 0}, {4, 3}, {4, 4}, {4, 5},
                    {5, even - 1}, {5, even}, {5, even + 4}, {5, even + 5}
            };
            case 60 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2}, {0, -3}, {0, -4},
                    {1, even + 1}, {1, even}, {1, even - 1}, {1, even - 2}, {1, even - 3}, {1, even - 4},
                    {2, 1}, {2, 0}, {2, -1}, {2, -2}, {2, -3}, {2, -4},
                    {3, even + 2}, {3, even + 1}, {3, even},
                    {4, 1}, {4, 0}, {4, -1},
                    {5, even + 1}, {5, even}, {5, even - 1},
                    {6, 0}, {6, -1}, {6, -2},
                    {7, even}, {7, even - 1}
            };
            case 120 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2},
                    {-1, even}, {-1, even - 1}, {-1, even - 2},
                    {-2, -1}, {-2, -2}, {-2, -3},
                    {-3, even - 1}, {-3, even - 2}, {-3, even - 3},
                    {-4, -2}, {-4, -3},
                    {1, even}, {1, even - 1}, {1, even - 2}, {1, even - 3}, {1, even - 4}, {1, even - 5},
                    {2, -1}, {2, -2}, {2, -3}, {2, -4}, {2, -5}, {2, -6},
                    {3, even - 1}, {3, even - 2}, {3, even - 3}, {3, even - 4}, {3, even - 5}
            };
            case 180 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2}, {0, -3},
                    {-1, even + 1}, {-1, even}, {-1, even - 1}, {-1, even - 2}, {-1, even - 3},
                    {-2, 1}, {-2, 0}, {-2, -1}, {-2, -2}, {-2, -3}, {-2, -4},
                    {-3, even + 2}, {-3, even + 1}, {-3, even}, {-3, even - 2}, {-3, even - 3}, {-3, even - 4},
                    {-4, 2}, {-4, 1}, {-4, 0}, {-4, -3}, {-4, -4}, {-4, -5},
                    {-5, even + 2}, {-5, even + 1}, {-5, even - 3}, {-5, even - 4}
            };
            case 240 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2}, {0, -3}, {0, -4},
                    {-1, even}, {-1, even + 1}, {-1, even + 2}, {-1, even + 3}, {-1, even + 4}, {-1, even + 5},
                    {-2, -1}, {-2, 0}, {-2, 1}, {-2, 2}, {-2, 3}, {-2, 4},
                    {-3, even - 1}, {-3, even}, {-3, even + 1},
                    {-4, -1}, {-4, 0}, {-4, 1},
                    {-5, even}, {-5, even + 1}, {-5, even + 2},
                    {-6, 0}, {-6, 1}, {-6, 2},
                    {-7, even + 1}, {-7, even + 2}
            };
            case 300 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2},
                    {1, even + 1}, {1, even + 2}, {1, even + 3},
                    {2, 1}, {2, 2}, {2, 3},
                    {3, even + 2}, {3, even + 3}, {3, even + 4},
                    {4, 2}, {4, 3},
                    {-1, even + 1}, {-1, even + 2}, {-1, even + 3}, {-1, even + 4}, {-1, even + 5}, {-1, even + 6},
                    {-2, 1}, {-2, 2}, {-2, 3}, {-2, 4}, {-2, 5}, {-2, 6},
                    {-3, even + 2}, {-3, even + 3}, {-3, even + 4}, {-3, even + 5}, {-3, even + 6}
            };
            default -> new int[0][0];
        };

        for (int[] offset : offsets) {
            hexes.add(scenario.grid.hexes.get(row + offset[0]).get(col + offset[1]));
        }
    }

    @Override
    public String toString() {
        return "12-" + letter;
    }
}