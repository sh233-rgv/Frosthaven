public class Hide1 extends LootCard {
    public Hide1(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Hide1.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        c.hide += 1 + enhancements;
    }

    @Override
    public String toString() {
        return (1 + enhancements) + "Hide";
    }
}
