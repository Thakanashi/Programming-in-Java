package Zad5;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
       BubbleSortTest.class,
        CombSortTest.class,
        InsertionSortTest.class,
        SelectionSortTest.class,
        ShellSortTest.class
})
public class TestForSorting {

}