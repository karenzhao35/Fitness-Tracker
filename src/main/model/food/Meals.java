package model.food;

import model.Date;
import model.Event;
import model.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.List;

// All the meals of the day
public class Meals implements Writable {
    private List<Food> foods;
    private Date date;

    // EFFECTS: constructs a new Meals with today's date
    public Meals() {
        foods = new ArrayList<>();
        date = new Date();
    }

    // EFFECTS: constructs a new Meals with no foods and a new date
    public Meals(String date) {
        foods = new ArrayList<>();
        this.date = new Date(date);
    }

    // getters
    public List<Food> getFoods() {
        return foods;
    }

    public String getDate() {
        return date.getDate();
    }


    // MODIFIES: this
    // EFFECTS: add given food item to meal
    public void addFood(Food food) {
        foods.add(food);
        String description = food.getName() + " logged for meal on " + date.getDate() + ".";
        EventLog.getInstance().logEvent(new Event(description));
    }

    // MODIFIES: this
    // EFFECTS: removes given food item from meal
    public void removeFood(Food food) {
        foods.remove(food);
        String description = food.getName() + " removed for meal on " + date.getDate() + ".";
        EventLog.getInstance().logEvent(new Event(description));
    }

    // MODIFIES: this
    // EFFECTS: add given list of foods to meal
    public void addFoods(List<Food> foods) {
        for (Food f : foods) {
            this.foods.add(f);
            String description = f.getName() + " logged for meal on " + date.getDate() + ".";
            EventLog.getInstance().logEvent(new Event(description));
        }
    }

    // EFFECTS: returns the total calories consumed
    public int sumCalories() {
        int soFar = 0;
        for (Food f : foods) {
            soFar += f.getCalories();
        }
        return soFar;
    }

    // EFFECTS: returns the total protein consumed
    public int sumProtein() {
        int soFar = 0;
        for (Food f : foods) {
            soFar += f.getProtein();
        }
        return soFar;
    }


    // EFFECTS: returns the meal data as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date", getDate());
        json.put("foods", foodsToJson());
        return json;
    }

    // EFFECTS: return the food data as a JSON array
    private JSONArray foodsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food f : foods) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }
}
