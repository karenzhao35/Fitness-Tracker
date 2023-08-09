package ui;

import model.AllData;
import model.food.AllMeals;
import model.workout.AllWorkouts;
import ui.panels.Panels;
import ui.panels.data.meals.SearchFoodPanel;
import ui.panels.data.workouts.SearchWorkoutPanel;
import ui.panels.logs.MealPanel;
import ui.panels.logs.WorkoutPanel;
import javax.swing.*;
import java.awt.*;

// Card Panel that stores the log and data panels
public class CardPanel implements Panels {
    public static final Dimension DIMENSION = new Dimension(FitnessInterface.WINDOW_WIDTH, FitnessInterface.HEIGHT);
    public static final int WIDTH = FitnessInterface.WINDOW_WIDTH;
    public static final int HEIGHT = FitnessInterface.HEIGHT;

    private CardLayout card;
    private JPanel main;

    // EFFECTS: constructs a CardPanel
    public CardPanel(AllData allData, JFrame frame) {
        main = new JPanel();
        card = new CardLayout();
        main.setLayout(card);
        main.setPreferredSize(DIMENSION);
        addPanels(allData, frame);
    }

    // MODIFIES: this
    // EFFECTS: add panels to card panel
    private void addPanels(AllData allData, JFrame frame) {
        MealPanel mealPanel = new MealPanel(allData, frame);
        WorkoutPanel workoutPanel = new WorkoutPanel(allData, frame);
        SearchFoodPanel searchFoodPanel = new SearchFoodPanel(allData, frame);
        SearchWorkoutPanel searchWorkoutPanel = new SearchWorkoutPanel(allData, frame);

        main.add(workoutPanel.getPanel(), "workout");
        main.add(searchWorkoutPanel.getPanel(), "search workout");
        main.add(searchFoodPanel.getPanel(), "search food");
        main.add(mealPanel.getPanel(), "food");
    }

    // EFFECTS: returns main card
    public CardLayout getMainCard() {
        return card;
    }

    // EFFECTS: returns main panel
    @Override
    public JPanel getPanel() {
        return main;
    }
}