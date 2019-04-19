package Zad5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    @Test
    void sort() {
        int [] arr = {3,5,4,8,6,9,2,1,7};
        InsertionSort insertionSort = new InsertionSort();
        int[] expresult = {1,2,3,4,5,6,7,8,9};
        int[] result = insertionSort.sort(arr);
        assertArrayEquals(expresult,result);
    }
}