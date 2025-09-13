import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class SO17 extends Event {
    public SO17(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO17.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO17.png", camp);
    }

    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton button1 = new JButton("Continue");
        button1.setBounds(175, 439, 50, 20);
        button1.setFont(Methods.DEFAULTFONT);

        button1.addActionListener(_ ->
        {
            ArrayList<LootCard> deck = new ArrayList<>();

            createDialog(deck, 8);

            buttonClicked(dialog, layeredPane);
            JButton buttonc = new JButton("Continue");
            buttonc.setBounds(108, 221, 55, 20);
            buttonc.setFont(Methods.DEFAULTFONT);
            buttonc.addActionListener(_ ->
            {
                camp.activeSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
                dialog.dispose();
                if (camp.traitInParty("Strong")) {
                    createDialog(deck, 9);
                }
                deck.add(camp.lootCards.remove(camp.findLootCard("Arrowvine")));
                deck.add(camp.lootCards.remove(camp.findLootCard("Axenut")));
                deck.add(camp.lootCards.remove(camp.findLootCard("Rockroot")));

                Collections.shuffle(deck);

                JDialog dialog1 = new JDialog((Frame) null, "Draw Cards", true);
                dialog1.setLayout(null);
                dialog1.setSize(300, 200);
                dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog1.setLocationRelativeTo(null);

                JButton continueButton = new JButton("Finish Drawing");
                continueButton.setBounds(75, 90, 150, 30);

                JButton drawCard = new JButton("Draw Card");
                drawCard.setBounds(75, 50, 150, 30);
                drawCard.addActionListener(_ ->
                {
                    JDialog dialog2 = new JDialog((Frame) null, "Draw Cards", true);
                    dialog2.setLayout(null);
                    dialog2.setSize(268, 437);
                    dialog2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog2.setLocationRelativeTo(null);

                    JLayeredPane layeredPane1 = new JLayeredPane();
                    layeredPane1.setBounds(0, 0, 265, 407);

                    JLabel card = deck.getFirst().getImage();
                    card.setBounds(0, 0, 265, 407);
                    layeredPane1.add(card, JLayeredPane.DEFAULT_LAYER);
                    dialog2.setContentPane(layeredPane1);

                    JButton continueButton2 = new JButton("Continue");
                    continueButton2.setBounds(165, 377, 100, 30);
                    continueButton2.addActionListener(_ ->
                    {
                        if (deck.getFirst().toString().endsWith("Coin")) {
                            camp.distributeThings(3, "Gold");
                        } else if (deck.getFirst().toString().endsWith("Hide")) {
                            camp.distributeThings(1, "Hide");
                        } else if (deck.getFirst().toString().endsWith("Lumber")) {
                            camp.distributeThings(1, "Lumber");
                        } else if (deck.getFirst().toString().endsWith("Metal")) {
                            camp.distributeThings(1, "Metal");
                        } else {
                            camp.increaseMorale(-1);
                        }
                        dialog2.dispose();
                        camp.lootCards.add(deck.removeFirst());
                        if (deck.isEmpty()) {
                            continueButton.doClick();
                        }
                    });
                    layeredPane1.add(continueButton2, JLayeredPane.PALETTE_LAYER);

                    dialog2.setVisible(true);
                    dialog2.repaint();
                    dialog2.revalidate();
                });
                dialog1.add(drawCard);

                continueButton.addActionListener(_ ->
                {
                    dialog1.dispose();
                    while (!deck.isEmpty()) {
                        camp.lootCards.add(deck.removeFirst());
                    }
                });
                dialog1.add(continueButton);

                dialog1.setVisible(true);
                dialog1.repaint();
                dialog1.revalidate();
            });
            layeredPane.add(buttonc, JLayeredPane.PALETTE_LAYER);
        });
        buttons.add(button1);

        return buttons;
    }

    private void createDialog(ArrayList<LootCard> deck, int num) {
        JDialog dialog1 = new JDialog((Frame) null, "Add cards to deck", true);
        dialog1.setLayout(null);
        dialog1.setSize(380, 200);
        dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog1.setLocationRelativeTo(null);

        if (camp.findLootCard("Lumber") != -1) {
            JButton lumber = new JButton("Lumber");
            lumber.setBounds(140, 30, 100, 30);
            lumber.addActionListener(_ ->
            {
                deck.add(camp.lootCards.remove(camp.findLootCard("Lumber")));
                if (deck.size() == num) {
                    dialog1.dispose();
                }
            });
            dialog1.add(lumber);
        }
        if (camp.findLootCard("Metal") != -1) {
            JButton metal = new JButton("Metal");
            metal.setBounds(140, 60, 100, 30);
            metal.addActionListener(_ ->
            {
                deck.add(camp.lootCards.remove(camp.findLootCard("Metal")));
                if (deck.size() == num) {
                    dialog1.dispose();
                }
            });
            dialog1.add(metal);
        }
        if (camp.findLootCard("Hide") != -1) {
            JButton hide = new JButton("Hide");
            hide.setBounds(140, 90, 100, 30);
            hide.addActionListener(_ ->
            {
                deck.add(camp.lootCards.remove(camp.findLootCard("Hide")));
                if (deck.size() == num) {
                    dialog1.dispose();
                }
            });
            dialog1.add(hide);
        }
        if (camp.findLootCard("Coin") != -1) {
            JButton coin = new JButton("Coin");
            coin.setBounds(140, 120, 100, 30);
            coin.addActionListener(_ ->
            {
                deck.add(camp.lootCards.remove(camp.findLootCard("Coin")));
                if (deck.size() == num) {
                    dialog1.dispose();
                }
            });
            dialog1.add(coin);
        }

        dialog1.setVisible(true);
        dialog1.repaint();
        dialog1.revalidate();
    }
}