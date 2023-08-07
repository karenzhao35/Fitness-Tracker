package ui.panels;

import model.food.AllMeals;
import model.workout.AllWorkouts;
import ui.ColourPicker;
import ui.CardPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

// instantiates the search bar
public abstract class MainSearchSetUp implements ActionListener, Panels {
    protected AllMeals allMeals;
    protected AllWorkouts allWorkouts;
    protected JTextField dateTextField;
    protected JPanel mainPanel;
    protected JButton submitDateButton;
    protected JLabel error;

    // EFFECTS: constructs a MainSearchSetUp with given allWorkouts and allMeals and creates needed panels and buttons
    public MainSearchSetUp(AllWorkouts allWorkouts, AllMeals allMeals) {
        mainPanel = new JPanel();
        this.allMeals = allMeals;
        this.allWorkouts = allWorkouts;
        dateTextField = new JTextField();
        submitDateButton = new JButton("Submit");
        error = new JLabel("Invalid entry: Does not follow format.");

        setUp();
    }

    // MODIFIES: this
    // EFFECTS: sets up the elements for the search bar
    private void setUp() {
        generateDateTextField(dateTextField);
        generateButton(submitDateButton, 435, 120, 70);
        generateDateErrorMsg(error);

        mainPanel.setLayout(null);
        mainPanel.setBackground(ColourPicker.MAIN_COLOUR);
        mainPanel.setPreferredSize(CardPanel.DIMENSION);
    }

    // MODIFIES: this
    // EFFECTS: adds all items to the main panel
    public void addComponents() {
        mainPanel.add(submitDateButton);
        mainPanel.add(dateTextField);
        mainPanel.add(generateDateLabel());
        mainPanel.add(generateDateSubheading());
        mainPanel.add(error);
    }

    // MODIFIES: this
    // EFFECTS: generates date text field
    public void generateDateTextField(JTextField date) {
        date.setBounds(105, 120, 330, 30);
        date.setFont(new Font("Monospace", Font.PLAIN, 15));
        date.setForeground(ColourPicker.MAIN_COLOUR);
        date.setBackground(ColourPicker.SIDEBAR);
        date.setCaretColor(ColourPicker.MAIN_COLOUR);
    }

    // MODIFIES: this
    // EFFECTS: generates main heading
    public JLabel generateHeading(String label, int x) {
        JLabel heading = new JLabel(label);
        heading.setForeground(ColourPicker.SIDEBAR);
        heading.setFont(new Font("Monospace", Font.BOLD, 25));
        heading.setBounds(x, 40, 500, 40);
        return heading;
    }

    // MODIFIES: this
    // EFFECTS: generates sub heading describing input format
    public JLabel generateDateSubheading() {
        JLabel sub = new JLabel("Enter a date in YYYY-MM-DD format or \"today\"");
        sub.setForeground(ColourPicker.SIDEBAR);
        sub.setFont(new Font("Monospace", Font.BOLD, 12));
        sub.setBounds(120, 65, 300, 40);
        return sub;
    }

    // MODIFIES: dateLabel
    // EFFECTS: returns a label for the date text field
    public JLabel generateDateLabel() {
        JLabel dateLabel = new JLabel("Date: ");
        dateLabel.setForeground(ColourPicker.SIDEBAR);
        dateLabel.setFont(new Font("Monospace", Font.BOLD, 15));
        dateLabel.setBounds(55, 120, 100, 28);
        return dateLabel;
    }

    // MODIFIES: this
    // EFFECTS: generates error message
    public void generateDateErrorMsg(JLabel error) {
        error.setForeground(ColourPicker.SIDEBAR);
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

    // MODIFIES: this
    // EFFECTS: generates generic button
    public void generateButton(JButton button, int x, int y, int width) {
        button.setBounds(x, y, width, 30);
        button.setFont(new Font("Monospace", Font.PLAIN, 12));
        button.addActionListener(this);
        button.setFocusable(false);
        button.setForeground(ColourPicker.SIDEBAR);
    }
}