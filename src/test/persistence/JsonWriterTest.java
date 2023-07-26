package persistence;

import model.AllData;
import model.food.AllMeals;
import model.food.Food;
import model.food.MealType;
import model.food.Meals;
import model.workout.AllWorkouts;
import model.workout.Exercise;
import model.workout.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// The following code was inspired by the JsonWriterTest class in the JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/d79763d7ed5bb61196c51570598336948efe1202/src
// /test/persistence/JsonWriterTest.java#L52


class JsonWriterTest extends JsonTest {
    private AllData allData;

    @BeforeEach
    void runBefore() {
        allData = new AllData("My data" , "Karen");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLog() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLog.json");
            writer.open();
            writer.write(allData);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLog.json");
            allData = reader.read();
            assertEquals("My data", allData.getName());
            assertEquals("Karen", allData.getUser());
            assertEquals(0, allData.getAllWorkouts().getWorkouts().size());
            assertEquals(0, allData.getAllMeals().getAllMeals().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLog() {
        try {
            initData();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLog.json");
            writer.open();
            writer.write(allData);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLog.json");
            allData = reader.read();
            assertEquals("My data", allData.getName());
            assertEquals("Karen", allData.getUser());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLogWorkout() {
        try {
            initData();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLog.json");
            writer.open();
            writer.write(allData);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLog.json");
            allData = reader.read();
            List<Workout> allWorkouts = allData.getAllWorkouts().getWorkouts();
            assertEquals(1, allWorkouts.size());
            assertEquals("2023-07-23", allWorkouts.get(0).getDate());
            assertEquals("Leg press", allWorkouts.get(0).getExercises().get(0).getName()); // Test exercise name
            assertEquals(10, allWorkouts.get(0).getExercises().get(0).getReps().get(0)); // Test reps
            assertEquals(200, allWorkouts.get(0).getExercises().get(0).getWeight().get(0)); // Test weights
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testGeneralLogMeals() {
        try {
            initData();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLog.json");
            writer.open();
            writer.write(allData);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLog.json");
            allData = reader.read();

            List<Meals> allMeals = allData.getAllMeals().getAllMeals();
            assertEquals(1, allMeals.size());
            assertEquals("2023-07-24", allMeals.get(0).getDate());
            checkFoods("Pasta", MealType.DINNER, 300, 20, allMeals.get(0).getFoods().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    void initData() {
        Food food = new Food("Pasta", MealType.DINNER, 300, 20);
        Meals meals = new Meals("2023-07-24");
        AllMeals allMeals = new AllMeals();
        meals.addFood(food);
        allMeals.addMeals(meals);

        List<Integer> reps = new ArrayList<>();
        List<Integer> weight = new ArrayList<>();
        reps.add(10);
        weight.add(200);
        Exercise exercise = new Exercise("Leg press", reps, weight);
        Workout workout = new Workout("2023-07-23");
        AllWorkouts allWorkouts = new AllWorkouts();
        workout.addExercise(exercise);
        allWorkouts.addWorkout(workout);

        allData.setAllWorkouts(allWorkouts);
        allData.setAllMeals(allMeals);
    }
}