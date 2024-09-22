import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EventPanel extends JPanel {
    final int SUBMIT_WIDTH = 100;
    final int SUBMIT_HEIGHT = 30;
    Event event;
    JButton completeButton = new JButton("Complete");
    EventPanel(Event event, int panelWidth, int panelHeight, EventListPanel eventListPanel) {
        this.event = event;
        this.setPreferredSize(new Dimension(panelWidth-40, 200));
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setVisible(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(event.getDisplayString());
        textArea.setSize(panelWidth-60, 50);
        textArea.setPreferredSize(new Dimension(panelWidth-60, 50));
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setLocation(10,10);
        textArea.setVisible(true);
        completeButton.setText("Complete");
        completeButton.setSize(SUBMIT_WIDTH, SUBMIT_HEIGHT);
        completeButton.setLocation(0, 150);
        completeButton.addActionListener(e -> {
           if (event instanceof Meeting) {
               ((Meeting) event).complete();
           } else if (event instanceof Deadline) {
               ((Deadline) event).complete();
           }
           eventListPanel.redrawDisplay();
        });
        completeButton.setBackground(Color.WHITE);
        completeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        completeButton.setVisible(true);
        this.add(textArea);
        this.add(completeButton);

    }

    void updateUrgency() {

    }


}
