package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealsTest {
    private Meals testMeals;
    private Food food1;
    private Food food2;
    private Food food3;

    @BeforeEach
    void runBefore() {
        testMeals = new Meals("2019-09-08");
        food1 = new Food("Apple", MealType.SNACK, 30);
        food2 = new Food("Steak", MealType.DINNER, 600);
        food3 = new Food("Avacado", MealType.LUNCH, 240);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testMeals.getMeal().size());
    }

    @Test
    void testAddOneWorkouts() {
        testMeals.addFood(food1);
        assertEquals(1, testMeals.getMeal().size());
        assertEquals(food1, testMeals.getMeal().get(0));
    }

    @Test
    void testAddThreeWorkouts() {
        testMeals.addFood(food1);
        testMeals.addFood(food2);
        assertEquals(2, testMeals.getMeal().size());
        assertEquals(food2, testMeals.getMeal().get(1));
        testMeals.addFood(food3);
        assertEquals(3, testMeals.getMeal().size());
        assertEquals(food3, testMeals.getMeal().get(2));
    }

    @Test
    void testRemoveOneWorkout() {
        testMeals.addFood(food1);
        testMeals.addFood(food2);
        testMeals.addFood(food3);
        testMeals.removeFood(food2);
        assertEquals(2, testMeals.getMeal().size());
        assertEquals(food1, testMeals.getMeal().get(0));
        assertEquals(food3, testMeals.getMeal().get(1));
    }

    @Test
    void testRemoveThreeWorkout() {
        testMeals.addFood(food1);
        testMeals.addFood(food2);
        testMeals.addFood(food3);
        testMeals.removeFood(food1);
        testMeals.removeFood(food2);
        testMeals.removeFood(food3);
        assertEquals(0, testMeals.getMeal().size());
    }
}
