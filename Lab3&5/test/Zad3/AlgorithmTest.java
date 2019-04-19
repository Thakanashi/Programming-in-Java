package Zad3;


import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AlgorithmTest {
    private Algorithm algorithm=new Algorithm();
        @Test
    public void LookingForCountsOfSubstringInMainString(){
            String subString = "abcd";
            String mainString = "cdabcdab";
            int result =algorithm.substring(subString,mainString);

            assertThat(result, is(3));
        }
    @Test(expected =NoSuchElementException.class)
    public void hereShouldBeThrownExceptionBecauseThereIsNoSolution(){
        String subString = "sa";
        String mainString = "srfd";
        algorithm.substring(subString,mainString);

    }

}