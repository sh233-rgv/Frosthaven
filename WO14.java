import javax.swing.*;
import java.util.ArrayList;

public class WO14 extends Event {
    public WO14(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO14.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO14.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 409, 453, 45, 250, 45, 455);
    }

    @Override
    public void optionA() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.distributeThings(20, "Gold");
        camp.distributeThings(3, "Metal");
        if (camp.findEventActive("Winter Outpost", 13) != -1) {
            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.remove(camp.findEventActive("Winter Outpost", 13)));
        }
    }

    @Override
    public void optionB() {
        camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        camp.distributeThings(5, "Gold");
        camp.distributeThings(1, "Metal");
        if (camp.findEventActive("Winter Outpost", 13) != -1) {
            camp.inactiveWinterOutpost.add(camp.activeWinterOutpost.remove(camp.findEventActive("Winter Outpost", 13)));
        }
    }
}