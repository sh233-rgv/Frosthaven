import java.util.ArrayList;

public class BannerSpear extends Character {
    public BannerSpear(Campaign camp) {
        super(camp, Character.HIGHHEALTH, 10, 0, "Armored", "Persuasive", "Resourceful", "Human", new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Banner Spear";
    }

    @Override
    public CharacterStandee getStandee(Scenario scenario) {
        return new BannerSpearStandee(this, scenario);
    }
}
