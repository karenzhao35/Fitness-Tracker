package ui;

import model.*;

import java.util.List;
import java.util.Scanner;

public class FitnessApp {
    private AllMeals allMeals;
    private AllWorkouts allWorkouts;
    private Scanner input;

    // the following code is inspired from the TellerApp class in the Teller Application
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java

    // EFFECTS: runs the fitness application
    public FitnessApp() {
        runFitness();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runFitness() {
        boolean cont = true;
        String command = null;

        init();
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
    private void init() {
        allMeals = new AllMeals();
        allWorkouts = new AllWorkouts();
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
        System.out.println("\tq -> to quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("w")) {
            chooseWorkout();
        } else if (command.equals("f")) {
            chooseMeals();
        } else if (command.equals("d")) {
            searchData();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input for a new workout
    private void chooseWorkout() {
        boolean cont = true;
        String command = null;

        while (cont) {
            initWorkout();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                cont = false;
            } else if (command.equals("view")) {
                printTodaysWorkout();
            } else {
                processWorkoutCommand(command);
            }
        }
    }

    // EFFECTS: prints the options for starting a new workout
    private void initWorkout() {
        System.out.println("\nEnter the date you would like to record the workout for,");
        System.out.println("or 'today' if it's for today's workout,");
        if (allWorkouts.today()) {
            System.out.println("or 'view' to view today's calorie and protein intake,");
        }
        System.out.println("or 'back' to return back to the menu");
        System.out.println("NOTE: Please format the date as YYYY-MM-DD");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for a new workout
    private void processWorkoutCommand(String command) {
        if (command.equals("today")) {
            workoutToday();
        } else {
            workoutAnyDay(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new workout with today's date
    private void workoutToday() {
        Workout workout = new Workout();
        allWorkouts.addWorkout(workout);
        startWorkout(workout);
    }

    // MODIFIES: this
    // EFFECTS: creates a new workout with a given date
    private void workoutAnyDay(String command) {
        Workout workout = new Workout(command);
        startWorkout(workout);
    }

    // EFFECTS: prints all the exercises and sets from today's workout
    private void printTodaysWorkout() {
        Date date = new Date();
        Workout todaysWorkout = allWorkouts.retrieveWorkout(date.getDate());
        printExercises(todaysWorkout.getExercises());
        System.out.println("Taking you back to the workout menu now...");
    }


    // MODIFIES: this
    // EFFECTS: adds the user's exercise to the workout
    private void startWorkout(Workout workout) {
        allWorkouts.addWorkout(workout);
        Exercise exercise = newExercise();
        workout.addExercise(exercise);
        continueWorkout(workout, exercise);
    }

    // EFFECTS: processes the user's input for exercise and produces the exercise
    private Exercise newExercise() {
        System.out.print("Enter exercise name: ");
        String name = input.next();
        System.out.print("Enter reps: ");
        int reps = input.nextInt();
        System.out.print("Enter weight (in lbs): ");
        int weight = input.nextInt();

        Exercise exercise = new Exercise(name, reps, weight);
        return exercise;
    }

    // MODIFIES: this
    // EFFECTS: processes user input to see if user would like to continue exercise or workout
    private void continueWorkout(Workout workout, Exercise exercise) {
        boolean cont = true;
        String command = null;

        while (cont) {
            System.out.println("Great! Enter 'continue' to add a new set, 'new' to add another exercise,");
            System.out.println("or 'back' to return to the workout menu.");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                cont = false;
            } else if (command.equals("continue")) {
                addSet(exercise);
            } else if (command.equals("new")) {
                Exercise newExercise = newExercise();
                workout.addExercise(newExercise);
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new set based on user input to the given exercise
    private void addSet(Exercise exercise) {
        System.out.println("You got this!");
        System.out.print("Enter reps: ");
        int reps = input.nextInt();
        System.out.print("Enter weight (in lbs): ");
        int weight = input.nextInt();
        exercise.addSet(reps, weight);
    }

    // MODIFIES: this
    // EFFECTS: processes user input for a new meal
    private void chooseMeals() {
        boolean cont = true;
        String command = null;

        while (cont) {
            initMeal();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                cont = false;
            } else {
                processMealCommand(command);
            }
        }
    }

    // EFFECTS: prints the options for starting a new meal
    private void initMeal() {
        System.out.println("\nEnter the date you would like to record your food intake for,");
        System.out.println("or 'today' if it's for today's food intake,");
        if (allMeals.today()) {
            System.out.println("or 'view' to view today's calorie and protein intake,");
        }
        System.out.println("or 'back' to return back to the menu");
        System.out.println("NOTE: Please format the date as YYYY-MM-DD");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for a new meal
    private void processMealCommand(String command) {
        if (command.equals("today")) {
            mealsToday();
        } else if (command.equals("view")) {
            calculateTodaysFoodIntake();
        } else {
            mealsAnyDay(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new meal with today's date
    private void mealsToday() {
        Meals meal = new Meals();
        allMeals.addMeals(meal);
        startMeals(meal);
    }

    // MODIFIES: this
    // EFFECTS: creates a new meal with the given date
    private void mealsAnyDay(String date) {
        Meals meal = new Meals(date);
        allMeals.addMeals(meal);
        startMeals(meal);
    }

    // EFFECTS: calculates and produces today's calorie and protein intake
    private void calculateTodaysFoodIntake() {
        Date date = new Date();
        Meals todaysMeals = allMeals.retreiveMeals(date.getDate());
        int calories = todaysMeals.sumCalories();
        double protein = todaysMeals.sumProtein();
        System.out.println("Nice! Today you ate " + calories + " calories and " + protein + " grams of protein.");
    }

    // MODIFIES: this
    // EFFECTS: adds the user's food to the meal
    private void startMeals(Meals meal) {
        allMeals.addMeals(meal);
        Food food = newFood();
        meal.addFood(food);
        continueMeals(meal);
    }

    // EFFECTS: processes the user's input for food and produces the food
    private Food newFood() {
        System.out.print("Whatcha eating?: ");
        String name = input.next();
        System.out.print("Yum! Is it for breakfast, lunch, dinner, or a snack? (enter one): ");
        String command = input.next();
        command.toLowerCase();
        MealType type = getType(command);
        System.out.print("How many calories is in a(n) " + name + " (in kcal)? ");
        int calories = input.nextInt();
        System.out.print("How many grams of protein is in a(n) " + name + "? ");
        double protein = input.nextDouble();

        Food food = new Food(name, type, calories, protein);
        return food;
    }

    // EFFECTS: returns proper MealType based on user input
    private MealType getType(String command) {
        if (command.equals("breakfast")) {
            return MealType.BREAKFAST;
        } else if (command.equals("lunch")) {
            return MealType.LUNCH;
        } else if (command.equals("dinner")) {
            return MealType.DINNER;
        } else if (command.equals("snack")) {
            return MealType.SNACK;
        } else {
            return MealType.OTHER;
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input to see if user would like to continue meal
    private void continueMeals(Meals meal) {
        boolean cont = true;
        String command = null;

        while (cont) {
            System.out.println("That sounds delicious!");
            System.out.println("Enter 'new' to add another food item, or 'back' to go back to the meal menu.");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                cont = false;
            } else if (command.equals("new")) {
                Food newFood = newFood();
                meal.addFood(newFood);
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input for searching their data
    private void searchData() {
        boolean cont = true;
        String command = null;

        while (cont) {
            initData();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                cont = false;
            } else {
                processDataCommand(command);
            }
        }
    }

    // EFFECTS: message to guide user input for data archive
    private void initData() {
        System.out.println("Welcome to your archive.");
        System.out.println("Would you like to view your previous workouts, food intake, or go back?");
        System.out.println("\tw    -> workouts");
        System.out.println("\tf    -> food intake");
        System.out.println("\tback -> back");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for viewing data
    private void processDataCommand(String command) {
        if (command.equals("w")) {
            System.out.println("\nLet's look at your past together...");
            viewWorkouts();
        } else if (command.equals("f")) {
            viewMeals();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input for viewing workout data
    private void viewWorkouts() {
        boolean cont = true;
        String command = null;

        while (cont) {
            initView();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                cont = false;
            } else {
                processViewWorkoutCommand(command);
            }
        }
    }

    // EFFECTS: guides user input for viewing workout data
    private void initView() {
        System.out.println("\nEnter in the date you would like to view, or enter 'back' to go back: ");
        System.out.println("NOTE: Please format the date as YYYY-MM-DD");
    }

    // MODIFIES: this
    // EFFECTS: processes user input date to view workout data
    private void processViewWorkoutCommand(String date) {
        if (allWorkouts.exists(date)) {
            Workout workout = allWorkouts.retrieveWorkout(date);
            accessWorkout(workout);
        } else {
            System.out.println("Unfortunately, workout with given date doesn't exist :(");
        }
    }

    // MODIFIES: this
    // EFFECTS: accesses the workout on the given date to view exercises and choose to remove workout
    private void accessWorkout(Workout workout) {
        System.out.println("Here is your workout on " + workout.getDate() + "!");
        printExercises(workout.getExercises());
        editWorkout(workout);
    }

    // EFFECTS: prints a list of exercises and the sets for each exercise
    private void printExercises(List<Exercise> exercises) {
        for (Exercise e : exercises) {
            System.out.println("For " + e.getName() + " you did:");
            for (int i = 0; i < e.getReps().size(); i++) {
                System.out.println("\t" + e.getReps().get(i) + " reps at " + e.getWeight().get(i) + " lbs");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes the user command and removes workout if prompted
    private void editWorkout(Workout workout) {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            System.out.println("Enter 'remove' to remove this workout, ");
            System.out.println("enter 'back' to go back to the workout database menu.");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                keepGoing = false;
            } else if (command.equals("remove")) {
                allWorkouts.removeWorkout(workout);
                System.out.println("Your workout has been removed.");
                System.out.println("Taking you back to the workout database menu...");
                keepGoing = false;
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input for viewing food data
    private void viewMeals() {
        boolean cont = true;
        String command = null;

        while (cont) {
            initView();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                cont = false;
            } else {
                processViewMealsCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input date to view workout data
    private void processViewMealsCommand(String date) {
        if (allMeals.exists(date)) {
            accessMeals(date);
        } else {
            System.out.println("Unfortunately, food intake record with given date doesn't exist :(");
        }
    }

    // MODIFIES: this
    // EFFECTS: accesses the meal on the given date to view foods and choose to remove meal
    private void accessMeals(String date) {
        System.out.println("On " + date + " you ate:");
        Meals meals = allMeals.retreiveMeals(date);
        printFoods(meals);
        editMeal(meals);
    }

    // EFFECTS: prints the list of foods with calories and protein for given meal
    private void printFoods(Meals meals) {
        for (Food f : meals.getMeal()) {
            String n = f.getName();
            int c = f.getCalories();
            double p = f.getProtein();
            System.out.println("\t-" + n + " which had " + c + " calories and " + p + " grams of protein");
        }
        int kcal = meals.sumCalories();
        double protein = meals.sumProtein();
        System.out.println("In total, you ate " + kcal + " calories and " + protein + " grams of protein.");
    }

    // MODIFIES: this
    // EFFECTS: processes the user command and removes meal if prompted
    private void editMeal(Meals meals) {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            System.out.println("Enter 'remove' to remove this day's food intake, ");
            System.out.println("enter 'back' to go back to the workout database menu.");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                keepGoing = false;
            } else if (command.equals("remove")) {
                allMeals.removeMeals(meals);
                System.out.println("This day's food intake has been removed.");
                System.out.println("Taking you back to the food intake database menu...");
                keepGoing = false;
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

}
