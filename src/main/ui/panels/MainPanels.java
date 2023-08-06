package ui.panels;

import model.food.AllMeals;
import model.workout.AllWorkouts;
import ui.Colors;
import ui.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public abstract class MainPanels extends Panel {
    protected AllMeals allMeals;
    protected AllWorkouts allWorkouts;
    protected JTextField dateTextField;
    protected JPanel mainPanel;
    protected JButton submitDateButton;
    protected JLabel error;

    public MainPanels(AllWorkouts allWorkouts, AllMeals allMeals) {
        mainPanel = new JPanel();
        this.allMeals = allMeals;
        this.allWorkouts = allWorkouts;
        dateTextField = new JTextField();
        submitDateButton = new JButton("Submit");
        error = new JLabel("Invalid entry: Does not follow format.");

        generateDateTextField(dateTextField);
        generateButton(submitDateButton, 435, 120, 70);
        generateDateErrorMsg(error);

        mainPanel.setLayout(null);
        mainPanel.setBackground(Colors.MAIN_COLOUR);
        mainPanel.setPreferredSize(MainPanel.DIMENSION);
    }

    public void addComponents() {
        mainPanel.add(submitDateButton);
        mainPanel.add(dateTextField);
        mainPanel.add(generateDateLabel());
        mainPanel.add(generateDateSubheading());
        mainPanel.add(error);
    }

    public void generateDateTextField(JTextField date) {
        date.setBounds(105, 120, 330, 30);
        date.setFont(new Font("Monospace", Font.PLAIN, 15));
        date.setForeground(Colors.MAIN_COLOUR);
        date.setBackground(Colors.SIDEBAR);
        date.setCaretColor(Colors.MAIN_COLOUR);
    }

    public JLabel generateHeading(String label, int x) {
        JLabel heading = new JLabel(label);
        heading.setForeground(Colors.SIDEBAR);
        heading.setFont(new Font("Monospace", Font.BOLD, 25));
        heading.setBounds(x, 40, 500, 40);
        return heading;
    }

    public JLabel generateDateSubheading() {
        JLabel sub = new JLabel("Enter a date in YYYY-MM-DD format or \"today\"");
        sub.setForeground(Colors.SIDEBAR);
        sub.setFont(new Font("Monospace", Font.BOLD, 12));
        sub.setBounds(120, 65, 300, 40);
        return sub;
    }

    public JLabel generateDateLabel() {
        JLabel dateLabel = new JLabel("Date: ");
        dateLabel.setForeground(Colors.SIDEBAR);
        dateLabel.setFont(new Font("Monospace", Font.BOLD, 15));
        dateLabel.setBounds(55, 120, 100, 28);
        return dateLabel;
    }

    public void generateDateErrorMsg(JLabel error) {
        error.setForeground(Colors.SIDEBAR);
        error.setFont(new Font("Monospace", Font.BOLD, 12));
        error.setBounds(150, 150, 500, 40);
        error.setVisible(false);
    }

    // EFFECTS: produces true if given command follows the YYYY-MM-DD date format
    public boolean dateFormat(String command) {
        String pattern = "\\b\\d{4}-((0[1-9])|(1[0-2]))-((0[1-9])|([1,2]\\d)|(3[0,1]))\\b";
        boolean matches = Pattern.matches(pattern, command);
        return matches;
    }
}