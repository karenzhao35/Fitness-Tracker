package ui.console;

import model.exceptions.DoesNotExist;
import model.food.AllMeals;
import model.food.Meals;
import ui.console.Application;

// Meals data for users to view previous food entries
public class MealsData extends Application {

    // EFFECTS: constructs a new MealData with given AllMeals data
    public MealsData(AllMeals allMeals) {
        super(null, allMeals);
    }

    // EFFECTS: guides user input for viewing workout data
    @Override
    protected void menu() {
        printDataDialogue();
    }

    // MODIFIES: this
    // EFFECTS: processes user input date to view meals data
    protected void processCommand(String date) {
        if (date.equals("today")) {
            mealDateToday();
        } else if (!dateFormat(date)) {
            System.out.println("You didn't follow the format :(");
            System.out.println("Please try again!");
        } else {
            try {
                printFoods(allMeals.retreiveMeals(date));
                editMeal(allMeals.retreiveMeals(date));
            } catch (DoesNotExist e) {
                System.out.println("Unfortunately, food intake record with given date doesn't exist :(");
                System.out.println("Please try again!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: accesses the meal today to view foods and choose to remove meal
    private void mealDateToday() {
        Meals meals = null;
        try {
            meals = allMeals.retreiveMeals(new java.sql.Date(current).toString());
            printFoods(meals);
            editMeal(meals);
        } catch (DoesNotExist e) {
            System.out.println("Oh no! There was no food log today.");

        }
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
