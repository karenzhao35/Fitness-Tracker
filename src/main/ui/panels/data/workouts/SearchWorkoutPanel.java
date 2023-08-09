package ui.panels.data.workouts;

import model.AllData;
import model.Date;
import model.exceptions.DoesNotExist;
import model.workout.AllWorkouts;
import model.workout.Workout;
import ui.ColourPicker;
import ui.panels.MainSearchSetUp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// search workout panel
public class SearchWorkoutPanel extends MainSearchSetUp {
    private ScrollInterfaceWorkout scrollInterfacePanel;
    private JPanel display;
    private JPanel single;
    private JButton back;

    // EFFECTS: constructs SearchWorkoutPanel
    public SearchWorkoutPanel(AllData allData, JFrame frame) {
        super(allData, frame);
        back = new JButton("Back");
        scrollInterfacePanel = new ScrollInterfaceWorkout(allData, frame);
        single = new JPanel(new BorderLayout());
        display = scrollInterfacePanel.getPanel();

        generateButton(back, 240, 300, 70);
        back.setVisible(false);
        single.setVisible(false);
        addComponents();
    }

    // EFFECTS: adds components to main
    public void addComponents() {
        super.addComponents();
        mainPanel.add(single);
        mainPanel.add(back);
        mainPanel.add(display);
        mainPanel.add(generateHeading("SEARCH PREVIOUS WORKOUTS", 75));
    }

    // MODIFIES: this
    // EFFECTS: filter through workouts
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitDateButton) {
            filterWorkout();
        }
        if (e.getSource() == back) {
            error.setVisible(false);
            back.setVisible(false);
            display.setVisible(true);
            single.setVisible(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: filter workout by date
    private void filterWorkout() {
        Date today = new Date();
        String date = dateTextField.getText();
        if (date.equals("today")) {
            try {
                Workout retrieved = allWorkouts.retrieveWorkout(today.getDate());
                DisplayWorkout workout = new DisplayWorkout(allData, frame, retrieved, 0);
                displaySingleEntry();
                single.add(workout.getPanel());
            } catch (DoesNotExist ex) {
                error.setVisible(true);
            }
        } else if (dateFormat(date)) {
            try {
                Workout retrieved = allWorkouts.retrieveWorkout(date);
                DisplayWorkout workout = new DisplayWorkout(allData, frame, retrieved, 0);
                displaySingleEntry();
                single.add(workout.getPanel());
            } catch (DoesNotExist ex) {
                error.setVisible(true);
            }
        } else {
            error.setVisible(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: display a single entry
    private void displaySingleEntry() {
        error.setVisible(false);
        single.setVisible(true);
        single.setBounds(65, 200, 420, 100);
        single.setBackground(ColourPicker.SIDEBAR);
        display.setVisible(false);
        back.setVisible(true);
    }

    // EFFECTS: return main panel
    @Override
    public JPanel getPanel() {
        return mainPanel;
    }
}
