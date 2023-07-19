package model;

// A singular food item with meal type and calories

public class Food {
    private String name;
    private MealType type;
    private int calories;
    private int protein;

    // EFFECTS: constructs food with given name, meal type, calories (kcal), and protein (g)
    public Food(String name, MealType type, int calories, int protein) {
        this.name = name;
        this.type = type;
        this.calories = calories;
        this.protein = protein;
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

    public int getProtein() {
        return protein;
    }
}
