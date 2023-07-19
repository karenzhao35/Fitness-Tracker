package model;

// All the meals of the day

import java.util.ArrayList;
import java.util.List;

public class Meals {
    private List<Food> meal;
    private Date date;

    // EFFECTS: constructs a new Meals with today's date
    public Meals() {
        meal = new ArrayList<>();
        date = new Date();
    }

    // EFFECTS: constructs a new Meals with no foods and a new date
    public Meals(String date) {
        meal = new ArrayList<>();
        this.date = new Date(date);
    }

    // getters and setters
    public List<Food> getMeal() {
        return meal;
    }

    public String getDate() {
        return date.getDate();
    }


    // MODIFIES: this
    // EFFECTS: add given food item to meal
    public void addFood(Food food) {
        meal.add(food);
    }

    // MODIFIES: this
    // EFFECTS: removes given food item from meal
    public void removeFood(Food food) {
        meal.remove(food);
    }

    // EFFECTS: returns the total calories consumed
    public int sumCalories() {
        int soFar = 0;
        for (Food f : meal) {
            soFar += f.getCalories();
        }
        return soFar;
    }

    // EFFECTS: returns the total protein consumed
    public int sumProtein() {
        int soFar = 0;
        for (Food f : meal) {
            soFar += f.getProtein();
        }
        return soFar;
    }
}
