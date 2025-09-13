import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public abstract class Scenario {
    public ArrayList<MonsterType> monsterTypes;
    public ArrayList<MapTile> mapTiles;
    public ScenarioGrid grid;
    public Campaign camp;
    public ArrayList<Hex> startHexes;
    public int playerCount;
    public ArrayList<Figure> figures;
    public JDialog dialog;
    public JLayeredPane layeredPane;
    public JDialog LOSDialog;
    public JLayeredPane LOSLayeredPane;
    public ArrayList<AttackModifier> monsterAMDDeck;
    public ArrayList<AttackModifier> monsterAMDDiscard;
    public ArrayList<AttackModifier> allyAMDDeck;
    public ArrayList<AttackModifier> allyAMDDiscard;
    public ArrayList<LootCard> lootDeck;
    public Queue<Runnable> mainQueue;
    public int number;
    public ArrayList<Integer> elements;

    public Scenario(ArrayList<MonsterType> monsterTypes, ArrayList<Point> startHexLocations,
                    Campaign camp, int[] lootNumbers, int number) {
        this.monsterTypes = monsterTypes;
        elements = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
        mainQueue = new LinkedList<>();
        grid = new ScenarioGrid();
        setupLOSDialog();
        mapTiles = new ArrayList<>();
        this.number = number;
        monsterAMDDeck = new ArrayList<>();
        monsterAMDDeck.add(new AttackModifier("Null", 0, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/Null.png"));
        monsterAMDDeck.add(new AttackModifier("-2", -2, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/-2.png"));
        for (int i = 0; i < 5; i++) {
            monsterAMDDeck.add(new AttackModifier("-1", -1, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/-1.png"));
            monsterAMDDeck.add(new AttackModifier("+1", 1, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/+1.png"));
            monsterAMDDeck.add(new AttackModifier("+0", 0, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/+0.png"));
        }
        monsterAMDDeck.add(new AttackModifier("+0", 0, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/+0.png"));

        monsterAMDDeck.add(new AttackModifier("+2", 2, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/+2.png"));
        monsterAMDDeck.addFirst(new AttackModifier("2x", 0, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/2x.png"));
        monsterAMDDiscard = new ArrayList<>();

        allyAMDDeck = new ArrayList<>();
        allyAMDDeck.add(new AttackModifier("Null", 0, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/Null.png"));
        allyAMDDeck.add(new AttackModifier("-2", -2, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/-2.png"));
        for (int i = 0; i < 5; i++) {
            allyAMDDeck.add(new AttackModifier("-1", -1, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/-1.png"));
            allyAMDDeck.add(new AttackModifier("+1", 1, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/+1.png"));
            allyAMDDeck.add(new AttackModifier("+0", 0, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/+0.png"));
        }
        allyAMDDeck.add(new AttackModifier("+0", 0, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/+0.png"));

        allyAMDDeck.add(new AttackModifier("+2", 2, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/+2.png"));
        allyAMDDeck.add(new AttackModifier("2x", 0, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/2x.png"));
        allyAMDDiscard = new ArrayList<>();
        Collections.shuffle(allyAMDDeck);
        this.camp = camp;
        startHexes = new ArrayList<>();
        for (Point point : startHexLocations) {
            startHexes.add(grid.hexes.get((int) point.getX()).get((int) point.getY()));
        }
        playerCount = camp.activeCharacters.size();
        figures = new ArrayList<>();
        for (Character c : camp.activeCharacters) {
            figures.add(c.getStandee(this));
        }
        Collections.shuffle(camp.lootCards);
        lootDeck = new ArrayList<>();
        for (LootCard lc : camp.lootCards) {
            if (lc.toString().endsWith("Coin")) {
                if (lootNumbers[0] > 0) {
                    lootNumbers[0]--;
                    lootDeck.add(lc);
                }
            } else if (lc.toString().endsWith("Lumber")) {
                if (lootNumbers[1] > 0) {
                    lootNumbers[1]--;
                    lootDeck.add(lc);
                }
            } else if (lc.toString().endsWith("Metal")) {
                if (lootNumbers[2] > 0) {
                    lootNumbers[2]--;
                    lootDeck.add(lc);
                }
            } else if (lc.toString().endsWith("Hide")) {
                if (lootNumbers[3] > 0) {
                    lootNumbers[3]--;
                    lootDeck.add(lc);
                }
            } else if (lc.toString().endsWith("Arrowvine")) {
                if (lootNumbers[4] > 0) {
                    lootNumbers[4]--;
                    lootDeck.add(lc);
                }
            } else if (lc.toString().endsWith("Axenut")) {
                if (lootNumbers[5] > 0) {
                    lootNumbers[5]--;
                    lootDeck.add(lc);
                }
            } else if (lc.toString().endsWith("Corpsecap")) {
                if (lootNumbers[6] > 0) {
                    lootNumbers[6]--;
                    lootDeck.add(lc);
                }
            } else if (lc.toString().endsWith("Flamefruit")) {
                if (lootNumbers[7] > 0) {
                    lootNumbers[7]--;
                    lootDeck.add(lc);
                }
            } else if (lc.toString().endsWith("Rockroot")) {
                if (lootNumbers[8] > 0) {
                    lootNumbers[8]--;
                    lootDeck.add(lc);
                }
            } else if (lc.toString().endsWith("Snowthistle")) {
                if (lootNumbers[9] > 0) {
                    lootNumbers[9]--;
                    lootDeck.add(lc);
                }
            } else if (lc.toString().endsWith("Random Item")) {
                if (lootNumbers[10] > 0) {
                    lootNumbers[10]--;
                    lootDeck.add(lc);
                }
            }
        }
    }

    public void setMapTiles(ArrayList<MapTile> mapTiles1) {
        for (MapTile mapTile : mapTiles1) {
            mapTiles.add(mapTile);
            mapTile.getHexes();
        }
    }

    public void showScenario() {
        dialog = Methods.scrollableDialog(1600, 1000);
        JScrollPane scrollPane = (JScrollPane) (dialog.getContentPane()).getComponent(0);
        JPanel contentPanel = (JPanel) scrollPane.getViewport().getView();
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 2000, 1500);
        layeredPane.setLayout(null);
        contentPanel.add(layeredPane);
        contentPanel.setPreferredSize(new Dimension(2000, 1500));
        contentPanel.setLayout(null);
        for (MapTile mapTile : mapTiles) {
            mapTile.addMapTile(layeredPane);
        }
        for (Figure f : figures) {
            f.label.setLocation(0, 0);
            layeredPane.add(f.label, Integer.valueOf(2));
        }

        int[] charactersPlaced = {0};
        CountDownLatch latch = new CountDownLatch(1);

        ArrayList<JLabel> startHexLabels = new ArrayList<>();
        for (Hex hex : startHexes) {
            startHexLabels.add(startHex(hex, layeredPane, startHexLabels, charactersPlaced, latch));
        }
        System.out.println("Choose a hex to place " + camp.activeCharacters.getFirst().toString());
        setupRoom1();



        layeredPane.repaint();
        dialog.setVisible(true);
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setupLOSDialog() {
        LOSDialog = Methods.scrollableDialog(1600, 1000);
        JScrollPane scrollPane = (JScrollPane) (LOSDialog.getContentPane()).getComponent(0);
        JPanel contentPanel = (JPanel) scrollPane.getViewport().getView();
        LOSLayeredPane = new JLayeredPane();
        LOSLayeredPane.setBounds(0, 0, 2000, 1500);
        LOSLayeredPane.setLayout(null);
        contentPanel.add(LOSLayeredPane);
        contentPanel.setPreferredSize(new Dimension(2000, 1500));
        contentPanel.setLayout(null);
        for (ArrayList<Hex> lineHexes : grid.hexes) {
            for (Hex currentHex : lineHexes) {
                LOSLayeredPane.add(Methods.showUnvalidHexLOS(currentHex), Integer.valueOf(2));
                JLabel label = new JLabel(currentHex.row + ", " + currentHex.col);
                label.setBounds((int)currentHex.getPositionLOS().getX() - 15, (int)currentHex.getPositionLOS().getY() - 15, 30, 30);
                LOSLayeredPane.add(label, Integer.valueOf(10));
            }
        }
    }

    public void setupRoom1() {

    }

    public void setupRoom2() {

    }

    private JLabel startHex(Hex hex, JLayeredPane layeredPane, ArrayList<JLabel> startHexLabels, int[] charactersPlaced, CountDownLatch latch) {
        JLabel label = Methods.showStartHex(hex);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                placeFigure(hex, figures.get(charactersPlaced[0]));
                label.setVisible(false);
                startHexLabels.remove(label);
                charactersPlaced[0]++;
                layeredPane.repaint();
                if (camp.activeCharacters.size() == charactersPlaced[0]) {
                    for (JLabel label1 : startHexLabels) {
                        layeredPane.remove(label1);
                    }
                    layeredPane.repaint();
                    latch.countDown();
                } else {
                    System.out.println("Choose a hex to place " + camp.activeCharacters.get(charactersPlaced[0]).toString());
                }
            }
        });
        layeredPane.add(label, Integer.valueOf(1));
        return label;
    }

    public void round() {
        for (int i = 0; i < camp.activeCharacters.size(); i++) {

        }
    }

    public void addMonsterStandee(int num, int row, int col, boolean e) {
        Hex hex = grid.hexes.get(row).get(col);
        MonsterType mt = monsterTypes.get(num);
        MonsterStandee ms = monsterTypes.get(num).newStandee(grid, e, this);
        figures.add(ms);
        placeFigure(hex, ms);
        ms.label.setLocation(ms.getHexLocation());
        layeredPane.add(ms.label, Integer.valueOf(2));
    }

    public void placeFigure(Hex hex, Figure f) {
        f.hex = hex;
        hex.figure = f;
        f.label.setLocation(f.getHexLocation());
    }

    public void preScenario() {
        for (Figure f : figures) {
            Character c = ((CharacterStandee) f).c;
            JDialog preDialog = new JDialog((Frame) null, "Select Cards for " + c, true);
            preDialog.setLayout(null);
            preDialog.setSize(650, 900);
            preDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            preDialog.setLocationRelativeTo(null);

            JButton continueButton = new JButton("Confirm Cards");
            continueButton.setBounds(400, 760, 200, 30);


            for (int i = 0; i < c.availableCards.size(); i++) {
                JLabel check = Methods.createImage("/Users/saahilherrero/Downloads/Images/httpssteamusercontentaakamaihdnetugc1691647367272106475D85EBA610CEE366AABFD005AA313F433B3526233.png", 40);
                check.setLocation(210, 29 * i + 10);
                check.setVisible(false);
                preDialog.add(check);

                CharacterAbilityCard cac = c.availableCards.get(i);
                JButton cardButton = new JButton(cac.toString());
                cardButton.setBounds(10, 10 + 29 * i, 200, 25);
                cardButton.addActionListener(_ ->
                {
                    if (((CharacterStandee) f).hand.contains(cac)) {
                        check.setVisible(false);
                        ((CharacterStandee) f).hand.remove(cac);
                    } else {
                        check.setVisible(true);
                        ((CharacterStandee) f).hand.add(cac);
                    }
                    continueButton.setVisible(((CharacterStandee) f).hand.size() == c.handSize);
                });

                JLabel cardLabel = Methods.createImage(cac.imagePath, 50);
                cardLabel.setLocation(250, 150);
                preDialog.add(cardLabel);
                cardLabel.setVisible(false);
                cardButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        cardLabel.setVisible(true);
                        preDialog.repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        cardLabel.setVisible(false);
                        preDialog.repaint();
                    }
                });
                preDialog.add(cardButton);
            }

            continueButton.addActionListener(_ ->
                    preDialog.dispose());
            preDialog.add(continueButton);
            continueButton.setVisible(false);

            preDialog.setVisible(true);
        }
    }

    public void createWallLine(Hex hex1, Hex hex2) {
        if (!hex1.adjacentHexes.remove(hex2) || !hex2.adjacentHexes.remove(hex1)) {
            throw new ArrayIndexOutOfBoundsException("Hexes already not adjacent");
        }

        int x1 = (int) hex1.getPositionLOS().getX();
        int y1 = (int) hex1.getPositionLOS().getY();
        int x2 = (int) hex2.getPositionLOS().getX();
        int y2 = (int) hex2.getPositionLOS().getY();

        int drawX1, drawY1, drawX2, drawY2;

        if (y1 == y2) {
            drawX1 = x1 + 52;
            drawY1 = y1 - 30;
            drawX2 = x1 + 52;
            drawY2 = y1 + 30;
        } else if (x2 > x1) {
            drawX1 = x1 + 52;
            drawY1 = y1 + 30;
            drawX2 = x1;
            drawY2 = y1 + 60;
        } else {
            drawX1 = x1;
            drawY1 = y1 + 60;
            drawX2 = x1 - 52;
            drawY2 = y1 + 30;
        }

        int strokeWidth = 2;
        int padding = (int) Math.ceil((strokeWidth / 2.0) * Math.sqrt(2)) + 1;

        int panelX = Math.min(drawX1, drawX2) - padding;
        int panelY = Math.min(drawY1, drawY2) - padding;
        int width = Math.abs(drawX2 - drawX1) + 2 * padding;
        int height = Math.abs(drawY2 - drawY1) + 2 * padding;

        JPanel linePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(strokeWidth));
                g2.drawLine(drawX1 - panelX, drawY1 - panelY, drawX2 - panelX, drawY2 - panelY);
            }
        };
        linePanel.setOpaque(false);
        linePanel.setBounds(panelX, panelY, width, height);

        LOSLayeredPane.add(linePanel, Integer.valueOf(4));
    }

    public static void main(String[] args) {
        Campaign camp = new Campaign();
        Scenario scenario = new Scenario1(camp);
        //scenario.LOSDialog.setVisible(true);

        scenario.showScenario();

        //Methods.enqueue(scenario.mainQueue, () -> scenario.figures.getFirst().attack(true, 3, 1, 1, new ArrayList<>(Arrays.asList("Area of Effect: \nRed: 1, 2\nRed: 2, 3\nGrey: 1, 1\nBlue: 2, 2")), () -> Methods.finishQueue(scenario.mainQueue)));
        for (Figure f : scenario.figures) {
            f.changeHealth(-6);
        }
        CharacterAbilityCard card = new CombinedEffort(camp.activeCharacters.getFirst());
        card.characterStandee = (CharacterStandee)scenario.figures.getFirst();

        Methods.enqueue(scenario.mainQueue, () -> card.bottomAction());
        Methods.enqueue(scenario.mainQueue, () -> System.out.println("Done"));
    }

    @Override
    public abstract String toString();
}