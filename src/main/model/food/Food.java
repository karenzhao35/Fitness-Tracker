package model.food;

import org.json.JSONObject;
import persistence.Writable;

// A singular food item with meal type and calories
public class Food implements Writable {
    private String name;
    private MealType type;
    private int calories;
    private double protein;

    // EFFECTS: constructs food with given name, meal type, calories (kcal), and protein (g)
    public Food(String name, MealType type, int calories, double protein) {
        this.name = name;
        this.type = type;
        this.calories = calories;
        this.protein = protein;
    }

    // getters

    public String getName() {
        return name;
    }

    public MealType getType() {
        return type;
    }

    public int getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("meal type", type);
        json.put("calories", calories);
        json.put("protein", protein);
        return json;
    }
}
