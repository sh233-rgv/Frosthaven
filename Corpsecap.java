public class Corpsecap extends LootCard {
    public Corpsecap(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Corpsecap.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        camp.corpsecap += 1 + enhancements;
    }

    @Override
    public String toString() {
        return (1 + enhancements) + " Corpsecap";
    }
}
