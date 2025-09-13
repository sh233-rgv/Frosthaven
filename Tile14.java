import java.awt.*;

public class Tile14 extends MapTile {
    //keyhex: Topmost row left
    public Tile14(char letter, int row, int col, Scenario scenario, int rotation) {
        super(row, col, letter, scenario, rotation, new Point(-201, -87), new Point[]{new Point(0, 0),
                new Point(-575, -130), new Point(-624, -478), new Point(-100, -693), new Point(-24, -563), new Point(26, -217)}, 25.68053416);
    }

    @Override
    public void getHexes() {
        int even = (row % 2 == 0) ? -1 : 0;
        int[][] offsets = switch (rotation) {
            case 0 -> new int[][]{
                    {0, 0}, {0, 1},
                    {1, even}, {1, even + 1}, {1, even + 2},
                    {2, -1}, {2, 0}, {2, 1}, {2, 2},
                    {3, even - 1}, {3, even}, {3, even + 1}, {3, even + 2}, {3, even + 3},
                    {4, -1}, {4, 0}, {4, 1}, {4, 2},
                    {5, even - 1}, {5, even}, {5, even + 1}, {5, even + 2}, {5, even + 3},
                    {6, -1}, {6, 0}, {6, 1}, {6, 2},
                    {7, even - 1}, {7, even}, {7, even + 1}, {7, even + 2}, {7, even + 3},
                    {8, -1}, {8, 0}, {8, 1}, {8, 2}
            };
            case 60 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2}, {0, -3},
                    {1, even + 1}, {1, even}, {1, even - 1}, {1, even - 2}, {1, even - 3}, {1, even - 4},
                    {2, 0}, {2, -1}, {2, -2}, {2, -3}, {2, -4}, {2, -5}, {2, -6},
                    {3, even}, {3, even - 1}, {3, even - 2}, {3, even - 3}, {3, even - 4}, {3, even - 5}, {3, even - 6},
                    {4, -1}, {4, -2}, {4, -3}, {4, -4}, {4, -5}, {4, -6},
                    {5, even - 2}, {5, even - 3}, {5, even - 4}, {5, even - 5},
                    {6, -4}, {6, -5}
            };
            case 120 -> new int[][]{
                    {0, 0}, {0, -1}, {0, -2}, {0, -3}, {0, -4}, {0, -5},
                    {1, even}, {1, even - 1}, {1, even - 2}, {1, even - 3},
                    {-1, even}, {-1, even - 1}, {-1, even - 2}, {-1, even - 3}, {-1, even - 4}, {-1, even - 5}, {-1, even - 6},
                    {-2, -1}, {-2, -2}, {-2, -3}, {-2, -4}, {-2, -5}, {-2, -6}, {-2, -7},
                    {-3, even - 1}, {-3, even - 2}, {-3, even - 3}, {-3, even - 4}, {-3, even - 5}, {-3, even - 6},
                    {-4, -3}, {-4, -4}, {-4, -5}, {-4, -6},
                    {-5, even - 4}, {-5, even - 5}
            };
            case 180 -> new int[][]{
                    {0, 0}, {0, -1},
                    {-1, even + 1}, {-1, even}, {-1, even - 1},
                    {-2, 1}, {-2, 0}, {-2, -1}, {-2, -2},
                    {-3, even + 2}, {-3, even + 1}, {-3, even}, {-3, even - 1}, {-3, even - 2},
                    {-4, 1}, {-4, 0}, {-4, -1}, {-4, -2},
                    {-5, even + 2}, {-5, even + 1}, {-5, even}, {-5, even - 1}, {-5, even - 2},
                    {-6, 1}, {-6, 0}, {-6, -1}, {-6, -2},
                    {-7, even + 2}, {-7, even + 1}, {-7, even}, {-7, even - 1}, {-7, even - 2},
                    {-8, 1}, {-8, 0}, {-8, -1}, {-8, -2}
            };
            case 240 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2}, {0, 3},
                    {-1, even}, {-1, even + 1}, {-1, even + 2}, {-1, even + 3}, {-1, even + 4}, {-1, even + 5},
                    {-2, 0}, {-2, 1}, {-2, 2}, {-2, 3}, {-2, 4}, {-2, 5}, {-2, 6},
                    {-3, even + 1}, {-3, even + 2}, {-3, even + 3}, {-3, even + 4}, {-3, even + 5}, {-3, even + 6}, {-3, even + 7},
                    {-4, 1}, {-4, 2}, {-4, 3}, {-4, 4}, {-4, 5}, {-4, 6},
                    {-5, even + 3}, {-5, even + 4}, {-5, even + 5}, {-5, even + 6},
                    {-6, 4}, {-6, 5}
            };
            case 300 -> new int[][]{
                    {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5},
                    {-1, even + 1}, {-1, even + 2}, {-1, even + 3}, {-1, even + 4},
                    {1, even + 1}, {1, even + 2}, {1, even + 3}, {1, even + 4}, {1, even + 5}, {1, even + 6}, {1, even + 7},
                    {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6}, {2, 7},
                    {3, even + 2}, {3, even + 3}, {3, even + 4}, {3, even + 5}, {3, even + 6}, {3, even + 7},
                    {4, 3}, {4, 4}, {4, 5}, {4, 6},
                    {5, even + 5}, {5, even + 6}
            };
            default -> new int[0][0];
        };

        for (int[] offset : offsets) {
            hexes.add(scenario.grid.hexes.get(row + offset[0]).get(col + offset[1]));
        }
    }

    @Override
    public String toString() {
        return "14-" + letter;
    }
}