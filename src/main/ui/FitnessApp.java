package ui;

import model.AllData;
import model.food.AllMeals;
import model.workout.AllWorkouts;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Fitness application
public class FitnessApp {
    private static final String JSON_STORE = "./data/log.json";
    private AllMeals allMeals;
    private AllWorkouts allWorkouts;
    private AllData allData;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // the following code is inspired from the TellerApp class in the Teller Application
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java

    // EFFECTS: runs the fitness application
    public FitnessApp() throws FileNotFoundException {
        init();
        runFitness();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runFitness() {
        boolean cont = true;
        String command = null;

        String name = greetUser();

        while (cont) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("See you tomorrow " + name + "!");
                cont = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes workouts and meals
    private void init() throws FileNotFoundException {
        allData = new AllData("My data");
        allMeals = allData.getAllMeals();
        allWorkouts = allData.getAllWorkouts();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: greets the user
    public String greetUser() {
        System.out.println("Yo! I'm your virtual gym bro!");
        System.out.println("Here, you can log your sets and count your calories!");
        System.out.print("\nTo start, what's your name? ");
        String name = input.next();
        System.out.println("Welcome " + name + "!");
        System.out.println("Now let's get working!");
        return name;
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Select from the following options:");
        System.out.println("\tw -> to start a workout");
        System.out.println("\tf -> to begin a recording your food intake");
        System.out.println("\td -> to view previous data");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\tq -> to quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("w")) {
            new WorkoutApp(allWorkouts);
        } else if (command.equals("f")) {
            new MealApp(allMeals);
        } else if (command.equals("d")) {
            new DataApp(allWorkouts, allMeals);
        } else if (command.equals("s")) {
            saveWorkRoom();
        } else if (command.equals("l")) {
            loadWorkRoom();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(allData);
            jsonWriter.close();
            System.out.println("Saved " + allData.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadWorkRoom() {
        try {
            allData = jsonReader.read();
            allWorkouts = allData.getAllWorkouts();
            allMeals = allData.getAllMeals();
            System.out.println("Loaded " + allData.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
