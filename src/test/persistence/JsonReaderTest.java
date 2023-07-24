package persistence;

import model.AllData;
import model.food.MealType;
import model.food.Meals;
import model.workout.Workout;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AllData d = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLog.json");
        try {
            AllData d = reader.read();
            assertEquals("My data", d.getName());
            assertEquals(0, d.getAllWorkouts().getWorkouts().size());
            assertEquals(0, d.getAllMeals().getAllMeals().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLogWorkout() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLog.json");
        try {
            AllData d = reader.read();
            assertEquals("My data", d.getName());
            assertEquals("Karen", d.getUser());
            List<Workout> allWorkouts = d.getAllWorkouts().getWorkouts();
            assertEquals(2, allWorkouts.size());
            assertEquals(2, allWorkouts.get(0).getExercises().size());
            assertEquals("2023-07-24", allWorkouts.get(0).getDate());

            assertEquals("Leg press", allWorkouts.get(0).getExercises().get(0).getName()); // Test exercise name
            assertEquals(10, allWorkouts.get(0).getExercises().get(0).getReps().get(0)); // Test reps
            assertEquals(20, allWorkouts.get(0).getExercises().get(0).getWeight().get(0)); // Test weights

            assertEquals("Lunges", allWorkouts.get(0).getExercises().get(1).getName());
            assertEquals(11, allWorkouts.get(0).getExercises().get(1).getReps().get(0));
            assertEquals(20, allWorkouts.get(0).getExercises().get(1).getWeight().get(0));

            assertEquals("2023-07-23", allWorkouts.get(1).getDate());
            assertEquals("Pull-ups", allWorkouts.get(1).getExercises().get(0).getName()); // Test exercise name
            assertEquals(9, allWorkouts.get(1).getExercises().get(0).getReps().get(0)); // Test reps
            assertEquals(90, allWorkouts.get(1).getExercises().get(0).getWeight().get(0)); // Test weights
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }




    @Test
    void testReaderGeneralLogMeals() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLog.json");
        try {
            AllData d = reader.read();
            List<Meals> allMeals = d.getAllMeals().getAllMeals();
            assertEquals(1, allMeals.size());
            assertEquals("2023-07-24", allMeals.get(0).getDate());
            assertEquals("Apples", allMeals.get(0).getFoods().get(0).getName());
            assertEquals(30, allMeals.get(0).getFoods().get(0).getCalories());
            assertEquals(0, allMeals.get(0).getFoods().get(0).getProtein());
            assertEquals(MealType.LUNCH, allMeals.get(0).getFoods().get(0).getType());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
