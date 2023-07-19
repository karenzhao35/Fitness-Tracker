package model;

// An exercise with multiple sets of reps with different weights

import java.util.ArrayList;
import java.util.List;

public class Exercise {
    private String name;
    private List<Integer> reps;
    private List<Integer> weight;

    // EFFECTS: constructs an exercise with given name
    //          and add the first set of reps and weight
    public Exercise(String name, int reps, int weight) {
        this.name = name;
        this.reps = new ArrayList<>();
        this.weight = new ArrayList<>();

        this.reps.add(reps);
        this.weight.add(weight);
    }

    // getters:
    public String getName() {
        return this.name;
    }

    // MODIFIES: this
    // EFFECTS: adds reps and weights to their respective lists for this exercise
    public void addSet(int reps, int weight) {
        this.reps.add(reps);
        this.weight.add(weight);
    }

}
