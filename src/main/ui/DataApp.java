package ui;

// Data application for users to view previous entries

import model.*;

public class DataApp extends Application {

    public DataApp(AllWorkouts allWorkouts, AllMeals allMeals) {
        super(allWorkouts, allMeals);
    }

    // EFFECTS: message to guide user input for data archive
    @Override
    protected void menu() {
        System.out.println("\nWelcome to your archive.");
        System.out.println("Would you like to view your previous workouts, food intake, or go back?");
        System.out.println("\tw    -> workouts");
        System.out.println("\tf    -> food intake");
        System.out.println("\tback -> back");
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
