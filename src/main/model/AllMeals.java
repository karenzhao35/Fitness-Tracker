package model;

// A database for all meals eaten

import java.util.ArrayList;
import java.util.List;

public class AllMeals {
    private List<Meals> allMeals;

    public AllMeals() {
        allMeals = new ArrayList<>();
    }

    // getters:
    public List<Meals> getAllMeals() {
        return allMeals;
    }

    // MODIFIES: this
    // EFFECTS: adds meals to list of all meals
    public void addMeals(Meals meals) {
        allMeals.add(meals);
    }

    // MODIFIES: this
    // EFFECTS: removes meals from list of all meals
    public void removeMeals(Meals meals) {
        allMeals.remove(meals);
    }
}