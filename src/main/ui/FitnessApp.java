package ui;

// Fitness application

import model.*;
import java.util.Scanner;

public class FitnessApp {
    private AllMeals allMeals;
    private AllWorkouts allWorkouts;
    private Scanner input;

    // the following code is inspired from the TellerApp class in the Teller Application
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java

    // EFFECTS: runs the fitness application
    public FitnessApp() {
        runFitness();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runFitness() {
        boolean cont = true;
        String command = null;

        init();
        String name = greetUser();

        while (cont) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("See you tomorrow " + name + "!");
                cont = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes workouts and meals
    private void init() {
        allMeals = new AllMeals();
        allWorkouts = new AllWorkouts();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: greets the user
    public String greetUser() {
        System.out.println("Yo! I'm your virtual gym bro!");
        System.out.println("Here, you can log your sets and count your calories!");
        System.out.print("\nTo start, what's your name? ");
        String name = input.next();
        System.out.println("Welcome " + name + "!");
        System.out.println("Now let's get working!");
        return name;
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Select from the following options:");
        System.out.println("\tw -> to start a workout");
        System.out.println("\tf -> to begin a recording your food intake");
        System.out.println("\td -> to view previous data");
        System.out.println("\tq -> to quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("w")) {
            new WorkoutApp(allWorkouts);
        } else if (command.equals("f")) {
            new MealApp(allMeals);
        } else if (command.equals("d")) {
            new DataApp(allWorkouts, allMeals);
        } else {
            System.out.println("Selection not valid...");
        }
    }

}
