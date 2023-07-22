package model.food;

import model.AllData;
import model.Date;
import java.util.ArrayList;
import java.util.List;

// A database for all meals eaten
public class AllMeals implements AllData {
    private List<Meals> allMeals;

    // EFFECTS: constructs a new AllMeals with no meals in it
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

    // EFFECTS: produces true if there is a meal from today
    public boolean today() {
        Date date = new Date();
        for (Meals m : allMeals) {
            if (m.getDate().equals(date.getDate())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns true if meal with given date exists
    public boolean exists(String date) {
        for (Meals m : allMeals) {
            if (m.getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: meal with given date must exist in all meals list
    // EFFECTS: return the meals with the given date from list
    public Meals retreiveMeals(String date) {
        for (Meals m : allMeals) {
            if (m.getDate().equals(date)) {
                return m;
            }
        }
        return null;
    }
}

