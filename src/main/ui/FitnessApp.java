package ui;

import model.AllData;
import model.food.AllMeals;
import model.workout.AllWorkouts;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


// the following code is inspired from the TellerApp class in the Teller Application
// https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java

// and also from the WorkRoomApp from the JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/d79763d7ed5bb61196c51570598336948efe1202/src
// /main/ui/WorkRoomApp.java#L122-L121

// Fitness application
public class FitnessApp {
    private static final String JSON_STORE = "./data/log.json";
    private AllMeals allMeals;
    private AllWorkouts allWorkouts;
    private AllData allData;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the fitness application
    public FitnessApp() throws FileNotFoundException {
        init();
        String user = "";
        if (runLoad()) {
            user = greetUser();
            initData(user);
        } else {
            user = allData.getUser();
            System.out.println("\nWelcome back " + user);
        }
        runFitness(user);
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runFitness(String user) {
        boolean cont = true;
        String command = null;

        while (cont) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                runSave();
                System.out.println("See you tomorrow " + user + "!");
                cont = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes workouts and meals
    private void initData(String user) {
        allData = new AllData("My data", user);
        allMeals = allData.getAllMeals();
        allWorkouts = allData.getAllWorkouts();
    }

    // MODIFIES: this
    // EFFECTS: initializes JsonReader, JsonWriter and scanner
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: prompts user to choose to load their data
    private void promptLoad() {
        System.out.println("Would you like to load previous data?");
        System.out.print("Enter \"yes\" or \"no\": ");
    }

    // MODIFIES: this
    // EFFECTS: processes user input for loading data
    public boolean runLoad() {
        boolean cont = true;
        String command = null;

        while (cont) {
            promptLoad();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("yes")) {
                loadWorkRoom();
                cont = false;
                return false;
            } else if (command.equals("no")) {
                cont = false;
                return true;
            } else {
                System.out.println("Selection invalid!");
            }
        }
        return false;
    }

    // EFFECTS: processes user input to choose to save data
    private void runSave() {
        boolean cont = true;
        String command = null;

        while (cont) {
            promptSave();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("yes")) {
                saveWorkRoom();
                cont = false;
            } else if (command.equals("no")) {
                cont = false;
            } else {
                System.out.println("Selection invalid!");
            }
        }
    }

    // EFFECTS: prompts user to save their data
    private void promptSave() {
        System.out.println("Wait! Before you go, would you like to save your data?");
        System.out.print("Enter \"yes\" or \"no\": ");
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
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: saves workout and meal data to file
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

    // MODIFIES: this
    // EFFECTS: loads workout and meal data from file
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
