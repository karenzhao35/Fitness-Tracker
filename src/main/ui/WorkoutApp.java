package ui;

import model.*;
import model.exceptions.DoesNotExist;
import model.workout.AllWorkouts;
import model.workout.Exercise;
import model.workout.Workout;

// Workout Application for users to enter new workout entries
public class WorkoutApp extends Application {

    public WorkoutApp(AllWorkouts allWorkouts) {
        super(allWorkouts, null);
    }


    // EFFECTS: prints the options for starting a new workout
    @Override
    protected void menu() {
        System.out.println("\nEnter the date you would like to record the workout for,");
        System.out.println("or 'today' if it's for today's workout,");
        if (allWorkouts.today()) {
            System.out.println("or 'view' to view today's workout,");
        }
        System.out.println("or 'back' to return back to the menu");
        System.out.println("NOTE: Please format the date as YYYY-MM-DD");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for a new workout
    @Override
    protected void processCommand(String command) {
        if (command.equals("today")) {
            workoutToday();
        } else if (command.equals("view")) {
            printTodaysWorkout();
        } else if (dateFormat(command)) {
            workoutAnyDay(command);
        } else {
            System.out.println("You did not follow the date format :(");
            System.out.println("Please try again!");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new workout with today's date
    private void workoutToday() {
        Workout workout = new Workout();
        startWorkout(workout);
    }

    // MODIFIES: this
    // EFFECTS: creates a new workout with a given date
    private void workoutAnyDay(String command) {
        Workout workout = new Workout(command);
        startWorkout(workout);
    }

    // EFFECTS: prints all the exercises and sets from today's workout
    private void printTodaysWorkout() {
        Date date = new Date();
        Workout todaysWorkout = null;
        try {
            todaysWorkout = allWorkouts.retrieveWorkout(date.getDate());
        } catch (DoesNotExist e) {
            System.out.println("Record doesn't exist!");
        }
        printExercises(todaysWorkout);
        System.out.println("Taking you back to the workout menu now...");
    }


    // MODIFIES: this
    // EFFECTS: adds the user's exercise to the workout
    private void startWorkout(Workout workout) {
        Exercise exercise = newExercise();
        workout.addExercise(exercise);
        continueWorkout(workout, exercise);
        allWorkouts.addWorkout(workout);
    }

    // EFFECTS: processes the user's input for exercise and produces the exercise
    private Exercise newExercise() {
        System.out.print("Enter exercise name: ");
        String name = input.next();
        System.out.print("Enter reps: ");
        int reps = ensureInteger();
        System.out.print("Enter weight (in lbs): ");
        int weight = ensureInteger();

        Exercise exercise = new Exercise(name, reps, weight);
        return exercise;
    }

    // MODIFIES: this
    // EFFECTS: processes user input to see if user would like to continue exercise or workout
    private void continueWorkout(Workout workout, Exercise exercise) {
        boolean cont = true;
        String command = null;

        while (cont) {
            promptContinue();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                cont = false;
            } else if (command.equals("c")) {
                addSet(exercise);
            } else if (command.equals("n")) {
                Exercise newExercise = newExercise();
                workout.addExercise(newExercise);
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    // EFFECTS: prints options to continue or start a new workout
    private void promptContinue() {
        System.out.println("Great!");
        System.out.println("\nSelect from the following options");
        System.out.println("c -> continue this exercise");
        System.out.println("n -> begin a new workout");
        System.out.println("q -> to return to the workout menu");
    }

    // MODIFIES: this
    // EFFECTS: adds a new set based on user input to the given exercise
    private void addSet(Exercise exercise) {
        System.out.println("You got this!");
        System.out.print("Enter reps: ");
        int reps = ensureInteger();
        System.out.print("Enter weight (in lbs): ");
        int weight = ensureInteger();
        exercise.addSet(reps, weight);
    }
}
