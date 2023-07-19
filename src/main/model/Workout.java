package model;

//

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private List<Exercise> exercises;
    private Date date;

    public Workout() {
        exercises = new ArrayList<>();
        date = new Date();
    }

    public Workout(String date) {
        exercises = new ArrayList<>();
        this.date = new Date(date);
    }

}
