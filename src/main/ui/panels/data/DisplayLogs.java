package ui.panels.data;

import model.food.AllMeals;
import model.food.Meals;
import model.workout.AllWorkouts;
import model.workout.Workout;
import ui.ColourPicker;
import ui.panels.Panels;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// display logs on scroll panel
public abstract class DisplayLogs implements ActionListener, Panels {
    protected AllWorkouts allWorkouts;
    protected Workout workout;
    protected AllMeals allMeals;
    protected Meals meals;
    protected JPanel main;
    protected JButton remove;
    protected JButton expand;
    private int iteration;

    // EFFECTS: constructs a new DisplayLogs with given items and instantiates panel items
    public DisplayLogs(AllWorkouts allWorkouts, Workout workout, AllMeals allMeals, Meals meals, int iteration) {
        this.allWorkouts = allWorkouts;
        this.workout = workout;
        this.allMeals = allMeals;
        this.meals = meals;
        this.iteration = iteration;
        remove = new JButton();
        expand = new JButton();
        setUpMain();
    }

    // MODIFIES: this
    // EFFECTS: adds items to main panels
    private void setUpMain() {
        main = new JPanel(null);
        main.setBackground(ColourPicker.SIDEBAR);
        main.setBounds(15, calculateHeight(), 420, 90);
        main.setBorder(BorderFactory.createLineBorder(ColourPicker.MAIN_COLOUR, 3));
    }

    // MODIFIES: this
    // EFFECTS: adds components to main
    public void addComponents() {
        main.add(generateButton(remove,"trash.png", 30, 370));
        main.add(generateButton(expand, "expand.png", 25, 330));
    }

    // MODIFIES: DATE
    // EFFECTS: generate date label
    protected JLabel generateDate(String day, int height) {
        JLabel date = new JLabel(day);
        date.setBounds(20, height, 135, 30);
        date.setFont(new Font("Didot", Font.BOLD, 20));
        date.setForeground(ColourPicker.MAIN_COLOUR);
        return date;
    }

//    ICON CREDIT:
//    trash can:
//    <a target="_blank" href="https://icons8.com/icon/1941/trash">Trash</a> icon by <a target="_blank" href=
//    "https://icons8.com">Icons8</a>
//    expand:
//    <a target="_blank" href="https://icons8.com/icon/78833/expand">Expand</a> icon by <a target="_blank" href=
//    "https://icons8.com">Icons8</a>

    // MODIFIES: button
    // EFFECTS: generate generic button
    private JButton generateButton(JButton button, String file, int width, int x) {
        ImageIcon unscaled = new ImageIcon(ScrollInterface.class.getResource(file));
        Image image = unscaled.getImage().getScaledInstance(width, width, unscaled.getImage().SCALE_SMOOTH);
        ImageIcon removeIcon = new ImageIcon(image);
        button.setBounds(x, 25, 40, 40);
        button.addActionListener(this);
        button.setIcon(removeIcon);
        button.setFocusable(false);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        return button;
    }

    // EFFECTS: calculate height in which the given panel needs to be placed
    private int calculateHeight() {
        return 15 + (105 * iteration);
    }
}
