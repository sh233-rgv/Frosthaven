import javax.swing.*;
import java.util.ArrayList;

public class SO11 extends Event {
    public SO11(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO11.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO11.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 439, 239, 191, 239, 428);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(1);
        camp.gainMaterials(3);
        camp.campaignStickers.add("Brummix");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(1);
        System.out.println("One character gains Deathproof Charm (to be done)");
        camp.campaignStickers.add("Brummix");
    }
}