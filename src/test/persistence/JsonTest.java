package persistence;

import model.food.Food;
import model.food.MealType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkFoods(String name, MealType type, int calories, double protein, Food food) {
        assertEquals(name, food.getName());
        assertEquals(type, food.getType());
        assertEquals(calories, food.getCalories());
        assertEquals(protein, food.getProtein());
    }
}
