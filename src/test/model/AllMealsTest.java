package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllMealsTest {
    private AllMeals testAllMeals;
    private Meals meals1;
    private Meals meals2;
    private Meals meals3;

    @BeforeEach
    void runBefore() {
        testAllMeals = new AllMeals();
        meals1 = new Meals("2019-02-10");
        meals2 = new Meals("2021-09-18");
        meals3 = new Meals();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testAllMeals.getAllMeals().size());
    }

    @Test
    void testAddOneWorkouts() {
        testAllMeals.addMeals(meals1);
        assertEquals(1, testAllMeals.getAllMeals().size());
        assertEquals(meals1, testAllMeals.getAllMeals().get(0));
    }

    @Test
    void testAddThreeWorkouts() {
        testAllMeals.addMeals(meals1);
        testAllMeals.addMeals(meals2);
        assertEquals(2, testAllMeals.getAllMeals().size());
        assertEquals(meals2, testAllMeals.getAllMeals().get(1));
        testAllMeals.addMeals(meals3);
        assertEquals(3, testAllMeals.getAllMeals().size());
        assertEquals(meals3, testAllMeals.getAllMeals().get(2));
    }

    @Test
    void testRemoveOneWorkout() {
        testAllMeals.addMeals(meals1);
        testAllMeals.addMeals(meals2);
        testAllMeals.addMeals(meals3);
        testAllMeals.removeMeals(meals2);
        assertEquals(2, testAllMeals.getAllMeals().size());
        assertEquals(meals1, testAllMeals.getAllMeals().get(0));
        assertEquals(meals3, testAllMeals.getAllMeals().get(1));
    }

    @Test
    void testRemoveThreeWorkout() {
        testAllMeals.addMeals(meals1);
        testAllMeals.addMeals(meals2);
        testAllMeals.addMeals(meals3);
        testAllMeals.removeMeals(meals1);
        testAllMeals.removeMeals(meals2);
        testAllMeals.removeMeals(meals3);
        assertEquals(0, testAllMeals.getAllMeals().size());
    }
}
