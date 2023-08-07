package ui.panels.data.workouts;

import model.Date;
import model.exceptions.DoesNotExist;
import model.workout.AllWorkouts;
import ui.Colors;
import ui.panels.MainSearchSetUp;
import ui.panels.data.workouts.DisplayWorkout;
import ui.panels.data.workouts.ScrollInterfaceWorkout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SearchWorkoutPanel extends MainSearchSetUp {
    private ScrollInterfaceWorkout scrollInterfacePanel;
    private JPanel display;
    private JPanel single;
    private JButton back;

    public SearchWorkoutPanel(AllWorkouts allWorkouts) {
        super(allWorkouts, null);
        back = new JButton("Back");
        generateButton(back, 240, 300, 70);
        back.setVisible(false);
        scrollInterfacePanel = new ScrollInterfaceWorkout(allWorkouts);
        single = new JPanel(new BorderLayout());
        single.setVisible(false);
        display = scrollInterfacePanel.getPanel();
        addComponents();
    }

    public void addComponents() {
        super.addComponents();
        mainPanel.add(single);
        mainPanel.add(back);
        mainPanel.add(display);
        mainPanel.add(generateHeading("SEARCH PREVIOUS WORKOUTS", 75));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Date today = new Date();
        if (e.getSource() == submitDateButton) {
            String date = dateTextField.getText();
            if (date.equals("today")) {
                try {
                    DisplayWorkout workout = new DisplayWorkout(allWorkouts, allWorkouts.retrieveWorkout(today.getDate()), 0);
                    displaySingleEntry();
                    single.add(workout.getPanel());
                } catch (DoesNotExist ex) {
                    error.setVisible(true);
                }
            } else if (dateFormat(date)) {
                try {
                    DisplayWorkout workout = new DisplayWorkout(allWorkouts, allWorkouts.retrieveWorkout(date), 0);
                    displaySingleEntry();
                    single.add(workout.getPanel());
                } catch (DoesNotExist ex) {
                    error.setVisible(true);
                }
            } else {
                error.setVisible(true);
            }
        }
        if (e.getSource() == back) {
            error.setVisible(false);
            back.setVisible(false);
            display.setVisible(true);
            single.setVisible(false);
        }
    }

    private void displaySingleEntry() {
        error.setVisible(false);
        single.setVisible(true);
        single.setBounds(65, 200, 420, 100);
        single.setBackground(Colors.SIDEBAR);
        display.setVisible(false);
        back.setVisible(true);
    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }
}
