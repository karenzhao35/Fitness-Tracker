package model;

// A database for all workouts performed

import java.util.ArrayList;
import java.util.List;

public class AllWorkouts implements AllData {
    private List<Workout> workouts;

    // EFFECTS: constructs a new AllWorkouts with no workouts added
    public AllWorkouts() {
        workouts = new ArrayList<>();
    }

    // getters:
    public List<Workout> getWorkouts() {
        return this.workouts;
    }

    // MODIFIES: this
    // EFFECTS: adds workout to list of workouts
    public void addWorkout(Workout workout) {
        this.workouts.add(workout);
    }

    // MODIFIES: this
    // EFFECTS: removes workout from list of workouts
    public void removeWorkout(Workout workout) {
        this.workouts.remove(workout);
    }

    // EFFECTS: produces true if there is a workout from today
    public boolean today() {
        Date date = new Date();
        for (Workout w : workouts) {
            if (w.getDate().equals(date.getDate())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns true if workout with given date exists
    public boolean exists(String date) {
        for (Workout w : workouts) {
            if (w.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: workout with given date must exist in all workouts list
    // EFFECTS: return the workout with the given date from list
    public Workout retrieveWorkout(String date) {
        for (Workout w : workouts) {
            if (w.getDate().equals(date)) {
                return w;
            }
        }
        return null;
    }
}
