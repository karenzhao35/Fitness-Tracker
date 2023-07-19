package ui;

import model.AllMeals;
import model.AllWorkouts;
import model.Workout;
import model.Exercise;

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

    // EFFECTS: displays menu of options to user
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

    // MODIFIES: this
    // EFFECTS: processes user input for a new workout
    private void startWorkout() {
        boolean cont = true;
        String command = null;

        while (cont) {
            initWorkout();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                cont = false;
            } else {
                processWorkoutCommand(command);
            }
        }
    }

    // EFFECTS: prints the options for starting a new workout
    private void initWorkout() {
        System.out.println("Enter the date you would like to record the workout for,");
        System.out.println("or 'today' if it's for today's workout,");
        System.out.println("or 'back' to return back to the menu");
        System.out.println("NOTE: Please format the date as YYYY-MM-DD");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for a new workout
    private void processWorkoutCommand(String command) {
        if (command.equals("today")) {
            todaysWorkout();
        } else {
            anyWorkout(command);
        }
    }

    // TODO: check if there is any way around this duplication
    // MODIFIES: this
    // EFFECTS: begins a new workout for today
    private void todaysWorkout() {
        Workout workout = new Workout();
        allWorkouts.addWorkout(workout);
        workout.addExercise(newExercise());
        addNewExercise();
    }

    // MODIFIES: this
    // EFFECTS: begins a new workout for given date
    private void anyWorkout(String command) {
        Workout workout = new Workout(command);
        allWorkouts.addWorkout(workout);
        Exercise exercise = newExercise();
        workout.addExercise(exercise);
        if (addNewExercise()) {
            System.out.println("Great!");
            System.out.print("Enter reps: ");
            int reps = input.nextInt();
            System.out.print("Enter weight: ");
            int weight = input.nextInt();
            exercise.addSet(reps, weight);
        }
    }


    private Exercise newExercise() {
        System.out.print("Enter exercise name: ");
        String name = input.next();
        System.out.print("Enter reps: ");
        int reps = input.nextInt();
        System.out.print("Enter weight: ");
        int weight = input.nextInt();

        Exercise exercise = new Exercise(name, reps, weight);
        return exercise;
    }

    private boolean addNewExercise() {
        boolean cont = true;
        String command = null;

        while (cont) {
            System.out.println("Great! Enter 'continue' to add a new set, 'new' to go back to the workout menu");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("new")) {
                cont = false;
                return false;
            } else if (command.equals("continue")) {
                return true;
            } else {
                System.out.println("Selection not valid...");
            }
        }
        return false;
    }


    private void beginMeal() {
    }

    private void searchData() {
    }


}
