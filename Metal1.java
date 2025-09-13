public class Metal1 extends LootCard {
    public Metal1(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Metal1.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        c.metal += 1 + enhancements;
    }

    @Override
    public String toString() {
        return (1 + enhancements) + "Metal";
    }
}
