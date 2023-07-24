package ui;

import model.*;
import model.exceptions.DoesNotExist;
import model.food.AllMeals;
import model.food.Food;
import model.food.MealType;
import model.food.Meals;

import java.util.regex.Pattern;

// Meals Application for users to enter in new food entries
public class MealApp extends Application {

    public MealApp(AllMeals allMeals) {
        super(null, allMeals);
    }


    // EFFECTS: prints the options for starting a new meal
    @Override
    protected void menu() {
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
    @Override
    protected void processCommand(String command) {
        if (command.equals("today")) {
            mealsToday();
        } else if (command.equals("view")) {
            try {
                printFoods(allMeals.retreiveMeals(new java.sql.Date(current).toString()));
            } catch (DoesNotExist e) {
                System.out.println("Record doesn't exist!");
            }
        } else if (dateFormat(command)) {
            mealsAnyDay(command);
        } else {
            System.out.println("You did not follow the date format :(");
            System.out.println("Please try again!");
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

    // MODIFIES: this
    // EFFECTS: adds the user's food to the meal
    private void startMeals(Meals meal) {
        Food food = newFood();
        meal.addFood(food);
        continueMeals(meal);
    }

    // EFFECTS: processes the user's input for food and produces the food
    private Food newFood() {
        System.out.print("Whatcha eating?: ");
        String name = input.next();

        MealType type = typeWindow();

        System.out.print("How many calories is in a(n) " + name + " (in kcal)? ");
        int calories = ensureInteger();

        System.out.print("How many grams of protein is in a(n) " + name + "? ");
        double protein = ensureDouble();

        Food food = new Food(name, type, calories, protein);
        return food;
    }

    // REQUIRES: this
    // EFFECTS: processes a new set of user input
    protected MealType typeWindow() {
        boolean cont = true;
        String command = null;
        System.out.print("Yum! Is it for breakfast, lunch, dinner, or a snack? (enter one): ");

        while (cont) {
            command = input.next();
            command = command.toLowerCase();

            if (!typeFormat(command)) {
                System.out.print("That is not an option! Please try again: ");
            } else {
                cont = false;
                return getType(command);
            }
        }
        return null;
    }

    // EFFECTS: produces true if given command follows the YYYY-MM-DD date format
    protected boolean typeFormat(String command) {
        String pattern = "\\b^((snack)|(breakfast)|(lunch)|(dinner))$\\b";
        boolean matches = Pattern.matches(pattern, command);
        return matches;
    }


    // EFFECTS: returns proper MealType based on user input
    private MealType getType(String command) {
        if (command.equals("breakfast")) {
            return MealType.BREAKFAST;
        } else if (command.equals("lunch")) {
            return MealType.LUNCH;
        } else if (command.equals("dinner")) {
            return MealType.DINNER;
        } else {
            return MealType.SNACK;
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input to see if user would like to continue meal
    private void continueMeals(Meals meal) {
        boolean cont = true;
        String command = null;

        while (cont) {
            continuePrompt();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                cont = false;
            } else if (command.equals("n")) {
                Food newFood = newFood();
                meal.addFood(newFood);
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    private void continuePrompt() {
        System.out.println("That sounds delicious!");
        System.out.println("\nSelect from the following options:");
        System.out.println("n -> add another food item");
        System.out.println("q -> to go back to the meal menu");
    }
}
