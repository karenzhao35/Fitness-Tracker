package ui.console;

import model.food.AllMeals;
import model.workout.AllWorkouts;

// Data application for users to view previous entries
public class DataApp extends Application {

    // EFFECTS: constructs a DataApp from given AllWorkout and AllMeals data
    public DataApp(AllWorkouts allWorkouts, AllMeals allMeals) {
        super(allWorkouts, allMeals);
    }

    // EFFECTS: message to guide user input for data archive
    @Override
    protected void menu() {
        System.out.println("\nWelcome to your archive!");
        System.out.println("Would you like to view your previous workouts, food intake, or go back?");
        System.out.println("\tw    -> workouts");
        System.out.println("\tf    -> food intake");
        System.out.println("\tback -> return to main menu");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for viewing data
    @Override
    protected void processCommand(String command) {
        if (command.equals("w")) {
            System.out.println("\nLet's look at your past together...");
            new WorkoutData(allWorkouts);
        } else if (command.equals("f")) {
            new MealsData(allMeals);
        } else {
            System.out.println("Selection not valid...");
        }
    }


}
