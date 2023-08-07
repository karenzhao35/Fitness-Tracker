package ui;

import model.food.AllMeals;
import model.workout.AllWorkouts;
import ui.panels.data.meals.SearchFoodPanel;
import ui.panels.data.workouts.SearchWorkoutPanel;
import ui.panels.logs.MealPanel;
import ui.panels.logs.WorkoutPanel;
import javax.swing.*;
import java.awt.*;


public class CardPanel extends Panel {
    public static final Dimension DIMENSION = new Dimension(FitnessInterface.WINDOW_WIDTH, FitnessInterface.HEIGHT);
    public static final int WIDTH = FitnessInterface.WINDOW_WIDTH;
    public static final int HEIGHT = FitnessInterface.HEIGHT;

    private CardLayout card;
    private JPanel main;

    private AllMeals allMeals;
    private AllWorkouts allWorkouts;

    public CardPanel(AllWorkouts allWorkouts, AllMeals allMeals) {
        this.allWorkouts = allWorkouts;
        this.allMeals = allMeals;

        main = new JPanel();
        card = new CardLayout();
        main.setLayout(card);
        main.setPreferredSize(DIMENSION);

        MealPanel mealPanel = new MealPanel(allMeals);
        WorkoutPanel workoutPanel = new WorkoutPanel(allWorkouts);
        SearchFoodPanel searchFoodPanel = new SearchFoodPanel(allMeals);
        SearchWorkoutPanel searchWorkoutPanel = new SearchWorkoutPanel(allWorkouts);

        main.add(workoutPanel.getPanel(), "workout");
        main.add(searchWorkoutPanel.getPanel(), "search workout");
        main.add(searchFoodPanel.getPanel(), "search food");
        main.add(mealPanel.getPanel(), "food");
    }

    public CardLayout getMainCard() {
        return card;
    }

    public JPanel getPanel() {
        return main;
    }
}