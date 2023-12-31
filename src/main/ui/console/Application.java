package ui.console;

import model.food.AllMeals;
import model.food.Food;
import model.food.MealType;
import model.food.Meals;
import model.workout.AllWorkouts;
import model.workout.Exercise;
import model.workout.Workout;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.List;
import java.util.Scanner;

// A generic application that includes the shared behaviour
public abstract class Application {
    protected AllMeals allMeals;
    protected AllWorkouts allWorkouts;
    protected Scanner input;
    protected long current;

    // EFFECTS: constructs a new Application with the given allWorkouts, allMeals, and initialized input
    public Application(AllWorkouts allWorkouts, AllMeals allMeals) {
        this.input = new Scanner(System.in);
        input.useDelimiter("\n");
        this.allMeals = allMeals;
        this.allWorkouts = allWorkouts;
        current = System.currentTimeMillis();
        newWindow();
    }

    // REQUIRES: this
    // EFFECTS: processes a new set of user input
    protected void newWindow() {
        boolean cont = true;
        String command = null;

        while (cont) {
            menu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("back")) {
                cont = false;
                System.out.println("Taking you back to the previous menu...");
            } else {
                processCommand(command);
            }
        }
    }

    // EFFECTS: guides user input by displaying the menu options
    protected abstract void menu();

    // MODIFIES: this
    // EFFECTS: processes user command from given input
    protected abstract void processCommand(String command);


    // EFFECTS: prints a list of exercises and the sets for each exercise
    protected void printExercises(Workout workout) {
        System.out.println("***************************************************************");
        System.out.println("\nHere is your workout on " + workout.getDate() + "!");
        for (Exercise e : workout.getExercises()) {
            System.out.println("For " + e.getName() + " you did:");
            for (int i = 0; i < e.getReps().size(); i++) {
                System.out.println("\t- " + e.getReps().get(i) + " reps at " + e.getWeight().get(i) + " lbs");
            }
        }
        System.out.println("\n***************************************************************");
    }

    // EFFECTS: sorts foods into a list of list
    //          the list will contain all the food in each meal type in order
    //          breakfast, lunch, dinner, snack
    public ArrayList<ArrayList<Food>> separateFoodTypes(Meals meals) {
        ArrayList<ArrayList<Food>> soFar = new ArrayList<>();
        ArrayList<Food> breakfast = new ArrayList<>();
        ArrayList<Food> lunch = new ArrayList<>();
        ArrayList<Food> dinner = new ArrayList<>();
        ArrayList<Food> snack = new ArrayList<>();
        for (Food f : meals.getFoods()) {
            if (f.getType().equals(MealType.BREAKFAST)) {
                breakfast.add(f);
            } else if (f.getType().equals(MealType.LUNCH)) {
                lunch.add(f);
            } else if (f.getType().equals(MealType.DINNER)) {
                dinner.add(f);
            } else {
                snack.add(f);
            }
        }
        soFar.add(breakfast);
        soFar.add(lunch);
        soFar.add(dinner);
        soFar.add(snack);
        return soFar;
    }

    // EFFECTS: prints the list of foods with calories and protein for given meal
    protected void printFoods(Meals meals) {
        System.out.println("****************** On " + meals.getDate() + " you ate: ********************");
        System.out.println("\nOn " + meals.getDate() + " you ate:");
        ArrayList<ArrayList<Food>> sorted = separateFoodTypes(meals);
        for (int i = 0; i < sorted.size(); i++) {
            typeLabel(i, sorted.get(i));
            for (Food f : sorted.get(i)) {
                String n = f.getName();
                int c = f.getCalories();
                double p = f.getProtein();
                System.out.println("\t- " + n + ", which had " + c + " calories and " + p + " grams of protein");
            }
        }
        int kcal = meals.sumCalories();
        double protein = meals.sumProtein();
        System.out.println("In total, you ate " + kcal + " calories and " + protein + " grams of protein.");
        System.out.println("\n***************************************************************");
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

    // EFFECTS: guides uer to enter date to search data
    protected void printDataDialogue() {
        System.out.println("\nEnter in the date you would like to view,");
        System.out.println("enter 'today' to view today's data, or enter 'back' to go back: ");
        System.out.println("NOTE: Please format the date as YYYY-MM-DD");
    }

    // EFFECTS: produces true if given command follows the YYYY-MM-DD date format
    public boolean dateFormat(String command) {
        String pattern = "\\b\\d{4}-((0[1-9])|(1[0-2]))-((0[1-9])|([1,2]\\d)|(3[0,1]))\\b";
        boolean matches = Pattern.matches(pattern, command);
        return matches;
    }

    // EFFECTS: produces true if given string is an integer
    protected boolean isInteger(String command) {
        try {
            Integer.parseInt(command);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // EFFECTS: produces true if given string is a double
    protected boolean isDouble(String command) {
        try {
            Double.parseDouble(command);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // EFFECTS: processes user command turning it into an integer if it is one
    public int ensureInteger() {
        boolean cont = true;
        String command = null;

        while (cont) {
            command = input.next();
            command = command.toLowerCase();

            if (isInteger(command)) {
                cont = false;
                return Integer.parseInt(command);
            } else {
                System.out.print("That is not an integer! Please try again: ");
            }
        }
        return 0;
    }

    // EFFECTS: processes user command turning it into a double if it is one
    protected double ensureDouble() {
        boolean cont = true;
        String command = null;

        while (cont) {
            command = input.next();
            command = command.toLowerCase();

            if (isDouble(command)) {
                cont = false;
                return Double.parseDouble(command);
            } else {
                System.out.print("That is not an integer! Please try again: ");
            }
        }
        return 0.0;
    }

}
