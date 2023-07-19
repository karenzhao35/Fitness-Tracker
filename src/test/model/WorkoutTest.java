package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutTest {
    private Workout testWorkout1;
    private Workout testWorkout2;
    private Exercise exercise1;
    private Exercise exercise2;
    private Exercise exercise3;

    @BeforeEach
    void runBefore() {
        testWorkout1 = new Workout("2023-05-21");
        testWorkout2 = new Workout("2022-08-01");
        exercise1 = new Exercise("Hamstring Extensions", 5, 100);
        exercise2 = new Exercise("Hip Thrusts", 8, 150);
        exercise3 = new Exercise("Lunges", 10, 0);
    }

    @Test
    void testConstructor1() {
        assertEquals(0, testWorkout1.getExercises().size());
        assertEquals("2023-05-21", testWorkout1.getDate().getDate());
    }

    @Test
    void testConstructor2() {
        assertEquals(0, testWorkout2.getExercises().size());
        assertEquals("2022-08-01", testWorkout2.getDate().getDate());
    }

    @Test
    void testAddExercise1() {
        testWorkout1.addExercise(exercise1);
        assertEquals(1, testWorkout1.getExercises().size());
        assertEquals(exercise1, testWorkout1.getExercises().get(0));
    }

    @Test
    void testAddExercise2() {
        testWorkout2.addExercise(exercise1);
        testWorkout2.addExercise(exercise2);
        testWorkout2.addExercise(exercise3);
        assertEquals(3, testWorkout2.getExercises().size());
        assertEquals(exercise1, testWorkout2.getExercises().get(0));
        assertEquals(exercise2, testWorkout2.getExercises().get(1));
        assertEquals(exercise3, testWorkout2.getExercises().get(2));

    }
}
