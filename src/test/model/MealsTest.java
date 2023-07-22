package model;

import model.food.Food;
import model.food.MealType;
import model.food.Meals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealsTest {
    private Meals testMeals1;
    private Meals testMeals2;
    private Food food1;
    private Food food2;
    private Food food3;
    private Food food4;
    private Food food5;
    private Food food6;
    private Food food7;
    private long current;

    @BeforeEach
    void runBefore() {
        testMeals1 = new Meals("2019-09-08");
        testMeals2 = new Meals();
        food1 = new Food("Apple", MealType.SNACK, 30, 0.0);
        food2 = new Food("Steak", MealType.DINNER, 600, 20.0);
        food3 = new Food("Avacado", MealType.LUNCH, 240, 2.0);
        food4 = new Food("Cheese", MealType.BREAKFAST, 30, 14.0);
        food5 = new Food("Lollipop", MealType.SNACK, 90, 0.0);
        food6 = new Food("Sandwich", MealType.LUNCH, 300, 5.0);
        food7 = new Food("Pasta", MealType.DINNER, 305, 7.0);
        current = System.currentTimeMillis();
    }

    @Test
    void testConstructor1() {
        assertEquals(0, testMeals1.getFoods().size());
        assertEquals("2019-09-08", testMeals1.getDate());
    }

    @Test
    void testConstructor2() {
        assertEquals(0, testMeals2.getFoods().size());
        assertEquals(new java.sql.Date(current).toString(), testMeals2.getDate());
    }


    @Test
    void testAddOneWorkouts() {
        testMeals1.addFood(food1);
        assertEquals(1, testMeals1.getFoods().size());
        assertEquals(food1, testMeals1.getFoods().get(0));
    }

    @Test
    void testAddThreeWorkouts() {
        testMeals1.addFood(food1);
        testMeals1.addFood(food2);
        assertEquals(2, testMeals1.getFoods().size());
        assertEquals(food2, testMeals1.getFoods().get(1));
        testMeals1.addFood(food3);
        assertEquals(3, testMeals1.getFoods().size());
        assertEquals(food3, testMeals1.getFoods().get(2));
    }

    @Test
    void testRemoveOneWorkout() {
        addFoods(testMeals1);
        testMeals1.removeFood(food2);
        assertEquals(6, testMeals1.getFoods().size());
        assertEquals(food1, testMeals1.getFoods().get(0));
        assertEquals(food3, testMeals1.getFoods().get(1));
    }

    @Test
    void testRemoveThreeWorkout() {
        addFoods(testMeals1);
        testMeals1.removeFood(food1);
        testMeals1.removeFood(food2);
        testMeals1.removeFood(food3);
        assertEquals(4, testMeals1.getFoods().size());
    }

    @Test
    void testSumNoCalories() {
        assertEquals(0, testMeals2.sumCalories());
    }

    @Test
    void testSumThreeCalories() {
        addFoods(testMeals1);
        assertEquals(1595, testMeals1.sumCalories());
    }

    @Test
    void testSumNoProtein() {
        assertEquals(0.0, testMeals2.sumProtein());
    }

    @Test
    void testSumThreeProteins() {
        addFoods(testMeals1);
        assertEquals(48.0, testMeals1.sumProtein());
    }

    @Test
    void testSortFoodTypesEmpty() {
        assertEquals(4, testMeals1.separateFoodTypes().size());
        assertEquals(0, testMeals1.separateFoodTypes().get(0).size());
        assertEquals(0, testMeals1.separateFoodTypes().get(1).size());
        assertEquals(0, testMeals1.separateFoodTypes().get(2).size());
        assertEquals(0, testMeals1.separateFoodTypes().get(3).size());
    }

    @Test
    void testSortFoodTypesAllTypes() {
        addFoods(testMeals1);
        testMeals1.addFood(food7);
        testMeals1.addFood(food5);
        testMeals1.addFood(food5);
        assertEquals(4, testMeals1.separateFoodTypes().size());
        assertEquals(1, testMeals1.separateFoodTypes().get(0).size());
        assertEquals(2, testMeals1.separateFoodTypes().get(1).size());
        assertEquals(3, testMeals1.separateFoodTypes().get(2).size());
        assertEquals(4, testMeals1.separateFoodTypes().get(3).size());
    }

    private void addFoods(Meals meals) {
        meals.addFood(food1);
        meals.addFood(food2);
        meals.addFood(food3);
        meals.addFood(food4);
        meals.addFood(food5);
        meals.addFood(food6);
        meals.addFood(food7);

    }
}
