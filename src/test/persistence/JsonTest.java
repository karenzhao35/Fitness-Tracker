package persistence;

import model.food.Food;
import model.food.Meals;
import model.workout.Exercise;
import model.workout.Workout;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkWorkout(String date, List<Exercise> exercises, Workout workout) {
        assertEquals(date, workout.getDate());
        assertEquals(exercises, workout.getExercises());
    }

    protected void checkMeals(String date, List<Food> foods, Meals meals) {
        assertEquals(date, meals.getDate());
        assertEquals(foods, meals.getFoods());
    }
}
