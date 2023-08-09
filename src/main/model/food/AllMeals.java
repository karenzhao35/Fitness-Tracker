package model.food;

import model.Data;
import model.Date;
import model.Event;
import model.EventLog;
import model.exceptions.DoesNotExist;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// A database for all meals eaten
public class AllMeals implements Data, Writable {
    private List<Meals> allMeals;

    // EFFECTS: constructs a new AllMeals with no meals in it
    public AllMeals() {
        this.allMeals = new ArrayList<>();
    }

    // getters:
    public List<Meals> getAllMeals() {
        return allMeals;
    }

    // MODIFIES: this
    // EFFECTS: if meal's date doesn't exist, add meals to list of all meals
    //          if it does, add food items in meal to already existing meal
    public void addMeals(Meals meals) {
        String date = meals.getDate();
        try {
            Meals exists = retreiveMeals(date);
            retreiveMeals(date).addFoods(meals.getFoods());
            EventLog.getInstance().logEvent(new Event("Meal on " + exists.getDate() + " updated."));
        } catch (DoesNotExist e) {
            allMeals.add(meals);
            EventLog.getInstance().logEvent(new Event("New meal for " + meals.getDate() + " logged."));
        }
    }

    // MODIFIES: this
    // EFFECTS: removes meals from list of all meals
    public void removeMeals(Meals meals) {
        allMeals.remove(meals);
        EventLog.getInstance().logEvent(new Event("Meal on " + meals.getDate() + " removed."));
    }

    // EFFECTS: produces true if there is a meal from today
    @Override
    public boolean today() {
        Date date = new Date();
        for (Meals m : allMeals) {
            if (m.getDate().equals(date.getDate())) {
                return true;
            }
        }
        return false;
    }


    // EFFECTS: return the meals with the given date from list
    //          throws DoesNotExist exception if meal is not in the list
    public Meals retreiveMeals(String date) throws DoesNotExist {
        for (Meals m : allMeals) {
            if (m.getDate().equals(date)) {
                return m;
            }
        }
        throw new DoesNotExist();
    }


    // EFFECTS: returns the all meals data as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("meals", mealsToJson());
        return json;
    }

    // EFFECTS: returns the meal data as JSON array
    private JSONArray mealsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Meals m : allMeals) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }
}

