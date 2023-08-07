package ui.panels.logs;

import model.workout.AllWorkouts;
import model.workout.Exercise;
import model.workout.Workout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ExercisePanel extends InnerPanel {
    private AllWorkouts allWorkouts;
    private Workout workout;

    private JButton continueButton;

    private JTextField repsTextField;
    private JTextField weightTextField;

    private List<Integer> reps;
    private List<Integer> weight;

    public ExercisePanel() {
        super();
        reps = new ArrayList<>();
        weight = new ArrayList<>();
        continueButton = new JButton("Continue");
        repsTextField = new JTextField();
        weightTextField = new JTextField();
        generatePanelItems();
        addToFoodPanel();
        main.setVisible(false);
    }

    @Override
    public void generatePanelItems() {
        super.generatePanelItems();
        generateButton(continueButton, 161, 280, 80);
        generateTextField(repsTextField, 115);
        generateTextField(weightTextField, 170);
    }

    @Override
    public void addToFoodPanel() {
        super.addToFoodPanel();
        main.add(continueButton);
        main.add(repsTextField);
        main.add(weightTextField);
        main.add(generateLabels("add exercise:", 157, 10));
        main.add(generateLabels("reps:", 51, 107));
        main.add(generateLabels("weight: ", 50, 165));
        main.add(generateLabels("(lbs)", 350, 165));
    }

    @Override
    public void clearTextFields() {
        nameTextField.setText("");
        repsTextField.setText("");
        weightTextField.setText("");
    }

    public void startWorkout(AllWorkouts allWorkouts, Workout workout) {
        this.allWorkouts = allWorkouts;
        this.workout = workout;
        main.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == continueButton) {
            if (checkValid()) {
                error.setVisible(false);
                continueWorkout();
            } else {
                error.setVisible(true);
            }
        }
        if (e.getSource() == submitButton) {
            if (nameTextField.isEnabled()) {
                newWorkout();
            } else {
                nameTextField.setEnabled(true);
                finishExercise();
            }
        }
        if (e.getSource() == doneButton) {
            finishWorkout();
        }
    }

    private void finishWorkout() {
        main.setVisible(false);
        reps = new ArrayList<>();
        weight = new ArrayList<>();
        clearTextFields();
        if (!workout.getExercises().isEmpty()) {
            allWorkouts.addWorkout(workout);
        }
    }

    private void finishExercise() {
        error.setVisible(false);
        String name = nameTextField.getText();
        Exercise exercise = new Exercise(name, reps, weight);
        workout.addExercise(exercise);
        reps = new ArrayList<>();
        weight = new ArrayList<>();
        clearTextFields();
    }

    private void newWorkout() {
        if (checkValid()) {
            error.setVisible(false);
            submitWorkout();
            clearTextFields();
        } else {
            error.setVisible(true);
        }
    }

    private void submitWorkout() {
        String name = nameTextField.getText();
        reps.add(Integer.parseInt(repsTextField.getText()));
        weight.add(Integer.parseInt(weightTextField.getText()));
        Exercise exercise = new Exercise(name, reps, weight);
        workout.addExercise(exercise);
        reps = new ArrayList<>();
        weight = new ArrayList<>();
    }

    private void continueWorkout() {
        nameTextField.setEnabled(false);
        reps.add(Integer.parseInt(repsTextField.getText()));
        weight.add(Integer.parseInt(weightTextField.getText()));
        repsTextField.setText("");
        weightTextField.setText("");
    }


    @Override
    public boolean checkValid() {
        return (!nameTextField.getText().equals("")
                && isInteger(repsTextField.getText())
                && isInteger(weightTextField.getText()));
    }


}