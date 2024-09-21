import javax.swing.*;
import java.awt.*;

public class EventPanel extends JPanel {
    Event event;
    JButton completeButton = new JButton("Complete");
    EventPanel(Event event, int panelWidth, int panelHeight) {
        this.event = event;
        this.setPreferredSize(new Dimension(panelWidth-40, 200));
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setVisible(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(event.getDisplayString());
        textArea.setSize(panelWidth-60, 180);
        textArea.setPreferredSize(new Dimension(panelWidth-60, 180));
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setLocation(10,10);
        textArea.setVisible(true);
        completeButton.setVisible(true);
        this.add(textArea);
        this.add(completeButton);

    }



}
