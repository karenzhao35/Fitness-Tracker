package ui;

// EFFECTS: Meals data for users to view previous food entries

import model.AllMeals;
import model.Food;
import model.Meals;

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
    // EFFECTS: processes user input date to view workout data
    protected void processCommand(String date) {
        if (date.equals("today")) {
            mealDateToday(date);
        }else if (!dateFormat(date)) {
            System.out.println("You didn't follow the format :(");
        } else if (allMeals.exists(date)) {
            accessMeals(date);
        } else {
            System.out.println("Unfortunately, food intake record with given date doesn't exist :(");
        }
    }

    private void mealDateToday(String date) {
        if (allMeals.today()) {
            Meals meals = allMeals.retreiveMeals(date);
            accessMeals(date);
        } else {
            System.out.println("Oh no! There was no food log today.");
        }
    }

    // MODIFIES: this
    // EFFECTS: accesses the meal on the given date to view foods and choose to remove meal
    private void accessMeals(String date) {
        System.out.println("\n*******************************************************");
        System.out.println("\nOn " + date + " you ate:");
        Meals meals = allMeals.retreiveMeals(date);
        printFoods(meals);
        System.out.println("\n*******************************************************");

        editMeal(meals);
    }

    // EFFECTS: prints the list of foods with calories and protein for given meal
    private void printFoods(Meals meals) {
        for (Food f : meals.getMeal()) {
            String n = f.getName();
            int c = f.getCalories();
            double p = f.getProtein();
            System.out.println("\t- " + n + " which had " + c + " calories and " + p + " grams of protein");
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
