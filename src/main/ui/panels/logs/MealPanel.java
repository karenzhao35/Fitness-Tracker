package ui.panels.logs;

import model.food.AllMeals;
import model.food.Meals;
import ui.MainPanel;
import ui.panels.MainPanels;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class MealPanel extends MainPanels {
    private FoodPanel foodPanel;
    private JPanel panel;



    public MealPanel(AllMeals allMeals) {
        super(null, allMeals);
        foodPanel = new FoodPanel();
        panel = foodPanel.getPanel();
        addComponents();
    }



    public void addComponents() {
        super.addComponents();
        mainPanel.add(panel);
        mainPanel.add(generateHeading("LET'S TRACK YOUR MEAL", 110));
    }


    public JPanel getPanel() {
        return mainPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitDateButton) {
            String date = dateTextField.getText();
            if (date.equals("today")) {
                Meals meal = new Meals();
                error.setVisible(false);
                foodPanel.startMeal(allMeals, meal);
            } else if (dateFormat(date)) {
                Meals meal = new Meals(date);
                error.setVisible(false);
                foodPanel.startMeal(allMeals, meal);
            } else {
                error.setVisible(true);
            }
        }
    }




}