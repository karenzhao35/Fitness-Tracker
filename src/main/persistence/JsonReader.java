package persistence;

import model.AllData;
import model.food.Food;
import model.food.MealType;
import model.food.Meals;
import model.workout.Exercise;
import model.workout.Workout;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// The following code was inspired by the JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/d79763d7ed5bb61196c51570598336948efe1202/src
// /main/persistence/JsonReader.java#L17

// Represents a reader that reads fitness and meal data from JSON data stored in file
public class JsonReader {
    private String source;


    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads fitness and meal data from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public AllData read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAllData(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses fitness and meal data from JSON object and returns it
    private AllData parseAllData(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String user = jsonObject.getString("user");
        JSONObject jsonObjectMeals = jsonObject.getJSONObject("Food Log");
        JSONObject jsonObjectWorkouts = jsonObject.getJSONObject("Fitness Log");

        AllData data = new AllData(name, user);
        addAllMeals(data, jsonObjectMeals);
        addAllWorkouts(data, jsonObjectWorkouts);
        return data;
    }

    // EFFECTS: parses all meal data from JSON object
    private void addAllMeals(AllData data, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("meals");
        for (Object json : jsonArray) {
            JSONObject nextMeals = (JSONObject) json;
            addMeals(data, nextMeals);
        }
    }

    // MODIFIES: data
    // EFFECTS: parses meal data from JSON object and adds it to AllData
    private void addMeals(AllData data, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("foods");
        String date = jsonObject.getString("date");
        Meals meals = new Meals(date);
        data.getAllMeals().addMeals(meals);
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFoods(meals, nextFood);
        }
    }

    // MODIFIES: meals
    // EFFECTS: parses food data from JSON object and adds it to the given meal
    private void addFoods(Meals meals, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MealType type = MealType.valueOf(jsonObject.getString("meal type"));
        int calories = jsonObject.getInt("calories");
        double protein = jsonObject.getDouble("protein");
        Food food = new Food(name, type, calories, protein);
        meals.addFood(food);
    }

    // EFFECTS: parses all workout data from JSON object
    private void addAllWorkouts(AllData data, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("workouts");
        for (Object json : jsonArray) {
            JSONObject nextWorkouts = (JSONObject) json;
            addWorkouts(data, nextWorkouts);
        }
    }

    // MODIFIES: data
    // EFFECTS: parses workouts from JSON object and adds it to AllData
    private void addWorkouts(AllData data, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("exercises");
        String date = jsonObject.getString("date");
        Workout workout = new Workout(date);
        data.getAllWorkouts().addWorkout(workout);
        for (Object json : jsonArray) {
            JSONObject nextExercises = (JSONObject) json;
            addExercises(workout, nextExercises);
        }
    }

    // MODIFIES: workouts
    // EFFECTS: parses through the exercise data and adds it to the workout
    private void addExercises(Workout workout, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray jsonArrayReps = jsonObject.getJSONArray("reps");
        JSONArray jsonArrayWeights = jsonObject.getJSONArray("weight");
        List<Integer> reps = new ArrayList<>();
        List<Integer> weight = new ArrayList<>();
        for (Object json : jsonArrayReps) {
            int i =  Integer.parseInt(json.toString());
            reps.add(i);
        }
        for (Object json : jsonArrayWeights) {
            int i =  Integer.parseInt(json.toString());
            weight.add(i);
        }
        Exercise exercise = new Exercise(name, reps, weight);
        workout.addExercise(exercise);
    }

}
