package ui;

// A generic application that includes the shared behaviour

import model.AllMeals;
import model.AllWorkouts;
import model.Exercise;
import java.util.regex.Pattern;
import java.util.List;
import java.util.Scanner;

public abstract class Application {
    protected AllMeals allMeals;
    protected AllWorkouts allWorkouts;
    protected Scanner input;

    // EFFECTS: constructs a new Application with the given allWorkouts, allMeals, and initialized input
    public Application(AllWorkouts allWorkouts, AllMeals allMeals) {
        this.input = new Scanner(System.in);
        input.useDelimiter("\n");
        this.allMeals = allMeals;
        this.allWorkouts = allWorkouts;
        newWindow();
    }

    // REQUIRES: this
    // EFFECTS: processes a new set of user input
    protected void newWindow() {
        boolean cont = true;
        String command = null;

        while (cont) {
            menu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                cont = false;
                System.out.println("Taking you back to the previous menu...");
            } else {
                processCommand(command);
            }
        }
    }

    // EFFECTS: guides user input by displaying the menu options
    protected abstract void menu();

    // MODIFIES: this
    // EFFECTS: processes user command from given input
    protected abstract void processCommand(String command);


    // EFFECTS: prints a list of exercises and the sets for each exercise
    protected void printExercises(List<Exercise> exercises) {
        for (Exercise e : exercises) {
            System.out.println("For " + e.getName() + " you did:");
            for (int i = 0; i < e.getReps().size(); i++) {
                System.out.println("\t" + e.getReps().get(i) + " reps at " + e.getWeight().get(i) + " lbs");
            }
        }
    }

    // EFFECTS: guides uer to enter date to search data
    protected void printDataDialogue() {
        System.out.println("\nEnter in the date you would like to view,");
        System.out.println("enter 'today' to view today's data, or enter 'back' to go back: ");
        System.out.println("NOTE: Please format the date as YYYY-MM-DD");
    }

    // EFFECTS: produces true if given command follows the YYYY-MM-DD date format
    protected boolean dateFormat(String command) {
        String pattern = "\\b\\d{4}-((0[1-9])|(1[0-2]))-((0[1-9])|([1,2]\\d)|(3[0,1]))\\b";
        boolean matches = Pattern.matches(pattern, command);
        return matches;
    }

}
