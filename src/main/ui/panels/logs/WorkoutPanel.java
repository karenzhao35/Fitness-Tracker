package ui.panels.logs;

import model.AllData;
import model.workout.Workout;
import ui.panels.MainSearchSetUp;
import javax.swing.*;
import java.awt.event.ActionEvent;

// panel for users to log new workouts
public class WorkoutPanel extends MainSearchSetUp {
    private ExercisePanel exercisePanel;
    private JPanel panel;


    // EFFECTS: constructs a new WorkoutPanel with given allWorkouts
    public WorkoutPanel(AllData allData, JFrame frame) {
        super(allData, frame);
        exercisePanel = new ExercisePanel();
        panel = exercisePanel.getPanel();
        addComponents();
    }

    // MODIFIES: this
    // EFFECTS: adds components to main panel
    public void addComponents() {
        super.addComponents();
        mainPanel.add(generateHeading("LET'S TRACK YOUR WORKOUT", 75));
        mainPanel.add(panel);
    }


    // MODIFIES: this
    // EFFECTS: starts a new workout based on user input
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitDateButton) {
            String date = dateTextField.getText();
            if (date.equals("today")) {
                Workout workout = new Workout();
                error.setVisible(false);
                exercisePanel.start(allData, workout, frame);
            } else if (dateFormat(date)) {
                Workout workout = new Workout(date);
                error.setVisible(false);
                exercisePanel.start(allData, workout, frame);
            } else {
                error.setVisible(true);
            }
        }

    }

    // EFFECTS: returns main panel
    @Override
    public JPanel getPanel() {
        return mainPanel;
    }
}