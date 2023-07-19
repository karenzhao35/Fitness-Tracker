package model;

// A database for all workouts performed

import java.util.ArrayList;
import java.util.List;

public class AllWorkouts {
    private List<Workout> workouts;

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
    // EFFECTS: adds workout to list of workouts
    public void removeWorkout(Workout workout) {
        this.workouts.remove(workout);
    }
}
