public class SpecialCoin2 extends LootCard {
    public SpecialCoin2(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Special Coin 2.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        System.out.println("To be done");
    }

    @Override
    public String toString() {
        return "2 Special Coin";
    }
}
