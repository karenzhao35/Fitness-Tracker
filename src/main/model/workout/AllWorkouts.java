package model.workout;

import model.Data;
import model.Date;
import model.Event;
import model.EventLog;
import model.exceptions.DoesNotExist;
import model.food.Meals;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// A database for all workouts performed
public class AllWorkouts implements Data, Writable {
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
    // EFFECTS: if workout's date doesn't exist, add workouts to list of all workouts
    //          if it does, add exercises in given workout to already existing workout
    public void addWorkout(Workout workout) {
        String date = workout.getDate();
        try {
            Workout exists = retrieveWorkout(date);
            exists.addExercises(workout.getExercises());
            EventLog.getInstance().logEvent(new Event("Workout on " + exists.getDate() + " updated."));
        } catch (DoesNotExist e) {
            allWorkouts.add(workout);
            EventLog.getInstance().logEvent(new Event("New workout on " + workout.getDate() + " logged."));
        }
    }

    // MODIFIES: this
    // EFFECTS: removes workout from list of workouts
    public void removeWorkout(Workout workout) {
        this.allWorkouts.remove(workout);
        EventLog.getInstance().logEvent(new Event("Workout on " + workout.getDate() + " removed."));
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


    // EFFECTS: return the workout with the given date from list
    //          if workout doesn't exist, throws DoesNotExist exception
    public Workout retrieveWorkout(String date) throws DoesNotExist {
        for (Workout w : allWorkouts) {
            if (w.getDate().equals(date)) {
                return w;
            }
        }
        throw new DoesNotExist();
    }

    // EFFECTS: returns the all workout data as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("workouts", workoutsToJson());
        return json;
    }

    // EFFECTS: returns the workout data as a JSON array
    private JSONArray workoutsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Workout w : allWorkouts) {
            jsonArray.put(w.toJson());
        }
        return jsonArray;
    }
}
