import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WO63 extends Event {
    public WO63(Campaign camp) {
        super("/Users/saahilherrero/Downloads/Images/Winter Outpost Front/WO63.png", "/Users/saahilherrero/Downloads/Images/Winter Outpost Back/WO63.png", camp);
    }

    @Override
    public ArrayList<JButton> getButtons(JLayeredPane layeredPane, JDialog dialog) {
        return basicButtons(layeredPane, dialog, 424, 453, 195, 146, 140, 497);
    }

    @Override
    public void optionA() {
        camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        JDialog dialog = new JDialog((Frame) null, "Select Building", true);
        dialog.setLayout(null);
        dialog.setSize(380, 550);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);

        int[] building = {-1};

        JButton continueButton = new JButton("Select");
        continueButton.setBounds(37, 200, 300, 30);
        continueButton.setVisible(false);

        JButton miningCamp = new JButton("Mining Camp");
        miningCamp.setBounds(115, 30, 150, 30);
        miningCamp.addActionListener(_ ->
        {
            continueButton.setText("Select Mining Camp");
            building[0] = 5;
            continueButton.setVisible(true);
        });
        dialog.add(miningCamp);
        JButton huntingLodge = new JButton("Hunting Lodge");
        huntingLodge.setBounds(115, 60, 150, 30);
        huntingLodge.addActionListener(_ ->
        {
            continueButton.setText("Select Hunting Lodge");
            building[0] = 12;
            continueButton.setVisible(true);
        });
        dialog.add(huntingLodge);
        JButton loggingCamp = new JButton("Logging Camp");
        loggingCamp.setBounds(115, 90, 150, 30);
        loggingCamp.addActionListener(_ ->
        {
            continueButton.setText("Select Logging Camp");
            building[0] = 17;
            continueButton.setVisible(true);
        });
        dialog.add(loggingCamp);
        continueButton.addActionListener(_ ->
        {
            camp.buildingOperationsModifiers.add("Repeat " + building[0]);
            dialog.dispose();
        });
        dialog.add(continueButton);
        dialog.setVisible(true);
    }

    @Override
    public void optionB() {
        camp.activeWinterOutpost.add(camp.activeWinterOutpost.removeFirst());
        int count = 0;
        for (int i = 0; i < camp.unlockedBuildings.size(); i++) {
            if (camp.unlockedBuildings.get(i).wrecked) {
                count++;
                camp.unlockedBuildings.get(i).wrecked = false;
            }
        }
        camp.gainMaterials(count);
        if (count == 0) {
            camp.increaseProsperityBoxes(1);
        }
    }
}