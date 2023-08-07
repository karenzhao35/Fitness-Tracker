package ui.panels.data.workouts;

import model.workout.AllWorkouts;
import ui.panels.data.ScrollInterface;
import ui.panels.data.workouts.DisplayWorkout;

public class ScrollInterfaceWorkout extends ScrollInterface {

    public ScrollInterfaceWorkout(AllWorkouts allWorkouts) {
        super(allWorkouts, null);
    }

    @Override
    public void placeItems() {
        for (int i = 0; i < allWorkouts.getWorkouts().size(); i++) {
            DisplayWorkout displayWorkout = new DisplayWorkout(allWorkouts, allWorkouts.getWorkouts().get(i), i);
            mainPanel.add(displayWorkout.getPanel());
        }
    }

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
