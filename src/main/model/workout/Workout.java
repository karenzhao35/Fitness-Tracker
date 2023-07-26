package model.workout;

import model.Date;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.List;

// a workout, which comprises a list of exercises performed that day
// and the date in which they were performed
public class Workout implements Writable {
    private List<Exercise> exercises;
    private Date date;

    // EFFECTS: constructs a workout with no exercises and the current date
    public Workout() {
        exercises = new ArrayList<>();
        date = new Date();
    }

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

    // MODIFIES: this
    // EFFECTS: adds given exercise from exercises
    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    // MODIFIES: this
    // EFFECTS: adds the list of given exercises
    public void addExercises(List<Exercise> exercises) {
        for (Exercise e : exercises) {
            this.exercises.add(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes given exercise from exercises
    public void removeExercise(Exercise exercise) {
        this.exercises.remove(exercise);
    }

    // EFFECTS: puts the workout data into a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", getDate());
        json.put("exercises", exercisesToJson());
        return json;
    }

    // EFFECTS: returns the exercises as a JSON array
    private JSONArray exercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : exercises) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }
}
