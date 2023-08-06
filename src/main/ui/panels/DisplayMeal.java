package ui.panels;


import model.food.AllMeals;
import model.food.Meals;
import ui.Colors;
import ui.panels.Panels;

import javax.swing.*;
import java.awt.*;

public class DisplayMeal implements Panels {
    private AllMeals allMeals;
    private Meals meals;
    private JPanel main;
    private int i;

    public DisplayMeal(AllMeals allMeals, Meals meals, int i) {
        this.allMeals = allMeals;
        this.meals = meals;
        this.i = i;

        setUpMain();

        main.add(generateDate());
        main.add(generateTotalCalories());
        main.add(generateTotalProtein());
        main.add(generateButton("trash2.png", 30, 370));
        main.add(generateButton("plus.png", 25, 330));

    }

    private JButton generateButton(String file, int width, int x) {
        ImageIcon unscaled = new ImageIcon(ScrollSetUp.class.getResource(file));
        Image image = unscaled.getImage().getScaledInstance(width, width, unscaled.getImage().SCALE_SMOOTH);
        ImageIcon removeIcon = new ImageIcon(image);

        JButton button = new JButton();
        button.setBounds(x, 25, 40, 40);
        button.setIcon(removeIcon);
        button.setFocusable(false);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        return button;
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

    private JLabel generateDate() {
        JLabel date = new JLabel(meals.getDate());
        date.setBounds(20, 15, 135, 30);
        date.setFont(new Font("Didot", Font.BOLD, 20));
        date.setForeground(Colors.MAIN_COLOUR);
        return date;
    }

    private void setUpMain() {
        main = new JPanel(null);
        main.setBackground(Colors.SIDEBAR);
        main.setBounds(15, calculateHeight(), 420, 90);
        main.setBorder(BorderFactory.createLineBorder(Colors.MAIN_COLOUR, 5));
    }

    private int calculateHeight() {
        return 15 + (105 * i);
    }

    public JPanel getPanel() {
        return main;
    }


}