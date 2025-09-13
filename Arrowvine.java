public class Arrowvine extends LootCard {
    public Arrowvine(Campaign camp) {
        super( "/Users/saahilherrero/Downloads/Images/Loot Cards/Arrowvine.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        camp.arrowvine += 1 + enhancements;
    }

    public String toString() {
        return (1 + enhancements) + " Arrowvine";
    }
}
