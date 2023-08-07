package ui.panels.data.workouts;

import model.workout.Exercise;
import model.workout.Workout;
import ui.ColourPicker;
import javax.swing.*;
import java.awt.*;

// Displays the exercise
public class DisplayExercise {
    private Workout workout;
    private JPanel exerciseBase;
    private JPanel exerciseMain;
    private JFrame main;

    // EFFECTS: constructs a DisplayExercise with given workout
    public DisplayExercise(Workout workout) {
        this.workout = workout;
        main = new JFrame();
        exerciseBase = new JPanel();
        exerciseMain = new JPanel();
        setUpMain(workout);
        setUpBasePanel();
        setUpMainPanels();
        printExercises();
        addComponents();
        main.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds components to main frame
    private void addComponents() {
        main.add(exerciseBase);
        exerciseBase.add(addScroll(exerciseMain), BorderLayout.CENTER);
        main.pack();
    }

    // MODIFIES: this
    // EFFECTS: sets up main frame
    private void setUpMain(Workout workout) {
        main.setTitle("Workout on " + workout.getDate());
        main.setLayout(null);
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main.setMinimumSize(new Dimension(300, 400));
        main.getContentPane().setBackground(ColourPicker.MAIN_COLOUR);
    }

    // EFFECTS: generates scroll pane
    public JScrollPane addScroll(JPanel panel) {
        JScrollPane pane = new JScrollPane(panel);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setBorder(null);
        return pane;
    }

    // MODIFIES: this
    // EFFECTS: sets up main exercise panel
    public void setUpMainPanels() {
        exerciseMain.setLayout(new BoxLayout(exerciseMain, BoxLayout.Y_AXIS));
        exerciseMain.setBackground(ColourPicker.SIDEBAR);
        exerciseMain.setPreferredSize(new Dimension(500, calculateHeight()));
    }

    // MODIFIES: this
    // EFFECTS: sets up base exercise panel
    public void setUpBasePanel() {
        exerciseBase.setLayout(new BorderLayout());
        exerciseBase.setBackground(ColourPicker.SIDEBAR);
        exerciseBase.setBounds(10, 20, 280, 330);
    }

    // EFFECTS: calculates the height of the panel
    public int calculateHeight() {
        int height = (18 * (workout.getExercises().size() + 1));
        if (height < 335) {
            return 335;
        } else {
            return height;
        }
    }

    // MODIFIES: this
    // EFFECTS: prints labels for each exercise and set and add it to main panel
    public void printExercises() {
        exerciseMain.add(new JLabel(" "));
        for (Exercise exercise : workout.getExercises()) {
            JLabel name = new JLabel("  For " + exercise.getName() + " you did:");
            name.setForeground(ColourPicker.MAIN_COLOUR);
            name.setFont(new Font("Monospace", Font.BOLD, 14));
            exerciseMain.add(name);
            for (int i = 0; i < exercise.getReps().size(); i++) {
                JLabel set = new JLabel();
                set.setForeground(ColourPicker.MAIN_COLOUR);
                set.setFont(new Font("Monospace", Font.PLAIN, 14));
                set.setText("    - " + exercise.getReps().get(i) + " reps at " + exercise.getWeight().get(i) + " lbs.");
                exerciseMain.add(set);
            }
        }
    }
}
