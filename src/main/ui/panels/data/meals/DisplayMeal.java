package ui.panels.data.meals;


import model.food.AllMeals;
import model.food.Meals;
import ui.Colors;
import ui.panels.data.DisplayLogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DisplayMeal extends DisplayLogs {

    public DisplayMeal(AllMeals allMeals, Meals meals, int iteration) {
        super(null, null, allMeals, meals, iteration);
        addComponents();
    }

    public void addComponents() {
        super.addComponents();
        main.add(generateDate(meals.getDate(), 15));
        main.add(generateTotalCalories());
        main.add(generateTotalProtein());
    }


    public JLabel generateTotalProtein() {
        JLabel protein = new JLabel("total protein:  " + meals.sumProtein());
        protein.setBounds(20, 60, 135, 15);
        protein.setFont(new Font("Didot", Font.BOLD, 12));
        protein.setForeground(Colors.MAIN_COLOUR);
        return protein;
    }

    private JLabel generateTotalCalories() {
        JLabel calories = new JLabel("total calories: " + meals.sumCalories());
        calories.setBounds(20, 45, 135, 15);
        calories.setFont(new Font("Didot", Font.BOLD, 12));
        calories.setForeground(Colors.MAIN_COLOUR);
        return calories;
    }




    public JPanel getPanel() {
        return main;
    }


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

    public void removeMeal(int answer) {
        if (answer == 0) {
            allMeals.removeMeals(meals);
        }
    }


}