package ui;

// Workout Application for users to enter new workout entries

import model.*;

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
            System.out.println("or 'view' to view today's calorie and protein intake,");
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
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new workout with today's date
    private void workoutToday() {
        Workout workout = new Workout();
        allWorkouts.addWorkout(workout);
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
        Workout todaysWorkout = allWorkouts.retrieveWorkout(date.getDate());
        printExercises(todaysWorkout.getExercises());
        System.out.println("Taking you back to the workout menu now...");
    }


    // MODIFIES: this
    // EFFECTS: adds the user's exercise to the workout
    private void startWorkout(Workout workout) {
        allWorkouts.addWorkout(workout);
        Exercise exercise = newExercise();
        workout.addExercise(exercise);
        continueWorkout(workout, exercise);
    }

    // EFFECTS: processes the user's input for exercise and produces the exercise
    private Exercise newExercise() {
        System.out.print("Enter exercise name: ");
        String name = input.next();
        System.out.print("Enter reps: ");
        int reps = input.nextInt();
        System.out.print("Enter weight (in lbs): ");
        int weight = input.nextInt();

        Exercise exercise = new Exercise(name, reps, weight);
        return exercise;
    }

    // MODIFIES: this
    // EFFECTS: processes user input to see if user would like to continue exercise or workout
    private void continueWorkout(Workout workout, Exercise exercise) {
        boolean cont = true;
        String command = null;

        while (cont) {
            System.out.println("Great! Enter 'continue' to add a new set, 'new' to add another exercise,");
            System.out.println("or 'back' to return to the workout menu.");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                cont = false;
            } else if (command.equals("continue")) {
                addSet(exercise);
            } else if (command.equals("new")) {
                Exercise newExercise = newExercise();
                workout.addExercise(newExercise);
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new set based on user input to the given exercise
    private void addSet(Exercise exercise) {
        System.out.println("You got this!");
        System.out.print("Enter reps: ");
        int reps = input.nextInt();
        System.out.print("Enter weight (in lbs): ");
        int weight = input.nextInt();
        exercise.addSet(reps, weight);
    }
}
