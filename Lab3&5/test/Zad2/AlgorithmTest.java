package Zad2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AlgorithmTest {
    private List<Integer> listOfIntegers;
    private Algorithm algorithm;

    @Before
    public void setUp() {
        listOfIntegers = new ArrayList<>();
        algorithm=new Algorithm();
    }

    @Test(expected =IllegalArgumentException.class)
    public void hereShouldBeThrownExceptionBecauseThisValueisTooBig(){
        listOfIntegers=algorithm.randomList(100001);
    }
    @Test
    public void hereShouldBeReturnTheSmallestValueWhichIsNotInList() {
        listOfIntegers.add(1);
        listOfIntegers.add(3);
        listOfIntegers.add(2);
        listOfIntegers.add(4);
        listOfIntegers.add(6);

        int result = algorithm.solution(listOfIntegers);
        assertEquals(5, result);
    }
}