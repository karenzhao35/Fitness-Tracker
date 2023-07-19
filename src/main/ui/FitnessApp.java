package ui;

import model.AllMeals;
import model.AllWorkouts;
import model.Workout;

import java.util.Scanner;

public class FitnessApp {
    private AllMeals allMeals;
    private AllWorkouts allWorkouts;
    private Scanner input;

    // the following code is inspired from the TellerApp class in the Teller Application

    public FitnessApp() {
        runFitness();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runFitness() {
        boolean cont = true;
        String command = null;

        init();

        while (cont) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                cont = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("w")) {
            startWorkout();
        } else if (command.equals("f")) {
            beginMeal();
        } else if (command.equals("d")) {
            searchData();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void displayMenu() {
        System.out.println("\nYo! I'm your virtual gym bro!");
        System.out.println("\nHere, you can log your sets and count your calories!");
        System.out.println("\nTo begin, select from:");
        System.out.println("\tw -> to start a workout");
        System.out.println("\tf -> to begin a meal");
        System.out.println("\td -> to view previous data");
        System.out.println("\tq -> to quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes workouts and meals
    private void init() {
        allMeals = new AllMeals();
        allWorkouts = new AllWorkouts();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    private void startWorkout() {
        System.out.println("Amazing! What date would you like to record the workout for?");
        System.out.println("Please format the date as YYYY-MM-DD");
        String date = input.next();
        Workout workout = new Workout(date);
        allWorkouts.addWorkout(workout);
        newExercise();
    }

    private void beginMeal() {
    }

    private void searchData() {
    }
    
    private void newExercise() {
        System.out.print("Enter exercise name");
        String exercise = input.next();
        System.out.print("Enter reps:");
        int reps = input.nextInt();
        System.out.print("Enter weight:");
        int weight = input.nextInt();
        
    }


}
