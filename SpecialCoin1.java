public class SpecialCoin1 extends LootCard {
    public SpecialCoin1(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Special Coin 1.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        System.out.println("To be done");
    }

    @Override
    public String toString() {
        return "1 Special Coin";
    }
}