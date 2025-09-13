public class Rockroot extends LootCard {
    public Rockroot(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Rockroot.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        camp.rockroot += 1 + enhancements;
    }

    @Override
    public String toString() {
        return (1 + enhancements) + "Rockroot";
    }
}
