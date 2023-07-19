package model;

// A singular food item with meal type and calories

public class Food {
    private String name;
    private MealType type;
    private int calories;

    public Food(String name, MealType type, int calories) {
        this.name = name;
        this.type = type;
        this.calories = calories;
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
}
