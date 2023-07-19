package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllWorkoutsTest {
    private AllWorkouts testWorkouts;
    private Workout workout1;
    private Workout workout2;
    private Workout workout3;

    @BeforeEach
    void runBefore() {
        testWorkouts = new AllWorkouts();
        workout1 = new Workout("2019-02-10");
        workout2 = new Workout("2021-09-18");
        workout3 = new Workout("2022-11-15");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testWorkouts.getWorkouts().size());
    }

    @Test
    void testAddOneWorkouts() {
        testWorkouts.addWorkout(workout1);
        assertEquals(1, testWorkouts.getWorkouts().size());
        assertEquals(workout1, testWorkouts.getWorkouts().get(0));
    }

    @Test
    void testAddThreeWorkouts() {
        testWorkouts.addWorkout(workout1);
        testWorkouts.addWorkout(workout2);
        assertEquals(2, testWorkouts.getWorkouts().size());
        assertEquals(workout2, testWorkouts.getWorkouts().get(1));
        testWorkouts.addWorkout(workout3);
        assertEquals(3, testWorkouts.getWorkouts().size());
        assertEquals(workout3, testWorkouts.getWorkouts().get(2));
    }

    @Test
    void testRemoveOneWorkout() {
        testWorkouts.addWorkout(workout1);
        testWorkouts.addWorkout(workout2);
        testWorkouts.addWorkout(workout3);
        testWorkouts.removeWorkout(workout2);
        assertEquals(2, testWorkouts.getWorkouts().size());
        assertEquals(workout1, testWorkouts.getWorkouts().get(0));
        assertEquals(workout3, testWorkouts.getWorkouts().get(1));
    }

    @Test
    void testRemoveThreeWorkout() {
        testWorkouts.addWorkout(workout1);
        testWorkouts.addWorkout(workout2);
        testWorkouts.addWorkout(workout3);
        testWorkouts.removeWorkout(workout1);
        testWorkouts.removeWorkout(workout2);
        testWorkouts.removeWorkout(workout3);
        assertEquals(0, testWorkouts.getWorkouts().size());
    }
}
