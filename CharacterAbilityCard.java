
public abstract class CharacterAbilityCard {
    Character character;
    CharacterStandee characterStandee;
    public String imagePath;
    public int initiative;
    public int level;
    public String activeStatus;

    public CharacterAbilityCard(Character c, int i, int l) {
        imagePath = "/Users/saahilherrero/Downloads/Images/Character Ability Cards/" + c.toString() + "/" + this + ".png";
        character = c;
        initiative = i;
        level = l;
    }

    public abstract void topAction();

    public abstract void bottomAction();

    public void discardPlayedCard() {
        if (characterStandee.played.contains(this)) {
            characterStandee.discard.add(characterStandee.played.get(characterStandee.played.indexOf(this)));
        } else {
            throw new UnsupportedOperationException("Card not found!");
        }
    }

    public void activePlayedCard(String as) {
        if (characterStandee.played.contains(this)) {
            characterStandee.active.add(characterStandee.played.get(characterStandee.played.indexOf(this)));
            activeStatus = as;
        } else {
            throw new UnsupportedOperationException("Card not found!");
        }
    }

    @Override
    public abstract String toString();
}
