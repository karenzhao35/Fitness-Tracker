package ui.panels.data.meals;

import model.food.AllMeals;
import ui.panels.data.ScrollInterface;
import ui.panels.data.meals.DisplayMeal;

// Scroll interface for meal data
public class ScrollInterfaceMeal extends ScrollInterface {

    // EFFECTS: constructs a ScrollInterfaceMeal with given allMeals
    public ScrollInterfaceMeal(AllMeals allMeals) {
        super(null, allMeals);
    }

    // MODIFIES: this
    // EFFECTS: places each meal on the panel
    public void placeItems() {
        for (int i = 0; i < allMeals.getAllMeals().size(); i++) {
            DisplayMeal displayMeal = new DisplayMeal(allMeals, allMeals.getAllMeals().get(i), i);
            mainPanel.add(displayMeal.getPanel());
        }
    }

    // EFFECTS: calculates the height of the main panel
    public int calculateHeight() {
        int height = 15 + (105 * allMeals.getAllMeals().size());
        if (height > 550) {
            return height;
        } else {
            return 550;
        }
    }



}
