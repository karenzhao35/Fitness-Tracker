package ui.panels.data.meals;

import model.food.AllMeals;
import ui.panels.data.ScrollInterface;
import ui.panels.data.meals.DisplayMeal;

public class ScrollInterfaceMeal extends ScrollInterface {

    public ScrollInterfaceMeal(AllMeals allMeals) {
        super(null, allMeals);
    }

    public void placeItems() {
        for (int i = 0; i < allMeals.getAllMeals().size(); i++) {
            DisplayMeal displayMeal = new DisplayMeal(allMeals, allMeals.getAllMeals().get(i), i);
            mainPanel.add(displayMeal.getPanel());
        }
    }

    public int calculateHeight() {
        int height = 15 + (105 * allMeals.getAllMeals().size());
        if (height > 550) {
            return height;
        } else {
            return 550;
        }
    }



}
