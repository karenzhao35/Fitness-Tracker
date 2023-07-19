package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealsTest {
    private Meals testMeals1;
    private Meals testMeals2;
    private Food food1;
    private Food food2;
    private Food food3;
    private long current;

    @BeforeEach
    void runBefore() {
        testMeals1 = new Meals("2019-09-08");
        testMeals2 = new Meals();
        food1 = new Food("Apple", MealType.SNACK, 30, 0.0);
        food2 = new Food("Steak", MealType.DINNER, 600, 20.0);
        food3 = new Food("Avacado", MealType.LUNCH, 240, 2.0);
        current = System.currentTimeMillis();
    }

    @Test
    void testConstructor1() {
        assertEquals(0, testMeals1.getMeal().size());
        assertEquals("2019-09-08", testMeals1.getDate());
    }

    @Test
    void testConstructor2() {
        assertEquals(0, testMeals2.getMeal().size());
        assertEquals(new java.sql.Date(current).toString(), testMeals2.getDate());
    }


    @Test
    void testAddOneWorkouts() {
        testMeals1.addFood(food1);
        assertEquals(1, testMeals1.getMeal().size());
        assertEquals(food1, testMeals1.getMeal().get(0));
    }

    @Test
    void testAddThreeWorkouts() {
        testMeals1.addFood(food1);
        testMeals1.addFood(food2);
        assertEquals(2, testMeals1.getMeal().size());
        assertEquals(food2, testMeals1.getMeal().get(1));
        testMeals1.addFood(food3);
        assertEquals(3, testMeals1.getMeal().size());
        assertEquals(food3, testMeals1.getMeal().get(2));
    }

    @Test
    void testRemoveOneWorkout() {
        addFoods(testMeals1);
        testMeals1.removeFood(food2);
        assertEquals(2, testMeals1.getMeal().size());
        assertEquals(food1, testMeals1.getMeal().get(0));
        assertEquals(food3, testMeals1.getMeal().get(1));
    }

    @Test
    void testRemoveThreeWorkout() {
        addFoods(testMeals1);
        testMeals1.removeFood(food1);
        testMeals1.removeFood(food2);
        testMeals1.removeFood(food3);
        assertEquals(0, testMeals1.getMeal().size());
    }

    @Test
    void testSumNoCalories() {
        assertEquals(0, testMeals2.sumCalories());
    }

    @Test
    void testSumThreeCalories() {
        addFoods(testMeals1);
        assertEquals(870, testMeals1.sumCalories());
    }

    @Test
    void testSumNoProtein() {
        assertEquals(0.0, testMeals2.sumProtein());
    }

    @Test
    void testSumThreeProteins() {
        addFoods(testMeals1);
        assertEquals(22.0, testMeals1.sumProtein());
    }


    private void addFoods(Meals meal) {
        meal.addFood(food1);
        meal.addFood(food2);
        meal.addFood(food3);
    }
}
