package ui.panels.data.workouts;

import model.AllData;
import model.workout.AllWorkouts;
import ui.panels.data.ScrollInterface;

import javax.swing.*;

// scroll interface for workout
public class ScrollInterfaceWorkout extends ScrollInterface {

    // EFFECTS: constructs scroll interface with given allWorkouts
    public ScrollInterfaceWorkout(AllData allData, JFrame frame) {
        super(allData, frame);
    }

    // MODIFIES: this
    // EFFECTS: places every individual workout
    @Override
    public void placeItems() {
        for (int i = 0; i < allWorkouts.getWorkouts().size(); i++) {
            DisplayWorkout displayWorkout = new DisplayWorkout(allData, frame, allWorkouts.getWorkouts().get(i), i);
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
