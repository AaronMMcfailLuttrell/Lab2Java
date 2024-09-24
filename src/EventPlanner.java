import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventPlanner {

    public static void main(String[] args) {

        EventListPanel eventListPanel = new EventListPanel();
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.MAIN_FRAME_SIZE_X,Constants.MAIN_FRAME_SIZE_Y);
        frame.setVisible(true);
        frame.add(eventListPanel);

        //Create default events




        addDefaultEvents(eventListPanel);
        frame.repaint();
        frame.revalidate();
    }

    static void addDefaultEvents(EventListPanel events) {
        Meeting defaultMeeting = new Meeting("Default Meeting", LocalDateTime.now(), LocalDateTime.now().plusDays(1), "Default Location");
        Deadline defaultDeadline = new Deadline("Default Deadline", LocalDateTime.now().plusDays(2));
        events.addEvent(defaultMeeting);
        events.addEvent(defaultDeadline);
        events.redrawDisplay();
    }

}
