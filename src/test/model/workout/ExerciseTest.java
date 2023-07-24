package model.workout;

import model.workout.Exercise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciseTest {
    private Exercise exercise1;
    private Exercise exercise2;
    private Exercise exercise3;
    private ArrayList<Integer> reps;
    private ArrayList<Integer> weight;

    @BeforeEach
    void runBefore() {
        reps = new ArrayList<>();
        weight = new ArrayList<>();
        exercise1 = new Exercise("Bench Press", 10, 135);
        exercise2 = new Exercise("Leg Press", 5, 150);
        exercise3 = new Exercise("Lunges", reps, weight);
    }

    @Test
    void testConstructor1() {
        assertEquals("Bench Press", exercise1.getName());
        assertEquals(1, exercise1.getReps().size());
        assertEquals(10, exercise1.getReps().get(0));

        assertEquals(1, exercise2.getWeight().size());
        assertEquals(135, exercise1.getWeight().get(0));
    }

    @Test
    void testConstructor2() {
        assertEquals("Leg Press", exercise2.getName());
        assertEquals(1, exercise2.getReps().size());
        assertEquals(5, exercise2.getReps().get(0));

        assertEquals(1, exercise2.getWeight().size());
        assertEquals(150, exercise2.getWeight().get(0));
    }

    @Test
    void testConstructor3() {
        assertEquals("Lunges", exercise3.getName());
        assertEquals(0, exercise3.getReps().size());
        assertEquals(0, exercise3.getWeight().size());
    }

    @Test
    void testAddSet1() {
        exercise1.addSet(8, 140);
        assertEquals(2, exercise1.getReps().size());
        assertEquals(10, exercise1.getReps().get(0));
        assertEquals(8, exercise1.getReps().get(1));

        assertEquals(2, exercise1.getWeight().size());
        assertEquals(135, exercise1.getWeight().get(0));
        assertEquals(140, exercise1.getWeight().get(1));
    }

    @Test
    void testAddSet2() {
        exercise2.addSet(2, 180);
        assertEquals(2, exercise2.getReps().size());
        assertEquals(5, exercise2.getReps().get(0));
        assertEquals(2, exercise2.getReps().get(1));

        assertEquals(2, exercise2.getWeight().size());
        assertEquals(150, exercise2.getWeight().get(0));
        assertEquals(180, exercise2.getWeight().get(1));

        exercise2.addSet(9, 120);
        exercise2.addSet(2, 200);
        assertEquals(4, exercise2.getReps().size());
        assertEquals(9, exercise2.getReps().get(2));
        assertEquals(2, exercise2.getReps().get(3));

        assertEquals(4, exercise2.getWeight().size());
        assertEquals(120, exercise2.getWeight().get(2));
        assertEquals(200, exercise2.getWeight().get(3));

    }
}