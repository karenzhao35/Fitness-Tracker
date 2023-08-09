package ui.panels.logs;

import model.AllData;
import model.food.Meals;
import ui.panels.MainSearchSetUp;
import javax.swing.*;
import java.awt.event.ActionEvent;

// panel for users to log their meals
public class MealPanel extends MainSearchSetUp {
    private FoodPanel foodPanel;
    private JPanel panel;

    // EFFECTS: constructs a MealPanel with given allMeals and instantiates panel items
    public MealPanel(AllData allData, JFrame frame) {
        super(allData, frame);
        foodPanel = new FoodPanel();
        panel = foodPanel.getPanel();
        addComponents();
    }

    // MODIFIES: this
    // EFFECTS: adds items to main panel
    public void addComponents() {
        super.addComponents();
        mainPanel.add(panel);
        mainPanel.add(generateHeading("LET'S TRACK YOUR MEAL", 110));
    }

    // MODIFIES: this
    // EFFECTS: starts a new meal based on user input
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitDateButton) {
            String date = dateTextField.getText();
            if (date.equals("today")) {
                Meals meal = new Meals();
                error.setVisible(false);
                foodPanel.start(allData, meal, frame);
            } else if (dateFormat(date)) {
                Meals meal = new Meals(date);
                error.setVisible(false);
                foodPanel.start(allData, meal, frame);
            } else {
                error.setVisible(true);
            }
        }
    }

    // EFFECTS: returns main panel
    @Override
    public JPanel getPanel() {
        return mainPanel;
    }

}