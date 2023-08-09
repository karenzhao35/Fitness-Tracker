package ui.panels.logs;

import model.AllData;
import model.food.AllMeals;
import model.food.Food;
import model.food.MealType;
import model.food.Meals;
import ui.ColourPicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// panel to create a new food entry
public class FoodPanel extends InnerPanel {
    private AllMeals allMeals;
    private Meals meals;
    private JTextField calTextField;
    private JTextField proteinTextField;
    private JComboBox typeComboBox;

    // EFFECTS: constructs a new food panel and panel items
    public FoodPanel() {
        super();
        calTextField = new JTextField();
        proteinTextField = new JTextField();
        generatePanelItems();
        addToFoodPanel();
        main.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: generates food panel items
    @Override
    public void generatePanelItems() {
        super.generatePanelItems();
        generateTextField(calTextField, 160);
        generateTextField(proteinTextField, 215);
        generateTypeComboBox();
    }

    // MODIFIES: this
    // EFFECTS: adds items to main panel
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

    // MODIFIES: this
    // EFFECTS: generate type combo box
    public void generateTypeComboBox() {
        String[] types = {"breakfast", "lunch", "dinner", "snack"};
        typeComboBox = new JComboBox(types);
        typeComboBox.setFont(new Font("Monospace", Font.PLAIN, 13));
        typeComboBox.setBackground(ColourPicker.MAIN_COLOUR);
        typeComboBox.setForeground(ColourPicker.TEXT_COLOR);
        typeComboBox.setBounds(110, 100, 120, 50);
    }

    // MODIFIES: this
    // EFFECTS: instantiate meal fields
    public void start(AllData allData, Meals meals, JFrame frame) {
        super.start(allData, frame);
        this.allMeals = allData.getAllMeals();
        this.meals = meals;
        main.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: submits food if its valid
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
                refresh();
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: resets text fields
    public void clearTextFields() {
        nameTextField.setText("");
        typeComboBox.setSelectedIndex(0);
        calTextField.setText("");
        proteinTextField.setText("");
    }

    // MODIFIES: this
    // EFFECTS: create food based on user input and add to a meal
    public void newFood() {
        String name = nameTextField.getText();
        int calories = Integer.parseInt(calTextField.getText());
        double protein = Double.parseDouble(proteinTextField.getText());
        MealType type = returnType();
        Food food = new Food(name, type, calories, protein);
        meals.addFood(food);
    }

    // EFFECTS: returns meal type based on user input
    private MealType returnType() {
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

    // EFFECTS: return true if input is valid
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