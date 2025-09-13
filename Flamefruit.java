public class Flamefruit extends LootCard {
    public Flamefruit(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Flamefruit.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        camp.flamefruit += 1 + enhancements;
    }

    @Override
    public String toString() {
        return (1 + enhancements) + "Flamefruit";
    }
}
