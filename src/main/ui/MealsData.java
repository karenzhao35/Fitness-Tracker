package ui;

import model.food.AllMeals;
import model.food.Food;
import model.food.Meals;

import java.util.ArrayList;

// EFFECTS: Meals data for users to view previous food entries
public class MealsData extends Application {

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
        } else if (allMeals.exists(date)) {
            accessMeals(allMeals.retreiveMeals(date));
        } else {
            System.out.println("Unfortunately, food intake record with given date doesn't exist :(");
            System.out.println("Please try again!");
        }
    }

    // MODIFIES: this
    // EFFECTS: accesses the meal today to view foods and choose to remove meal
    private void mealDateToday() {
        if (allMeals.today()) {
            Meals meals = allMeals.retreiveMeals(new java.sql.Date(current).toString());
            accessMeals(meals);
        } else {
            System.out.println("Oh no! There was no food log today.");
        }
    }


    // MODIFIES: this
    // EFFECTS: accesses the meal on the given date to view foods and choose to remove meal
    private void accessMeals(Meals meals) {
        System.out.println("\n*******************************************************");
        System.out.println("\nOn " + meals.getDate() + " you ate:");
        printFoods(meals);
        System.out.println("\n*******************************************************");

        editMeal(meals);
    }

    // EFFECTS: prints the list of foods with calories and protein for given meal
    private void printFoods(Meals meals) {
        ArrayList<ArrayList<Food>> sorted = meals.separateFoodTypes();
        for (int i = 0; i < sorted.size(); i++) {
            typeLabel(i, sorted.get(i));
            for (Food f : sorted.get(i)) {
                String n = f.getName();
                int c = f.getCalories();
                double p = f.getProtein();
                System.out.println("\t- " + n + " which had " + c + " calories and " + p + " grams of protein");
            }
        }
        int kcal = meals.sumCalories();
        double protein = meals.sumProtein();
        System.out.println("In total, you ate " + kcal + " calories and " + protein + " grams of protein.");
    }

    // EFFECTS: determines if type label is needed and prints it
    private void typeLabel(int index, ArrayList<Food> meals) {
        if (!(meals.size() == 0)) {
            printTypeLabel(index);
        }
    }

    // EFFECTS: determines which label to print
    private void printTypeLabel(int index) {
        if (index == 0) {
            System.out.println("FOR BREAKFAST:");
        } else if (index == 1) {
            System.out.println("FOR LUNCH:");
        } else if (index == 2) {
            System.out.println("FOR DINNER:");
        } else {
            System.out.println("FOR SNACK:");
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
