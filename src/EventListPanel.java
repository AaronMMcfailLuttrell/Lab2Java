import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EventListPanel extends JPanel {

    final int CHECK_DIMENSION = 20;
    final int CONTROL_PANEL_X = 600;
    final int CONTROL_PANEL_Y = 200;
    final int DISPLAY_PANEL_X = 1000;
    final int DISPLAY_PANEL_Y = 700;

    ArrayList<Event> events = new ArrayList<>();
    EventListPanel(ArrayList<Event> events) {
        this.events = events;
    }
    JPanel controlPanel = new JPanel();
    private JPanel displayPanel = new JPanel();

    private JScrollPane itemHolder = new JScrollPane(displayPanel);
    JComboBox sortDropDown = new JComboBox();
    JCheckBox filterDisplay = new JCheckBox();
    JButton addEventButton = new JButton();



    public EventListPanel() {
        this.setLayout(null);
        this.setBackground(Color.gray);
        this.setVisible(true);
        //controlPanel Config
        controlPanel.setSize(CONTROL_PANEL_X,CONTROL_PANEL_Y);
        controlPanel.setLayout(null);
        controlPanel.setBackground(Color.white);
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        controlPanel.setVisible(true);
        controlPanel.setLocation(300, 750);
        this.add(controlPanel);
        //DisplayPanel Config
        displayPanel.setSize(DISPLAY_PANEL_X,DISPLAY_PANEL_Y);
        displayPanel.setBackground(Color.white);
        displayPanel.setLocation(100,0);
        displayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        displayPanel.setVisible(true);
        displayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        itemHolder.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        itemHolder.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        itemHolder.setSize(DISPLAY_PANEL_X,DISPLAY_PANEL_Y);
        itemHolder.setBackground(Color.white);
        itemHolder.setBorder(BorderFactory.createLineBorder(Color.black));

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
        addEventButton.addActionListener(e -> {AddEventModal eventMod = new AddEventModal(this);});
        addEventButton.setLocation(200,100);
        addEventButton.setBorder(BorderFactory.createLineBorder(Color.black));
        addEventButton.setBackground(Color.white);
        addEventButton.setLayout(null);
        addEventButton.setSize(200,20);
        controlPanel.add(addEventButton);

        this.add(itemHolder);
        itemHolder.setPreferredSize(new Dimension(DISPLAY_PANEL_X, DISPLAY_PANEL_Y));
        displayPanel.setPreferredSize(new Dimension(DISPLAY_PANEL_X,DISPLAY_PANEL_Y));

    }

    public void redrawDisplay() {
        displayPanel.removeAll();
        for (Event event : events) {
            displayPanel.add(new EventPanel(event, DISPLAY_PANEL_X, DISPLAY_PANEL_Y));
            displayPanel.add(Box.createVerticalStrut(5));
        }
        displayPanel.setPreferredSize(new Dimension(DISPLAY_PANEL_X, displayPanel.getComponentCount() * 200));
        displayPanel.revalidate();
        displayPanel.repaint();
        itemHolder.revalidate();
        itemHolder.repaint();
    }
}
