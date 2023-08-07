package ui.panels.data.meals;

import model.Date;
import model.exceptions.DoesNotExist;
import model.food.AllMeals;
import ui.Colors;
import ui.panels.MainSearchSetUp;
import ui.panels.data.meals.DisplayMeal;
import ui.panels.data.meals.ScrollInterfaceMeal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SearchFoodPanel extends MainSearchSetUp {
    private ScrollInterfaceMeal scrollInterfacePanel;
    private JPanel display;
    private JPanel single;
    private JButton back;


    public SearchFoodPanel(AllMeals allMeals) {
        super(null, allMeals);
        single = new JPanel(new BorderLayout());
        single.setVisible(false);
        scrollInterfacePanel = new ScrollInterfaceMeal(allMeals);
        display = scrollInterfacePanel.getPanel();
        back = new JButton("Back");
        generateButton(back, 240, 300, 70);
        back.setVisible(false);
        addComponents();
    }

    public void addComponents() {
        super.addComponents();
        mainPanel.add(back);
        mainPanel.add(single);
        mainPanel.add(display);
        mainPanel.add(generateHeading("SEARCH PREVIOUS MEALS", 105));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Date today = new Date();
        if (e.getSource() == submitDateButton) {
            String date = dateTextField.getText();
            if (date.equals("today")) {
                try {
                    DisplayMeal meal = new DisplayMeal(allMeals, allMeals.retreiveMeals(today.getDate()), 0);
                    displaySingleMeal();
                    single.add(meal.getPanel());
                } catch (DoesNotExist ex) {
                    error.setVisible(true);
                }
            } else if (dateFormat(date)) {
                try {
                    DisplayMeal meal = new DisplayMeal(allMeals, allMeals.retreiveMeals(date), 0);
                    displaySingleMeal();
                    single.add(meal.getPanel());
                } catch (DoesNotExist ex) {
                    error.setVisible(true);
                }
            } else {
                error.setVisible(true);
            }
        }
        if (e.getSource() == back) {
            error.setVisible(false);
            back.setVisible(false);
            display.setVisible(true);
            single.setVisible(false);
        }
    }

    private void displaySingleMeal() {
        single.setVisible(true);
        single.setBounds(65, 200, 420, 100);
        single.setBackground(Colors.SIDEBAR);
        display.setVisible(false);
        back.setVisible(true);
    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }
}