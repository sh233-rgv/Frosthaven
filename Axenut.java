public class Axenut extends LootCard {
    public Axenut(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Axenut.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        camp.axenut += 1 + enhancements;
    }

    public String toString() {
        return (1 + enhancements) + " Axenut";
    }
}
