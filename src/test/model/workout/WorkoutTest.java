package model.workout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class WorkoutTest {
    private Workout testWorkout1;
    private Workout testWorkout2;
    private Workout testWorkout3;
    private Exercise exercise1;
    private Exercise exercise2;
    private Exercise exercise3;
    private long current;

    @BeforeEach
    void runBefore() {
        testWorkout1 = new Workout("2023-05-21");
        testWorkout2 = new Workout("2022-08-01");
        testWorkout3 = new Workout();
        exercise1 = new Exercise("Hamstring Extensions", 5, 100);
        exercise2 = new Exercise("Hip Thrusts", 8, 150);
        exercise3 = new Exercise("Lunges", 10, 0);
        current = System.currentTimeMillis();
    }

    @Test
    void testConstructor1() {
        assertEquals(0, testWorkout1.getExercises().size());
        assertEquals("2023-05-21", testWorkout1.getDate());
    }

    @Test
    void testConstructor2() {
        assertEquals(0, testWorkout2.getExercises().size());
        assertEquals("2022-08-01", testWorkout2.getDate());
    }

    @Test
    void testConstructor3() {
        assertEquals(0, testWorkout3.getExercises().size());
        assertEquals(new java.sql.Date(current).toString(), testWorkout3.getDate());
    }

    @Test
    void testAddExercise1() {
        testWorkout1.addExercise(exercise1);
        assertEquals(1, testWorkout1.getExercises().size());
        assertEquals(exercise1, testWorkout1.getExercises().get(0));
    }

    @Test
    void testAddExercise2() {
        addExercise(testWorkout2);
        assertEquals(3, testWorkout2.getExercises().size());
        assertEquals(exercise1, testWorkout2.getExercises().get(0));
        assertEquals(exercise2, testWorkout2.getExercises().get(1));
        assertEquals(exercise3, testWorkout2.getExercises().get(2));

    }

    @Test
    void testRemoveExercise1() {
        testWorkout1.addExercise(exercise1);
        testWorkout1.removeExercise((exercise1));
        assertEquals(0, testWorkout1.getExercises().size());
    }

    @Test
    void testRemoveExercise2() {
        addExercise(testWorkout2);
        testWorkout2.removeExercise(exercise2);
        assertEquals(2, testWorkout2.getExercises().size());
        assertEquals(exercise1, testWorkout2.getExercises().get(0));
        assertEquals(exercise3, testWorkout2.getExercises().get(1));
    }

    private void addExercise(Workout workout) {
        workout.addExercise(exercise1);
        workout.addExercise(exercise2);
        workout.addExercise(exercise3);
    }
}
