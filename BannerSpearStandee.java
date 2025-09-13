import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class BannerSpearStandee extends CharacterStandee {

    public BannerSpearStandee(Character c, Scenario scenario) {
        super(c, scenario);
    }

    @Override
    public void applyPerkNumber(int number) {
        Figure f = this;
        if (number == 1 || number == 2 || number == 3) {
            removeFromAMD("-1");
            attackModifierDeck.add(new AttackModifier("+0 Shield 1 Rolling", 0, true, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/Banner Spear/+0 Shield 1 Rolling.png") {
                @Override
                public void attackEffect(Figure target, Queue<Runnable> amdEffectQueue) {
                    roundShieldVal++;
                    Methods.finishQueue(amdEffectQueue);
                }
            });
        } else if (number == 4 || number == 5) {
            removeFromAMD("+0");
            attackModifierDeck.add(new AttackModifier("+1 Add +1 Attack for each ally adjacent to the target", 0, true, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/Banner Spear/+1 Add +1 Attack for each ally adjacent to the target.png") {
                @Override
                public int calculateValue(Figure target) {
                    int num = 0;
                    for (Hex hex : target.hex.adjacentHexes) {
                        if (!hex.isUnoccupied() && !hex.figure.equals(f) && hex.figure.team == team) {
                            num++;
                        }
                    }
                    return num;
                }
            });
        } else if (number == 6 || number == 7) {
            attackModifierDeck.add(new AttackModifier("+1 Disarm", 0, true, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/Banner Spear/+1 Disarm.png") {
                @Override
                public void attackEffect(Figure target, Queue<Runnable> amdEffectQueue) {
                    target.gainCondition("Disarm");
                    Methods.finishQueue(amdEffectQueue);
                }
            });
        } else if (number == 8 || number == 9) {
            attackModifierDeck.add(new AttackModifier("+2 Push 1", 0, true, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/Banner Spear/+2 Push 1.png") {
                @Override
                public void attackEffect(Figure target, Queue<Runnable> amdEffectQueue) {
                    f.push(target, 1, () -> Methods.finishQueue(amdEffectQueue));
                }
            });
        } else if (number == 10 || number == 11) {
            attackModifierDeck.add(new AttackModifier("+1 Rolling", 0, true, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/Banner Spear/+1 Rolling.png"));
        } else if (number == 12 || number == 13) {
            attackModifierDeck.add(new AttackModifier("+0 Heal 1 Self Rolling", 0, true, "/Users/saahilherrero/Downloads/Images/Attack Modifiers/Banner Spear/+0 Heal 1 Self Rolling.png") {
                @Override
                public void attackEffect(Figure target, Queue<Runnable> amdEffectQueue) {
                    f.heal(1, 1, 0, new ArrayList<>(Arrays.asList("Target Self")), () -> Methods.finishQueue(amdEffectQueue));
                }
            });
        } else if (number == 14) {
            ignoreItemEffects = true;
            removeFromAMD("-1");
        } else if (number == 15) {
            nonAMDS.add(new NonAMD("Tireless Leadership", "/Users/saahilherrero/Downloads/Images/Attack Modifiers/Banner Spear/Tireless Leadership.png"));
        } else if (number == 16) {
            nonAMDS.add(new NonAMD("Into the Breach", "/Users/saahilherrero/Downloads/Images/Attack Modifiers/Banner Spear/Into the Breach.png"));
        } else if (number == 18) {
            nonAMDS.add(new NonAMD("Iron Will", "/Users/saahilherrero/Downloads/Images/Attack Modifiers/Banner Spear/Iron Will.png"));
        }
    }

    @Override
    public String toString() {
        return "Banner Spear";
    }
}
