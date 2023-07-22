package model.workout;

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
    }

    public Exercise(String name, List<Integer> reps, List<Integer> weight) {
        this.name = name;
        this.reps = reps;
        this.weight = weight;
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
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("reps", repsToJson());
        json.put("weight", weightsToJson());
        return json;
    }

    private JSONArray repsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (int i : reps) {
            jsonArray.put(i);
        }
        return jsonArray;
    }

    private JSONArray weightsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (int i : weight) {
            jsonArray.put(i);
        }
        return jsonArray;
    }

}
