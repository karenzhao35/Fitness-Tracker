package ui.panels.data.workouts;

import model.workout.AllWorkouts;
import ui.panels.data.ScrollInterface;

// scroll interface for workout
public class ScrollInterfaceWorkout extends ScrollInterface {

    // EFFECTS: constructs scroll interface with given allWorkouts
    public ScrollInterfaceWorkout(AllWorkouts allWorkouts) {
        super(allWorkouts, null);
    }

    // MODIFIES: this
    // EFFECTS: places every individual workout
    @Override
    public void placeItems() {
        for (int i = 0; i < allWorkouts.getWorkouts().size(); i++) {
            DisplayWorkout displayWorkout = new DisplayWorkout(allWorkouts, allWorkouts.getWorkouts().get(i), i);
            mainPanel.add(displayWorkout.getPanel());
        }
    }

    // EFFECTS: calculate height of the main panel
    @Override
    public int calculateHeight() {
        int height = 15 + (105 * allWorkouts.getWorkouts().size());
        if (height > 550) {
            return height;
        } else {
            return 550;
        }
    }

}
