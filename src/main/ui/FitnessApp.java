package ui;

import model.*;

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
        greetUser();

        while (cont) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("See you tomorrow!");
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
    public void greetUser() {
        System.out.println("Yo! I'm your virtual gym bro!");
        System.out.println("Here, you can log your sets and count your calories!");
        System.out.print("\nTo start, what's your name? ");
        String name = input.next();
        System.out.println("Welcome " + name + "!");
        System.out.println("Now let's get working!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Select from the following options:");
        System.out.println("\tw -> to start a workout");
        System.out.println("\tm -> to begin a recording your food intake");
        System.out.println("\td -> to view previous data");
        System.out.println("\tq -> to quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("w")) {
            chooseWorkout();
        } else if (command.equals("m")) {
            chooseMeal();
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
            } else {
                processWorkoutCommand(command);
            }
        }
    }

    // EFFECTS: prints the options for starting a new workout
    private void initWorkout() {
        System.out.println("Enter the date you would like to record the workout for,");
        System.out.println("or 'today' if it's for today's workout,");
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
    private void chooseMeal() {
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
        if (allMeals.mealToday()) {
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
        System.out.print("Yum! Is it for breakfast, lunch, dinner, or snack? (enter one): ");
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

// TODO: finish implementation of search data
    private void searchData() {
    }


}
