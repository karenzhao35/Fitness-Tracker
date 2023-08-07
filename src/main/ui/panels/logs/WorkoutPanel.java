package ui.panels.logs;

import model.workout.AllWorkouts;
import model.workout.Workout;
import ui.panels.MainSearchSetUp;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class WorkoutPanel extends MainSearchSetUp {
    private ExercisePanel exercisePanel;
    private JPanel panel;


    public WorkoutPanel(AllWorkouts allWorkouts) {
        super(allWorkouts, null);
        exercisePanel = new ExercisePanel();
        panel = exercisePanel.getPanel();
        addComponents();
    }

    public void addComponents() {
        super.addComponents();
        mainPanel.add(generateHeading("LET'S TRACK YOUR WORKOUT", 75));
        mainPanel.add(panel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitDateButton) {
            String date = dateTextField.getText();
            if (date.equals("today")) {
                Workout workout = new Workout();
                error.setVisible(false);
                exercisePanel.startWorkout(allWorkouts, workout);
            } else if (dateFormat(date)) {
                Workout workout = new Workout(date);
                error.setVisible(false);
                exercisePanel.startWorkout(allWorkouts, workout);
            } else {
                error.setVisible(true);
            }
        }

    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }
}