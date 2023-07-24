package model.food;

import model.food.Food;
import model.food.MealType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodTest {
    private Food food1;
    private Food food2;

    @BeforeEach
    void runBefore() {
        food1 = new Food("Apple", MealType.BREAKFAST, 30, 8.0);
        food2 = new Food("Steak", MealType.DINNER, 600, 25.0);
    }

    @Test
    void testConstructor1() {
        assertEquals("Apple", food1.getName());
        assertEquals(MealType.BREAKFAST, food1.getType());
        assertEquals(30, food1.getCalories());
        assertEquals(8.0, food1.getProtein());
    }

    @Test
    void testConstructor2() {
        assertEquals("Steak", food2.getName());
        assertEquals(MealType.DINNER, food2.getType());
        assertEquals(600, food2.getCalories());
        assertEquals(25.0, food2.getProtein());

    }
}
