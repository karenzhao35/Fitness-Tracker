package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        addAllMeals();
        testAllMeals.removeMeals(meals2);
        assertEquals(2, testAllMeals.getAllMeals().size());
        assertEquals(meals1, testAllMeals.getAllMeals().get(0));
        assertEquals(meals3, testAllMeals.getAllMeals().get(1));
    }

    @Test
    void testRemoveThreeWorkout() {
        addAllMeals();
        testAllMeals.removeMeals(meals1);
        testAllMeals.removeMeals(meals2);
        testAllMeals.removeMeals(meals3);
        assertEquals(0, testAllMeals.getAllMeals().size());
    }

    @Test
    void testTrueMealTodayOne() {
        testAllMeals.addMeals(meals3);
        assertTrue(testAllMeals.today());
    }

    @Test
    void testTrueMealToday() {
        addAllMeals();
        assertTrue(testAllMeals.today());
    }

    @Test
    void testEmptyMealToday() {
        assertFalse(testAllMeals.today());
    }

    @Test
    void testFalseMealToday() {
        testAllMeals.addMeals(meals1);
        testAllMeals.addMeals(meals2);
        assertFalse(testAllMeals.today());
    }

    @Test
    void testEmptyMealExists() {
        assertFalse(testAllMeals.exists("2017-07-14"));
    }

    @Test
    void testTrueMealExistsOne() {
        testAllMeals.addMeals(meals1);
        assertTrue(testAllMeals.exists(meals1.getDate()));
    }

    @Test
    void testMealExists() {
        addAllMeals();
        assertTrue(testAllMeals.exists(meals2.getDate()));
        assertFalse(testAllMeals.exists("2015-08-06"));
    }

    @Test
    void testRetrieveMealsEmpty() {
        assertNull(testAllMeals.retreiveMeals("2018-08-25"));
    }

    @Test
    void testRetrieveMeals() {
        addAllMeals();
        assertEquals(meals1, testAllMeals.retreiveMeals(meals1.getDate()));
        assertEquals(meals3, testAllMeals.retreiveMeals(meals3.getDate()));
    }

    @Test
    void testRetrieveMealsNotThere() {
        addAllMeals();
        assertNull(testAllMeals.retreiveMeals("1978-07-31"));
    }

    // MODIFIES: this
    // EFFECTS: adds meals to allMeals
    private void addAllMeals() {
        testAllMeals.addMeals(meals1);
        testAllMeals.addMeals(meals2);
        testAllMeals.addMeals(meals3);
    }
}
