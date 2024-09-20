import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.constant.PackageDesc;
import java.time.LocalDateTime;

public class AddEventModal extends JDialog {
    final int WINDOW_WIDTH = 500;
    final int WINDOW_HEIGHT = 400;
    final int RADIAL_DIMENSION = 20;
    final int TEXTBOX_WIDTH = 200;
    final int TEXTBOX_HEIGHT = 20;
    final int ITEM_PADDING = 10;
    final int BOX_LABEL_WIDTH = 200;
    final int BOX_LABEL_HEIGHT = 20;
    final int MEETING_ARRAY_SIZE = 8;
    final int DEAD_ARRAY_SIZE = 4;
    final int MEET_RADIAL_X = TEXTBOX_WIDTH / 3 + (ITEM_PADDING * 2);
    final int BUTTON_WIDTH = 100;
    final int BUTTON_HEIGHT = 20;


    int whichRadialActive = 0;
    JRadioButton meet = new JRadioButton("Meeting");
    JRadioButton dead = new JRadioButton("Deadline");
    JTextField meetLabel = new JTextField();
    JTextField deadLabel = new JTextField();
    /*
    Sets the text fields for both meeting and deadline, but sets initially to false
    */
    //Meeting array, first four indeces hold the labels for the input boxes, last 4 indeces are where the user inputs the information
        //[0] = meetingNameLabel, [1] = meetingDateTimeLabel, [2] = meetingDuration, [3] = meetingLocation, the next 4 indeces are the actual text boxes
    JTextField[] meetingFieldArray = new JTextField[MEETING_ARRAY_SIZE];
    String[] labelStrings = {"Name:", "Date time:", "Duration: ", "Location: "};

    //Deadline Radial
        //[0] = deadlineName, [1] = deadlineDateTime, other 2 are the insert boxes

    JTextField[] deadFieldArray = new JTextField[DEAD_ARRAY_SIZE];
    String[] deadLabelStrings = {"Name:", "Date time:"};

    //Submit button
    JButton submitButton = new JButton("Submit");

    //Design
    AddEventModal() {
        this.setTitle("Add Event");
        this.setModal(true);
        this.setResizable(false);
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        //Set design for meeting
        for (int i = 0; i < MEETING_ARRAY_SIZE / 2; i++) {
            //label array
            meetingFieldArray[i] = new JTextField();
            meetingFieldArray[i].setEditable(false);
            meetingFieldArray[i].setText(labelStrings[i]);
            meetingFieldArray[i].setSize(TEXTBOX_WIDTH/3, TEXTBOX_HEIGHT);
            meetingFieldArray[i].setLocation(0, 50 + ((BOX_LABEL_HEIGHT) * i) + ITEM_PADDING);
            meetingFieldArray[i].setVisible(false);
            this.add(meetingFieldArray[i]);
        }

        for (int i = MEETING_ARRAY_SIZE / 2; i < MEETING_ARRAY_SIZE; i++) {
            meetingFieldArray[i] = new JTextField();
            meetingFieldArray[i].setEditable(true);
            meetingFieldArray[i].setSize(TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
            meetingFieldArray[i].setLocation(BOX_LABEL_WIDTH/3 + ITEM_PADDING, 50 + ((BOX_LABEL_HEIGHT) * (i-4) + ITEM_PADDING));
            meetingFieldArray[i].setBackground(Color.WHITE);
            meetingFieldArray[i].setVisible(false);
            this.add(meetingFieldArray[i]);
        }

        //Set design for Deadline
        for (int i = 0; i < DEAD_ARRAY_SIZE/2; i++) {
            deadFieldArray[i] = new JTextField();
            deadFieldArray[i].setEditable(false);
            deadFieldArray[i].setSize(TEXTBOX_WIDTH/3, TEXTBOX_HEIGHT);
            deadFieldArray[i].setText(deadLabelStrings[i]);
            deadFieldArray[i].setLocation(0, 50 + ((BOX_LABEL_HEIGHT) * (i) + ITEM_PADDING));
            deadFieldArray[i].setVisible(false);
            this.add(deadFieldArray[i]);
        }

        for (int i = DEAD_ARRAY_SIZE / 2; i < DEAD_ARRAY_SIZE; i++) {
            deadFieldArray[i] = new JTextField();
            deadFieldArray[i].setEditable(true);
            deadFieldArray[i].setSize(TEXTBOX_WIDTH, TEXTBOX_HEIGHT);
            deadFieldArray[i].setLocation(BOX_LABEL_WIDTH/3 + ITEM_PADDING, 50+ ((BOX_LABEL_HEIGHT) * (i-2) + ITEM_PADDING));
            deadFieldArray[i].setBackground(Color.WHITE);
            deadFieldArray[i].setVisible(false);
            this.add(deadFieldArray[i]);
        }


        //Set radio buttons to display specific options
        meetLabel.setEditable(false);
        meetLabel.setSize(TEXTBOX_WIDTH/3, TEXTBOX_HEIGHT);
        meetLabel.setLocation(0, 0);
        meetLabel.setVisible(true);
        meetLabel.setText("Meeting");
        this.add(meetLabel);
        meet.setLocation(MEET_RADIAL_X,0);
        meet.setSize(RADIAL_DIMENSION,RADIAL_DIMENSION);
        meet.setVisible(true);
        meet.addActionListener(e -> {
            if (meet.isSelected()) {
                dead.setSelected(false);

                //display all meeting textboxes
                for (int i = 0; i < MEETING_ARRAY_SIZE; i++) {
                    meetingFieldArray[i].setVisible(true);
                }

                //Make deadline invisible
                for (int i = 0; i < DEAD_ARRAY_SIZE; i++) {
                    deadFieldArray[i].setVisible(false);
                }

                whichRadialActive = 1;

            } else if (!meet.isSelected() && whichRadialActive == 1) {
                meet.setSelected(true);
            }
        });
        this.add(meet);
        dead.setLocation((MEET_RADIAL_X + ITEM_PADDING)+40,0);
        dead.setSize(RADIAL_DIMENSION,RADIAL_DIMENSION);
        dead.setVisible(true);
        deadLabel.setEditable(false);
        deadLabel.setSize(TEXTBOX_WIDTH/3, TEXTBOX_HEIGHT);
        deadLabel.setLocation(MEET_RADIAL_X + TEXTBOX_WIDTH/2, 0);
        deadLabel.setVisible(true);
        deadLabel.setText("Deadline");
        this.add(deadLabel);
        dead.addActionListener(e -> {
            if (dead.isSelected()) {
                meet.setSelected(false);

                //makes meeting invisible
                for (int i = 0; i < MEETING_ARRAY_SIZE; i++) {
                    meetingFieldArray[i].setVisible(false);

                }

                //Displays deadline interface
                for (int i = 0; i < DEAD_ARRAY_SIZE; i++) {
                    deadFieldArray[i].setVisible(true);
                }
                whichRadialActive = 2;
            } else if (!dead.isSelected() && whichRadialActive == 2) {
                dead.setSelected(true);
            }
        });
        this.add(dead);
        submitButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        submitButton.setBackground(Color.WHITE);
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        submitButton.setLocation(100, 200);
        submitButton.setVisible(true);
        submitButton.addActionListener(e -> {

            if (whichRadialActive == 1) {
                //If meeting is being created
                Meeting tempMeeting = new Meeting(meetingFieldArray[4].getText(), meetingFieldArray[5].getText(), meetingFieldArray[6].getText(), meetingFieldArray[7].getText());
            } else if (whichRadialActive == 2) {
                //If deadline is being created
                LocalDateTime test = new LocalDateTime(//LocalDate, //LocalTime)
            }

        });
        this.add(submitButton);
        this.setVisible(true);
    }
}
