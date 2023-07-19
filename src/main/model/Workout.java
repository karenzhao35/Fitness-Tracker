package model;

// a workout, which comprises a list of exercises performed that day
// and the date in which they were performed

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private List<Exercise> exercises;
    private Date date;

//    // EFFECTS: constructs a workout with no exercises and the current date
//    public Workout() {
//        exercises = new ArrayList<>();
//        date = new Date();
//    }

    // EFFECTS: constructs a workout with no exercises and the given date
    public Workout(String date) {
        exercises = new ArrayList<>();
        this.date = new Date(date);
    }

    // getters

    public List<Exercise> getExercises() {
        return this.exercises;
    }

    public String getDate() {
        return this.date.getDate();
    }

    // EFFECTS: removes given exercise from exercises
    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    // MODIFIES: this
    // EFFECTS: removes given exercise from exercises
    public void removeExercise(Exercise exercise) {
        this.exercises.remove(exercise);
    }

}
