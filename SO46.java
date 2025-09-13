import javax.swing.*;
import java.util.ArrayList;

public class SO46 extends Event {
    public SO46(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO46.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO46.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 439, 468, 309, 220, 281, 366);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(-1);
        camp.unlockBuilding(83);
        camp.constructionModifiers.add("Upgrade Library for Free");
    }

    @Override
    public void optionB() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        camp.increaseMorale(1);
        camp.distributeThings(3, "Lumber");
        camp.distributeThings(2, "Metal");
    }

}
