import javax.swing.*;
import java.awt.*;

public class EventPanel extends JPanel {
    Event event;
    JButton completeButton = new JButton("Complete");
    EventPanel() {
        setPreferredSize(new Dimension(400, 200));
        setBackground(Color.WHITE);
        setVisible(true);
        completeButton.setVisible(true);
        add(completeButton);

        JLabel nameLabel = new JLabel("Name: " + event.getName());
        add(nameLabel);
        JLabel timeLabel = new JLabel("Time: " + event.getDateTime());
        add(timeLabel);

    }



}
