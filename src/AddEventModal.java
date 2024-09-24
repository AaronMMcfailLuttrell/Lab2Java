import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.constant.PackageDesc;
import java.sql.SQLOutput;
import java.time.Duration;
import java.time.LocalDateTime;

public class AddEventModal extends JDialog {

    int whichRadialActive = 0;
    JRadioButton meet = new JRadioButton("Meeting");
    JRadioButton dead = new JRadioButton("Deadline");
    JTextField meetLabel = new JTextField();
    JTextField deadLabel = new JTextField();
    /*
    Sets the text fields for both meeting and deadline, but sets initially to false
    */
    //Meeting array, first four indeces hold the labels for the input boxes, last 4 indeces are where the user inputs the information
        //[0] = meetingNameLabel, [1] = meetingLocation, [2] = meetingDateTimeLabel, [3] = meetingDuration, [3] = meetingLocation, the next 4 indeces are the actual text boxes
    JTextField[] meetingFieldArray = new JTextField[Constants.MEETING_ARRAY_SIZE];
    String[] labelStrings = {"Name:", "Location: ", "Date time:", "End Time: "};

    //Deadline Radial
        //[0] = deadlineName, [1] = deadlineDateTime, other 2 are the insert boxes

    JTextField[] deadFieldArray = new JTextField[Constants.DEAD_ARRAY_SIZE];
    String[] deadLabelStrings = {"Name:", "Date time:"};

    //Submit button
    JButton submitButton = new JButton("Submit");

    //Design
    AddEventModal(EventListPanel eventListPanel) {
        this.setTitle("Add Event");
        this.setModal(true);
        this.setResizable(false);
        this.setSize(Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        //Set design for meeting
        for (int i = 0; i < Constants.MEETING_ARRAY_SIZE / 2; i++) {
            //label array
            meetingFieldArray[i] = new JTextField();
            meetingFieldArray[i].setEditable(false);
            meetingFieldArray[i].setText(labelStrings[i]);
            meetingFieldArray[i].setSize(Constants.TEXTBOX_WIDTH/3, Constants.TEXTBOX_HEIGHT);
            meetingFieldArray[i].setLocation(0, 50 + ((Constants.BOX_LABEL_HEIGHT) * i) + Constants.ITEM_PADDING);
            meetingFieldArray[i].setVisible(false);
            this.add(meetingFieldArray[i]);
        }

        for (int i = Constants.MEETING_ARRAY_SIZE / 2; i < Constants.MEETING_ARRAY_SIZE; i++) {
            meetingFieldArray[i] = new JTextField();
            meetingFieldArray[i].setEditable(true);
            meetingFieldArray[i].setSize(Constants.TEXTBOX_WIDTH, Constants.TEXTBOX_HEIGHT);
            meetingFieldArray[i].setLocation(Constants.BOX_LABEL_WIDTH/3 + Constants.ITEM_PADDING, 50 + ((Constants.BOX_LABEL_HEIGHT) * (i-4) + Constants.ITEM_PADDING));
            meetingFieldArray[i].setBackground(Color.WHITE);
            meetingFieldArray[i].setVisible(false);
            this.add(meetingFieldArray[i]);
        }

        //set format text for meetingDateTime
        meetingFieldArray[6].setText("yyyy-mm-dd-hh-mm-ss");
        meetingFieldArray[7].setText("yyyy-mm-dd-hh-mm-ss");

        //Set design for Deadline
        for (int i = 0; i < Constants.DEAD_ARRAY_SIZE/2; i++) {
            deadFieldArray[i] = new JTextField();
            deadFieldArray[i].setEditable(false);
            deadFieldArray[i].setSize(Constants.TEXTBOX_WIDTH/3, Constants.TEXTBOX_HEIGHT);
            deadFieldArray[i].setText(deadLabelStrings[i]);
            deadFieldArray[i].setLocation(0, 50 + ((Constants.BOX_LABEL_HEIGHT) * (i) + Constants.ITEM_PADDING));
            deadFieldArray[i].setVisible(false);
            this.add(deadFieldArray[i]);
        }


        for (int i = Constants.DEAD_ARRAY_SIZE / 2; i < Constants.DEAD_ARRAY_SIZE; i++) {
            deadFieldArray[i] = new JTextField();
            deadFieldArray[i].setEditable(true);
            deadFieldArray[i].setSize(Constants.TEXTBOX_WIDTH, Constants.TEXTBOX_HEIGHT);
            deadFieldArray[i].setLocation(Constants.BOX_LABEL_WIDTH/3 + Constants.ITEM_PADDING, 50+ ((Constants.BOX_LABEL_HEIGHT) * (i-2) + Constants.ITEM_PADDING));
            deadFieldArray[i].setBackground(Color.WHITE);
            deadFieldArray[i].setVisible(false);
            this.add(deadFieldArray[i]);
        }
        deadFieldArray[3].setText("yyyy-mm-dd-hh-mm-ss");

        //Set radio buttons to display specific options
        meetLabel.setEditable(false);
        meetLabel.setSize(Constants.TEXTBOX_WIDTH/3, Constants.TEXTBOX_HEIGHT);
        meetLabel.setLocation(0, 0);
        meetLabel.setVisible(true);
        meetLabel.setText("Meeting");
        this.add(meetLabel);
        meet.setLocation(Constants.MEET_RADIAL_X,0);
        meet.setSize(Constants.RADIAL_DIMENSION,Constants.RADIAL_DIMENSION);
        meet.setVisible(true);
        meet.addActionListener(e -> {
            if (meet.isSelected()) {
                dead.setSelected(false);

                //display all meeting textboxes
                for (int i = 0; i < Constants.MEETING_ARRAY_SIZE; i++) {
                    meetingFieldArray[i].setVisible(true);
                }

                //Make deadline invisible
                for (int i = 0; i < Constants.DEAD_ARRAY_SIZE; i++) {
                    deadFieldArray[i].setVisible(false);
                }

                whichRadialActive = 1;

            } else if (!meet.isSelected() && whichRadialActive == 1) {
                meet.setSelected(true);
            }
        });
        this.add(meet);
        dead.setLocation((Constants.MEET_RADIAL_X + Constants.ITEM_PADDING)+40,0);
        dead.setSize(Constants.RADIAL_DIMENSION,Constants.RADIAL_DIMENSION);
        dead.setVisible(true);
        deadLabel.setEditable(false);
        deadLabel.setSize(Constants.TEXTBOX_WIDTH/3, Constants.TEXTBOX_HEIGHT);
        deadLabel.setLocation(Constants.MEET_RADIAL_X + Constants.TEXTBOX_WIDTH/2, 0);
        deadLabel.setVisible(true);
        deadLabel.setText("Deadline");
        this.add(deadLabel);
        dead.addActionListener(e -> {
            if (dead.isSelected()) {
                meet.setSelected(false);

                //makes meeting invisible
                for (int i = 0; i < Constants.MEETING_ARRAY_SIZE; i++) {
                    meetingFieldArray[i].setVisible(false);

                }

                //Displays deadline interface
                for (int i = 0; i < Constants.DEAD_ARRAY_SIZE; i++) {
                    deadFieldArray[i].setVisible(true);
                }
                whichRadialActive = 2;
            } else if (!dead.isSelected() && whichRadialActive == 2) {
                dead.setSelected(true);
            }
        });
        this.add(dead);
        submitButton.setSize(Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        submitButton.setBackground(Color.WHITE);
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        submitButton.setLocation(Constants.SUBMIT_LOC_X, Constants.SUBMIT_LOC_Y);
        submitButton.setVisible(true);
        submitButton.addActionListener(e -> {

            if (whichRadialActive == 1) {
                //If meeting is being created

                //Start local time
                String boxTextDate = meetingFieldArray[6].getText();
                String[] dateBoxInfo = boxTextDate.split("-");
                int[] dateValues = new int[6];
                int count = 0;
                //Sets each string to an int to create a localdatetime
                for (String placeholder : dateBoxInfo) {
                    dateValues[count] = Integer.parseInt(placeholder);
                    count++;
                }
                //Create LocalDateTime for event
                LocalDateTime tempLocalDateTime = LocalDateTime.of(dateValues[0], dateValues[1], dateValues[2], dateValues[3], dateValues[4], dateValues[5]);
                //Create end local time
                String boxTextEndDate = meetingFieldArray[7].getText();
                String[] endDateBoxInfo = boxTextEndDate.split("-");
                int[] endDateValues = new int[6];
                count = 0;
                for (String placeholder : endDateBoxInfo) {
                    endDateValues[count] = Integer.parseInt(placeholder);
                    count++;
                }
                LocalDateTime endLocalDate = LocalDateTime.of(endDateValues[0], endDateValues[1],endDateValues[2],endDateValues[3],endDateValues[4],endDateValues[5]);

                Meeting tempMeeting = new Meeting(meetingFieldArray[4].getText(), tempLocalDateTime, endLocalDate, meetingFieldArray[5].getText());

                eventListPanel.addEvent(tempMeeting);
                this.dispose();
                eventListPanel.sortDropDown.setSelectedIndex(0);
                eventListPanel.redrawDisplay();
                //Meeting tempMeeting = new Meeting(meetingFieldArray[4].getText(), meetingFieldArray[5].getText(), meetingFieldArray[6].getText(), meetingFieldArray[7].getText());
            } else if (whichRadialActive == 2) {
                //If deadline is being created
                String deadlineBoxDate = deadFieldArray[3].getText();
                String[] deadlineBoxInfo = deadlineBoxDate.split("-");
                int[] deadlineDateValues = new int[6];
                int count = 0;
                for (String placeholder : deadlineBoxInfo) {
                    deadlineDateValues[count] = Integer.parseInt(placeholder);
                    count++;
                }
                LocalDateTime deadlineDateTimePlaceholder = LocalDateTime.of(deadlineDateValues[0],deadlineDateValues[1],deadlineDateValues[2],deadlineDateValues[3],deadlineDateValues[4],deadlineDateValues[5]);
                Deadline tempDeadline = new Deadline(deadFieldArray[2].getText(), deadlineDateTimePlaceholder);
                eventListPanel.addEvent(tempDeadline);
                this.dispose();
                eventListPanel.sortDropDown.setSelectedIndex(0);
                eventListPanel.redrawDisplay();
            }

        });
        this.add(submitButton);
        this.setVisible(true);
    }
}
