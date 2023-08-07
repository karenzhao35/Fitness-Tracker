package ui.panels.data.workouts;

import model.workout.AllWorkouts;
import model.workout.Workout;
import ui.ColourPicker;
import ui.panels.data.DisplayLogs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Display a workout log
public class DisplayWorkout extends DisplayLogs {

    // EFFECTS: constructs a DisplayWorkout with given workout data and iteration
    public DisplayWorkout(AllWorkouts allWorkouts, Workout workout, int iteration) {
        super(allWorkouts, workout, null, null, iteration);
        addComponents();
    }

    // MODIFIES: this
    // EFFECTS: adds components to main
    @Override
    public void addComponents() {
        super.addComponents();
        main.add(generateDate(workout.getDate(), 25));
        main.add(generateTotalExercises());
    }

    // EFFECTS: generates total exercises label
    private JLabel generateTotalExercises() {
        JLabel exercises = new JLabel("total exercises: " + workout.getExercises().size());
        exercises.setBounds(20, 50, 135, 15);
        exercises.setFont(new Font("Didot", Font.BOLD, 12));
        exercises.setForeground(ColourPicker.MAIN_COLOUR);
        return exercises;
    }


    // MODIFIES: this
    // EFFECTS: processes user input to remove or view workout
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == remove) {
            int answer = JOptionPane.showConfirmDialog(null, "Confirm removal?");
            removeWorkout(answer);
        }
        if (e.getSource() == expand) {
            new DisplayExercise(workout);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes workout only if user chooses to
    public void removeWorkout(int answer) {
        if (answer == 0) {
            allWorkouts.removeWorkout(workout);
        }
    }

    // EFFECTS: returns main panel
    @Override
    public JPanel getPanel() {
        return main;
    }

}
