import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public abstract class CharacterStandee extends Figure {
    public Character c;
    public int xp;
    public ArrayList<Item> items;
    public ArrayList<CharacterAbilityCard> hand;
    public ArrayList<CharacterAbilityCard> discard;
    public ArrayList<CharacterAbilityCard> played;
    public ArrayList<CharacterAbilityCard> lost;
    public ArrayList<CharacterAbilityCard> active;
    public ArrayList<CharacterSummon> summons;
    public ArrayList<NonAMD> nonAMDS;
    public boolean ignoreScenarioEffects;
    public boolean ignoreItemEffects;

    public CharacterStandee(Character c, Scenario scenario) {
        this.c = c;
        maxHealth = c.healthProgression[c.level];
        health = maxHealth;
        xp = 0;
        image = "/Users/saahilherrero/Downloads/Images/Characters Standees/" + c + ".png";
        items = new ArrayList<>();
        team = 1;
        label = new LabelWithNumber(Methods.createImageCharacter(image, 20), health, null);
        this.scenario = scenario;
        attackModifierDeck = new ArrayList<>();
        attackModifierDeck.add(new AttackModifier("Null", 0, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/Null.png"));
        attackModifierDeck.add(new AttackModifier("-2", -2, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/-2.png"));
        for (int i = 0; i < 5; i++) {
            attackModifierDeck.add(new AttackModifier("-1", -1, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/-1.png"));
            attackModifierDeck.add(new AttackModifier("+1", 1, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/+1.png"));
            attackModifierDeck.add(new AttackModifier("+0", 0, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/+0.png"));
        }
        attackModifierDeck.add(new AttackModifier("+0", 0, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/+0.png"));

        attackModifierDeck.add(new AttackModifier("+2", 2, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/+2.png"));
        attackModifierDeck.add(new AttackModifier("2x", 0, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/2x.png"));
        attackModifierDiscard = new ArrayList<>();
        Collections.shuffle(attackModifierDeck);
        hand = new ArrayList<>();
        discard = new ArrayList<>();
        active = new ArrayList<>();
        lost = new ArrayList<>();
        summons = new ArrayList<>();
        played = new ArrayList<>();
        ignoreScenarioEffects = false;
        ignoreItemEffects = false;
        nonAMDS = new ArrayList<>();
    }

    public CompletableFuture<Boolean> summon(Class<?> type, Runnable onComplete) {
        return summon(1, type, onComplete);
    }


    public CompletableFuture<Boolean> summon(int number, Class<?> type, Runnable onComplete) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        new Thread(() -> {
            ArrayList<JLabel> labels = new ArrayList<>();
            ArrayList<CharacterSummon> placedSummons = new ArrayList<>();
            ArrayList<JLabel> transparentLabels = new ArrayList<>();
            ArrayList<Hex> placedHexes = new ArrayList<>();

            JLayeredPane layeredPane = scenario.layeredPane;

            int firstNum = 1;
            JDialog dialog = scenario.dialog;
            for (CharacterSummon cs : summons) {
                if (cs.getClass() == type) {
                    firstNum = cs.number+1;
                }
            }

            for (int i = firstNum; i <= number + firstNum - 1; i++) {
                CharacterSummon summon = Methods.createInstanceType(type);
                summon.createSummonStandee(this, i);
                placedSummons.add(summon);
                JLabel transparent = Methods.createImage(summon.image, 20, 0.6f);
                transparentLabels.add(transparent);
                transparent.setVisible(false);
                SwingUtilities.invokeLater(() -> layeredPane.add(transparent, JLayeredPane.PALETTE_LAYER));
            }


            JButton reset = new JButton("Reset");
            JButton placeSummon = new JButton("Place Summon");

            SwingUtilities.invokeLater(() -> {
                placeSummon.setFont(new Font("Markazi Text", Font.PLAIN, 40));
                placeSummon.setBounds(850, 20, 250, 50);
                placeSummon.setVisible(false);
                dialog.getLayeredPane().add(placeSummon, JLayeredPane.MODAL_LAYER);

                reset.setFont(new Font("Markazi Text", Font.PLAIN, 40));
                reset.setBounds(650, 20, 100, 50);
                reset.setVisible(false);
                dialog.getLayeredPane().add(reset, JLayeredPane.MODAL_LAYER);
            });

            placeSummon.addActionListener(_ ->
                    new Thread(() -> {
                        for (JLabel lab : transparentLabels) {
                            layeredPane.remove(lab);
                        }
                        for (JLabel lab : labels) {
                            layeredPane.remove(lab);
                        }
                        dialog.getLayeredPane().remove(reset);
                        dialog.getLayeredPane().remove(placeSummon);

                        for (int i = 0; i < placedSummons.size(); i++) {
                            Hex placedHex = placedHexes.get(i);
                            CharacterSummon characterSummon = placedSummons.get(i);
                            scenario.figures.add(characterSummon);
                            scenario.placeFigure(placedHex, characterSummon);
                            summons.add(characterSummon);
                            scenario.layeredPane.add(characterSummon.label, Integer.valueOf(2));
                        }

                        dialog.repaint();

                        SwingUtilities.invokeLater(() -> future.complete(true));
                        if (onComplete != null) {
                            SwingUtilities.invokeLater(onComplete);
                        }
                    }).start()
            );

            reset.addActionListener(_ ->
                    new Thread(() -> {
                        placedHexes.clear();
                        for (JLabel lab : labels) {
                            layeredPane.remove(lab);
                        }
                        labels.clear();
                        for (JLabel transparent : transparentLabels) {
                            transparent.setVisible(false);
                        }
                        layeredPane.repaint();
                        summonHelper(labels, layeredPane, dialog, transparentLabels, placedHexes, placedSummons, placeSummon, reset, number);
                    }).start()
            );
            summonHelper(labels, layeredPane, dialog, transparentLabels, placedHexes, placedSummons, placeSummon, reset, number);
        }).start();
        return future;
    }

    private void summonHelper(ArrayList<JLabel> labels, JLayeredPane layeredPane, JDialog dialog, ArrayList<JLabel> transparentLabels,
                              ArrayList<Hex> placedHexes, ArrayList<CharacterSummon> placedSummons, JButton placeSummon, JButton reset, int number) {
        CountDownLatch latch = new CountDownLatch(1);
        for (Hex adjacentHex : hex.adjacentHexes) {
            if (adjacentHex.validHex && adjacentHex.isEmpty()) {
                JLabel hexLabel = Methods.showHex(adjacentHex);
                hexLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        transparentLabels.get(placedHexes.size()).setLocation(getHexLocation(adjacentHex));
                        transparentLabels.get(placedHexes.size()).setVisible(true);
                        placedHexes.add(adjacentHex);
                        if (placedHexes.size() == number) {
                            for (JLabel lab : labels) {
                                layeredPane.remove(lab);
                            }
                            labels.clear();
                        } else {
                            hexLabel.setVisible(false);
                        }
                        placeSummon.setVisible(true);
                        reset.setVisible(true);
                        layeredPane.repaint();
                    }
                });
                labels.add(hexLabel);
                layeredPane.add(hexLabel, JLayeredPane.PALETTE_LAYER);
            }
        }
        layeredPane.repaint();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void applyPerks() {
        for (int i = 0; i < c.perks.length; i++) {
            if (c.perks[i]) {
                applyPerkNumber(i);
            }
        }
    }

    public abstract void applyPerkNumber(int number);

    void removeFromAMD(String s) {
        for (AttackModifier amd : attackModifierDeck) {
            if (amd.name.equals(s)) {
                attackModifierDeck.remove(amd);
                break;
            }
        }
    }

    @Override
    public abstract String toString();
}