import javax.swing.*;

public class AddEventModal extends JDialog {
    final int WINDOW_WIDTH = 500;
    final int WINDOW_HEIGHT = 400;
    final int RADIAL_DIMENSION = 20;

    int whichRadialActive = 0;
    JRadioButton meet = new JRadioButton("Meeting");
    JRadioButton dead = new JRadioButton("Deadline");
    AddEventModal() {
        this.setTitle("Add Event");
        this.setModal(true);
        this.setResizable(false);
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        //Set radio buttons to display specific options
        meet.setLocation(0,0);
        meet.setSize(RADIAL_DIMENSION,RADIAL_DIMENSION);
        meet.setVisible(true);
        meet.addActionListener(e -> {
            if (meet.isSelected()) {
                dead.setSelected(false);
            }
        });
        this.add(meet);
        dead.setLocation(50,0);
        dead.setSize(RADIAL_DIMENSION,RADIAL_DIMENSION);
        dead.setVisible(true);
        dead.addActionListener(e -> {
            if (dead.isSelected()) {
                meet.setSelected(false);
            }
        });
        this.add(dead);
        this.setVisible(true);
    }
}
