package ui.panels.logs;

import model.food.AllMeals;
import model.food.Food;
import model.food.MealType;
import model.food.Meals;
import ui.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FoodPanel extends InnerPanel {
    private AllMeals allMeals;
    private Meals meals;

    private JTextField calTextField;
    private JTextField proteinTextField;
    private JComboBox typeComboBox;



    public FoodPanel() {
        super();
        calTextField = new JTextField();
        proteinTextField = new JTextField();
        generatePanelItems();
        addToFoodPanel();
        main.setVisible(false);
    }



    @Override
    public void generatePanelItems() {
        super.generatePanelItems();
        generateTextField(calTextField, 160);
        generateTextField(proteinTextField, 215);
        generateTypeComboBox();
    }

    @Override
    public void addToFoodPanel() {
        super.addToFoodPanel();
        main.add(typeComboBox);
        main.add(calTextField);
        main.add(proteinTextField);

        main.add(generateLabels("add food:", 170, 10));
        main.add(generateLabels("type:", 51, 103));
        main.add(generateLabels("calories: ", 50, 156));
        main.add(generateLabels("(kcal)", 350, 156));
        main.add(generateLabels("protein:", 50, 210));
        main.add(generateLabels("(g)", 350, 210));
    }



    public void generateTypeComboBox() {
        String[] types = {"breakfast", "lunch", "dinner", "snack"};
        typeComboBox = new JComboBox(types);
        typeComboBox.setFont(new Font("Monospace", Font.PLAIN, 13));
        typeComboBox.setBackground(Colors.MAIN_COLOUR);
        typeComboBox.setForeground(Colors.TEXT_COLOR);
        typeComboBox.setBounds(110, 100, 120, 50);
    }

    public void startMeal(AllMeals allMeals, Meals meals) {
        this.allMeals = allMeals;
        this.meals = meals;
        main.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            if (checkValid()) {
                error.setVisible(false);
                newFood();
                clearTextFields();
            } else {
                error.setVisible(true);
            }
        }
        if (e.getSource() == doneButton) {
            main.setVisible(false);
            if (!meals.getFoods().isEmpty()) {
                allMeals.addMeals(meals);
            }
        }

    }

    public void clearTextFields() {
        nameTextField.setText("");
        typeComboBox.setSelectedIndex(0);
        calTextField.setText("");
        proteinTextField.setText("");
    }

    public void newFood() {
        String name = nameTextField.getText();
        int calories = Integer.parseInt(calTextField.getText());
        double protein = Double.parseDouble(proteinTextField.getText());
        MealType type = getType();
        Food food = new Food(name, type, calories, protein);
        meals.addFood(food);
    }

    private MealType getType() {
        String type = (String) typeComboBox.getSelectedItem();
        if (type.equals("breakfast")) {
            return MealType.BREAKFAST;
        } else if (type.equals("lunch")) {
            return MealType.LUNCH;
        } else if (type.equals("dinner")) {
            return MealType.DINNER;
        } else {
            return MealType.SNACK;
        }
    }

    @Override
    public boolean checkValid() {
        return (!nameTextField.getText().equals("")
                && isInteger(calTextField.getText())
                && isDouble(proteinTextField.getText()));
    }

    // EFFECTS: produces true if given string is a double
    protected boolean isDouble(String command) {
        try {
            Double.parseDouble(command);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}