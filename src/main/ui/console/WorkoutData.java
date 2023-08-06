package ui.console;

import model.exceptions.DoesNotExist;
import model.workout.AllWorkouts;
import model.workout.Workout;
import ui.console.Application;

// Workout data for users to view previous workout entries
public class WorkoutData extends Application {

    // EFFECTS: constructs WorkoutData with given AllWorkouts data
    public WorkoutData(AllWorkouts allWorkouts) {
        super(allWorkouts, null);
    }

    // EFFECTS: guides user input for viewing workout data
    @Override
    protected void menu() {
        printDataDialogue();
    }

    // MODIFIES: this
    // EFFECTS: processes user input date to view workout data
    @Override
    protected void processCommand(String date) {
        if (date.equals("today")) {
            workoutDateToday();
        } else if (!dateFormat(date)) {
            System.out.println("You didn't follow the format :(");
            System.out.println("Please try again!");
        } else {
            Workout workout = null;
            try {
                workout = allWorkouts.retrieveWorkout(date);
                printExercises(workout);
                editWorkout(workout);
            } catch (DoesNotExist e) {
                System.out.println("Unfortunately, workout with given date doesn't exist :(");
                System.out.println("Please try again!");
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: accesses the workout today to view exercises and choose to remove workout
    private void workoutDateToday() {
        Workout workout = null;
        try {
            workout = allWorkouts.retrieveWorkout(new java.sql.Date(current).toString());
            printExercises(workout);
            editWorkout(workout);
        } catch (DoesNotExist e) {
            System.out.println("Oh no! There are no workouts today.");
        }
    }


    // MODIFIES: this
    // EFFECTS: processes the user command and removes workout if prompted
    private void editWorkout(Workout workout) {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            System.out.println("Enter 'remove' to remove this workout, ");
            System.out.println("enter 'back' to go back to the workout database menu.");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                keepGoing = false;
            } else if (command.equals("remove")) {
                allWorkouts.removeWorkout(workout);
                System.out.println("Your workout has been removed.");
                System.out.println("Taking you back to the workout database menu...");
                keepGoing = false;
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

}
