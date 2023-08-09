package ui.panels.data.meals;

import model.AllData;
import model.Date;
import model.exceptions.DoesNotExist;
import model.food.AllMeals;
import ui.ColourPicker;
import ui.panels.MainSearchSetUp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// panel to search and view all food entries
public class SearchFoodPanel extends MainSearchSetUp {
    private ScrollInterfaceMeal scrollInterfacePanel;
    private JPanel display;
    private JPanel single;
    private JButton back;

    // EFFECTS: constructs a SearchFoodPanel with given allMeals
    public SearchFoodPanel(AllData allData, JFrame frame) {
        super(allData, frame);
        single = new JPanel(new BorderLayout());
        scrollInterfacePanel = new ScrollInterfaceMeal(allData, frame);
        display = scrollInterfacePanel.getPanel();
        back = new JButton("Back");

        generateButton(back, 240, 300, 70);
        back.setVisible(false);
        single.setVisible(false);
        addComponents();
    }

    // MODIFIES: this
    // EFFECTS: adds panel components to main
    public void addComponents() {
        super.addComponents();
        mainPanel.add(back);
        mainPanel.add(single);
        mainPanel.add(display);
        mainPanel.add(generateHeading("SEARCH PREVIOUS MEALS", 105));
    }


    // MODIFIES: this
    // EFFECTS: parses user input for filtering meals
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitDateButton) {
            filterWorkout();
        }
        if (e.getSource() == back) {
            error.setVisible(false);
            back.setVisible(false);
            display.setVisible(true);
            single.setVisible(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: filter workout based on date
    private void filterWorkout() {
        Date today = new Date();
        String date = dateTextField.getText();
        if (date.equals("today")) {
            try {
                DisplayMeal meal = new DisplayMeal(allData, frame, allMeals.retreiveMeals(today.getDate()), 0);
                displaySingleMeal();
                single.add(meal.getPanel());
            } catch (DoesNotExist ex) {
                error.setVisible(true);
            }
        } else if (dateFormat(date)) {
            try {
                DisplayMeal meal = new DisplayMeal(allData, frame, allMeals.retreiveMeals(date), 0);
                displaySingleMeal();
                single.add(meal.getPanel());
            } catch (DoesNotExist ex) {
                error.setVisible(true);
            }
        } else {
            error.setVisible(true);
        }
    }


    // MODIFIES: this
    // EFFECTS: displays single meal of given date if valid
    private void displaySingleMeal() {
        single.setVisible(true);
        display.setVisible(false);
        back.setVisible(true);
        single.setBounds(65, 200, 420, 100);
        single.setBackground(ColourPicker.SIDEBAR);
    }

    // EFFECTS: returns main panel
    @Override
    public JPanel getPanel() {
        return mainPanel;
    }
}