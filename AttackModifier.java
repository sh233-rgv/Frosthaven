import java.util.Queue;

public class AttackModifier {
    public String name;
    public int modifierValue;
    public boolean rolling;
    public boolean hasAttackEffect;
    public String frontImage;

    public AttackModifier(String name, int modifierValue, boolean rolling, boolean hae, String frontImage) {
        this.name = name;
        this.modifierValue = modifierValue;
        this.rolling = rolling;
        hasAttackEffect = hae;
        this.frontImage = frontImage;
    }

    public AttackModifier(String name, int modifierValue, String frontImage) {
        this(name, modifierValue, false, false, frontImage);
    }

    public AttackModifier(String name, int modifierValue, boolean rolling, String frontImage) {
        this(name, modifierValue, rolling, false, frontImage);
    }

    public AttackModifier() {
        this("null", 0, false, false, null);
    }

    public void attackEffect(Figure target, Queue<Runnable> amdEffectQueue) {

    }

    public void attackEffectOutpost() {

    }


    public int calculateValue(Figure target) {
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
