import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public abstract class Figure {
    public int health;
    public int maxHealth;
    public String image;
    public int team;
    public Hex hex;
    public Scenario scenario;
    LabelWithNumber label;
    public ArrayList<String> conditions = new ArrayList<>();
    public ArrayList<AttackModifier> attackModifierDeck;
    public ArrayList<AttackModifier> attackModifierDiscard;
    int shieldVal = 0;
    int roundShieldVal = 0;
    int retaliateVal = 0;
    int roundRetaliateVal = 0;
    static final Point[] LOSPOINTS = {new Point(0, -60), new Point(52, -30), new Point(52, 30),
            new Point(0, 60), new Point(-52, 30), new Point(-52, -30), new Point(0, 0)};
    public ArrayList<String> persistentEffects = new ArrayList<>();

    public void startTurn() {
        if (conditions.contains("Wound")) {
            changeHealth(-1);
        }
    }

    @Override
    public abstract String toString();

    public void move(int number, Runnable onComplete) {
        new Thread(() -> {
            ArrayList<Hex> movedThrough = new ArrayList<>();
            ArrayList<JLabel> labels = new ArrayList<>();

            JLayeredPane layeredPane = scenario.layeredPane;
            JDialog dialog = scenario.dialog;

            JLabel transparent = Methods.createImage(image, 20, 0.6f);
            transparent.setLocation(getHexLocation());
            SwingUtilities.invokeLater(() -> layeredPane.add(transparent, JLayeredPane.PALETTE_LAYER));

            JButton reset = new JButton("Reset");
            JButton endMovement = new JButton("End Movement");

            SwingUtilities.invokeLater(() -> {
                endMovement.setFont(new Font("Markazi Text", Font.PLAIN, 40));
                endMovement.setBounds(850, 20, 250, 50);
                dialog.getLayeredPane().add(endMovement, JLayeredPane.MODAL_LAYER);

                reset.setFont(new Font("Markazi Text", Font.PLAIN, 40));
                reset.setBounds(650, 20, 100, 50);
                reset.setVisible(false);
                dialog.getLayeredPane().add(reset, JLayeredPane.MODAL_LAYER);
            });

            endMovement.addActionListener(_ ->
                    new Thread(() -> {
                        layeredPane.remove(transparent);
                        for (JLabel lab : labels) {
                            layeredPane.remove(lab);
                        }
                        dialog.getLayeredPane().remove(reset);
                        dialog.getLayeredPane().remove(endMovement);
                        dialog.repaint();

                        if (!movedThrough.isEmpty()) {
                            for (Hex value : movedThrough) {
                                CountDownLatch latch1 = new CountDownLatch(1);
                                SwingUtilities.invokeLater(() ->
                                        Methods.animateLabelTo(dialog, label, getHexLocation(value), 200, latch1::countDown)
                                );
                                try {
                                    latch1.await();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            hex.figure = null;
                            hex = movedThrough.getLast();
                            hex.figure = this;
                        }

                        if (onComplete != null) {
                            SwingUtilities.invokeLater(onComplete);
                        }
                    }).start()
            );

            reset.addActionListener(_ ->
                    new Thread(() -> {
                        movedThrough.clear();
                        for (JLabel lab : labels) {
                            layeredPane.remove(lab);
                        }
                        labels.clear();
                        transparent.setLocation(getHexLocation());
                        dialog.repaint();
                        while (movedThrough.size() < number) {
                            createMoveLabels(movedThrough, labels, layeredPane, dialog, transparent, endMovement, reset);
                        }
                    }).start()
            );

            while (movedThrough.size() < number) {
                createMoveLabels(movedThrough, labels, layeredPane, dialog, transparent, endMovement, reset);
            }
        }).start();
    }

    private void createMoveLabels(ArrayList<Hex> movedThrough, ArrayList<JLabel> labels,
                                  JLayeredPane layeredPane, JDialog dialog, JLabel transparent,
                                  JButton endMovement, JButton reset) {
        CountDownLatch latch = new CountDownLatch(1);
        for (Hex adjacentHex : (movedThrough.isEmpty()) ? hex.adjacentHexes : movedThrough.getLast().adjacentHexes) {
            if (adjacentHex.figure == null || adjacentHex.figure.team == team) {
                JLabel hexLabel = Methods.showHex(adjacentHex);
                hexLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        for (JLabel lab : labels) {
                            layeredPane.remove(lab);
                        }
                        labels.clear();
                        Methods.animateLabelTo(dialog, transparent, getHexLocation(adjacentHex), 50, latch::countDown);
                        movedThrough.add(adjacentHex);
                        endMovement.setVisible(adjacentHex.figure == null || adjacentHex.figure.team != team || adjacentHex == hex);
                        reset.setVisible(true);
                    }
                });
                labels.add(hexLabel);
                layeredPane.add(hexLabel, JLayeredPane.PALETTE_LAYER);
            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void teleport(int number, Runnable onComplete) {
        new Thread(() -> {
            Hex[] endHex = {new Hex(0, 0)};
            ArrayList<JLabel> labels = new ArrayList<>();

            JLayeredPane layeredPane = scenario.layeredPane;
            JDialog dialog = scenario.dialog;

            JLabel transparent = Methods.createImage(image, 20, 0.6f);
            transparent.setLocation(getHexLocation());
            SwingUtilities.invokeLater(() -> layeredPane.add(transparent, JLayeredPane.PALETTE_LAYER));

            JButton reset = new JButton("Reset");
            JButton endTeleport = new JButton("End Teleport");

            SwingUtilities.invokeLater(() -> {
                endTeleport.setFont(new Font("Markazi Text", Font.PLAIN, 40));
                endTeleport.setBounds(850, 20, 250, 50);
                dialog.getLayeredPane().add(endTeleport, JLayeredPane.MODAL_LAYER);

                reset.setFont(new Font("Markazi Text", Font.PLAIN, 40));
                reset.setBounds(650, 20, 100, 50);
                reset.setVisible(false);
                dialog.getLayeredPane().add(reset, JLayeredPane.MODAL_LAYER);
            });

            endTeleport.addActionListener(_ ->
                    new Thread(() -> {
                        layeredPane.remove(transparent);
                        for (JLabel lab : labels) {
                            layeredPane.remove(lab);
                        }
                        dialog.getLayeredPane().remove(reset);
                        dialog.getLayeredPane().remove(endTeleport);
                        label.setLocation(getHexLocation(endHex[0]));
                        dialog.repaint();

                        hex.figure = null;
                        hex = endHex[0];
                        hex.figure = this;
                        if (onComplete != null) {
                            SwingUtilities.invokeLater(onComplete);
                        }
                    }).start()
            );

            reset.addActionListener(_ ->
                    new Thread(() -> {
                        endHex[0] = new Hex(0, 0);
                        for (JLabel lab : labels) {
                            layeredPane.remove(lab);
                        }
                        labels.clear();
                        transparent.setLocation(getHexLocation());
                        dialog.repaint();
                        createTeleportLabels(endHex, labels, layeredPane, dialog, transparent, endTeleport, reset, number);
                    }).start()
            );
            createTeleportLabels(endHex, labels, layeredPane, dialog, transparent, endTeleport, reset, number);
        }).start();
    }

    private void createTeleportLabels(Hex[] endHex, ArrayList<JLabel> labels,
                                      JLayeredPane layeredPane, JDialog dialog, JLabel transparent,
                                      JButton endTeleport, JButton reset, int number) {
        for (Hex validHex : scenario.grid.validHexes) {
            if (validHex.figure == null && Methods.getDistance(hex, validHex) <= number) {
                JLabel hexLabel = Methods.showHex(validHex);
                hexLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        for (JLabel lab : labels) {
                            layeredPane.remove(lab);
                        }
                        labels.clear();
                        transparent.setLocation(getHexLocation(validHex));
                        endHex[0] = validHex;
                        endTeleport.setVisible(true);
                        reset.setVisible(true);
                        dialog.repaint();
                    }
                });
                labels.add(hexLabel);
                layeredPane.add(hexLabel, JLayeredPane.PALETTE_LAYER);
            }
        }
    }

    /*targeting nums:
        1: Enemies
        2: Allies
        3: Allies and Self
        4: Self
        5: Figures
     */
    public void chooseFigures(int range, int numTargets, ArrayList<Figure> targets, int targeting, Runnable onComplete) {
        ArrayList<JLabel> labels = new ArrayList<>();
        ArrayList<JLabel> redLabels = new ArrayList<>();

        JButton resetTargeting = new JButton("Reset Targeting");
        resetTargeting.setFont(new Font("Markazi Text", Font.PLAIN, 40));

        JButton confirmFigures = new JButton("Confirm Figures");
        confirmFigures.setFont(new Font("Markazi Text", Font.PLAIN, 40));
        confirmFigures.setBounds(850, 20, 300, 50);
        scenario.dialog.getLayeredPane().add(confirmFigures, JLayeredPane.MODAL_LAYER);
        confirmFigures.addActionListener(_ ->
                new Thread(() -> {
                    for (JLabel hLabel : labels) {
                        scenario.layeredPane.remove(hLabel);
                    }
                    for (JLabel redLabel : redLabels) {
                        scenario.layeredPane.remove(redLabel);
                    }
                    scenario.dialog.getLayeredPane().remove(confirmFigures);
                    scenario.dialog.getLayeredPane().remove(resetTargeting);
                    scenario.dialog.repaint();
                    if (onComplete != null) {
                        SwingUtilities.invokeLater(onComplete);
                    }
                }).start()
        );
        confirmFigures.setVisible(false);

        resetTargeting.setBounds(550, 20, 300, 50);
        scenario.dialog.getLayeredPane().add(resetTargeting, JLayeredPane.MODAL_LAYER);
        resetTargeting.addActionListener(_ ->
                new Thread(() -> {
                    for (JLabel hLabel : labels) {
                        scenario.layeredPane.remove(hLabel);
                    }
                    for (JLabel redLabel : redLabels) {
                        scenario.layeredPane.remove(redLabel);
                    }
                    labels.clear();
                    redLabels.clear();
                    targets.clear();
                    confirmFigures.setVisible(false);
                    chooseFiguresHelper(range, labels, targets, redLabels, numTargets, confirmFigures, targeting);
                }).start()
        );
        chooseFiguresHelper(range, labels, targets, redLabels, numTargets, confirmFigures, targeting);
        if (labels.isEmpty()) {
            confirmFigures.doClick();
        }
    }

    private void chooseFiguresHelper(int range, ArrayList<JLabel> labels, ArrayList<Figure> targets, ArrayList<JLabel> redLabels,
                                     int numTargets, JButton confirmFigures, int targeting) {
        for (Figure f : scenario.figures) {
            if ((Methods.getRange(hex, f.hex) <= range || (range < 0)) &&
                    ((targeting == 1) ? (f.team != team) : ((targeting == 2) ? f.team == team && f != this :
                            ((targeting == 3) ? f.team == team : ((targeting == 4) ? f == this : true)))) && lineOfSight(hex, f.hex)) {
                JLabel hexLabel = Methods.showHex(f.hex);
                labels.add(hexLabel);
                hexLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        targets.add(f);
                        JLabel redLabel = Methods.createImage("/Users/saahilherrero/Downloads/Images/Red Hex.png", 21, 0.7f);
                        redLabel.setLocation(hexLabel.getLocation());
                        redLabels.add(redLabel);
                        scenario.layeredPane.add(redLabel, Integer.valueOf(10));
                        hexLabel.setVisible(false);
                        if (targets.size() == numTargets) {
                            for (JLabel hLabel : labels) {
                                scenario.layeredPane.remove(hLabel);
                            }
                        }
                        scenario.dialog.repaint();
                        confirmFigures.setVisible(true);
                    }
                });
                scenario.layeredPane.add(hexLabel, Integer.valueOf(10));
            }
        }
        scenario.dialog.repaint();
    }

    public CompletableFuture<Object> attack(boolean melee, int attackVal, int numTargets, int range, ArrayList<String> modifiers, Runnable onComplete) {
        CompletableFuture<Object> future = new CompletableFuture<>();
        Queue<Runnable> queue = new LinkedList<>();
        ArrayList<Figure> targets = new ArrayList<>();
        boolean isaoe = false;
        String aoeHexes = "";
        for (String modifier : modifiers) {
            if (modifier.startsWith("Area of Effect:")) {
                isaoe = true;
                aoeHexes = modifier;
                break;
            }
        }
        if (isaoe) {
            ArrayList<Integer> hexes = new ArrayList<>();
            int index = 0;
            while (index != -1) {
                index = aoeHexes.indexOf("\n", index + 1);
                if (index != -1) {
                    hexes.add(index + 1);
                }
            }
            ArrayList<Hex> redHexes = new ArrayList<>();
            Hex greyHex = null;
            ArrayList<Hex> blueHexes = new ArrayList<>();

            for (int hexNum : hexes) {
                if (aoeHexes.startsWith("Red: ", hexNum)) {
                    redHexes.add(scenario.grid.hexes.get(Integer.parseInt(aoeHexes.substring(hexNum + 5, hexNum + 6))).
                            get(Integer.parseInt(aoeHexes.substring(hexNum + 8, hexNum + 9))));
                } else {
                    int index1 = Integer.parseInt(aoeHexes.substring(hexNum + 6, hexNum + 7));
                    int index2 = Integer.parseInt(aoeHexes.substring(hexNum + 9, hexNum + 10));
                    if (aoeHexes.startsWith("Grey: ", hexNum)) {
                        greyHex = scenario.grid.hexes.get(index1).get(index2);
                    } else if (aoeHexes.startsWith("Blue: ", hexNum)) {
                        blueHexes.add(scenario.grid.hexes.get(index1).get(index2));
                    }
                }
            }
            AreaOfEffect aoe = new AreaOfEffect(redHexes, greyHex, blueHexes, scenario.grid);
            Methods.enqueue(queue, () -> createAOETargeting(aoe, targets, range, 1, () -> Methods.finishQueue(queue)));
        } else {
            Methods.enqueue(queue, () -> chooseFigures(range, numTargets, targets, 1, () -> Methods.finishQueue(queue)));
        }
        Methods.enqueue(queue, () -> {
            for (Figure f : targets) {
                int attackValue = attackVal + ((f.conditions.contains("Poison")) ? 1 : 0);
                JLabel redLabel = Methods.createImage("/Users/saahilherrero/Downloads/Images/Red Hex.png", 20, 0.7f);
                redLabel.setLocation(f.getHexLocation());
                scenario.layeredPane.add(redLabel, Integer.valueOf(10));
                boolean attackWA = false;
                boolean attackWD = (!melee && Methods.getRange(hex, f.hex) == 1) || (f.searchPersistentEffects("Attackers gain disadvantage"));
                ArrayList<AttackModifier> attackModifiers = drawModifier(attackVal, attackWA, attackWD);
                int modifierValue = 0;
                boolean nullDrawn = false;
                int twoTimesDrawn = 0;
                Queue<Runnable> amdEffectQueue = new LinkedList<>();
                for (AttackModifier modifier : attackModifiers) {
                    modifierValue += modifier.modifierValue + modifier.calculateValue(f);
                    Methods.enqueue(amdEffectQueue, () -> modifier.attackEffect(f, amdEffectQueue));
                    if (modifier.name.contains("Null")) {
                        nullDrawn = true;
                    } else if (modifier.name.contains("2x")) {
                        twoTimesDrawn++;
                    }
                }
                if (nullDrawn) {
                    modifierValue = -attackValue;
                }
                attackValue = ((attackValue + modifierValue) * (int) Math.pow(2, twoTimesDrawn)) - (f.shieldVal + f.roundShieldVal);
                f.changeHealth(-Math.max(attackValue, 0));
                for (String mod : modifiers) {
                    if (mod.startsWith("Condition: ")) {
                        f.gainCondition(mod.substring(11));
                    }
                }
                while (!attackModifiers.isEmpty()) {
                    attackModifierDiscard.add(attackModifiers.removeFirst());
                }
                scenario.layeredPane.remove(redLabel);
                scenario.dialog.repaint();
            }
            if (modifiers.contains("Return Action Performed")) {
                SwingUtilities.invokeLater(() -> future.complete(!targets.isEmpty()));
            }
            if (onComplete != null) {
                SwingUtilities.invokeLater(onComplete);
            }
        });
        return future;
    }

    public CompletableFuture<Object> heal(int healVal, int numTargets, int range, ArrayList<String> modifiers, Runnable onComplete) {
        CompletableFuture<Object> future = new CompletableFuture<>();
        Queue<Runnable> queue = new LinkedList<>();
        ArrayList<Figure> targets = new ArrayList<>();
        Methods.enqueue(queue, () -> chooseFigures(range, numTargets, targets, ((modifiers.contains("Target Ally")) ? 2 : ((modifiers.contains("Target Self")) ? 4 : 3)), () -> Methods.finishQueue(queue)));
        Methods.enqueue(queue, () -> {
            int[] numFiguresIncreased = {0};
            for (Figure f : targets) {
                if (!f.conditions.contains("Poison")) {
                    numFiguresIncreased[0] += (f.changeHealth(healVal)) ? 1 : 0;
                }
                f.conditions.removeAll(new ArrayList<>(Arrays.asList("Poison", "Wound", "Bane", "Brittle")));
            }
            if (modifiers.contains("Return figures increased heal")) {
                SwingUtilities.invokeLater(() -> future.complete(numFiguresIncreased[0]));
            }
            if (onComplete != null) {
                SwingUtilities.invokeLater(onComplete);
            }
        });
        return future;
    }

    public boolean changeHealth(int num) {
        int currentHealth = health;
        if (num < 0) {
            if (conditions.contains("Brittle")) {
                num *= 2;
                conditions.remove("Brittle");
            }
            if (conditions.contains("Ward")) {
                num /= 2;
                conditions.remove("Ward");
            }
        }
        health += num;
        if (health > maxHealth) {
            health = maxHealth;
        }
        if (health <= 0) {
            killFigure();
        }
        label.setHealthNumber(health);
        return health != currentHealth;
    }

    public void killFigure() {
        // to be done
    }

    public Point getHexLocation() {
        return getHexLocation(hex);
    }

    public Point getHexLocation(Hex hex) {
        return new Point((int) hex.getPosition().getX() - label.getWidth() / 2,
                (int) hex.getPosition().getY() - label.getHeight() / 2);
    }

    public ArrayList<AttackModifier> drawModifier(int attackVal, boolean advantage, boolean disadvantage) {
        ArrayList<AttackModifier> draw = new ArrayList<>();
        if (advantage && disadvantage) {
            advantage = false;
            disadvantage = false;
        }
        do {
            if (attackModifierDeck.isEmpty()) {
                reshuffleAttackModifiers();
            }
            draw.addFirst(attackModifierDeck.getFirst());
            attackModifierDeck.removeFirst();
        } while (draw.getFirst().rolling);
        int width = 407;
        if (advantage || disadvantage) {
            width += 407;
        }

        JDialog dialog = new JDialog((Frame) null, "Attack Modifier", true);
        dialog.setLayout(null);
        dialog.setSize(width + 5, 265 * draw.size() + 25);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setAlwaysOnTop(true);
        dialog.toFront();

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, width + 5, 265 * draw.size() + 25);
        dialog.setContentPane(layeredPane);

        JLabel label1;
        String image;
        for (int i = 0; i < draw.size(); i++) {
            image = draw.get(i).frontImage;
            label1 = Methods.createImage(image, 50);
            label1.setLocation(0, i * 265);

            layeredPane.add(label1, JLayeredPane.DEFAULT_LAYER);
            dialog.repaint();
        }

        if (advantage) {
            if (attackModifierDeck.isEmpty()) {
                reshuffleAttackModifiers();
            }
            draw.addFirst(attackModifierDeck.getFirst());
            attackModifierDeck.removeFirst();
            image = draw.getFirst().frontImage;
            label1 = Methods.createImage(image, 50);
            label1.setLocation(407, dialog.getHeight() - 290);
            dialog.add(label1);

            JButton chooseOne = new JButton("Choose card");
            chooseOne.setBounds(dialog.getWidth() - 540, dialog.getHeight() - 55, 130, 30);

            chooseOne.addActionListener(_ ->
            {
                attackModifierDiscard.add(draw.getFirst());
                draw.removeFirst();
                dialog.dispose();
            });
            layeredPane.add(chooseOne, JLayeredPane.PALETTE_LAYER);

            JButton chooseTwo = new JButton("Choose card");
            chooseTwo.setBounds(dialog.getWidth() - 130, dialog.getHeight() - 55, 130, 30);

            chooseTwo.addActionListener(_ ->
            {
                attackModifierDiscard.add(draw.get(1));
                draw.remove(1);
                dialog.dispose();
            });
            layeredPane.add(chooseTwo, JLayeredPane.PALETTE_LAYER);
        }
        int unused = 0;
        if (disadvantage) {
            if (attackModifierDeck.isEmpty()) {
                reshuffleAttackModifiers();
            }
            draw.addFirst(attackModifierDeck.getFirst());
            attackModifierDeck.removeFirst();
            image = draw.getFirst().frontImage;
            label1 = Methods.createImage(image, 50);
            label1.setBounds(407, dialog.getHeight() - 290, 407, 265);
            dialog.add(label1);

            int first = draw.getFirst().modifierValue;
            int second = draw.get(1).modifierValue;
            if (draw.getFirst().name.contains("2x")) {
                first = 2 * attackVal;
            }
            if (draw.get(1).name.contains("2x")) {
                second = 2 * attackVal;
            }

            if (draw.getFirst().name.contains("Null")) {
                unused = 1;
            } else if ((!draw.getFirst().hasAttackEffect && draw.get(1).hasAttackEffect) || (!draw.getFirst().hasAttackEffect && !draw.get(1).hasAttackEffect)) {
                if (first <= second) {
                    unused = 1;
                }
            }
            attackModifierDiscard.add(draw.remove(unused));
        }

        if (!advantage) {
            JButton continueButton = new JButton("Continue");
            continueButton.setBounds(dialog.getWidth() - 130, dialog.getHeight() - 55, 130, 30);
            if (disadvantage && unused == 0) {
                continueButton.setBounds(dialog.getWidth() - 130 - 407, dialog.getHeight() - 55, 130, 30);
            }
            continueButton.addActionListener(_ ->
                    dialog.dispose());
            layeredPane.add(continueButton, JLayeredPane.PALETTE_LAYER);
        }
        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();
        return draw;
    }

    public void reshuffleAttackModifiers() {
        while (!attackModifierDeck.isEmpty()) {
            attackModifierDeck.add(attackModifierDiscard.removeFirst());
        }
        Collections.shuffle(attackModifierDeck);
    }

    public boolean lineOfSight(Hex hex1, Hex hex2) {
        if (hex1.adjacentHexes.contains(hex2) || hex1 == hex2) {
            return true;
        }

        int x1 = (int) hex1.getPositionLOS().getX();
        int y1 = (int) hex1.getPositionLOS().getY();
        int x2 = (int) hex2.getPositionLOS().getX();
        int y2 = (int) hex2.getPositionLOS().getY();

        double angle = Methods.angleBetweenPoints(x1, y1, x2, y2);
        //Rotation line: 0: vertical, 1: topLeft to botRight, 2: botLeft to topRight
        int rotationLine;
        if (angle < 30 || (angle >= 150 && angle < 210) || angle >= 330) {
            rotationLine = 0;
        } else if ((angle >= 30 && angle < 90) || (angle >= 210 && angle < 270)) {
            rotationLine = 1;
        } else {
            rotationLine = 2;
        }

        ArrayList<Point> hex1Line = getLinePoints(x1 + (int) LOSPOINTS[(rotationLine == 0) ? 0 : ((rotationLine == 1) ? 5 : 4)].getX(),
                y1 + (int) LOSPOINTS[(rotationLine == 0) ? 0 : ((rotationLine == 1) ? 5 : 4)].getY(),
                x1 + (int) LOSPOINTS[(rotationLine == 0) ? 3 : ((rotationLine == 1) ? 2 : 1)].getX(),
                y1 + (int) LOSPOINTS[(rotationLine == 0) ? 3 : ((rotationLine == 1) ? 2 : 1)].getY());

        ArrayList<Point> hex2Line = getLinePoints(x2 + (int) LOSPOINTS[(rotationLine == 0) ? 0 : ((rotationLine == 1) ? 5 : 4)].getX(),
                y2 + (int) LOSPOINTS[(rotationLine == 0) ? 0 : ((rotationLine == 1) ? 5 : 4)].getY(),
                x2 + (int) LOSPOINTS[(rotationLine == 0) ? 3 : ((rotationLine == 1) ? 2 : 1)].getX(),
                y2 + (int) LOSPOINTS[(rotationLine == 0) ? 3 : ((rotationLine == 1) ? 2 : 1)].getY());

        BufferedImage backgroundImage = new BufferedImage(
                scenario.LOSLayeredPane.getWidth(), scenario.LOSLayeredPane.getHeight(), BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g2d = backgroundImage.createGraphics();
        scenario.LOSLayeredPane.paint(g2d);
        g2d.dispose();

        for (Point p1 : hex1Line) {
            for (Point p2 : hex2Line) {
                int p1x = (int) p1.getX();
                int p1y = (int) p1.getY();
                int p2x = (int) p2.getX();
                int p2y = (int) p2.getY();


                boolean redFound = false;
                Color targetRed = new Color(255, 0, 0);

                int dx = Math.abs(p2x - p1x);
                int dy = Math.abs(p2y - p1y);
                int sx = p1x < p2x ? 1 : -1;
                int sy = p1y < p2y ? 1 : -1;
                int err = dx - dy;

                int x = p1x;
                int y = p1y;

                int radius = 2;

                while (true) {
                    for (int dxr = -radius; dxr <= radius; dxr++) {
                        for (int dyr = -radius; dyr <= radius; dyr++) {
                            int px = x + dxr;
                            int py = y + dyr;

                            if (px < 0 || px >= backgroundImage.getWidth() ||
                                    py < 0 || py >= backgroundImage.getHeight()) {
                                continue;
                            }
                            if (dxr * dxr + dyr * dyr > radius * radius) continue;

                            Color c = new Color(backgroundImage.getRGB(px, py), true);
                            if (c.equals(targetRed)) {
                                redFound = true;
                                break;
                            }
                        }
                        if (redFound) break;
                    }

                    if (redFound || (x == p2x && y == p2y)) break;

                    int e2 = 2 * err;
                    if (e2 > -dy) {
                        err -= dy;
                        x += sx;
                    }
                    if (e2 < dx) {
                        err += dx;
                        y += sy;
                    }
                }
                if (!redFound) {
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<Point> getLinePoints(int x1, int y1, int x2, int y2) {
        ArrayList<Point> points = new ArrayList<>();
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        int x = x1;
        int y = y1;
        while (true) {
            points.add(new Point(x, y));
            if (x == x2 && y == y2) break;
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
        return points;
    }

    public void gainCondition(String condition) {
        if (!conditions.contains(condition)) {
            conditions.add(condition);
        }
    }

    public void push(Figure target, int number, Runnable onComplete) {
        new Thread(() -> {
            ArrayList<Hex> movedThrough = new ArrayList<>();
            ArrayList<JLabel> labels = new ArrayList<>();

            JLayeredPane layeredPane = scenario.layeredPane;
            JDialog dialog = scenario.dialog;

            JLabel transparent = Methods.createImage(target.image, 20, 0.6f);
            transparent.setLocation(target.getHexLocation());
            SwingUtilities.invokeLater(() -> layeredPane.add(transparent, JLayeredPane.PALETTE_LAYER));

            JButton reset = new JButton("Reset");
            JButton endPush = new JButton("End Push");

            SwingUtilities.invokeLater(() -> {
                endPush.setFont(new Font("Markazi Text", Font.PLAIN, 40));
                endPush.setBounds(850, 20, 250, 50);
                dialog.getLayeredPane().add(endPush, JLayeredPane.MODAL_LAYER);

                reset.setFont(new Font("Markazi Text", Font.PLAIN, 40));
                reset.setBounds(650, 20, 100, 50);
                reset.setVisible(false);
                dialog.getLayeredPane().add(reset, JLayeredPane.MODAL_LAYER);
            });

            endPush.addActionListener(_ ->
                    new Thread(() -> {
                        layeredPane.remove(transparent);
                        for (JLabel lab : labels) {
                            layeredPane.remove(lab);
                        }
                        dialog.getLayeredPane().remove(reset);
                        dialog.getLayeredPane().remove(endPush);
                        dialog.repaint();

                        if (!movedThrough.isEmpty()) {
                            for (Hex value : movedThrough) {
                                CountDownLatch latch1 = new CountDownLatch(1);
                                SwingUtilities.invokeLater(() ->
                                        Methods.animateLabelTo(dialog, target.label, target.getHexLocation(value), 200, latch1::countDown)
                                );
                                try {
                                    latch1.await();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            target.hex.figure = null;
                            target.hex = movedThrough.getLast();
                            target.hex.figure = this;
                        }

                        if (onComplete != null) {
                            SwingUtilities.invokeLater(onComplete);
                        }
                    }).start()
            );

            reset.addActionListener(_ ->
                    new Thread(() -> {
                        movedThrough.clear();
                        for (JLabel lab : labels) {
                            layeredPane.remove(lab);
                        }
                        labels.clear();
                        transparent.setLocation(target.getHexLocation());
                        dialog.repaint();
                        while (movedThrough.size() < number) {
                            createPushLabels(target, movedThrough, labels, layeredPane, dialog, transparent, endPush, reset);
                        }
                    }).start()
            );

            while (movedThrough.size() < number) {
                createPushLabels(target, movedThrough, labels, layeredPane, dialog, transparent, endPush, reset);
            }
        }).start();
    }

    private void createPushLabels(Figure target, ArrayList<Hex> movedThrough, ArrayList<JLabel> labels,
                                  JLayeredPane layeredPane, JDialog dialog, JLabel transparent,
                                  JButton endMovement, JButton reset) {
        CountDownLatch latch = new CountDownLatch(1);
        for (Hex adjacentHex : (movedThrough.isEmpty()) ? target.hex.adjacentHexes : movedThrough.getLast().adjacentHexes) {
            if (Methods.getRange(hex, (movedThrough.isEmpty()) ? target.hex : movedThrough.getLast()) < Methods.getRange(hex, adjacentHex) && (adjacentHex.figure == null || adjacentHex.figure.team == target.team)) {
                JLabel hexLabel = Methods.showHex(adjacentHex);
                hexLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        for (JLabel lab : labels) {
                            layeredPane.remove(lab);
                        }
                        labels.clear();
                        Methods.animateLabelTo(dialog, transparent, getHexLocation(adjacentHex), 50, latch::countDown);
                        movedThrough.add(adjacentHex);
                        endMovement.setVisible(adjacentHex.figure == null || adjacentHex.figure.team != target.team);
                        reset.setVisible(true);
                    }
                });
                labels.add(hexLabel);
                layeredPane.add(hexLabel, JLayeredPane.PALETTE_LAYER);
            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void createAOETargeting(AreaOfEffect aoe, ArrayList<Figure> targets, int range, int targeting, Runnable onComplete) {
        ArrayList<JLabel> labels = new ArrayList<>();

        JButton confirmTargeting = new JButton("Confirm Targeting");
        confirmTargeting.setFont(new Font("Markazi Text", Font.PLAIN, 40));

        Hex[] keyHex = {aoe.getKeyHex()};
        for (int i = 0; i < aoe.redHexes.size(); i++) {
            Hex redHex = aoe.redHexes.get(i);
            int redRow = redHex.row + hex.row - keyHex[0].row;
            int redCol = redHex.col + hex.col - keyHex[0].col - (((redHex.row - hex.row) % 2 == 1) ? 1 : 0);
            aoe.redHexes.set(i, scenario.grid.hexes.get(redRow).get(redCol));
        }
        for (int i = 0; i < aoe.blueHexes.size(); i++) {
            Hex blueHex = aoe.blueHexes.get(i);
            int blueRow = blueHex.row + hex.row - keyHex[0].row;
            int blueCol = blueHex.col + hex.col - keyHex[0].col - (((blueHex.row - hex.row) % 2 == 1) ? 1 : 0);
            aoe.blueHexes.set(i, scenario.grid.hexes.get(blueRow).get(blueCol));
        }
        if (aoe.greyHex != null) {
            int greyRow = hex.row;
            int greyCol = hex.col;
            aoe.greyHex = scenario.grid.hexes.get(greyRow).get(greyCol);
        }
        keyHex[0] = aoe.getKeyHex();
        aoeTargetingLabels(keyHex[0], aoe, labels, targeting, confirmTargeting);
        keyHex[0] = aoe.getKeyHex();

        if (aoe.greyHex != null) {

        }

        JRootPane rootPane = scenario.dialog.getRootPane();

        InputMap im = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = rootPane.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "rPressed");
        am.put("rPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aoe.rotateArea();
                aoeTargetingLabels(keyHex[0], aoe, labels, targeting, confirmTargeting);
                keyHex[0] = aoe.getKeyHex();
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), "fPressed");
        am.put("fPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aoe.flipArea();
                aoeTargetingLabels(keyHex[0], aoe, labels, targeting, confirmTargeting);
                keyHex[0] = aoe.getKeyHex();
            }
        });

        KeyStroke shiftR = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK);
        im.put(shiftR, "shiftRPressed");
        am.put("shiftRPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 5; i++) {
                    aoe.rotateArea();
                }
                aoeTargetingLabels(keyHex[0], aoe, labels, targeting, confirmTargeting);
                keyHex[0] = aoe.getKeyHex();
            }
        });


        confirmTargeting.setBounds(850, 20, 300, 50);
        scenario.dialog.getLayeredPane().add(confirmTargeting, JLayeredPane.MODAL_LAYER);
        confirmTargeting.addActionListener(_ ->
                new Thread(() -> {
                    for (JLabel hLabel : labels) {
                        scenario.layeredPane.remove(hLabel);
                    }
                    scenario.dialog.getLayeredPane().remove(confirmTargeting);
                    scenario.dialog.repaint();
                    for (Hex redHex : aoe.redHexes) {
                        if (redHex.figure != null && targetingType(targeting, redHex.figure)) {
                            targets.add(redHex.figure);
                        }
                    }
                    im.remove(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0));
                    am.remove("rPressed");
                    im.remove(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0));
                    am.remove("fPressed");
                    im.remove(shiftR);
                    am.remove("shiftRPressed");
                    if (onComplete != null) {
                        SwingUtilities.invokeLater(onComplete);
                    }
                }).start()
        );
        confirmTargeting.setVisible(false);
    }

    private void aoeTargetingLabels(Hex keyHex, AreaOfEffect aoe, ArrayList<JLabel> labels, int targeting, JButton confirmTargeting) {
        for (JLabel label : labels) {
            scenario.layeredPane.remove(label);
        }
        labels.clear();
        for (Hex redHex : aoe.redHexes) {
            JLabel label = Methods.createImage("/Users/saahilherrero/Downloads/Images/Red Hex.png", 21, 0.7f);
            int newRow = redHex.row;
            int newCol = redHex.col;
            Hex placedHex = (scenario.grid.inGrid(newRow, newCol)) ? scenario.grid.hexes.get(newRow).get(newCol) : new Hex(newRow, newCol);
            label.setLocation((int) placedHex.getPosition().getX() - label.getWidth() / 2, (int) placedHex.getPosition().getY() - label.getHeight() / 2);
            labels.add(label);
            scenario.layeredPane.add(label, Integer.valueOf(10));
        }
        for (Hex blueHex : aoe.blueHexes) {
            int newRow = blueHex.row;
            int newCol = blueHex.col;
            Hex placedHex = (scenario.grid.inGrid(newRow, newCol)) ? scenario.grid.hexes.get(newRow).get(newCol) : new Hex(newRow, newCol);
            JLabel label = Methods.showHex(placedHex);
            labels.add(label);
            scenario.layeredPane.add(label, Integer.valueOf(10));
        }
        boolean targetingAllowed = false;
        for (Hex redHex : aoe.redHexes) {
            if (redHex.figure != null && targetingType(targeting, redHex.figure)) {
                targetingAllowed = true;
                break;
            }
        }
        for (Hex blueHex : aoe.blueHexes) {
            if (blueHex.figure == null || blueHex.figure.team != team) {
                targetingAllowed = false;
                break;
            }
        }
        confirmTargeting.setVisible(targetingAllowed);
        scenario.layeredPane.repaint();
    }

    public boolean targetingType(int targeting, Figure f) {
        return ((targeting == 1) ? (f.team != team) : ((targeting == 2) ? f.team == team && f != this :
                ((targeting == 3) ? f.team == team : ((targeting == 4) ? f == this : true)))) && lineOfSight(hex, f.hex);
    }

    public boolean searchPersistentEffects(String s) {
        for (String pe : persistentEffects) {
            if (pe.contains(s)) {
                return true;
            }
        }
        return false;
    }
}