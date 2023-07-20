package model;

// interface for the shared functions between ALlMeals and AllWorkouts

public interface AllData {

    // EFFECTS: return true if data occurs today
    public boolean today();

    // EFFECTS: returns true if data exists on given date
    public boolean exists(String date);

}
