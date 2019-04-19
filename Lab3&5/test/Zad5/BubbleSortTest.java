package Zad5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {
    @Test
    void bubbleSort() {
        int [] arr = {3,5,4,8,6,9,2,1,7};
        BubbleSort bubbleSort = new BubbleSort();
        int[] expresult = {1,2,3,4,5,6,7,8,9};
        int[] result = bubbleSort.bubbleSort(arr);
        assertArrayEquals(expresult,result);

    }
}