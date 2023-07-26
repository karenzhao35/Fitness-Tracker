package persistence;

import model.AllData;
import model.food.Food;
import model.food.MealType;
import model.food.Meals;
import model.workout.Workout;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


// The following code was inspired by the JsonReaderTest in the JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/d79763d7ed5bb61196c51570598336948efe1202/src
// /test/persistence/JsonReaderTest.java#L49

public class JsonReaderTest extends JsonTest{

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
    void testReaderGeneralLogAllWorkout() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLog.json");
        try {
            AllData d = reader.read();
            assertEquals("My data", d.getName());
            assertEquals("Karen", d.getUser());
            List<Workout> allWorkouts = d.getAllWorkouts().getWorkouts();
            assertEquals(2, allWorkouts.size());
            assertEquals(2, allWorkouts.get(0).getExercises().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLogWorkout0() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLog.json");
        try {
            AllData d = reader.read();
            List<Workout> allWorkouts = d.getAllWorkouts().getWorkouts();
            assertEquals("2023-07-24", allWorkouts.get(0).getDate());

            assertEquals("Leg press", allWorkouts.get(0).getExercises().get(0).getName()); // Test exercise name
            assertEquals(10, allWorkouts.get(0).getExercises().get(0).getReps().get(0)); // Test reps
            assertEquals(20, allWorkouts.get(0).getExercises().get(0).getWeight().get(0)); // Test weights

            assertEquals("Lunges", allWorkouts.get(0).getExercises().get(1).getName());
            assertEquals(11, allWorkouts.get(0).getExercises().get(1).getReps().get(0));
            assertEquals(20, allWorkouts.get(0).getExercises().get(1).getWeight().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    @Test
    void testReaderGeneralLogWorkout1() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLog.json");
        try {
            AllData d = reader.read();
            List<Workout> allWorkouts = d.getAllWorkouts().getWorkouts();
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
            Meals meals = allMeals.get(0);
            Food food = meals.getFoods().get(0);
            assertEquals(1, allMeals.size());
            assertEquals("2023-07-24", meals.getDate());
            checkFoods("Apples", MealType.LUNCH, 30, 0, food);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
