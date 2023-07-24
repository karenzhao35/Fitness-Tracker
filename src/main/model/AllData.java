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
    private String user;

    public AllData(String name, String user) {
        allWorkouts = new AllWorkouts();
        allMeals = new AllMeals();
        this.name = name;
        this.user = user;
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

    public String getUser() {
        return user;
    }

    public void setAllWorkouts(AllWorkouts allWorkouts) {
        this.allWorkouts = allWorkouts;
    }

    public void setAllMeals(AllMeals allMeals) {
        this.allMeals = allMeals;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("user", user);
        json.put("Fitness Log", allWorkouts.toJson());
        json.put("Food Log", allMeals.toJson());
        return json;
    }

}
