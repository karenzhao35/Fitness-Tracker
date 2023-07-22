package model;

import model.food.AllMeals;
import model.food.Meals;
import model.workout.AllWorkouts;
import model.workout.Workout;
import org.json.JSONObject;
import persistence.Writable;

public class AllData implements Writable {
    private AllWorkouts allWorkouts;
    private AllMeals allMeals;
    private String name;

    public AllData(String name) {
        allWorkouts = new AllWorkouts();
        allMeals = new AllMeals();
        this.name = name;
    }

    public AllWorkouts getAllWorkouts() {
        return allWorkouts;
    }

    public AllMeals getAllMeals() {
        return allMeals;
    }

    public String getName() {
        return name;
    }

    public void setAllWorkouts(AllWorkouts allWorkouts) {
        this.allWorkouts = allWorkouts;
    }

    public void setAllMeals(AllMeals allMeals) {
        this.allMeals = allMeals;
    }

    public void addDataWorkout(Workout workout) {
        this.allWorkouts.addWorkout(workout);
    }

    public void addDataMeal(Meals meals) {
        this.allMeals.addMeals(meals);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Fitness Log", allWorkouts.toJson());
        json.put("Food Log", allMeals.toJson());
        return json;
    }

}
