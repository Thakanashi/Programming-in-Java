package Zad5;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CombSortTest {

    public CombSort combSort = new CombSort();
    @Test
    void getNextGap() {
            assertEquals(1,combSort.getNextGap(-4)); //test ujemnej liczby
            assertEquals(1,combSort.getNextGap(0)); // test 0
            int tmp = 5;
            tmp = (tmp*10)/13;
            assertEquals(tmp,combSort.getNextGap(5));   //test liczby większej od 0


    }

    @Test
    void sort() {
        int [] arr = {3,5,4,8,6,9,2,1,7};           //test sortowanie na przykładzie tablicy
        int[] expresult = {1,2,3,4,5,6,7,8,9};
        int[] result = combSort.sort(arr);
        assertArrayEquals(expresult,result);
    }
}