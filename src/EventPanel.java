import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EventPanel extends JPanel {
    Event event;
    JButton completeButton = new JButton("Complete");
    EventPanel(Event event/*, EventListPanel eventListPanel*/) {
        this.event = event;
        this.setPreferredSize(new Dimension(Constants.DISPLAY_PANEL_X-40, 200));
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setVisible(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(event.getDisplayString());
        textArea.setSize(Constants.DISPLAY_PANEL_X-60, 50);
        textArea.setPreferredSize(new Dimension(Constants.DISPLAY_PANEL_X-60, 50));
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setLocation(10,10);
        textArea.setVisible(true);
        completeButton.setText("Complete");
        completeButton.setSize(Constants.SUBMIT_WIDTH, Constants.SUBMIT_HEIGHT);
        completeButton.setLocation(0, 150);
        completeButton.addActionListener(e -> {
           if (event instanceof Completable) {
               ((Completable) event).complete();
           }
           //eventListPanel.redrawDisplay();
        });
        completeButton.setBackground(Color.WHITE);
        completeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        completeButton.setVisible(true);
        updateUrgency();
        this.add(textArea);
        this.add(completeButton);

    }

    void updateUrgency() {
        //Set the timer that will update every second
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            Duration howFar;
            if (event instanceof Meeting) {
                howFar = Duration.between(LocalDateTime.now(), ((Meeting)this.event).getEndDateTime());
            } else {
                howFar = Duration.between(LocalDateTime.now(), this.event.getDateTime());
            }


            if (howFar.toDaysPart() <= 0 && howFar.toHoursPart() <= 0 && howFar.toMinutesPart() <= 0 && howFar.toSecondsPart() <= 0) {
                this.setBackground(Color.RED);
            } else if (howFar.toDaysPart() < 1) {
                this.setBackground(Color.YELLOW);
            } else if (howFar.toDaysPart() >= 1) {
                this.setBackground(Color.GREEN);
            }
        };

        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }

    public Event getEvent() {
        return event;
    }

}
