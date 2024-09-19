import javax.swing.*;
import java.awt.*;

public class EventPlanner {

    public static void main(String[] args) {
        EventListPanel eventListPanel = new EventListPanel();
        JFrame frame = new JFrame("Event Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,1000);
        frame.setVisible(true);
        frame.add(eventListPanel);
    }


}
