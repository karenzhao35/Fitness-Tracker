package model;

// interface for the shared functions between ALlMeals and AllWorkouts

import model.food.Meals;
import model.workout.Workout;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public interface Data {

    // EFFECTS: return true if data occurs today
    boolean today();

    // EFFECTS: returns true if data exists on given date
    boolean exists(String date);

    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}
