public class Snowthistle extends LootCard {
    public Snowthistle(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Snowthistle.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        camp.snowthistle += 1 + enhancements;
    }

    @Override
    public String toString() {
        return (1 + enhancements) + " Snowthistle";
    }
}
