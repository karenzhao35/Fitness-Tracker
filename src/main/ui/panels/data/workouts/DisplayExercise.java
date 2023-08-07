package ui.panels.data.workouts;

import model.workout.Exercise;
import model.workout.Workout;
import ui.Colors;

import javax.swing.*;
import java.awt.*;

public class DisplayExercise {
    private Workout workout;

    private JPanel exerciseBase;
    private JPanel exerciseMain;
    private JFrame main;

    public DisplayExercise(Workout workout) {
        this.workout = workout;
        main = new JFrame();
        exerciseBase = new JPanel();
        exerciseMain = new JPanel();

        main.setTitle("Workout on " + workout.getDate());
        main.setLayout(null);
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        main.setMinimumSize(new Dimension(300, 400));
        main.getContentPane().setBackground(Colors.MAIN_COLOUR);

        setUpBasePanel();
        setUpMainPanels();
        printExercises();
        main.add(exerciseBase);
        exerciseBase.add(addScroll(exerciseMain),BorderLayout.CENTER);

        main.pack();
        main.setVisible(true);
    }

    public JScrollPane addScroll(JPanel panel) {
        JScrollPane pane = new JScrollPane(panel);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setBorder(null);
        return pane;
    }

    public void setUpMainPanels() {
        exerciseMain.setLayout(new BoxLayout(exerciseMain, BoxLayout.Y_AXIS));
        exerciseMain.setBackground(Colors.SIDEBAR);
        exerciseMain.setPreferredSize(new Dimension(500, calculateHeight()));
    }

    public void setUpBasePanel() {
        exerciseBase.setLayout(new BorderLayout());
        exerciseBase.setBackground(Colors.SIDEBAR);
        exerciseBase.setBounds(10, 20, 280, 330);
    }

    public int calculateHeight() {
        int height = (18 * (workout.getExercises().size() + 1));
        if (height < 335) {
            return 335;
        } else {
            return height;
        }
    }

    public void printExercises() {
        exerciseMain.add(new JLabel(" "));
        for (Exercise exercise : workout.getExercises()) {
            JLabel name = new JLabel("  For " + exercise.getName() + " you did:");
            name.setForeground(Colors.MAIN_COLOUR);
            name.setFont(new Font("Monospace", Font.BOLD, 14));
            exerciseMain.add(name);
            for (int i = 0; i < exercise.getReps().size(); i++) {
                JLabel set = new JLabel();
                set.setForeground(Colors.MAIN_COLOUR);
                set.setFont(new Font("Monospace", Font.PLAIN, 14));
                set.setText("    - " + exercise.getReps().get(i) + " reps at " + exercise.getWeight().get(i) + " lbs.");
                exerciseMain.add(set);
            }
        }
    }

    public static void main(String[] args) {
        new DisplayExercise(new Workout());
    }
}
