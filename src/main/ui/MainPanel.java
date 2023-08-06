package ui;

import model.AllData;
import model.food.AllMeals;
import model.workout.AllWorkouts;
import ui.panels.SearchFoodPanel;
import ui.panels.logs.MealPanel;
import ui.panels.LoadPanel;
import ui.panels.logs.WorkoutPanel;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;


public class MainPanel extends Panel {
    public static final Dimension DIMENSION = new Dimension(FitnessInterface.WINDOW_WIDTH, FitnessInterface.HEIGHT);
    public static final int WIDTH = FitnessInterface.WINDOW_WIDTH;
    public static final int HEIGHT = FitnessInterface.HEIGHT;

    private CardLayout card;
    private static MainPanel mainPanel;
    private JPanel main;

    private AllMeals allMeals;
    private AllWorkouts allWorkouts;
    private AllData allData;

    public MainPanel() {
        main = new JPanel();
        card = new CardLayout();
        main.setLayout(card);
        main.setPreferredSize(DIMENSION);

        LoadPanel loadPanel = new LoadPanel();
        allData = loadPanel.getData();
        allMeals = allData.getAllMeals();
        allWorkouts = allData.getAllWorkouts();

        MealPanel mealPanel = new MealPanel(allMeals);
        WorkoutPanel workoutPanel = new WorkoutPanel(allWorkouts);
        SearchFoodPanel searchFoodPanel = new SearchFoodPanel(allMeals);


        main.add(loadPanel.getPanel(), "load");
        main.add(searchFoodPanel.getPanel(), "search food");
        main.add(workoutPanel.getPanel(), "workout");
        main.add(mealPanel.getPanel(), "food");
    }

    public static MainPanel getInstance() {
        if (mainPanel == null) {
            mainPanel = new MainPanel();
        }
        return mainPanel;
    }

    public CardLayout getMainCard() {
        return card;
    }

    public JPanel getPanel() {
        return main;
    }

    // EFFECTS: produces true if given command follows the YYYY-MM-DD date format
    public boolean dateFormat(String command) {
        String pattern = "\\b\\d{4}-((0[1-9])|(1[0-2]))-((0[1-9])|([1,2]\\d)|(3[0,1]))\\b";
        boolean matches = Pattern.matches(pattern, command);
        return matches;
    }

}