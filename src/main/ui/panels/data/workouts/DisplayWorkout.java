package ui.panels.data.workouts;

import model.workout.AllWorkouts;
import model.workout.Workout;
import ui.Colors;
import ui.panels.data.DisplayLogs;
import ui.panels.data.workouts.DisplayExercise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DisplayWorkout extends DisplayLogs {

    public DisplayWorkout(AllWorkouts allWorkouts, Workout workout, int iteration) {
        super(allWorkouts, workout, null, null, iteration);
        addComponents();
    }

    public void addComponents() {
        super.addComponents();
        main.add(generateDate(workout.getDate(), 25));
        main.add(generateTotalExercises());
    }

    private JLabel generateTotalExercises() {
        JLabel exercises = new JLabel("total exercises: " + workout.getExercises().size());
        exercises.setBounds(20, 50, 135, 15);
        exercises.setFont(new Font("Didot", Font.BOLD, 12));
        exercises.setForeground(Colors.MAIN_COLOUR);
        return exercises;
    }

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

    public void removeWorkout(int answer) {
        if (answer == 0) {
            allWorkouts.removeWorkout(workout);
        }
    }

    @Override
    public JPanel getPanel() {
        return main;
    }

}
