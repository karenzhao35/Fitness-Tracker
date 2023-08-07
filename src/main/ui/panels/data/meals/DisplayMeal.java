package ui.panels.data.meals;


import model.food.AllMeals;
import model.food.Meals;
import ui.ColourPicker;
import ui.panels.data.DisplayLogs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Displays a single meal
public class DisplayMeal extends DisplayLogs {

    // EFFECTS: constructs a DisplayMeal with given allMeals, meals, and iteration
    public DisplayMeal(AllMeals allMeals, Meals meals, int iteration) {
        super(null, null, allMeals, meals, iteration);
        addComponents();
    }

    // MODIFIES: THIS
    // EFFECTS: adds components to main panel
    @Override
    public void addComponents() {
        super.addComponents();
        main.add(generateDate(meals.getDate(), 15));
        main.add(generateTotalCalories());
        main.add(generateTotalProtein());
    }

    // EFFECTS: constructs and returns a total protein JLabel
    public JLabel generateTotalProtein() {
        JLabel protein = new JLabel("total protein:  " + meals.sumProtein());
        protein.setBounds(20, 60, 135, 15);
        protein.setFont(new Font("Didot", Font.BOLD, 12));
        protein.setForeground(ColourPicker.MAIN_COLOUR);
        return protein;
    }

    // EFFECTS: constructs and returns a total calories JLabel
    private JLabel generateTotalCalories() {
        JLabel calories = new JLabel("total calories: " + meals.sumCalories());
        calories.setBounds(20, 45, 135, 15);
        calories.setFont(new Font("Didot", Font.BOLD, 12));
        calories.setForeground(ColourPicker.MAIN_COLOUR);
        return calories;
    }

    // EFFECTS: returns the main panel
    @Override
    public JPanel getPanel() {
        return main;
    }


    // MODIFIES: this
    // EFFECTS: processes user input to either view or remove meal
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == remove) {
            int answer = JOptionPane.showConfirmDialog(null, "Confirm removal?");
            removeMeal(answer);
        }
        if (e.getSource() == expand) {
            new DisplayFoods(meals);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes meal if user chooses to
    public void removeMeal(int answer) {
        if (answer == 0) {
            allMeals.removeMeals(meals);
        }
    }


}