import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;

public class Campaign {
    public int week;
    public boolean summer;
    public int lumber;
    public int metal;
    public int hide;
    public int arrowvine;
    public int axenut;
    public int corpsecap;
    public int flamefruit;
    public int rockroot;
    public int snowthistle;
    public int inspiration;
    public int defense;
    public int soldiers;
    public int soldierCapacity;
    public int morale;
    public int prosperity;
    public int prosperityBoxes;
    public final int[] PROSPPOS = {0, 6, 15, 27, 42, 60, 81, 105, 132};
    public final ArrayList<Integer> BUILDINGSNUMS = new ArrayList<>(Arrays.asList(5, 12, 17, 21, 24, 34, 35, 37, 39, 42, 44, 65, 67, 72, 74, 81, 83, 84, 85, 88, 90, 98));
    public final ArrayList<Integer> ODDBUILDINGSNUMS = new ArrayList<>(Arrays.asList(5, 17, 21, 35, 37, 39, 65, 67, 81, 83, 85));
    public final ArrayList<Integer> EVENBUILDINGSNUMS = new ArrayList<>(Arrays.asList(12, 24, 34, 42, 44, 72, 74, 84, 88, 90, 98));
    public ArrayList<String> campaignStickers;
    public ArrayList<Building> unlockedBuildings;
    public ArrayList<Building> lockedBuildings;
    public ArrayList<Building> walls;
    public ArrayList<Event> activeSummerOutpost;
    public ArrayList<Event> inactiveSummerOutpost;
    public ArrayList<Event> activeSummerRoad;
    public ArrayList<Event> inactiveSummerRoad;
    public ArrayList<Event> activeWinterOutpost;
    public ArrayList<Event> inactiveWinterOutpost;
    public ArrayList<Event> activeWinterRoad;
    public ArrayList<Event> inactiveWinterRoad;
    public ArrayList<Event> activeBoat;
    public ArrayList<Event> inactiveBoat;
    public ArrayList<Character> activeCharacters;
    public ArrayList<ArrayList<Double>> calendarSections;
    public ArrayList<Item> availableItems;
    public ArrayList<Item> unavailableItems;
    public ArrayList<AttackModifier> townGuardDeck;
    public ArrayList<AttackModifier> townGuardDeckDiscard;
    public ArrayList<String> constructionModifiers;
    public ArrayList<String> buildingOperationsModifiers;
    public ArrayList<LootCard> lootCards;
    public final int[] goldConversion = {2, 2, 3, 3, 4, 4, 5, 6};
    public int scenarioLevel;
    public int diffScenarioLevel;
    public boolean boat;
    public boolean sled;
    public boolean climbingGear;

    public Campaign() {
        week = 8;
        summer = true;
        lumber = 0;
        metal = 0;
        hide = 0;
        arrowvine = 0;
        axenut = 0;
        corpsecap = 0;
        flamefruit = 0;
        rockroot = 0;
        snowthistle = 0;
        inspiration = 0;
        defense = 0;
        soldiers = 4;
        soldierCapacity = 4;
        morale = 0;
        prosperity = 1;
        prosperityBoxes = 0;
        campaignStickers = new ArrayList<>();
        boat = false;
        sled = false;
        climbingGear = false;
        scenarioLevel = 1;
        diffScenarioLevel = 0;

        lootCards = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            lootCards.add(new Coin1(this));
        }
        for (int i = 0; i < 3; i++) {
            lootCards.add(new Lumber22(this));
            lootCards.add(new Lumber23(this));
            lootCards.add(new Metal22(this));
            lootCards.add(new Metal23(this));
            lootCards.add(new Hide22(this));
            lootCards.add(new Hide23(this));
        }
        for (int i = 0; i < 6; i++) {
            lootCards.add(new Coin2(this));
        }
        for (int i = 0; i < 2; i++) {
            lootCards.add(new Lumber1(this));
            lootCards.add(new Metal1(this));
            lootCards.add(new Hide1(this));
            lootCards.add(new Arrowvine(this));
            lootCards.add(new Axenut(this));
            lootCards.add(new Corpsecap(this));
            lootCards.add(new Flamefruit(this));
            lootCards.add(new Rockroot(this));
            lootCards.add(new Snowthistle(this));
            lootCards.add(new Coin3(this));
        }
        lootCards.add(new RandomItem(this));

        unlockedBuildings = new ArrayList<>();
        lockedBuildings = new ArrayList<>();
        unlockedBuildings.add(new MiningCamp(this));
        unlockedBuildings.add(new HuntingLodge(this));
        unlockedBuildings.add(new LoggingCamp(this));
        unlockedBuildings.getFirst().increaseLevel();
        unlockedBuildings.get(1).increaseLevel();
        unlockedBuildings.get(2).increaseLevel();
        lockedBuildings.add(new Inn(this));
        lockedBuildings.add(new Garden(this));
        unlockedBuildings.add(new Craftsman(this));
        unlockedBuildings.get(3).increaseLevel();
        unlockedBuildings.add(new Alchemist(this));
        unlockedBuildings.get(4).increaseLevel();
        lockedBuildings.add(new TradingPost(this));
        lockedBuildings.add(new Jeweler(this));
        lockedBuildings.add(new TempleoftheGreatOak(this));
        lockedBuildings.add(new Enhancer(this));
        lockedBuildings.add(new MetalDepot(this));
        lockedBuildings.add(new LumberDepot(this));
        lockedBuildings.add(new HideDepot(this));
        lockedBuildings.add(new Tavern(this));
        lockedBuildings.add(new HallofRevelry(this));
        lockedBuildings.add(new Library(this));
        unlockedBuildings.add(new Workshop(this));
        unlockedBuildings.get(5).increaseLevel();
        lockedBuildings.add(new Carpenter(this));
        lockedBuildings.add(new Stables(this));
        lockedBuildings.add(new TownHall(this));
        unlockedBuildings.add(new Barracks(this));
        unlockedBuildings.get(6).increaseLevel();
        walls = new ArrayList<>();
        walls.add(new WallJ(this));
        walls.add(new WallK(this));
        walls.add(new WallL(this));
        walls.add(new WallM(this));
        walls.add(new WallN(this));

        constructionModifiers = new ArrayList<>();
        buildingOperationsModifiers = new ArrayList<>();

        activeSummerOutpost = new ArrayList<>();
        inactiveSummerOutpost = new ArrayList<>();
        activeWinterOutpost = new ArrayList<>();
        inactiveWinterOutpost = new ArrayList<>();

        activeSummerRoad = new ArrayList<>();
        inactiveSummerRoad = new ArrayList<>();
        activeWinterRoad = new ArrayList<>();
        inactiveWinterRoad = new ArrayList<>();

        activeBoat = new ArrayList<>();
        inactiveBoat = new ArrayList<>();

        activeCharacters = new ArrayList<>();
        activeCharacters.add(new BannerSpear(this));
        activeCharacters.getFirst().level = 2;
        activeCharacters.add(new BannerSpear(this));
        activeCharacters.add(new BannerSpear(this));
        activeCharacters.add(new BannerSpear(this));

        activeSummerOutpost.add(new SO1(this));
        activeSummerOutpost.add(new SO2(this));
        activeSummerOutpost.add(new SO3(this));
        activeSummerOutpost.add(new SO4(this));
        activeSummerOutpost.add(new SO5(this));
        activeSummerOutpost.add(new SO6(this));
        activeSummerOutpost.add(new SO7(this));
        activeSummerOutpost.add(new SO8(this));
        activeSummerOutpost.add(new SO9(this));
        activeSummerOutpost.add(new SO10(this));
        activeSummerOutpost.add(new SO11(this));
        activeSummerOutpost.add(new SO12(this));
        activeSummerOutpost.add(new SO13(this));
        activeSummerOutpost.add(new SO14(this));
        activeSummerOutpost.add(new SO15(this));
        activeSummerOutpost.add(new SO16(this));
        activeSummerOutpost.add(new SO17(this));
        activeSummerOutpost.add(new SO18(this));
        activeSummerOutpost.add(new SO19(this));
        activeSummerOutpost.add(new SO20(this));
        inactiveSummerOutpost.add(new SO21(this));
        inactiveSummerOutpost.add(new SO22(this));
        inactiveSummerOutpost.add(new SO23(this));
        inactiveSummerOutpost.add(new SO24(this));
        inactiveSummerOutpost.add(new SO25(this));
        inactiveSummerOutpost.add(new SO26(this));
        inactiveSummerOutpost.add(new SO27(this));
        inactiveSummerOutpost.add(new SO28(this));
        inactiveSummerOutpost.add(new SO29(this));
        inactiveSummerOutpost.add(new SO30(this));
        inactiveSummerOutpost.add(new SO31(this));
        inactiveSummerOutpost.add(new SO32(this));
        inactiveSummerOutpost.add(new SO33(this));
        inactiveSummerOutpost.add(new SO34(this));
        inactiveSummerOutpost.add(new SO35(this));
        inactiveSummerOutpost.add(new SO36(this));
        inactiveSummerOutpost.add(new SO37(this));
        inactiveSummerOutpost.add(new SO38(this));
        inactiveSummerOutpost.add(new SO39(this));
        inactiveSummerOutpost.add(new SO40(this));
        inactiveSummerOutpost.add(new SO41(this));
        inactiveSummerOutpost.add(new SO42(this));
        inactiveSummerOutpost.add(new SO43(this));
        inactiveSummerOutpost.add(new SO44(this));
        inactiveSummerOutpost.add(new SO45(this));
        inactiveSummerOutpost.add(new SO46(this));
        inactiveSummerOutpost.add(new SO47(this));
        inactiveSummerOutpost.add(new SO48(this));
        inactiveSummerOutpost.add(new SO49(this));
        inactiveSummerOutpost.add(new SO50(this));
        inactiveSummerOutpost.add(new SO51(this));
        inactiveSummerOutpost.add(new SO52(this));
        inactiveSummerOutpost.add(new SO53(this));
        inactiveSummerOutpost.add(new SO54(this));
        inactiveSummerOutpost.add(new SO55(this));
        inactiveSummerOutpost.add(new SO56(this));
        inactiveSummerOutpost.add(new SO57(this));
        inactiveSummerOutpost.add(new SO58(this));
        inactiveSummerOutpost.add(new SO59(this));
        inactiveSummerOutpost.add(new SO60(this));
        inactiveSummerOutpost.add(new SO61(this));
        inactiveSummerOutpost.add(new SO62(this));
        inactiveSummerOutpost.add(new SO63(this));
        inactiveSummerOutpost.add(new SO64(this));
        inactiveSummerOutpost.add(new SO65(this));

        activeWinterOutpost.add(new WO1(this));
        activeWinterOutpost.add(new WO2(this));
        activeWinterOutpost.add(new WO3(this));
        activeWinterOutpost.add(new WO4(this));
        activeWinterOutpost.add(new WO5(this));
        activeWinterOutpost.add(new WO6(this));
        activeWinterOutpost.add(new WO7(this));
        activeWinterOutpost.add(new WO8(this));
        activeWinterOutpost.add(new WO9(this));
        activeWinterOutpost.add(new WO10(this));
        activeWinterOutpost.add(new WO11(this));
        activeWinterOutpost.add(new WO12(this));
        activeWinterOutpost.add(new WO13(this));
        activeWinterOutpost.add(new WO14(this));
        activeWinterOutpost.add(new WO15(this));
        activeWinterOutpost.add(new WO16(this));
        activeWinterOutpost.add(new WO17(this));
        activeWinterOutpost.add(new WO18(this));
        activeWinterOutpost.add(new WO19(this));
        activeWinterOutpost.add(new WO20(this));
        inactiveWinterOutpost.add(new WO21(this));
        inactiveWinterOutpost.add(new WO22(this));
        inactiveWinterOutpost.add(new WO23(this));
        inactiveWinterOutpost.add(new WO24(this));
        inactiveWinterOutpost.add(new WO25(this));
        inactiveWinterOutpost.add(new WO26(this));
        inactiveWinterOutpost.add(new WO27(this));
        inactiveWinterOutpost.add(new WO28(this));
        inactiveWinterOutpost.add(new WO29(this));
        inactiveWinterOutpost.add(new WO30(this));
        inactiveWinterOutpost.add(new WO31(this));
        inactiveWinterOutpost.add(new WO32(this));
        inactiveWinterOutpost.add(new WO33(this));
        inactiveWinterOutpost.add(new WO34(this));
        inactiveWinterOutpost.add(new WO35(this));
        inactiveWinterOutpost.add(new WO36(this));
        inactiveWinterOutpost.add(new WO37(this));
        inactiveWinterOutpost.add(new WO38(this));
        inactiveWinterOutpost.add(new WO39(this));
        inactiveWinterOutpost.add(new WO40(this));
        inactiveWinterOutpost.add(new WO41(this));
        inactiveWinterOutpost.add(new WO42(this));
        inactiveWinterOutpost.add(new WO43(this));
        inactiveWinterOutpost.add(new WO44(this));
        inactiveWinterOutpost.add(new WO45(this));
        inactiveWinterOutpost.add(new WO46(this));
        inactiveWinterOutpost.add(new WO47(this));
        inactiveWinterOutpost.add(new WO48(this));
        inactiveWinterOutpost.add(new WO49(this));
        inactiveWinterOutpost.add(new WO50(this));
        inactiveWinterOutpost.add(new WO51(this));
        inactiveWinterOutpost.add(new WO52(this));
        inactiveWinterOutpost.add(new WO53(this));
        inactiveWinterOutpost.add(new WO54(this));
        inactiveWinterOutpost.add(new WO55(this));
        inactiveWinterOutpost.add(new WO56(this));
        inactiveWinterOutpost.add(new WO57(this));
        inactiveWinterOutpost.add(new WO58(this));
        inactiveWinterOutpost.add(new WO59(this));
        inactiveWinterOutpost.add(new WO60(this));
        inactiveWinterOutpost.add(new WO61(this));
        inactiveWinterOutpost.add(new WO62(this));
        inactiveWinterOutpost.add(new WO63(this));
        inactiveWinterOutpost.add(new WO64(this));
        inactiveWinterOutpost.add(new WO65(this));
        inactiveWinterOutpost.add(new WO66(this));
        inactiveWinterOutpost.add(new WO67(this));
        inactiveWinterOutpost.add(new WO68(this));
        inactiveWinterOutpost.add(new WO69(this));
        inactiveWinterOutpost.add(new WO70(this));
        inactiveWinterOutpost.add(new WO71(this));
        inactiveWinterOutpost.add(new WO72(this));
        inactiveWinterOutpost.add(new WO73(this));
        inactiveWinterOutpost.add(new WO74(this));
        inactiveWinterOutpost.add(new WO75(this));
        inactiveWinterOutpost.add(new WO76(this));
        inactiveWinterOutpost.add(new WO77(this));
        inactiveWinterOutpost.add(new WO78(this));
        inactiveWinterOutpost.add(new WO79(this));
        inactiveWinterOutpost.add(new WO80(this));
        inactiveWinterOutpost.add(new WO81(this));

        calendarSections = new ArrayList<>();
        for (int i = 0; i < 120; i++) {
            calendarSections.add(new ArrayList<>());
        }

        calendarSections.get(5).add(32.3);
        calendarSections.get(10).add(183.3);
        calendarSections.get(10).add(21.4);
        calendarSections.get(20).add(129.3);
        calendarSections.get(25).add(183.3);
        calendarSections.get(30).add(183.3);
        calendarSections.get(40).add(184.1);
        calendarSections.get(80).add(137.2);

        availableItems = new ArrayList<>();
        unavailableItems = new ArrayList<>();

        activeCharacters.getFirst().gold = 5;
        activeCharacters.get(1).gold = 5;
        activeCharacters.get(2).gold = 12;
        activeCharacters.get(3).gold = 5;
        lumber = 4;
        metal = 10;
        hide = 4;
        arrowvine = 4;
        axenut = 4;
        rockroot = 4;
        corpsecap = 4;
        snowthistle = 4;
        flamefruit = 4;
        morale = 11;
        inspiration = 2;

        townGuardDeck = new ArrayList<>();

        townGuardDeck.add(new AttackModifier("Wreck", 0, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/Wreck.png"));
        townGuardDeck.add(new AttackModifier("-20", -20, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/-20.png"));
        townGuardDeck.add(new AttackModifier("-10", -10, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/-10.png"));
        townGuardDeck.add(new AttackModifier("-10", -10, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/-10.png"));
        townGuardDeck.add(new AttackModifier("-10", -10, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/-10.png"));
        townGuardDeck.add(new AttackModifier("-10", -10, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/-10.png"));
        townGuardDeck.add(new AttackModifier("-10", -10, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/-10.png"));
        townGuardDeck.add(new AttackModifier("+0", 0, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+0.png"));
        townGuardDeck.add(new AttackModifier("+0", 0, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+0.png"));
        townGuardDeck.add(new AttackModifier("+0", 0, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+0.png"));
        townGuardDeck.add(new AttackModifier("+0", 0, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+0.png"));
        townGuardDeck.add(new AttackModifier("+0", 0, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+0.png"));
        townGuardDeck.add(new AttackModifier("+0", 0, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+0.png"));
        townGuardDeck.add(new AttackModifier("+10", 10, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+10.png"));
        townGuardDeck.add(new AttackModifier("+10", 10, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+10.png"));
        townGuardDeck.add(new AttackModifier("+10", 10, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+10.png"));
        townGuardDeck.add(new AttackModifier("+10", 10, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+10.png"));
        townGuardDeck.add(new AttackModifier("+10", 10, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+10.png"));
        townGuardDeck.add(new AttackModifier("+20", 20, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/+20.png"));
        townGuardDeck.add(new AttackModifier("Success", 0, "/Users/saahilherrero/Downloads/Images/Town Guard Deck/Success.png"));

        townGuardDeckDiscard = new ArrayList<>();

        Collections.shuffle(townGuardDeck);
    }

    public void passageOfTime(boolean fromOutpostPhase) {
        week++;
        if (week % 10 == 0) {
            summer = !summer;
        }
        if (fromOutpostPhase) {
            for (int i = 0; i < calendarSections.get(week).size(); i++) {
                System.out.println("(to be done): Read Section " + calendarSections.get(week).get(i));
            }
        } else {
            while (calendarSections.get(week).isEmpty()) {
                calendarSections.get(week + 1).add(calendarSections.get(week).removeFirst());
            }
        }
    }

    public void outpostEvent() {
        if (summer) {
            activeSummerOutpost.getFirst().doEvent();
        } else {
            activeWinterOutpost.getFirst().doEvent();
        }
    }

    public void increaseSoldiers(int num) {
        soldiers += num;
        if (soldiers > soldierCapacity) {
            soldiers = soldierCapacity;
        }
        if (soldiers < 0) {
            soldiers = 0;
        }
    }

    public static void main(String[] args) {
        Campaign camp = new Campaign();
        camp.outpostPhase();
    }

    public void buildingOperations() {
        ArrayList<Building> hasSun = new ArrayList<>();
        for (Building building : unlockedBuildings) {
            if (building.hasBuildingOperations()) {
                hasSun.add(building);
            }
        }

        for (int i = 0; i < hasSun.size(); i++) {
            JDialog dialog = new JDialog((Frame) null, "Building Effect", true);
            dialog.setLayout(null);
            dialog.setSize(380, 550);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setLocationRelativeTo(null);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setBounds(0, 0, 375, 525);

            JLabel label1;

            String image;

            if (hasSun.get(i).wrecked) {
                image = hasSun.get(i).backImages[hasSun.get(i).level - 1];
            } else {
                image = hasSun.get(i).frontImages[hasSun.get(i).level - 1];
            }
            label1 = Methods.createImage(image, 50);
            label1.setBounds(0, 0, 375, 525);

            layeredPane.add(label1, JLayeredPane.DEFAULT_LAYER);

            dialog.setContentPane(layeredPane);

            hasSun.get(i).buildingOperations(layeredPane, dialog);
            dialog.revalidate();
            dialog.repaint();
            dialog.setVisible(true);

            if (buildingOperationsModifiers.contains("Repeat 5")) {
                buildingOperationsModifiers.remove("Repeat 5");
                i--;
            }
            if (buildingOperationsModifiers.contains("Repeat 12")) {
                buildingOperationsModifiers.remove("Repeat 12");
                i--;
            }
            if (buildingOperationsModifiers.contains("Repeat 17")) {
                buildingOperationsModifiers.remove("Repeat 17");
                i--;
            }
        }
        buildingOperationsModifiers.clear();
    }

    public void downtime() {
        JDialog dialog = new JDialog((Frame) null, "Select Character to perform downtime", true);
        dialog.setSize(380, 450);
        dialog.setLayout(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        //add check to see if any pqs are complete
        JButton continueButton = new JButton("Continue to construction");
        continueButton.setBounds(37, 200, 300, 30);

        int[] purchasedItems = {0};

        for (int i = 0; i < activeCharacters.size(); i++) {
            downtimeHelper(dialog, i, purchasedItems);
        }

        continueButton.addActionListener(_ ->
                dialog.dispose());

        dialog.add(continueButton);
        dialog.setVisible(true);
    }

    private void downtimeHelper(JDialog dialog, int num, int[] purchasedItems) {
        Character c = activeCharacters.get(num);
        JButton class1 = new JButton(c.toString());
        class1.setBounds(20 + 180 * (num % 2), 25 + 75 * (num / 2), 150, 30);
        class1.addActionListener(_ ->
        {
            JDialog dialog1 = new JDialog((Frame) null, "Select Downtime Action", true);
            dialog1.setSize(380, 600);
            dialog1.setLayout(null);
            dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog1.setLocationRelativeTo(null);
            int index = 0;
            if (c.level < 9 && (c.xp >= c.LEVELXP[c.level] || c.level < prosperity / 2 + 1)) {
                JButton levelUp = new JButton("Level Up");
                levelUp.setBounds(150, 0, 180, 30);
                levelUp.addActionListener(_ ->
                        c.levelUp());
                dialog1.add(levelUp);
                index++;
            }
            //if (c.personalQuest complete) (to be done)
                    /*if (false)
                    {
                        JButton retire = new JButton("Retire");
                        retire.setBounds(150, 40*index, 180, 30);
                        retire.addActionListener(_ ->
                                {
                                    // to be done
                            });
                        dialog1.add(retire);
                        index++;
                    } */
            if (!unlockedBuildings.get(findBuildingInUnlocked(34)).wrecked) {
                JButton craft = new JButton("Craft Items");
                craft.setBounds(150, 40 * index, 180, 30);
                craft.addActionListener(_ ->
                {
                    // to be done
                });
                dialog1.add(craft);
                index++;
            }
            if (!unlockedBuildings.get(findBuildingInUnlocked(35)).wrecked) {
                JButton brew = new JButton("Brew Potions");
                brew.setBounds(150, 40 * index, 180, 30);
                brew.addActionListener(_ ->
                {
                    // to be done
                });
                dialog1.add(brew);
                index++;
            }
            if (!unlockedBuildings.get(findBuildingInUnlocked(35)).wrecked && unlockedBuildings.get(findBuildingInUnlocked(35)).level >= 2) {
                JButton distill = new JButton("Distill Potions");
                distill.setBounds(150, 40 * index, 180, 30);
                distill.addActionListener(_ ->
                {
                    // to be done
                });
                dialog1.add(distill);
                index++;
            }
            JButton sell = new JButton("Sell Items");
            sell.setBounds(150, 40 * index, 180, 30);
            sell.addActionListener(_ ->
            {
                // to be done
            });
            dialog1.add(sell);
            index++;
            if (findBuildingInUnlocked(37) != -1 && !unlockedBuildings.get(findBuildingInUnlocked(37)).wrecked
                    && purchasedItems[0] < unlockedBuildings.get(findBuildingInUnlocked(37)).level) {
                JButton purchase = new JButton("Purchase Items");
                purchase.setBounds(150, 40 * index, 180, 30);
                purchase.addActionListener(_ ->
                {
                    // to be done
                    purchasedItems[0]++;
                });
                dialog1.add(purchase);
                index++;
            }
            if (findBuildingInUnlocked(44) != -1 && !unlockedBuildings.get(findBuildingInUnlocked(37)).wrecked) {
                JButton enhance = new JButton("Purchase Enhancements");
                enhance.setBounds(150, 40 * index, 180, 30);
                enhance.addActionListener(_ ->
                {
                    // to be done
                });
                dialog1.add(enhance);
                index++;
            }

            JButton continueButton = new JButton("Continue");
            continueButton.setBounds(150, 40 * index + 20, 180, 30);
            continueButton.addActionListener(_ ->
                    dialog1.dispose());
            dialog1.add(continueButton);
        });
        dialog.add(class1);
    }

    public void construction() {
        boolean[] carpenter = {false};
        if (findBuildingInUnlocked(85) != -1 && unlockedBuildings.get(findBuildingInUnlocked(85)).level != 0 &&
                !unlockedBuildings.get(findBuildingInUnlocked(85)).wrecked) {
            carpenter[0] = true;
        }
        boolean[] carpenter2 = {false};
        if (carpenter[0] && unlockedBuildings.get(findBuildingInUnlocked(85)).level > 1) {
            carpenter2[0] = true;
        }
        JDialog dialog = new JDialog((Frame) null, "Construction", true);
        dialog.setLayout(null);
        dialog.setSize(380, 550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(140, 480, 100, 30);
        dialog.add(continueButton);
        continueButton.addActionListener(_ ->
                dialog.dispose());
        constructionHelper(dialog, carpenter[0], 0);
        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();

        if (morale >= 2 || (morale == 1 && carpenter2[0])) {
            int moraleCost = 2;
            if (carpenter2[0]) {
                moraleCost = 1;
            }
            JDialog dialog1 = new JDialog((Frame) null, "Pay " + moraleCost + " for Construction", true);
            dialog1.setLayout(null);
            dialog1.setSize(380, 550);
            dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog1.setLocationRelativeTo(null);
            JButton continueButton1 = new JButton("Continue");
            continueButton1.setBounds(140, 480, 100, 30);
            dialog1.add(continueButton1);
            continueButton1.addActionListener(_ ->
                    dialog1.dispose());
            dialog1.setVisible(true);
            dialog1.repaint();
            dialog1.revalidate();
            constructionHelper(dialog1, carpenter[0], moraleCost);
        }
        rebuildHelper(carpenter[0]);
        constructionModifiers.clear();
    }

    private void constructionHelper(JDialog dialog, boolean carpenter, int moraleCost) {
        ArrayList<Building> upgradeAvailable = new ArrayList<>();
        for (Building b : unlockedBuildings) {
            if (b.upgradeAvailable(carpenter)) {
                upgradeAvailable.add(b);
            }
        }
        for (Building wall : walls) {
            if (wall.upgradeAvailable(carpenter)) {
                upgradeAvailable.add(wall);
            }
        }

        for (int i = 0; i < upgradeAvailable.size(); i++) {
            upgradeAvailable.get(i).buttonForConstruction(dialog, i, carpenter, moraleCost);
        }
        if (upgradeAvailable.isEmpty()) {
            dialog.dispose();
        }
    }

    public void rebuildHelper(boolean carpenter) {
        JDialog dialog = new JDialog((Frame) null, "Rebuild", true);
        dialog.setLayout(null);
        dialog.setSize(380, 550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        ArrayList<Building> rebuildAvailable = new ArrayList<>();
        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(140, 480, 100, 30);
        dialog.add(continueButton);
        continueButton.addActionListener(_ ->
                dialog.dispose());
        for (Building unlockedBuilding : unlockedBuildings) {
            if (unlockedBuilding.rebuildAvailable(carpenter)) {
                rebuildAvailable.add(unlockedBuilding);
            }
        }
        boolean[] reduceCostTwo = {false};
        JButton reduceCost = new JButton("Reduce cost by 2: Off");
        reduceCost.setBounds(100, 25 + 40 * rebuildAvailable.size(), 180, 30);
        dialog.add(reduceCost);

        reduceCost.addActionListener(_ ->
        {
            reduceCostTwo[0] = !reduceCostTwo[0];
            if (reduceCostTwo[0]) {
                reduceCost.setText("Reduce cost by 2: On");
            } else {
                reduceCost.setText("Reduce cost by 2: Off");
            }
        });
        if (!constructionModifiers.contains("Reduce one rebuild cost by 2")) {
            reduceCost.setVisible(false);
        }
        for (int i = 0; i < rebuildAvailable.size(); i++) {
            rebuildAvailable.get(i).buttonForRebuild(dialog, i, carpenter, reduceCostTwo);
        }
        if (rebuildAvailable.isEmpty()) {
            dialog.dispose();
        }
        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();
    }

    public void outpostPhase() {
        passageOfTime(true);
        outpostEvent();
        buildingOperations();
        downtime();
        construction();
    }

    public void increaseMorale(int increase) {
        morale += increase;
        if (morale >= 20) {
            morale = 20;
            System.out.println("Read Section 161.3 first time (to be done)");
        }
        if (morale <= 0) {
            morale = 0;
            System.out.println("Read Section 126.1 first time (to be done)");
        }
    }

    public int getDefenseBonusFromMorale() {
        if (morale >= 14) {
            return 15;
        } else if (morale >= 11) {
            return 10;
        } else if (morale >= 8) {
            return 5;
        } else if (morale >= 5) {
            return 0;
        } else if (morale >= 3) {
            return -5;
        } else {
            return -10;
        }
    }

    public void gainInspiration(int num) {
        inspiration += num;
        if (inspiration < 0) {
            inspiration = 0;
        }
    }

    public void increaseProsperityBoxes(int increase) {
        prosperityBoxes += increase;
        if (prosperityBoxes < PROSPPOS[prosperity - 1]) {
            prosperityBoxes = PROSPPOS[prosperity - 1];
        }
        if (prosperityBoxes >= PROSPPOS[prosperity]) {
            prosperity++;
        }

    }

    public void distributeThings(int maxi, String type) {
        distributeThings(maxi, type, false);
    }

    public void distributeThings(int maxi, String type, boolean fromBuilding) {
        if (maxi != 0) {
            int[] max = new int[1];
            if (maxi < 0) {
                max[0] = Math.max(maxi, -countTotal(type, fromBuilding));
            } else {
                max[0] = maxi;
            }
            int num = activeCharacters.size();
            if (!type.equals("Gold")) {
                num++;
            }
            if (fromBuilding) {
                num++;
            }
            int[] trackers = new int[num];
            JDialog dialog = new JDialog((Frame) null, "Distribute " + maxi + " " + type, true);
            dialog.setLayout(null);
            dialog.setSize(380, 550);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setLocationRelativeTo(null);
            JButton continueButton = new JButton("Continue");
            continueButton.setBounds(100, 60 + 70 * trackers.length, 100, 30);
            continueButton.setVisible(false);
            for (int i = 0; i < activeCharacters.size(); i++) {
                distributeThingsButtonHelper(dialog, max, i, trackers, continueButton, type);
            }
            if (!type.equals("Gold")) {
                distributeThingsButtonHelper(dialog, max, activeCharacters.size(), trackers, continueButton, type);
            }
            if (fromBuilding) {
                distributeThingsButtonHelper(dialog, max, activeCharacters.size() + 1, trackers, continueButton, "Inspiration");
            }
            continueButton.addActionListener(_ ->
            {
                switch (type) {
                    case "Gold" -> goldChange(trackers);
                    case "Lumber" -> lumberChange(trackers);
                    case "Metal" -> metalChange(trackers);
                    case "Hide" -> hideChange(trackers);
                }
                if (fromBuilding) {
                    gainInspiration(trackers[trackers.length - 1]);
                }
                dialog.dispose();
            });
            dialog.add(continueButton);
            dialog.repaint();
            dialog.revalidate();
            dialog.setVisible(true);
        }
    }

    private void distributeThingsButtonHelper(JDialog dialog, int[] max, int num, int[] trackers, JButton continueButton, String type) {
        String[] label = {""};
        if (num < activeCharacters.size()) {
            label[0] = activeCharacters.get(num).toString();
        } else if (num == activeCharacters.size()) {
            label[0] = "Campaign";
        } else {
            label[0] = "Inspiration";
        }
        JLabel label1 = new JLabel(label[0] + ": 0");
        label1.setBounds(60, 30 + 70 * num, 150, 20);
        dialog.add(label1);

        JButton increas_ = new JButton("+");
        increas_.setBounds(170, 25 + 70 * num, 50, 30);
        JButton decreas_ = new JButton("-");
        decreas_.setBounds(10, 25 + 70 * num, 50, 30);

        increas_.addActionListener(_ ->
        {
            int total = 0;
            for (int tracker : trackers) {
                total += tracker;
            }
            if (max[0] > 0) {
                if (total < max[0]) {
                    trackers[num]++;
                    total++;
                }
            } else if (trackers[num] != 0) {
                trackers[num]++;
                total++;
            }
            label1.setText(label[0] + ": " + trackers[num]);
            continueButton.setVisible(total == max[0]);
        });
        decreas_.addActionListener(_ ->
        {
            if (max[0] > 0) {
                if (trackers[num] != 0) {
                    trackers[num]--;
                }
            } else if (notBelow(trackers, num, type)) {
                trackers[num]--;
            }
            label1.setText(label[0] + ": " + trackers[num]);
            int total = 0;
            for (int tracker : trackers) {
                total += tracker;
            }
            continueButton.setVisible(total == max[0]);

        });
        dialog.add(increas_);
        dialog.add(decreas_);
    }

    public void goldChange(int[] trackers) {
        for (int i = 0; i < activeCharacters.size() - 1; i++) {
            activeCharacters.get(i).gold += trackers[i];
        }
    }

    public void lumberChange(int[] trackers) {
        for (int i = 0; i < activeCharacters.size() - 1; i++) {
            activeCharacters.get(i).lumber += trackers[i];
        }
        lumber += trackers[activeCharacters.size()];
    }

    public void metalChange(int[] trackers) {
        for (int i = 0; i < activeCharacters.size() - 1; i++) {
            activeCharacters.get(i).metal += trackers[i];
        }
        metal += trackers[activeCharacters.size()];
    }

    public void hideChange(int[] trackers) {
        for (int i = 0; i < activeCharacters.size() - 1; i++) {
            activeCharacters.get(i).hide += trackers[i];
        }
        hide += trackers[activeCharacters.size()];
    }

    public void snowthistleChange(int[] trackers) {
        for (int i = 0; i < activeCharacters.size() - 1; i++) {
            activeCharacters.get(i).snowthistle += trackers[i];
        }
        snowthistle += trackers[activeCharacters.size()];
    }

    public void arrowvineChange(int[] trackers) {
        for (int i = 0; i < activeCharacters.size() - 1; i++) {
            activeCharacters.get(i).arrowvine += trackers[i];
        }
        arrowvine += trackers[activeCharacters.size()];
    }

    public void axenutChange(int[] trackers) {
        for (int i = 0; i < activeCharacters.size() - 1; i++) {
            activeCharacters.get(i).axenut += trackers[i];
        }
        axenut += trackers[activeCharacters.size()];
    }

    public void flamefruitChange(int[] trackers) {
        for (int i = 0; i < activeCharacters.size() - 1; i++) {
            activeCharacters.get(i).flamefruit += trackers[i];
        }
        flamefruit += trackers[activeCharacters.size()];
    }

    public void rockrootChange(int[] trackers) {
        for (int i = 0; i < activeCharacters.size() - 1; i++) {
            activeCharacters.get(i).rockroot += trackers[i];
        }
        rockroot += trackers[activeCharacters.size()];
    }

    public void corpsecapChange(int[] trackers) {
        for (int i = 0; i < activeCharacters.size() - 1; i++) {
            activeCharacters.get(i).corpsecap += trackers[i];
        }
        corpsecap += trackers[activeCharacters.size()];
    }

    public int countTotal(String type) {
        return countTotal(type, false);
    }

    public int countTotal(String type, boolean fromBuilding) {
        int count = 0;
        if (type.equals("Gold")) {
            for (Character activeCharacter : activeCharacters) {
                count += activeCharacter.gold;
            }
        }
        if (type.equals("Lumber") || type.equals("Material")) {
            for (Character activeCharacter : activeCharacters) {
                count += activeCharacter.lumber;
            }
            count += lumber;
        }
        if (type.equals("Metal") || type.equals("Material")) {
            for (Character activeCharacter : activeCharacters) {
                count += activeCharacter.metal;
            }
            count += metal;
        }
        if (type.equals("Hide") || type.equals("Material")) {
            for (Character activeCharacter : activeCharacters) {
                count += activeCharacter.hide;
            }
            count += hide;
        }
        if (type.equals("Snowthistle") || type.equals("Herb")) {
            for (Character activeCharacter : activeCharacters) {
                count += activeCharacter.snowthistle;
            }
            count += snowthistle;
        }
        if (type.equals("Arrowvine") || type.equals("Herb")) {
            for (Character activeCharacter : activeCharacters) {
                count += activeCharacter.arrowvine;
            }
            count += arrowvine;
        }
        if (type.equals("Axenut") || type.equals("Herb")) {
            for (Character activeCharacter : activeCharacters) {
                count += activeCharacter.axenut;
            }
            count += axenut;
        }
        if (type.equals("Rockroot") || type.equals("Herb")) {
            for (Character activeCharacter : activeCharacters) {
                count += activeCharacter.rockroot;
            }
            count += rockroot;
        }
        if (type.equals("Corpsecap") || type.equals("Herb")) {
            for (Character activeCharacter : activeCharacters) {
                count += activeCharacter.corpsecap;
            }
            count += corpsecap;
        }
        if (type.equals("Flamefruit") || type.equals("Herb")) {
            for (Character activeCharacter : activeCharacters) {
                count += activeCharacter.flamefruit;
            }
            count += flamefruit;
        }
        if (fromBuilding) {
            count += inspiration;
        }
        return count;
    }

    public boolean notBelow(int[] trackers, int num, String type) {
        return notBelow(trackers, num, type, num);
    }

    public boolean notBelow(int[] trackers, int num, String type, int num2) {
        if (type.equals("Inspiration")) {
            return trackers[num] > -inspiration;
        } else if (type.equals("Gold")) {
            return trackers[num] > -activeCharacters.get(num2).gold;
        } else if (num == activeCharacters.size() || (num2 != num && num >= trackers.length - 3)) {
            switch (type) {
                case "Metal" -> {
                    return trackers[num] > -metal;
                }
                case "Hide" -> {
                    return trackers[num] > -hide;
                }
                case "Lumber" -> {
                    return trackers[num] > -lumber;
                }
                case "Snowthistle" -> {
                    return trackers[num] > -snowthistle;
                }
                case "Axenut" -> {
                    return trackers[num] > -axenut;
                }
                case "Arrowvine" -> {
                    return trackers[num] > -arrowvine;
                }
                case "Rockroot" -> {
                    return trackers[num] > -rockroot;
                }
                case "Corpsecap" -> {
                    return trackers[num] > -corpsecap;
                }
                case "Flamefruit" -> {
                    return trackers[num] > -flamefruit;
                }
            }
        } else {
            switch (type) {
                case "Metal" -> {
                    return trackers[num] > -activeCharacters.get(num2).metal;
                }
                case "Hide" -> {
                    return trackers[num] > -activeCharacters.get(num2).hide;
                }
                case "Lumber" -> {
                    return trackers[num] > -activeCharacters.get(num2).lumber;
                }
            }
        }
        return true;
    }

    public boolean traitInParty(String trait) {
        for (Character x : activeCharacters) {
            if (x.trait1.equals(trait) || x.trait2.equals(trait) || x.trait3.equals(trait)) {
                return true;
            }
        }
        return false;
    }

    public boolean ancestryInParty(String ancestry) {
        for (Character x : activeCharacters) {
            if (x.ancestry.equals(ancestry)) {
                return true;
            }
        }
        return false;
    }

    public void distributeItem(String itemName) {
        JDialog dialog = new JDialog((Frame) null, "Distribute Item", true);
        dialog.setLayout(null);
        dialog.setSize(380, 550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        int[] index = {-1};
        Item[] item = new Item[1];
        boolean[] unavailable = {false};
        for (int i = 0; i < unavailableItems.size(); i++) {
            if (unavailableItems.get(i).toString().equals(itemName)) {
                index[0] = i;
                item[0] = unavailableItems.get(i);
                i = unavailableItems.size();
                unavailable[0] = true;
            }
        }

        if (index[0] == -1) {
            for (int i = 0; i < availableItems.size(); i++) {
                if (availableItems.get(i).toString().equals(itemName)) {
                    index[0] = i;
                    item[0] = availableItems.get(i);
                    i = availableItems.size();
                    unavailable[0] = false;
                }
            }

            if (index[0] == -1) {
                for (Character activeCharacter : activeCharacters) {
                    for (int j = 0; j < activeCharacter.items.size(); j++) {
                        if (activeCharacter.items.get(j).toString().equals(itemName)) {
                            distributeToOne(activeCharacter.items.get(j).sellCost(), "Gold");
                            dialog.dispose();
                            return;
                        }
                    }
                }
            }
        }

        JButton continueButton = new JButton("Give " + itemName + " to ");
        continueButton.setBounds(37, 200, 300, 30);
        continueButton.setVisible(false);

        int[] character = {-1};

        for (int i = 0; i < activeCharacters.size(); i++) {
            distributeItemHelper(i, itemName, dialog, continueButton, character);
        }

        continueButton.addActionListener(_ ->
        {
            activeCharacters.get(character[0]).items.add(item[0]);
            if (unavailable[0]) {
                unavailableItems.remove(index[0]);
            } else {
                availableItems.remove(index[0]);
            }
            dialog.dispose();
        });
        dialog.add(continueButton);
        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();
    }

    private void distributeItemHelper(int num, String itemName, JDialog dialog, JButton continueButton, int[] character) {
        if (findItemNameIndex(activeCharacters.get(num).items, itemName) == -1) {
            JButton className = new JButton(activeCharacters.get(num).toString());
            className.setBounds(20 + 180 * (num % 2), 25 + 75 * (num / 2), 150, 30);
            className.addActionListener(_ ->
            {
                continueButton.setText("Give " + itemName + " to " + activeCharacters.get(num).toString());
                character[0] = num;
                continueButton.setVisible(true);
            });
            dialog.add(className);
        }
    }

    public void distributeToOne(int num, String type) {
        distributeToOne(num, type, "");
    }

    public int distributeToOne(int num, String type, String ancestry) {
        JDialog dialog = new JDialog((Frame) null, "Distribute " + type + " to one", true);
        dialog.setLayout(null);
        dialog.setSize(380, 550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        JButton continueButton = new JButton("Give " + type.toLowerCase() + " to ");
        continueButton.setBounds(40, 200, 300, 30);
        continueButton.setVisible(false);

        int[] character = {-1};

        distributeToOneButtonHelper(dialog, continueButton, type, character, 0, ancestry);
        distributeToOneButtonHelper(dialog, continueButton, type, character, 1, ancestry);

        if (activeCharacters.size() >= 3) {
            distributeToOneButtonHelper(dialog, continueButton, type, character, 2, ancestry);
        }

        if (activeCharacters.size() >= 4) {
            distributeToOneButtonHelper(dialog, continueButton, type, character, 3, ancestry);
        }

        continueButton.addActionListener(_ ->
        {
            if (type.equals("Gold")) {
                activeCharacters.get(character[0]).gold += num;
            }
            if (type.equals("Lumber")) {
                activeCharacters.get(character[0]).lumber += num;
            }
            if (type.equals("Metal")) {
                activeCharacters.get(character[0]).metal += num;
            }
            if (type.equals("Hide")) {
                activeCharacters.get(character[0]).hide += num;
            }
            dialog.dispose();
        });
        dialog.add(continueButton);
        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();
        if (!ancestry.isEmpty() && !ancestryInParty(ancestry)) {
            dialog.dispose();
        }
        return character[0];
    }

    private void distributeToOneButtonHelper(JDialog dialog, JButton continueButton, String type, int[] character, int num, String ancestry) {
        if (ancestry.isEmpty() || (activeCharacters.get(num).ancestry.equals(ancestry))) {
            JButton class1 = new JButton(activeCharacters.get(num).toString());
            class1.setBounds(20 + 180 * (num % 2), 25 + 75 * (num / 2), 150, 30);
            class1.addActionListener(_ ->
            {
                continueButton.setText("Give " + type + " to " + activeCharacters.get(num).toString());
                character[0] = num;
                continueButton.setVisible(true);
            });
            dialog.add(class1);
        }
    }

    public Character selectCharacter() {
        return selectCharacter("");
    }

    public Character selectCharacter(String ancestry) {
        Character[] selected = new Character[1];
        JDialog dialog = new JDialog((Frame) null, "Select Character", true);
        dialog.setSize(380, 550);
        dialog.setLayout(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        int[] character = {-1};
        JButton continueButton = new JButton("Select");
        continueButton.setBounds(37, 200, 300, 30);
        continueButton.setVisible(false);

        for (int i = 0; i < activeCharacters.size(); i++) {
            if (ancestry.isEmpty() || ancestry.equals(activeCharacters.get(i).ancestry)) {
                selectCharacterButtonHelper(dialog, continueButton, character, i);
            }
        }

        continueButton.addActionListener(_ ->
        {
            selected[0] = activeCharacters.get(character[0]);
            dialog.dispose();
        });

        dialog.add(continueButton);
        dialog.setVisible(true);

        return selected[0];
    }

    private void selectCharacterButtonHelper(JDialog dialog, JButton continueButton, int[] character, int num) {
        JButton class1 = new JButton(activeCharacters.get(num).toString());
        class1.setBounds(20 + 180 * (num % 2), 25 + 75 * (num / 2), 150, 30);
        class1.addActionListener(_ ->
        {
            continueButton.setText("Select " + activeCharacters.get(num).toString());
            character[0] = num;
            continueButton.setVisible(true);
        });
        dialog.add(class1);
    }

    public int findItemNameIndex(ArrayList<Item> items, String itemName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).toString().equals(itemName)) {
                return i;
            }
        }
        return -1;
    }

    public boolean canPay(String type, int num) {
        return canPay(type, num, false);
    }

    public boolean canPay(String type, int num, boolean fromBuilding) {
        int count = countTotal(type, fromBuilding);
        return count >= num;
    }

    public void gainMaterials(int num) {
        gainMaterials(num, false);
    }

    public void gainMaterials(int num, boolean fromBuilding) {
        if (num != 0) {
            JDialog dialog = new JDialog((Frame) null, "Distribute " + num + " materials", true);
            dialog.setLayout(null);
            dialog.setSize(380, 650);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setLocationRelativeTo(null);

            int size = (activeCharacters.size() + 1) * 3;
            if (fromBuilding) {
                size++;
            }
            int[] trackers = new int[size];
            int[] max = new int[1];
            if (num < 0) {
                max[0] = Math.max(num, -countTotal("Material", fromBuilding));
            } else {
                max[0] = num;
            }
            JButton continueButton = new JButton("Continue");
            continueButton.setBounds(100, (size) * 110 / 3 + 10, 100, 30);

            dialog.add(continueButton);
            for (int i = 0; i <= activeCharacters.size(); i++) {
                interfaceForDistributeMaterials(dialog, i, i * 110 + 40, trackers, max, continueButton);
            }
            if (fromBuilding) {
                JLabel label1 = new JLabel("Inspiration: 0");
                label1.setBounds(110, activeCharacters.size() * 110 + 110, 150, 20);
                dialog.add(label1);

                JButton increas_1 = new JButton("+");
                increas_1.setBounds(200, activeCharacters.size() * 110 + 105, 50, 30);
                JButton decreas_1 = new JButton("-");
                decreas_1.setBounds(60, activeCharacters.size() * 110 + 105, 50, 30);
                dialog.add(increas_1);
                dialog.add(decreas_1);

                increas_1.addActionListener(_ ->
                {
                    int total = 0;
                    for (int tracker : trackers) {
                        total += tracker;
                    }
                    if (trackers[trackers.length - 1] != 0) {
                        trackers[trackers.length - 1]++;
                        total++;
                    }
                    label1.setText("Inspiration: " + trackers[trackers.length - 1]);
                    continueButton.setVisible(total == max[0]);
                });
                decreas_1.addActionListener(_ ->
                {
                    if (notBelow(trackers, trackers.length - 1, "Inspiration")) {
                        trackers[trackers.length - 1]--;
                    }
                    label1.setText("Inspiration: " + trackers[trackers.length - 1]);
                    int total = 0;
                    for (int tracker : trackers) {
                        total += tracker;
                    }
                    continueButton.setVisible(total == max[0]);
                });
            }

            if (0 != max[0]) {
                continueButton.setVisible(false);
            }

            continueButton.addActionListener(_ ->
            {
                for (int i = 0; i < activeCharacters.size(); i++) {
                    activeCharacters.get(i).lumber += trackers[i * 3];
                    activeCharacters.get(i).metal += trackers[i * 3 + 1];
                    activeCharacters.get(i).hide += trackers[i * 3 + 2];
                }
                lumber += trackers[activeCharacters.size() * 3];
                metal += trackers[activeCharacters.size() * 3 + 1];
                hide += trackers[activeCharacters.size() * 3 + 2];
                if (fromBuilding) {
                    gainInspiration(trackers[trackers.length - 1]);
                }
                dialog.dispose();
            });
            dialog.setVisible(true);
            dialog.repaint();
            dialog.revalidate();
        }
    }

    public void interfaceForDistributeMaterials(JDialog dialog, int num, int pos, int[] trackers, int[] max, JButton continueButton) {
        JLabel label1 = new JLabel();
        if (num != activeCharacters.size()) {
            label1.setText(activeCharacters.get(num) + "");
        } else {
            label1.setText("Campaign");
        }
        JLabel label11 = new JLabel("Lumber: 0");
        JLabel label12 = new JLabel("Metal: 0");
        JLabel label13 = new JLabel("Hide: 0");
        label1.setBounds(60, pos, 150, 20);
        label11.setBounds(200, pos - 30, 150, 20);
        label12.setBounds(200, pos, 150, 20);
        label13.setBounds(200, pos + 30, 150, 20);
        dialog.add(label1);
        dialog.add(label11);
        dialog.add(label12);
        dialog.add(label13);

        JButton increas_1 = new JButton("+");
        increas_1.setBounds(270, pos - 35, 50, 30);
        JButton decreas_1 = new JButton("-");
        decreas_1.setBounds(150, pos - 35, 50, 30);
        JButton increas_2 = new JButton("+");
        increas_2.setBounds(270, pos - 5, 50, 30);
        JButton decreas_2 = new JButton("-");
        decreas_2.setBounds(150, pos - 5, 50, 30);
        JButton increas_3 = new JButton("+");
        increas_3.setBounds(270, pos + 25, 50, 30);
        JButton decreas_3 = new JButton("-");
        decreas_3.setBounds(150, pos + 25, 50, 30);
        dialog.add(increas_1);
        dialog.add(decreas_1);
        dialog.add(increas_2);
        dialog.add(decreas_2);
        dialog.add(increas_3);
        dialog.add(decreas_3);

        increas_1.addActionListener(_ ->
        {
            int total = 0;
            for (int tracker : trackers) {
                total += tracker;
            }
            if (max[0] > 0) {
                if (total < max[0]) {
                    trackers[num * 3]++;
                    total++;
                }
            } else if (trackers[num * 3] != 0) {
                trackers[num * 3]++;
                total++;
            }
            label11.setText("Lumber: " + trackers[num * 3]);
            continueButton.setVisible(total == max[0]);
        });
        decreas_1.addActionListener(_ ->
        {
            if (max[0] > 0) {
                if (trackers[num * 3] != 0) {
                    trackers[num * 3]--;
                }
            } else if (notBelow(trackers, num * 3, "Lumber", num)) {
                trackers[num * 3]--;
            }
            label11.setText("Lumber: " + trackers[num * 3]);
            int total = 0;
            for (int tracker : trackers) {
                total += tracker;
            }
            continueButton.setVisible(total == max[0]);
        });
        increas_2.addActionListener(_ ->
        {
            int total = 0;
            for (int tracker : trackers) {
                total += tracker;
            }
            if (max[0] > 0) {
                if (total < max[0]) {
                    trackers[num * 3 + 1]++;
                    total++;
                }
            } else if (trackers[num * 3 + 1] != 0) {
                trackers[num * 3 + 1]++;
                total++;
            }
            label12.setText("Metal: " + trackers[num * 3 + 1]);
            continueButton.setVisible(total == max[0]);
        });
        decreas_2.addActionListener(_ ->
        {
            if (max[0] > 0) {
                if (trackers[num * 3 + 1] != 0) {
                    trackers[num * 3 + 1]--;
                }
            } else if (notBelow(trackers, num * 3 + 1, "Metal", num)) {
                trackers[num * 3 + 1]--;
            }
            label12.setText("Metal: " + trackers[num * 3 + 1]);
            int total = 0;
            for (int tracker : trackers) {
                total += tracker;
            }
            continueButton.setVisible(total == max[0]);
        });
        increas_3.addActionListener(_ ->
        {
            int total = 0;
            for (int tracker : trackers) {
                total += tracker;
            }
            if (max[0] > 0) {
                if (total < max[0]) {
                    trackers[num * 3 + 2]++;
                    total++;
                }
            } else if (trackers[num * 3 + 2] != 0) {
                trackers[num * 3 + 2]++;
                total++;
            }
            label13.setText("Hide: " + trackers[num * 3 + 2]);
            continueButton.setVisible(total == max[0]);
        });
        decreas_3.addActionListener(_ ->
        {
            if (max[0] > 0) {
                if (trackers[num * 3 + 2] != 0) {
                    trackers[num * 3 + 2]--;
                }
            } else if (notBelow(trackers, num * 3 + 2, "Hide", num)) {
                trackers[num * 3 + 2]--;
            }
            label13.setText("Hide: " + trackers[num * 3 + 2]);
            int total = 0;
            for (int tracker : trackers) {
                total += tracker;
            }
            continueButton.setVisible(total == max[0]);
        });
    }

    public void damageBuildingOfChoice() {
        JDialog dialog = new JDialog((Frame) null, "Damage Building", true);
        dialog.setLayout(null);
        dialog.setSize(380, 550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        ArrayList<Building> damageable = new ArrayList<>();
        for (Building unlockedBuilding : unlockedBuildings) {
            if (!unlockedBuilding.wrecked && unlockedBuilding.level != 0) {
                damageable.add(unlockedBuilding);
            }
        }

        for (int i = 0; i < damageable.size(); i++) {
            buttonForDamageBuildingOfChoice(dialog, damageable, i);
        }

        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();

        if (damageable.isEmpty()) {
            dialog.dispose();
        }
    }

    private void buttonForDamageBuildingOfChoice(JDialog dialog, ArrayList<Building> damageable, int num) {
        JButton buildingName = new JButton(damageable.get(num).toString());
        buildingName.setBounds(100, 25 + 40 * num, 180, 30);
        dialog.add(buildingName, JLayeredPane.PALETTE_LAYER);

        buildingName.addActionListener(_ ->
                new Thread(() ->
                {
                    dialog.dispose();
                    damageable.get(num).damageBuilding();
                }).start());
    }

    public int wreckBuildingOfChoice(ArrayList<Integer> options) {
        JDialog dialog = new JDialog((Frame) null, "Wreck Building", true);
        dialog.setLayout(null);
        dialog.setSize(380, 550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        ArrayList<Building> wreckable = new ArrayList<>();
        for (Integer option : options) {
            if (findBuildingInUnlocked(option) != -1 && !unlockedBuildings.get(findBuildingInUnlocked(option)).wrecked
                    && unlockedBuildings.get(findBuildingInUnlocked(option)).level != 0) {
                wreckable.add(unlockedBuildings.get(findBuildingInUnlocked(option)));
            }
        }

        int[] wreckedBuilding = {0};

        for (int i = 0; i < wreckable.size(); i++) {
            buttonForWreckBuildingOfChoice(dialog, wreckable, i, wreckedBuilding);
        }
        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();
        if (wreckable.isEmpty()) {
            dialog.dispose();
        }
        return wreckedBuilding[0];
    }

    private void buttonForWreckBuildingOfChoice(JDialog dialog, ArrayList<Building> wreckable, int num, int[] wreckedBuilding) {
        JButton buildingName = new JButton(wreckable.get(num).toString());
        buildingName.setBounds(100, 25 + 40 * num, 180, 30);
        dialog.add(buildingName, JLayeredPane.PALETTE_LAYER);

        buildingName.addActionListener(_ ->
                new Thread(() ->
                {
                    wreckable.get(num).wrecked = true;
                    wreckedBuilding[0] = wreckable.get(num).number;
                    dialog.dispose();
                }).start());
    }

    public int findBuildingInUnlocked(int buildingNum) {
        for (int i = 0; i < unlockedBuildings.size(); i++) {
            if (unlockedBuildings.get(i).number == (buildingNum)) {
                return i;
            }
        }
        return -1;
    }

    public int findBuildingInLocked(int buildingNum) {
        for (int i = 0; i < lockedBuildings.size(); i++) {
            if (lockedBuildings.get(i).number == (buildingNum)) {
                return i;
            }
        }
        return -1;
    }

    public int loseAnyNumThings(String type) {
        int[] trackers;
        if (type.equals("Gold")) {
            trackers = new int[activeCharacters.size()];
        } else {
            trackers = new int[activeCharacters.size() + 1];
        }

        JDialog dialog = new JDialog((Frame) null, "Lose any number of", true);
        dialog.setLayout(null);
        dialog.setSize(380, 550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(100, 60 + 70 * trackers.length, 100, 30);
        if (!type.equals("Arrowvine") && !type.equals("Axenut") && !type.equals("Corpsecap") &&
                !type.equals("Flamefruit") && !type.equals("Rockroot") && !type.equals("Snowthistle")) {
            for (int i = 0; i < trackers.length; i++) {
                loseAnyNumThingsButtonHelper(dialog, i, trackers, type);
            }
        }
        continueButton.addActionListener(_ ->
        {
            switch (type) {
                case "Gold" -> goldChange(trackers);
                case "Lumber" -> lumberChange(trackers);
                case "Metal" -> metalChange(trackers);
                case "Hide" -> hideChange(trackers);
                case "Snowthistle" -> snowthistleChange(trackers);
                case "Arrowvine" -> arrowvineChange(trackers);
                case "Axenut" -> axenutChange(trackers);
                case "Rockroot" -> rockrootChange(trackers);
                case "Corpsecap" -> corpsecapChange(trackers);
                case "Flamefruit" -> flamefruitChange(trackers);
            }
            dialog.dispose();
        });
        dialog.add(continueButton);

        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();

        int total = 0;
        for (int tracker : trackers) {
            total += tracker;
        }
        return total;
    }

    private void loseAnyNumThingsButtonHelper(JDialog dialog, int num, int[] trackers, String type) {
        String[] label = {""};
        if (num < activeCharacters.size()) {
            label[0] = activeCharacters.get(num).toString();
        } else {
            label[0] = "Campaign";
        }
        JLabel label1 = new JLabel(label[0] + ": 0");
        label1.setBounds(60, 30 + 70 * num, 150, 20);
        dialog.add(label1);

        JButton increas_ = new JButton("+");
        increas_.setBounds(160, 25 + 70 * num, 50, 30);
        JButton decreas_ = new JButton("-");
        decreas_.setBounds(10, 25 + 70 * num, 50, 30);

        increas_.addActionListener(_ ->
        {
            if (trackers[num] != 0) {
                trackers[num]++;
            }
            label1.setText(label[0] + ": " + trackers[num]);
        });
        decreas_.addActionListener(_ ->
        {
            if (notBelow(trackers, num, type)) {
                trackers[num]--;
            }
            label1.setText(label[0] + ": " + trackers[num]);
        });
        dialog.add(increas_);
        dialog.add(decreas_);
    }

    public void distributeHerbs(int maxi) {
        int[] trackers = new int[6];
        int[] max = new int[1];
        if (maxi < 0) {
            max[0] = Math.max(maxi, -countTotal("Herb"));
        } else {
            max[0] = maxi;
        }

        JDialog dialog = new JDialog((Frame) null, "Distribute " + max[0] + " Herbs", true);
        dialog.setLayout(null);
        dialog.setSize(380, 550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(100, 60 + 70 * trackers.length, 100, 30);
        continueButton.setVisible(false);

        distributeHerbsButtonHelper(dialog, max, 0, trackers, continueButton, "Arrowvine");
        distributeHerbsButtonHelper(dialog, max, 1, trackers, continueButton, "Axenut");

        distributeHerbsButtonHelper(dialog, max, 2, trackers, continueButton, "Corpsecap");
        distributeHerbsButtonHelper(dialog, max, 3, trackers, continueButton, "Flamefruit");

        distributeHerbsButtonHelper(dialog, max, 4, trackers, continueButton, "Rockroot");
        distributeHerbsButtonHelper(dialog, max, 5, trackers, continueButton, "Snowthistle");

        continueButton.addActionListener(_ ->
        {
            arrowvine += trackers[0];
            axenut += trackers[0];
            corpsecap += trackers[0];
            flamefruit += trackers[0];
            rockroot += trackers[0];
            snowthistle += trackers[0];

            dialog.dispose();
        });
        dialog.add(continueButton);

        if (0 == max[0]) {
            continueButton.setVisible(true);
        }

        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();
    }

    private void distributeHerbsButtonHelper(JDialog dialog, int[] max, int num, int[] trackers, JButton continueButton, String herb) {
        JLabel label1 = new JLabel(herb + ": 0");
        label1.setBounds(60, 30 + 70 * num, 150, 20);
        dialog.add(label1);

        JButton increas_ = new JButton("+");
        increas_.setBounds(160, 25 + 70 * num, 50, 30);
        JButton decreas_ = new JButton("-");
        decreas_.setBounds(10, 25 + 70 * num, 50, 30);

        increas_.addActionListener(_ ->
        {
            int total = 0;
            for (int tracker : trackers) {
                total += tracker;
            }
            if (max[0] > 0) {
                if (total < max[0]) {
                    trackers[num]++;
                    total++;
                }
            } else if (trackers[num] != 0) {
                trackers[num]++;
                total++;
            }
            label1.setText(herb + ": " + trackers[num]);
            continueButton.setVisible(total == max[0]);
        });
        decreas_.addActionListener(_ ->
        {
            if (max[0] > 0 && trackers[num] != 0) {
                trackers[num]--;
            } else if (notBelowForDistributeHerbs(trackers, num)) {
                trackers[num]--;
            }
            label1.setText(herb + ": " + trackers[num]);
            int total = 0;
            for (int tracker : trackers) {
                total += tracker;
            }
            continueButton.setVisible(total == max[0]);
        });
        dialog.add(increas_);
        dialog.add(decreas_);
    }

    public boolean notBelowForDistributeHerbs(int[] trackers, int num) {
        int count = 0;
        if (num == 0) {
            count = arrowvine;
        }
        if (num == 1) {
            count = axenut;
        }
        if (num == 2) {
            count = corpsecap;
        }
        if (num == 3) {
            count = flamefruit;
        }
        if (num == 4) {
            count = rockroot;
        }
        if (num == 5) {
            count = snowthistle;
        }
        return trackers[num] > -count;
    }

    public int outpostAttack(int attackVal, int targets, ArrayList<Integer> targeting) {
        return outpostAttack(false, false, new ArrayList<>(), false, attackVal, false, targets, true, targeting, false, 0, 0, 0, false, 0);
    }

    public int outpostAttack(int attackVal, int targets, ArrayList<Integer> targeting, int attacksWithA, int attacksWithD) {
        return outpostAttack(false, false, new ArrayList<>(), false, attackVal, false, targets, true, targeting, false, attacksWithA, attacksWithD, 0, false, 0);
    }

    public int outpostAttack(int attackVal, int targets, ArrayList<Integer> targeting, int attacksWithA, int attacksWithD, int nonDisadvantageBuilding) {
        return outpostAttack(false, false, new ArrayList<>(), false, attackVal, false, targets, true, targeting, false, attacksWithA, attacksWithD, nonDisadvantageBuilding, false, 0);
    }

    public int outpostAttack(int attackVal, int targets, ArrayList<Integer> targeting, boolean wreckDamaged) {
        return outpostAttack(false, false, new ArrayList<>(), false, attackVal, false, targets, true, targeting, false, 0, 0, 0, wreckDamaged, 0);
    }

    public int outpostAttack(boolean repeatTargeting, int attackVal, int targets, ArrayList<Integer> targeting, boolean wreckDamaged) {
        return outpostAttack(false, false, new ArrayList<>(), repeatTargeting, attackVal, false, targets, true, targeting, false, 0, 0, 0, wreckDamaged, 0);
    }

    public int outpostAttack(int attackVal, int targets, ArrayList<Integer> targeting, String empty, boolean drawForAttacker) {
        return outpostAttack(false, false, new ArrayList<>(), false, attackVal, false, targets, true, targeting, drawForAttacker, 0, 0, 0, false, 0);
    }

    public int outpostAttack(int attackVal, int targets, ArrayList<Integer> targeting, int attacksWithA, int attacksWithD, boolean wreckDamaged, int reduceOneAttack) {
        return outpostAttack(false, false, new ArrayList<>(), false, attackVal, false, targets, true, targeting, false, attacksWithA, attacksWithD, 0, wreckDamaged, reduceOneAttack);
    }

    public int outpostAttack(int attackVal, int targets, boolean wallDefense, ArrayList<Integer> targeting, int attacksWithA, int attacksWithD) {
        return outpostAttack(false, false, new ArrayList<>(), false, attackVal, false, targets, wallDefense, targeting, false, attacksWithA, attacksWithD, 0, false, 0);
    }

    public int outpostAttack(int attackVal, boolean wreckNegativeModifier, int targets, ArrayList<Integer> targeting) {
        return outpostAttack(false, false, new ArrayList<>(), false, attackVal, wreckNegativeModifier, targets, true, targeting, false, 0, 0, 0, false, 0);
    }

    public int outpostAttack(boolean targetNextClosest, int attackVal, int targets, ArrayList<Integer> targeting) {
        return outpostAttack(targetNextClosest, false, new ArrayList<>(), false, attackVal, false, targets, true, targeting, false, 0, 0, 0, false, 0);
    }

    public int outpostAttack(boolean targetNextClosest, boolean increaseTargetCount, ArrayList<Integer> untargetable, boolean repeatTargeting, int attackVal, int targets, ArrayList<Integer> targeting) {
        return outpostAttack(targetNextClosest, increaseTargetCount, untargetable, repeatTargeting, attackVal, false, targets, true, targeting, false, 0, 0, 0, false, 0);
    }

    public int outpostAttack(boolean targetNextClosest, boolean increaseTargetCount, ArrayList<Integer> untargetable, boolean repeatTargeting,
                             int attackVal, boolean wreckNegativeModifier, int targets, boolean wallDefense, ArrayList<Integer> targeting, boolean drawForAttacker, int attacksWithA,
                             int attacksWithD, int nonDisadvantageBuilding, boolean wreckDamaged, int reduceOneAttack) {
        CountDownLatch latch = new CountDownLatch(1);
        int targetedBuildings = 0;
        Collections.shuffle(townGuardDeck);
        int[] reduceAttack = {reduceOneAttack};

        ArrayList<Building> toBeWrecked = new ArrayList<>();
        ArrayList<Building> toBeDamaged = new ArrayList<>();
        ArrayList<Integer> targetedBuildingsNums = new ArrayList<>();

        int successfulDefends = 0;

        while (!targeting.isEmpty()) {
            boolean remove = true;
            if (findBuildingInUnlocked(targeting.getFirst()) != -1 && unlockedBuildings.get(findBuildingInUnlocked(targeting.getFirst())).level != 0 &&
                    !unlockedBuildings.get(findBuildingInUnlocked(targeting.getFirst())).wrecked && !untargetable.contains(targeting.getFirst())) {
                boolean advantage = false;
                boolean disadvantage = false;
                if (attacksWithA > targetedBuildings) {
                    advantage = true;
                }
                if (attacksWithD > targetedBuildings && targeting.getFirst() != nonDisadvantageBuilding) {
                    disadvantage = true;
                }
                boolean successful = attackBuilding(attackVal, wreckNegativeModifier, wallDefense, targeting.getFirst(), drawForAttacker, advantage,
                        disadvantage, wreckDamaged, reduceAttack, toBeWrecked, toBeDamaged);
                if (successful) {
                    successfulDefends++;
                }
                targetedBuildings++;
                if (targetNextClosest) {
                    targetedBuildingsNums.add(targeting.getFirst());
                    targeting = getClosestTargeting(targeting.getFirst());
                    for (Integer targetedBuildingsNum : targetedBuildingsNums) {
                        if (targeting.indexOf(targetedBuildingsNum) == 0 || !repeatTargeting ||
                                (toBeWrecked.contains(unlockedBuildings.get(findBuildingInUnlocked(targetedBuildingsNum))) ||
                                        toBeDamaged.contains(unlockedBuildings.get(findBuildingInUnlocked(targetedBuildingsNum))))) {
                            targeting.remove(targetedBuildingsNum);
                        }
                    }
                    remove = false;
                    if (increaseTargetCount && !successful) {
                        targets++;
                    }
                }
                if (successful && repeatTargeting) {
                    remove = false;
                }
            }
            if (remove) {
                targeting.removeFirst();
            }
            if (targetedBuildings == targets || targeting.isEmpty()) {
                targeting.clear();
                latch.countDown();
            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Building value : toBeWrecked) {
            value.wrecked = true;
        }
        for (Building building : toBeDamaged) {
            building.damageBuilding();
        }
        reshuffleTownGuard();
        return successfulDefends;
    }

    private boolean attackBuilding(int attackVal, boolean wreckNegativeModifier, boolean wallDefense, int buildingNum, boolean drawForAttacker,
                                   boolean advantage, boolean disadvantage, boolean wreckDamaged, int[] reduceAttack,
                                   ArrayList<Building> toBeWrecked, ArrayList<Building> toBeDamaged) {
        boolean[] successfulDefend = {false};
        Building b = unlockedBuildings.get(findBuildingInUnlocked(buildingNum));
        JDialog dialog = new JDialog((Frame) null, "Use Soldier for " + b.toString(), true);
        dialog.setLayout(null);
        dialog.setSize(380, 550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        JButton useSoldier = new JButton("Toggle Using Soldier");
        useSoldier.setBounds(90, 60, 200, 30);
        JButton continueButton = new JButton("Defend without Soldier");
        continueButton.setBounds(90, 90, 200, 30);
        if (soldiers == 0 || unlockedBuildings.get(findBuildingInUnlocked(98)).wrecked) {
            useSoldier.setVisible(false);
        }

        boolean[] usingSoldier = {false};

        useSoldier.addActionListener(_ ->
        {
            usingSoldier[0] = !usingSoldier[0];
            if (usingSoldier[0]) {
                continueButton.setText("Defend with Soldier");
            } else {
                continueButton.setText("Defend without Soldier");
            }
        });

        boolean[] reducingAttack = {false};

        if (reduceAttack[0] != 0) {
            JButton reduceOneAttack = new JButton("Toggle Reducing Attack: Not Reducing Attack");
            reduceOneAttack.setBounds(20, 30, 340, 30);

            reduceOneAttack.addActionListener(_ ->
            {
                reducingAttack[0] = !reducingAttack[0];
                if (reducingAttack[0]) {
                    reduceOneAttack.setText("Toggle Reducing Attack: Reducing Attack");
                } else {
                    reduceOneAttack.setText("Toggle Reducing Attack: Not Reducing Attack");
                }
            });
            dialog.add(reduceOneAttack);
        }

        continueButton.addActionListener(_ ->
                new Thread(() ->
                {
                    boolean advantageOnAttack = false;
                    boolean disadvantageOnAttack = false;
                    if (advantage || usingSoldier[0]) {
                        advantageOnAttack = true;
                    }
                    if (disadvantage || unlockedBuildings.get(findBuildingInUnlocked(98)).wrecked) {
                        disadvantageOnAttack = true;
                    }
                    AttackModifier attackerModifier = new AttackModifier();
                    if (drawForAttacker) {
                        JDialog dialog1 = new JDialog((Frame) null, "Attack Modifier for Attackers", true);
                        dialog1.setLayout(null);
                        dialog1.setSize(412, 290);
                        dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog1.setLocationRelativeTo(null);

                        JLayeredPane layeredPane = new JLayeredPane();
                        layeredPane.setBounds(0, 0, 412, 290);
                        dialog1.setContentPane(layeredPane);

                        JLabel label1;
                        String image;

                        if (townGuardDeck.isEmpty()) {
                            reshuffleTownGuard();
                        }
                        attackerModifier = townGuardDeck.removeFirst();
                        image = attackerModifier.frontImage;
                        label1 = Methods.createImage(image, 50);
                        label1.setLocation(0, 0);

                        layeredPane.add(label1, JLayeredPane.DEFAULT_LAYER);
                        JButton continueButton1 = new JButton("Continue");
                        continueButton1.setBounds(dialog1.getWidth() - 130, dialog1.getHeight() - 55, 130, 30);
                        continueButton1.addActionListener(_ ->
                                dialog1.dispose());
                        layeredPane.add(continueButton, JLayeredPane.PALETTE_LAYER);
                        dialog1.setVisible(true);
                        dialog1.revalidate();
                        dialog1.repaint();
                    }
                    ArrayList<AttackModifier> modifiers = drawModifier(advantageOnAttack, disadvantageOnAttack);
                    int modifierValue = 0;
                    for (AttackModifier modifier : modifiers) {
                        modifierValue += modifier.modifierValue;
                        modifier.attackEffectOutpost();
                    }
                    int attackValue = attackVal + attackerModifier.modifierValue;
                    int defenseValue = modifierValue + getDefenseBonusFromMorale();
                    if (wallDefense) {
                        defenseValue += defense;
                    }
                    if (usingSoldier[0]) {
                        attackValue -= ((unlockedBuildings.get(findBuildingInUnlocked(98)).level * 10) - 5);
                    }
                    if (reducingAttack[0]) {
                        attackValue -= reduceAttack[0];
                        reduceAttack[0] = 0;
                    }
                    System.out.println("Attack Value: " + attackValue);
                    System.out.println("Modifier Value: " + modifierValue);
                    System.out.println("Defense Value: " + (defenseValue));
                    if (wreckNegativeModifier && modifiers.getFirst().modifierValue < 0) {
                        toBeWrecked.add(b);
                    } else if (modifiers.getFirst().name.equals("Wreck")) {
                        toBeWrecked.add(b);
                    } else if ((attackValue > defenseValue && !modifiers.getFirst().name.equals("Success")) || attackerModifier.name.equals("Success")) {
                        if (wreckDamaged) {
                            toBeWrecked.add(b);
                        } else {
                            toBeDamaged.add(b);
                        }
                    } else {
                        successfulDefend[0] = true;
                    }
                    if (!attackerModifier.name.equals("null")) {
                        townGuardDeckDiscard.add(attackerModifier);
                    }
                    while (!modifiers.isEmpty()) {
                        townGuardDeckDiscard.add(modifiers.removeFirst());
                    }
                    if (usingSoldier[0]) {
                        soldiers--;
                    }
                    dialog.dispose();
                }).start());
        dialog.add(continueButton);
        dialog.add(useSoldier);
        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();

        return successfulDefend[0];
    }

    public int outpostAttackNonBuilding(int attackVal, int targets) {
        return outpostAttackNonBuilding(attackVal, targets, 0, 0, false);
    }

    public int outpostAttackNonBuilding(int attackVal, int targets, int attacksWithA, int attacksWithD, boolean reduceAttack) {
        CountDownLatch latch = new CountDownLatch(1);
        int[] numDamaged = {0};
        Collections.shuffle(townGuardDeck);
        int defenseFromMorale = getDefenseBonusFromMorale();
        for (int i = 0; i < targets; i++) {
            boolean advantage = false;
            boolean disadvantage = false;
            if (attacksWithA > i) {
                advantage = true;
            }
            if (attacksWithD > i) {
                disadvantage = true;
            }
            ArrayList<Object> attack = (attackBuildingNonBuilding(attackVal, defenseFromMorale, numDamaged, advantage, disadvantage));
            if (reduceAttack) {
                attackVal = (int) (attack.getFirst());
            }
            if (reduceAttack && (boolean) (attack.get(1))) {
                i = targets - 1;
            }
            if (i == targets - 1) {
                latch.countDown();
            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        reshuffleTownGuard();
        return numDamaged[0];
    }

    public ArrayList<Object> attackBuildingNonBuilding(int attackVal, int defenseFromMorale, int[] numDamaged, boolean advantage, boolean disadvantage) {
        ArrayList<Object> arr = new ArrayList<>();
        JDialog dialog = new JDialog((Frame) null, "Use Soldier for Attack", true);
        dialog.setLayout(null);
        dialog.setSize(380, 550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        JButton useSoldier = new JButton("Toggle Using Soldier");
        useSoldier.setBounds(100, 60, 200, 30);
        JButton continueButton = new JButton("Defend without Soldier");
        continueButton.setBounds(100, 90, 200, 30);
        if (soldiers == 0 || unlockedBuildings.get(findBuildingInUnlocked(98)).wrecked) {
            useSoldier.setVisible(false);
        }

        boolean[] usingSoldier = {false};

        useSoldier.addActionListener(_ ->
        {
            usingSoldier[0] = !usingSoldier[0];
            if (usingSoldier[0]) {
                continueButton.setText("Defend with Soldier");
            } else {
                continueButton.setText("Defend without Soldier");
            }
        });
        continueButton.addActionListener(_ ->
                new Thread(() ->
                {
                    boolean advantageOnAttack = false;
                    boolean disadvantageOnAttack = false;
                    if (advantage || usingSoldier[0]) {
                        advantageOnAttack = true;
                    }
                    if (disadvantage || unlockedBuildings.get(findBuildingInUnlocked(98)).wrecked) {
                        disadvantageOnAttack = true;
                    }
                    ArrayList<AttackModifier> modifiers = drawModifier(advantageOnAttack, disadvantageOnAttack);
                    int modifierValue = 0;
                    for (AttackModifier modifier : modifiers) {
                        modifierValue += modifier.modifierValue;
                        modifier.attackEffectOutpost();
                    }
                    int attackValue = attackVal;
                    int defenseValue = defense + modifierValue + defenseFromMorale;
                    arr.add(attackValue - defenseValue);
                    if (usingSoldier[0]) {
                        attackValue -= ((unlockedBuildings.get(findBuildingInUnlocked(98)).level * 10) - 5);
                    }
                    System.out.println("Attack Value: " + attackValue);
                    System.out.println("Modifier Value: " + modifierValue);
                    System.out.println("Defense Value: " + (defenseValue));
                    if (modifiers.getFirst().name.equals("Wreck")) {
                        numDamaged[0]++;
                        arr.add(false);
                    } else if (attackValue > defenseValue && !modifiers.getFirst().name.equals("Success")) {
                        numDamaged[0]++;
                        arr.add(false);
                    } else {
                        arr.add(true);
                    }
                    while (!modifiers.isEmpty()) {
                        townGuardDeckDiscard.add(modifiers.getFirst());
                        modifiers.removeFirst();
                    }
                    dialog.dispose();
                }).start());
        dialog.add(continueButton);
        dialog.add(useSoldier);
        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();
        return arr;
    }

    public ArrayList<AttackModifier> drawModifier(boolean advantage, boolean disadvantage) {
        ArrayList<AttackModifier> draw = new ArrayList<>();
        if (advantage && disadvantage) {
            advantage = false;
            disadvantage = false;
        }
        do {
            if (townGuardDeck.isEmpty()) {
                reshuffleTownGuard();
            }
            draw.addFirst(townGuardDeck.getFirst());
            townGuardDeck.removeFirst();
        } while (draw.getFirst().rolling);
        int width = 407;
        if (advantage || disadvantage) {
            width += 407;
        }

        JDialog dialog = new JDialog((Frame) null, "Attack Modifier", true);
        dialog.setLayout(null);
        dialog.setSize(width + 5, 265 * draw.size() + 25);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

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
            if (townGuardDeck.isEmpty()) {
                reshuffleTownGuard();
            }
            draw.addFirst(townGuardDeck.getFirst());
            townGuardDeck.removeFirst();
            image = draw.getFirst().frontImage;
            label1 = Methods.createImage(image, 50);
            label1.setLocation(407, dialog.getHeight() - 290);
            dialog.add(label1);

            JButton chooseOne = new JButton("Choose card");
            chooseOne.setBounds(dialog.getWidth() - 540, dialog.getHeight() - 55, 130, 30);

            chooseOne.addActionListener(_ ->
            {
                townGuardDeckDiscard.add(draw.getFirst());
                draw.removeFirst();
                dialog.dispose();
            });
            layeredPane.add(chooseOne, JLayeredPane.PALETTE_LAYER);

            JButton chooseTwo = new JButton("Choose card");
            chooseTwo.setBounds(dialog.getWidth() - 130, dialog.getHeight() - 55, 130, 30);

            chooseTwo.addActionListener(_ ->
            {
                townGuardDeckDiscard.add(draw.get(1));
                draw.remove(1);
                dialog.dispose();
            });
            layeredPane.add(chooseTwo, JLayeredPane.PALETTE_LAYER);
        }
        int unused = 0;
        if (disadvantage) {
            if (townGuardDeck.isEmpty()) {
                reshuffleTownGuard();
            }
            draw.addFirst(townGuardDeck.getFirst());
            townGuardDeck.removeFirst();
            image = draw.getFirst().frontImage;
            label1 = Methods.createImage(image, 50);
            label1.setBounds(407, dialog.getHeight() - 290, 407, 265);
            dialog.add(label1);

            if (draw.getFirst().name.equals("Wreck") || draw.get(1).name.equals("Success")) {
                unused = 1;
            } else if ((!draw.getFirst().hasAttackEffect && draw.get(1).hasAttackEffect) || (!draw.getFirst().hasAttackEffect && !draw.get(1).hasAttackEffect)) {
                if (draw.getFirst().modifierValue <= draw.get(1).modifierValue) {
                    unused = 1;
                }
            }
            townGuardDeckDiscard.add(draw.remove(unused));
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

    public ArrayList<AttackModifier> adrawModifier() {
        return drawModifier(false, false);
    }

    public void reshuffleTownGuard() {
        while (!townGuardDeckDiscard.isEmpty()) {
            townGuardDeck.add(townGuardDeckDiscard.getFirst());
            townGuardDeckDiscard.removeFirst();
        }
        Collections.shuffle(townGuardDeck);
    }

    public ArrayList<Integer> chooseOutpostAttackTargeting(ArrayList<Integer> possibleTargets, int maxTargets) {
        JDialog dialog = new JDialog((Frame) null, "Choose targets", true);
        dialog.setLayout(null);
        dialog.setSize(380, 600);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        ArrayList<Integer> targeting = new ArrayList<>();
        int index = 0;
        boolean anyVisible = false;
        for (Integer possibleTarget : possibleTargets) {
            if (findBuildingInUnlocked(possibleTarget) != -1 && unlockedBuildings.get(findBuildingInUnlocked(possibleTarget)).level != 0
                    && !unlockedBuildings.get(findBuildingInUnlocked(possibleTarget)).wrecked) {
                buttonsForChooseOutpostAttackTargeting(dialog, possibleTarget, maxTargets, index, targeting);
                index++;
                anyVisible = true;
            }
        }
        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();
        if (!anyVisible) {
            dialog.dispose();
        }
        return targeting;
    }

    private void buttonsForChooseOutpostAttackTargeting(JDialog dialog, int num, int maxTargets, int index, ArrayList<Integer> targeting) {
        JButton targetBuilding = new JButton(unlockedBuildings.get(findBuildingInUnlocked(num)).toString());
        targetBuilding.setBounds(100, 10 + 25 * index, 180, 30);
        targetBuilding.addActionListener(_ ->
        {
            targeting.add(num);
            targetBuilding.setVisible(false);
            if (targeting.size() == maxTargets) {
                dialog.dispose();
            } else {
                boolean anyVisible = false;
                Component[] components = dialog.getContentPane().getComponents();
                for (int k = 0; k < components.length; k++) {
                    if (components[k] instanceof JButton && components[k].isVisible()) {
                        anyVisible = true;
                        k = components.length;
                    }
                }
                if (!anyVisible) {
                    dialog.dispose();
                }
            }
        });
        dialog.add(targetBuilding);
    }

    public ArrayList<Integer> randomBuildings() {
        ArrayList<Integer> randomized = new ArrayList<>();
        for (Building unlockedBuilding : unlockedBuildings) {
            randomized.add(unlockedBuilding.number);
        }
        Collections.shuffle(randomized);
        return randomized;
    }

    public void giveToEach(int num, String type) {
        for (Character c : activeCharacters) {
            switch (type) {
                case "Gold" -> c.gold += num;
                case "Lumber" -> c.lumber += num;
                case "Metal" -> c.metal += num;
                case "Hide" -> c.hide += num;
                case "Experience" -> c.increaseExperience(num);
                case "Checkmark" -> c.increaseCheckmarks(num);
            }
        }
    }

    public int[] getActiveBuildingNums() {
        ArrayList<Building> buildings = new ArrayList<>();
        for (Building unlockedBuilding : unlockedBuildings) {
            if (unlockedBuilding.level != 0 && !unlockedBuilding.wrecked) {
                buildings.add(unlockedBuilding);
            }
        }
        int[] buildingNums = new int[buildings.size()];
        for (int i = 0; i < buildingNums.length; i++) {
            buildingNums[i] = buildings.get(i).number;
        }
        return buildingNums;
    }

    public void loseItem() {
        JDialog dialog = new JDialog((Frame) null, "Choose character to lose item", true);
        dialog.setLayout(null);
        dialog.setSize(380, 550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        boolean itemFound = false;

        for (int i = 0; i < activeCharacters.size(); i++) {
            if (!activeCharacters.get(i).items.isEmpty()) {
                loseItemHelper(dialog, i);
                itemFound = true;
            }
        }
        dialog.setVisible(true);
        dialog.repaint();
        dialog.revalidate();

        if (!itemFound) {
            dialog.dispose();
        }
    }

    private void loseItemHelper(JDialog dialog, int num) {
        Character c = activeCharacters.get(num);
        JButton characterButton = new JButton(c.toString());
        characterButton.setBounds(115, 25 + 50 * num, 150, 30);

        characterButton.addActionListener(_ ->
                new Thread(() ->
                {
                    JDialog dialog1 = new JDialog((Frame) null, "Choose item to lose for " + c, true);
                    dialog1.setLayout(null);
                    dialog1.setSize(380, 550);
                    dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog1.setLocationRelativeTo(null);
                    for (int i = 0; i < c.items.size(); i++) {
                        loseItemHelperHelper(c, dialog1, i, dialog);
                    }
                    dialog1.setVisible(true);
                    dialog1.repaint();
                    dialog1.revalidate();
                }).start());
        dialog.add(characterButton);
    }

    private void loseItemHelperHelper(Character c, JDialog dialog1, int num2, JDialog dialog) {
        JButton itemButton = new JButton(c.items.get(num2).toString());
        itemButton.setBounds(90, 25 + 50 * num2, 200, 30);
        itemButton.addActionListener(_ ->
        {
            availableItems.add(c.items.get(num2));
            c.items.remove(num2);
            dialog1.dispose();
            dialog.dispose();
        });
        dialog1.add(itemButton);
    }

    public int findEventActive(String deck, int num) {
        String event;
        switch (deck) {
            case "Summer Outpost" -> {
                event = "SO" + num;
                for (int i = 0; i < activeSummerOutpost.size(); i++) {
                    if (activeSummerOutpost.get(i).toString().equals(event)) {
                        return i;
                    }
                }
            }
            case "Winter Outpost" -> {
                event = "WO" + num;
                for (int i = 0; i < activeWinterOutpost.size(); i++) {
                    if (activeWinterOutpost.get(i).toString().equals(event)) {
                        return i;
                    }
                }
            }
            case "Winter Road" -> {
                event = "WR" + num;
                for (int i = 0; i < activeWinterRoad.size(); i++) {
                    if (activeWinterRoad.get(i).toString().equals(event)) {
                        return i;
                    }
                }
            }
            case "Summer Road" -> {
                event = "SR" + num;
                for (int i = 0; i < activeSummerRoad.size(); i++) {
                    if (activeSummerRoad.get(i).toString().equals(event)) {
                        return i;
                    }
                }
            }
            case "Boat" -> {
                event = "B" + num;
                for (int i = 0; i < activeBoat.size(); i++) {
                    if (activeBoat.get(i).toString().equals(event)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public int findEventInactive(String deck, int num) {
        String event;
        switch (deck) {
            case "Summer Outpost" -> {
                event = "SO" + num;
                for (int i = 0; i < inactiveSummerOutpost.size(); i++) {
                    if (inactiveSummerOutpost.get(i).toString().equals(event)) {
                        return i;
                    }
                }
            }
            case "Winter Outpost" -> {
                event = "WO" + num;
                for (int i = 0; i < inactiveWinterOutpost.size(); i++) {
                    if (inactiveWinterOutpost.get(i).toString().equals(event)) {
                        return i;
                    }
                }
            }
            case "Winter Road" -> {
                event = "WR" + num;
                for (int i = 0; i < inactiveWinterRoad.size(); i++) {
                    if (inactiveWinterRoad.get(i).toString().equals(event)) {
                        return i;
                    }
                }
            }
            case "Summer Road" -> {
                event = "SR" + num;
                for (int i = 0; i < inactiveSummerRoad.size(); i++) {
                    if (inactiveSummerRoad.get(i).toString().equals(event)) {
                        return i;
                    }
                }
            }
            case "Boat" -> {
                event = "B" + num;
                for (int i = 0; i < inactiveBoat.size(); i++) {
                    if (inactiveBoat.get(i).toString().equals(event)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public void addEvent(String deck, int num) {
        switch (deck) {
            case "Summer Outpost" -> {
                if (findEventInactive("Summer Outpost", num) != -1) {
                    activeSummerOutpost.add(inactiveSummerOutpost.remove(findEventInactive("Summer Outpost", num)));
                    Collections.shuffle(activeSummerOutpost);
                }
            }
            case "Winter Outpost" -> {
                if (findEventInactive("Winter Outpost", num) != -1) {
                    activeWinterOutpost.add(inactiveWinterOutpost.remove(findEventInactive("Winter Outpost", num)));
                    Collections.shuffle(activeWinterOutpost);
                }
            }
            case "Winter Road" -> {
                if (findEventInactive("Winter Road", num) != -1) {
                    activeWinterRoad.add(inactiveWinterRoad.remove(findEventInactive("Winter Road", num)));
                    Collections.shuffle(activeWinterRoad);
                }
            }
            case "Summer Road" -> {
                if (findEventInactive("Summer Road", num) != -1) {
                    activeSummerRoad.add(inactiveSummerRoad.remove(findEventInactive("Summer Road", num)));
                    Collections.shuffle(activeSummerRoad);
                }
            }
            case "Boat" -> {
                if (findEventInactive("Boat", num) != -1) {
                    activeBoat.add(inactiveBoat.remove(findEventInactive("Boat", num)));
                    Collections.shuffle(activeBoat);
                }
            }
        }
    }

    public void unlockBuilding(int number) {
        if (findBuildingInUnlocked(number) == -1) {
            for (int i = 0; i < unlockedBuildings.size(); i++) {
                if (unlockedBuildings.get(i).number > number) {
                    unlockedBuildings.add(i, lockedBuildings.remove(findBuildingInLocked(number)));
                    i = unlockedBuildings.size();
                }
            }
        }
    }

    public int findInTownGuardDeck(String name) {
        for (int i = 0; i < townGuardDeck.size(); i++) {
            if (townGuardDeck.get(i).name.equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Integer> getClosestTargeting(int num) {
        ArrayList<Integer> targeting = new ArrayList<>();
        if (num == 5) {
            targeting = new ArrayList<>(Arrays.asList(5, 65, 81, 83, 24, 39, 44, 21, 34, 37, 42, 35, 90, 72, 12, 85, 74, 67, 17, 84, 88, 98));
        } else if (num == 12) {
            targeting = new ArrayList<>(Arrays.asList(12, 72, 34, 42, 74, 44, 90, 24, 21, 83, 35, 84, 67, 65, 81, 39, 37, 85, 17, 5, 88, 98));
        } else if (num == 17) {
            targeting = new ArrayList<>(Arrays.asList(17, 67, 85, 98, 88, 90, 84, 74, 35, 37, 72, 21, 39, 42, 12, 83, 65, 44, 34, 81, 24, 5));
        } else if (num == 21) {
            targeting = new ArrayList<>(Arrays.asList(21, 35, 39, 83, 90, 65, 37, 42, 72, 81, 44, 5, 12, 74, 67, 85, 34, 24, 17, 84, 88, 98));
        } else if (num == 24) {
            targeting = new ArrayList<>(Arrays.asList(24, 44, 34, 81, 83, 5, 42, 65, 12, 21, 39, 72, 35, 90, 74, 37, 67, 85, 84, 17, 88, 98));
        } else if (num == 34) {
            targeting = new ArrayList<>(Arrays.asList(34, 44, 12, 24, 42, 83, 72, 81, 21, 65, 74, 5, 39, 90, 35, 37, 67, 84, 85, 17, 88, 98));
        } else if (num == 35) {
            targeting = new ArrayList<>(Arrays.asList(35, 21, 90, 37, 39, 85, 67, 42, 72, 83, 65, 17, 44, 74, 81, 5, 12, 34, 24, 84, 88, 98));
        } else if (num == 37) {
            targeting = new ArrayList<>(Arrays.asList(37, 35, 39, 21, 85, 90, 65, 83, 67, 42, 72, 81, 5, 17, 44, 74, 12, 24, 34, 84, 98, 88));
        } else if (num == 39) {
            targeting = new ArrayList<>(Arrays.asList(39, 65, 21, 37, 83, 35, 5, 81, 44, 42, 90, 85, 24, 72, 67, 34, 12, 74, 17, 84, 88, 98));
        } else if (num == 42) {
            targeting = new ArrayList<>(Arrays.asList(42, 72, 44, 12, 21, 34, 83, 90, 24, 74, 35, 37, 65, 81, 39, 67, 5, 85, 84, 17, 88, 98));
        } else if (num == 44) {
            targeting = new ArrayList<>(Arrays.asList(44, 24, 34, 83, 42, 81, 65, 12, 21, 5, 39, 72, 35, 90, 74, 37, 67, 85, 84, 17, 88, 98));
        } else if (num == 65) {
            targeting = new ArrayList<>(Arrays.asList(65, 39, 83, 5, 81, 21, 44, 37, 35, 24, 42, 34, 90, 72, 12, 85, 67, 74, 17, 84, 88, 98));
        } else if (num == 67) {
            targeting = new ArrayList<>(Arrays.asList(67, 85, 17, 90, 35, 74, 37, 72, 84, 21, 98, 88, 39, 42, 12, 83, 65, 44, 34, 81, 24, 5));
        } else if (num == 72) {
            targeting = new ArrayList<>(Arrays.asList(72, 90, 12, 74, 42, 21, 35, 34, 67, 44, 83, 84, 85, 37, 39, 24, 65, 17, 81, 88, 5, 98));
        } else if (num == 74) {
            targeting = new ArrayList<>(Arrays.asList(74, 72, 84, 90, 12, 42, 67, 35, 21, 88, 17, 34, 85, 44, 37, 83, 98, 39, 24, 65, 81, 5));
        } else if (num == 81) {
            targeting = new ArrayList<>(Arrays.asList(81, 5, 83, 65, 24, 44, 39, 21, 34, 42, 35, 37, 90, 12, 72, 74, 85, 67, 17, 84, 88, 98));
        } else if (num == 83) {
            targeting = new ArrayList<>(Arrays.asList(83, 65, 21, 44, 81, 39, 5, 42, 24, 34, 35, 37, 90, 72, 12, 74, 67, 85, 17, 84, 88, 98));
        } else if (num == 84) {
            targeting = new ArrayList<>(Arrays.asList(84, 74, 88, 90, 17, 67, 72, 98, 12, 85, 35, 42, 21, 37, 34, 83, 44, 39, 65, 25, 81, 5));
        } else if (num == 85) {
            targeting = new ArrayList<>(Arrays.asList(85, 67, 90, 17, 35, 37, 21, 72, 39, 74, 98, 84, 42, 88, 83, 65, 12, 44, 81, 34, 5, 24));
        } else if (num == 88) {
            targeting = new ArrayList<>(Arrays.asList(88, 98, 84, 17, 74, 67, 90, 85, 72, 35, 12, 21, 42, 37, 39, 34, 83, 44, 65, 24, 81, 5));
        } else if (num == 90) {
            targeting = new ArrayList<>(Arrays.asList(90, 72, 35, 67, 74, 21, 85, 42, 37, 17, 12, 84, 83, 39, 44, 65, 88, 34, 24, 98, 81, 5));
        } else if (num == 98) {
            targeting = new ArrayList<>(Arrays.asList(98, 88, 17, 67, 84, 85, 90, 74, 35, 72, 37, 21, 12, 42, 39, 83, 65, 34, 44, 24, 81, 5));
        }
        return targeting;
    }

    public void calculateScenarioLevel() {
        double totalLevel = 0;
        for (Character c : activeCharacters) {
            totalLevel += c.level;
        }
        double scenarioLvl = Math.ceil((totalLevel / activeCharacters.size()) / 2);
        scenarioLevel = Math.min(Math.max(((int) (scenarioLvl)) + diffScenarioLevel, 0), 7);
    }

    public void setDiffScenarioLevel() {
        diffScenarioLevel = 0;
        calculateScenarioLevel();
        JDialog dialog = new JDialog((Frame) null, "Select Character", true);
        dialog.setSize(380, 280);
        dialog.setLayout(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        setDiffScenarioLevelButtonHelper(dialog, 1, "Friendly", -2);
        setDiffScenarioLevelButtonHelper(dialog, 2, "Easy", -1);
        setDiffScenarioLevelButtonHelper(dialog, 3, "Normal", 0);
        setDiffScenarioLevelButtonHelper(dialog, 4, "Hard", 1);
        setDiffScenarioLevelButtonHelper(dialog, 5, "Brutal", 2);
        setDiffScenarioLevelButtonHelper(dialog, 6, "Deadly", 3);

        dialog.setVisible(true);
    }

    private void setDiffScenarioLevelButtonHelper(JDialog dialog, int num, String name, int diff) {
        if (scenarioLevel + diff > 0 && scenarioLevel + diff < 8) {
            String buttonName;
            if (diff <= 0) {
                buttonName = name + ": " + diff + " (" + (scenarioLevel + diff) + ")";
            } else {
                buttonName = name + ": +" + diff + " (" + (scenarioLevel + diff) + ")";
            }
            JButton diffButton = new JButton(buttonName);
            diffButton.setBounds(115, num * 30, 150, 30);
            diffButton.addActionListener(_ ->
            {
                diffScenarioLevel = diff;
                calculateScenarioLevel();
                dialog.dispose();
            });
            dialog.add(diffButton);
        }
    }

    public int findLootCard(String type) {
        for (int i = 0; i < lootCards.size(); i++) {
            if (lootCards.get(i).toString().length() >= type.length() && lootCards.get(i).toString().endsWith(type)) {
                return i;
            }
        }
        return -1;
    }

    public void enhanceLootCard(String type) {
        ArrayList<LootCard> enhanceable = new ArrayList<>();
        for (LootCard c : lootCards) {
            if (c.toString().length() >= type.length() && c.toString().endsWith(type)) {
                enhanceable.add(c);
            }
        }
        JDialog dialog = new JDialog((Frame) null, "Select Card to Enhance", true);
        dialog.setSize(600, Math.max(450, (enhanceable.size() + 1) * 30));
        dialog.setLayout(null);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        for (int i = 0; i < enhanceable.size(); i++) {
            enhanceLootCardHelper(dialog, i, enhanceable);
        }

        dialog.setVisible(true);
    }

    private void enhanceLootCardHelper(JDialog dialog, int num, ArrayList<LootCard> enhanceable) {
        LootCard lootCard = enhanceable.get(num);
        JButton enhance = new JButton(lootCard.toString());
        int height = dialog.getHeight() / 2 - 28;
        if (num % 2 == 0) {
            height += num * 15 + 15;
        } else {
            height -= num * 15;
        }
        enhance.setBounds(20, height, 250, 30);
        JLabel label = lootCard.getImage();
        label.setBounds(300, dialog.getHeight() / 2 - 218, 265, 407);

        enhance.addActionListener(_ ->
        {
            lootCard.enhancements++;
            dialog.dispose();
        });
        enhance.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setVisible(true);
                dialog.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setVisible(false);
                dialog.repaint();
            }
        });
        dialog.add(label);
        label.setVisible(false);
        dialog.add(enhance);
    }

    public int countCampaignStickers(String name) {
        int count = 0;
        for (String s : campaignStickers) {
            if (s.equals(name)) {
                count++;
            }
        }
        return count;
    }
}