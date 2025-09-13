import javax.swing.*;
import java.util.ArrayList;

public class SO58 extends Event {
    public SO58(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Summer Outpost Front/SO58.png", "/Users/saahilherrero/Downloads/Images/Summer Outpost Back/SO58.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 468, 253, 220, 253, 498);
    }

    @Override
    public void optionA() {
        camp.inactiveSummerOutpost.add(camp.activeSummerOutpost.removeFirst());
        for (int i = 0; i < camp.activeSummerRoad.size(); i++) {
            if (camp.activeSummerRoad.get(i).toString().equals("SR47") || camp.activeSummerRoad.get(i).toString().equals("SR48")
                    || camp.activeSummerRoad.get(i).toString().equals("SR49")) {
                camp.activeSummerRoad.addFirst(camp.activeSummerRoad.remove(i));
                i = camp.activeSummerRoad.size();
            }
        }
    }

    @Override
    public void optionB() {
        optionA();
    }

}
