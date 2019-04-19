package Zad4;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;


public class AlgorithmTest {

    private Algorithm algorithm = new Algorithm();
    float tab[] = {2.1f,4.5f,5.7f};


    public Algorithm getAlgorithm() {
        return algorithm;
    }

    @Test
    public void solution() {

        float target = 6.6f;
        int res[] = {0,1};
        assertArrayEquals(res,algorithm.solution(tab,target));
    }

    @Test(expected = NoSuchElementException.class)
    public void hereShouldBeThrownExceptionBecauseThereIsNoSolution(){
        algorithm.solution(tab,2.9643f);
    }


}