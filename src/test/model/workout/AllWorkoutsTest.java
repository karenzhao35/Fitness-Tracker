package model.workout;

import model.exceptions.DoesNotExist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AllWorkoutsTest {
    private AllWorkouts testWorkouts;
    private Workout workout1;
    private Workout workout2;
    private Workout workout3;
    private Workout workout4;
    private Exercise exercise1;
    private Exercise exercise2;


    @BeforeEach
    void runBefore() {
        testWorkouts = new AllWorkouts();
        workout1 = new Workout("2019-02-10");
        workout2 = new Workout("2021-09-18");
        workout3 = new Workout();
        workout4 = new Workout("2019-02-10");
        exercise1 = new Exercise("Bench", 10, 155);
        exercise2 = new Exercise("Curls", 9, 50);
        workout1.addExercise(exercise1);
        workout4.addExercise(exercise2);
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
    void testAddWorkoutsAlreadyExists() {
        testWorkouts.addWorkout(workout1);
        testWorkouts.addWorkout(workout4);
        assertEquals(1, testWorkouts.getWorkouts().size());
        assertEquals(2, testWorkouts.getWorkouts().get(0).getExercises().size());
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

    @Test
    void testTrueWorkoutTodayOne() {
        testWorkouts.addWorkout(workout3);
        assertTrue(testWorkouts.today());
    }

    @Test
    void testTrueWorkoutToday() {
        addAllWorkouts();
        assertTrue(testWorkouts.today());
    }

    @Test
    void testEmptyWorkoutToday() {
        assertFalse(testWorkouts.today());
    }

    @Test
    void testFalseWorkoutToday() {
        testWorkouts.addWorkout(workout1);
        testWorkouts.addWorkout(workout2);
        assertFalse(testWorkouts.today());
    }


    @Test
    void testRetrieveWorkoutsEmpty() {
        try {
            testWorkouts.retrieveWorkout("2018-08-25");
            fail("Not expecting workout to be retrieved!");
        } catch (DoesNotExist e) {
            System.out.println("Great");
        }
    }

    @Test
    void testRetrieveWorkouts() {
        addAllWorkouts();
        try {
            assertEquals(workout1, testWorkouts.retrieveWorkout(workout1.getDate()));
            assertEquals(workout3, testWorkouts.retrieveWorkout(workout3.getDate()));
        } catch (DoesNotExist e) {
            System.out.println("Not expecting DoesNotExist exception!");
        }
    }

    @Test
    void testRetrieveWorkoutsNotThere() {
        addAllWorkouts();
        try {
            testWorkouts.retrieveWorkout("1978-07-31");
            fail("Not expecting workout to be retrieved!");
        } catch (DoesNotExist e) {
            System.out.println("Great");
        }
    }

    private void addAllWorkouts() {
        testWorkouts.addWorkout(workout1);
        testWorkouts.addWorkout(workout2);
        testWorkouts.addWorkout(workout3);
    }

}
