package model.workout;

import model.Event;
import model.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.List;

// An exercise with multiple sets of reps with different weights
public class Exercise implements Writable {
    private String name;
    private List<Integer> reps;
    private List<Integer> weight;

    // REQUIRES: reps are greater than 0
    //           weight is greater than or equal to 0
    // EFFECTS: constructs an exercise with given name
    //          and add the first set of reps and weight (lbs)
    public Exercise(String name, int reps, int weight) {
        this.name = name;
        this.reps = new ArrayList<>();
        this.weight = new ArrayList<>();

        this.reps.add(reps);
        this.weight.add(weight);
        EventLog.getInstance().logEvent(new Event("New set for " + name + " logged."));
    }

    // EFFECTS: constructs an Exercise with given sets
    public Exercise(String name, List<Integer> reps, List<Integer> weight) {
        this.name = name;
        this.reps = reps;
        this.weight = weight;
        EventLog.getInstance().logEvent(new Event("New sets for " + name + " logged."));
    }

    // getters:
    public String getName() {
        return this.name;
    }

    public List<Integer> getReps() {
        return this.reps;
    }

    public List<Integer> getWeight() {
        return this.weight;
    }

    // MODIFIES: this
    // EFFECTS: adds reps and weights to their respective lists for this exercise
    public void addSet(int reps, int weight) {
        this.reps.add(reps);
        this.weight.add(weight);
        EventLog.getInstance().logEvent(new Event("New set for " + name + " logged."));
    }


    // EFFECTS: returns the exercise data as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("reps", repsToJson());
        json.put("weight", weightsToJson());
        return json;
    }

    // EFFECTS: returns the reps as a JSON array
    private JSONArray repsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (int i : reps) {
            jsonArray.put(i);
        }
        return jsonArray;
    }

    // EFFECTS: returns the weights as a JSON array
    private JSONArray weightsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (int i : weight) {
            jsonArray.put(i);
        }
        return jsonArray;
    }

}
