import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EventListPanel extends JPanel {

    final int CHECK_DIMENSION = 20;


    ArrayList<Event> events;
    EventListPanel(ArrayList<Event> events) {
        this.events = events;
    }
    JPanel controlPanel = new JPanel();
    JPanel displayPanel = new JPanel();
    JComboBox sortDropDown = new JComboBox();
    JCheckBox filterDisplay = new JCheckBox();
    JButton addEventButton = new JButton();

    public EventListPanel() {
        this.setLayout(null);
        this.setBackground(Color.gray);
        this.setVisible(true);
        //controlPanel Config
        controlPanel.setSize(600,200);
        controlPanel.setLayout(null);
        controlPanel.setBackground(Color.white);
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        controlPanel.setVisible(true);
        controlPanel.setLocation(300, 750);
        this.add(controlPanel);
        //DisplayPanel Config
        displayPanel.setSize(1000,700);
        displayPanel.setBackground(Color.white);
        displayPanel.setLocation(100,0);
        displayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        displayPanel.setVisible(true);
        //dropdown
        sortDropDown.setBackground(Color.white);
        sortDropDown.setLayout(null);
        sortDropDown.setSize(200,30);
        sortDropDown.setLocation(100,50);
        sortDropDown.setBorder(BorderFactory.createLineBorder(Color.black));
        sortDropDown.setVisible(true);
        controlPanel.add(sortDropDown);
        //filterDisplay
        filterDisplay.setLocation(100,100);
        filterDisplay.setBorder(BorderFactory.createLineBorder(Color.black));
        filterDisplay.setBackground(Color.white);
        filterDisplay.setLayout(null);
        filterDisplay.setSize(CHECK_DIMENSION,CHECK_DIMENSION);
        filterDisplay.setVisible(true);
        controlPanel.add(filterDisplay);
        addEventButton.setText("Add Event"); //addEventModal
        addEventButton.addActionListener(e -> {AddEventModal eventMod = new AddEventModal();});
        addEventButton.setLocation(200,100);
        addEventButton.setBorder(BorderFactory.createLineBorder(Color.black));
        addEventButton.setBackground(Color.white);
        addEventButton.setLayout(null);
        addEventButton.setSize(200,20);
        controlPanel.add(addEventButton);
        this.add(displayPanel);

    }
}
