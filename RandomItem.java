public class RandomItem extends LootCard {
    public RandomItem(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Loot Cards/Random Item.png", camp);
    }

    @Override
    public void drawEffect(Character c) {
        System.out.println(c + " gains a random item (to be done)");
    }

    @Override
    public String toString() {
        return "Random Item";
    }
}
