package model.workout;

import model.Data;
import model.Date;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// A database for all workouts performed
public class AllWorkouts implements Data {
    private List<Workout> allWorkouts;

    // EFFECTS: constructs a new AllWorkouts with no workouts added
    public AllWorkouts() {
        this.allWorkouts = new ArrayList<>();
    }

    // getters:
    public List<Workout> getWorkouts() {
        return this.allWorkouts;
    }

    // MODIFIES: this
    // EFFECTS: adds workout to list of workouts
    public void addWorkout(Workout workout) {
        this.allWorkouts.add(workout);
    }

    // MODIFIES: this
    // EFFECTS: removes workout from list of workouts
    public void removeWorkout(Workout workout) {
        this.allWorkouts.remove(workout);
    }

    // EFFECTS: produces true if there is a workout from today
    @Override
    public boolean today() {
        Date date = new Date();
        for (Workout w : allWorkouts) {
            if (w.getDate().equals(date.getDate())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns true if workout with given date exists
    @Override
    public boolean exists(String date) {
        for (Workout w : allWorkouts) {
            if (w.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: workout with given date must exist in all workouts list
    // EFFECTS: return the workout with the given date from list
    public Workout retrieveWorkout(String date) {
        for (Workout w : allWorkouts) {
            if (w.getDate().equals(date)) {
                return w;
            }
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("workouts", workoutsToJson());
        return json;
    }

    private JSONArray workoutsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Workout w : allWorkouts) {
            jsonArray.put(w.toJson());
        }
        return jsonArray;
    }
}
