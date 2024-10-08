import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EventListPanel extends JPanel {

    boolean checkBoxShowComplete = false;
    boolean checkBoxShowMeetings = false;
    boolean checkBoxShowDeadlines = false;

    private ArrayList<Event> events = new ArrayList<>();
    EventListPanel(ArrayList<Event> events) {
        this.events = events;
    }
    JPanel controlPanel = new JPanel();
    private JPanel displayPanel = new JPanel();

    private JScrollPane itemHolder = new JScrollPane(displayPanel);
    JComboBox sortDropDown = new JComboBox();
    //JCheckBox -> [0] = Show Complete Tasks, [1] = Show Meetings, [2] = Show Deadlines
    JCheckBox[] filterDisplay = new JCheckBox[3];
    JTextField[] filterText = new JTextField[3];
    String[] filterTextString = {"Show Complete Tasks", "Show Meetings", "Show Deadlines"};
    JButton addEventButton = new JButton();



    public EventListPanel() {
        this.setLayout(null);
        this.setBackground(Color.gray);
        this.setVisible(true);
        //controlPanel Config
        controlPanel.setSize(Constants.CONTROL_PANEL_X,Constants.CONTROL_PANEL_Y);
        controlPanel.setLayout(null);
        controlPanel.setBackground(Color.white);
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        controlPanel.setVisible(true);
        controlPanel.setLocation(Constants.CONTROL_PANEL_LOC_X, Constants.CONTROL_PANEL_LOC_Y);
        this.add(controlPanel);
        //DisplayPanel Config
        displayPanel.setSize(Constants.DISPLAY_PANEL_X,Constants.DISPLAY_PANEL_Y);
        displayPanel.setBackground(Color.white);
        displayPanel.setLocation(Constants.DISPLAY_PANEL_LOC_X,Constants.DISPLAY_PANEL_LOC_Y);
        displayPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        displayPanel.setVisible(true);
        displayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        //Itemholder is the scrollbar for displayPanel
        itemHolder.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        itemHolder.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        itemHolder.setSize(Constants.DISPLAY_PANEL_X, Constants.DISPLAY_PANEL_Y);
        itemHolder.setBackground(Color.white);
        itemHolder.setBorder(BorderFactory.createLineBorder(Color.black));

        //dropdown
        sortDropDown.setBackground(Color.white);
        sortDropDown.setLayout(null);
        sortDropDown.setSize(Constants.DROP_DOWN_SIZE_X,Constants.DROP_DOWN_SIZE_Y);
        sortDropDown.setLocation(Constants.DROP_DOWN_LOC_X,Constants.DROP_DOWN_LOC_Y);
        sortDropDown.setBorder(BorderFactory.createLineBorder(Color.black));
        sortDropDown.setVisible(true);


        //Button declarations for sortDropDown
        JButton blankButton = new JButton();
        blankButton.setText("No Sort");
        blankButton.setBorder(BorderFactory.createLineBorder(Color.black));
        sortDropDown.addItem(blankButton);

        JButton nameButton = new JButton();
        nameButton.setText("Sort by Name");
        nameButton.setBorder(BorderFactory.createLineBorder(Color.black));
        sortDropDown.addItem(nameButton);

        JButton dateButton = new JButton();
        dateButton.setText("Sort by Date");
        dateButton.setBorder(BorderFactory.createLineBorder(Color.black));
        sortDropDown.addItem(dateButton);

        JButton nameReverseButton = new JButton();
        nameReverseButton.setText("Sort by Name Reverse");
        nameReverseButton.setBorder(BorderFactory.createLineBorder(Color.black));
        sortDropDown.addItem(nameReverseButton);

        JButton dateReverseButton = new JButton();
        dateReverseButton.setText("Sort by Date Reverse");
        dateReverseButton.setBorder(BorderFactory.createLineBorder(Color.black));
        sortDropDown.addItem(dateReverseButton);

        sortDropDown.setRenderer(new ListCellRenderer<JButton>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends JButton> list, JButton value, int index, boolean isSelected, boolean cellHasFocus) {
                return value;
            }
        });



        sortDropDown.addActionListener(e -> {
            if (sortDropDown.getSelectedIndex() == 0) {
                //Serves as a blank option to ignore sorting
            } else if (sortDropDown.getSelectedIndex() == 1) {
                //Sort by name

                Collections.sort(events, new Comparator<Event>() {
                    public int compare(Event event1, Event event2) {
                        return event1.getName().compareTo(event2.getName());
                    }
                });

                redrawDisplay();

            } else if (sortDropDown.getSelectedIndex() == 2) {
                //Sort by Date

                Collections.sort(events, new Comparator<Event>() {
                    public int compare(Event event1, Event event2) {
                        return event1.getDateTime().compareTo(event2.getDateTime());
                    }
                });

                redrawDisplay();
            } else if (sortDropDown.getSelectedIndex() == 3) {
                //Sort by reverse name

                Collections.sort(events, new Comparator<Event>() {
                    public int compare(Event event1, Event event2) {
                        return event2.getName().compareTo(event1.getName());
                    }
                });

                redrawDisplay();
            } else if (sortDropDown.getSelectedIndex() == 4) {
                //Sort by reverse Date

                Collections.sort(events, new Comparator<Event>() {
                    public int compare(Event event1, Event event2) {
                        return event2.getDateTime().compareTo(event1.getDateTime());
                    }
                });
            }
        });
        controlPanel.add(sortDropDown);
        //filterDisplay
        for (int i = 0; i < 3; i++) {
            filterDisplay[i] = new JCheckBox();
            filterDisplay[i].setLocation(10,10+40*i);
            filterDisplay[i].setBorder(BorderFactory.createLineBorder(Color.black));
            filterDisplay[i].setBackground(Color.white);
            filterDisplay[i].setLayout(null);
            filterDisplay[i].setSelected(true);
            filterDisplay[i].setSize(Constants.CHECK_DIMENSION,Constants.CHECK_DIMENSION);
            filterDisplay[i].setVisible(true);
            filterText[i] = new JTextField();
            filterText[i].setLocation(40,10+40*i);
            filterText[i].setText(filterTextString[i]);
            filterText[i].setSize(200, Constants.CHECK_DIMENSION);
            filterText[i].setEditable(false);
            filterText[i].setVisible(true);
            controlPanel.add(filterText[i]);
            controlPanel.add(filterDisplay[i]);
        }

        checkBoxShowComplete = true;
        checkBoxShowMeetings = true;
        checkBoxShowDeadlines = true;

        /*
        Commands for each filter button
        */
        //Display Complete
        filterDisplay[0].addActionListener(e -> {
            if (filterDisplay[0].isSelected()) {
                checkBoxShowComplete = true;
            } else if (!filterDisplay[0].isSelected()) {
                checkBoxShowComplete = false;
            }
            redrawDisplay();
        });
        filterDisplay[1].addActionListener(e -> {
            if (filterDisplay[1].isSelected()) {
                checkBoxShowMeetings = true;
            } else if (!filterDisplay[1].isSelected()) {
                checkBoxShowMeetings = false;
            }
            redrawDisplay();
        });
        filterDisplay[2].addActionListener(e -> {
            if (filterDisplay[2].isSelected()) {
                checkBoxShowDeadlines = true;
            } else if (!filterDisplay[2].isSelected()) {
                checkBoxShowDeadlines = false;
            }
            redrawDisplay();
        });

        addEventButton.setText("Add Event"); //addEventModal
        addEventButton.addActionListener(e -> {AddEventModal eventMod = new AddEventModal(this);});
        addEventButton.setLocation(Constants.ADD_EVENT_BUTTON_LOC_X,Constants.ADD_EVENT_BUTTON_LOC_Y);
        addEventButton.setBorder(BorderFactory.createLineBorder(Color.black));
        addEventButton.setBackground(Color.white);
        addEventButton.setLayout(null);
        addEventButton.setSize(Constants.ADD_EVENT_BUTTON_SIZE_X,Constants.ADD_EVENT_BUTTON_SIZE_Y);
        controlPanel.add(addEventButton);

        this.add(itemHolder);
        itemHolder.setPreferredSize(new Dimension(Constants.DISPLAY_PANEL_X, Constants.DISPLAY_PANEL_Y));
        displayPanel.setPreferredSize(new Dimension(Constants.DISPLAY_PANEL_X,Constants.DISPLAY_PANEL_Y));



    }

    public void redrawDisplay() {
        displayPanel.removeAll();

        if (checkBoxShowComplete) {

        }

        if (checkBoxShowMeetings) {

            if (checkBoxShowComplete) {
                for (Event event : events) {
                    if ((event instanceof Meeting)) {
                        displayPanel.add(new EventPanel(event));
                        displayPanel.add(Box.createVerticalStrut(5));
                    }
                }
            } else if (!checkBoxShowComplete) {
                for (Event event : events) {
                    if ((event instanceof Meeting) && (!(((Meeting) event).isComplete()))) {
                        displayPanel.add(new EventPanel(event));
                        displayPanel.add(Box.createVerticalStrut(5));
                    }
                }
            }

        }

        if (checkBoxShowDeadlines) {
            if (checkBoxShowComplete) {
                for (Event event : events) {
                    if ((event instanceof Deadline)) {
                        displayPanel.add(new EventPanel(event));
                        displayPanel.add(Box.createVerticalStrut(5));

                    }
                }
            } else if (!checkBoxShowComplete) {
                for (Event event : events) {
                    if ((event instanceof Deadline) && (!(((Deadline) event).isComplete()))) {
                        displayPanel.add(new EventPanel(event));
                        displayPanel.add(Box.createVerticalStrut(5));

                    }
                }
            }
        }

        displayPanel.setPreferredSize(new Dimension(Constants.DISPLAY_PANEL_X, displayPanel.getComponentCount() * 200));
        displayPanel.revalidate();
        displayPanel.repaint();
        itemHolder.revalidate();
        itemHolder.repaint();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

}
