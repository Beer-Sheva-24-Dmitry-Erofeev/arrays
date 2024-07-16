package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import static telran.util.Arrays.addElementToTheEnd;
import static telran.util.Arrays.insert;
import static telran.util.Arrays.insertAndKeepSorted;
import static telran.util.Arrays.isOneSwapToSorted;
import static telran.util.Arrays.remove;
import static telran.util.Arrays.searchBinary;
import static telran.util.Arrays.searchLinear;
import static telran.util.Arrays.sortBubble;

;

public class ArraysTest {

    int[] testArray = {10, 7, 12, -4, 13, 3, 14};
    //int[] testArray2 = {10, 7, 12, -4, 13, 3, 14};

    @Test
    void searchLinearTest() {
        assertEquals(0, searchLinear(testArray, 10));
        assertEquals(4, searchLinear(testArray, 13));
        assertEquals(6, searchLinear(testArray, 14));
        assertEquals(-1, searchLinear(testArray, 100));
    }

    @Test
    void addElementToTheEndTest() {
        int newNumber = 100;
        int[] expectedArray = {10, 7, 12, -4, 13, 3, 14, newNumber};
        assertArrayEquals(expectedArray, addElementToTheEnd(testArray, newNumber));
    }

    @Test
    void insertTest() {
        int newNumber = 69;
        int newNumberIndex = 5;
        int newNumberIndex2 = 2;
        int[] expectedArray = {10, 7, 12, -4, 13, 69, 3, 14};
        int[] expectedArray2 = {10, 7, 69, 12, -4, 13, 3, 14};
        assertArrayEquals(expectedArray, insert(testArray, newNumberIndex, newNumber));
        assertArrayEquals(expectedArray2, insert(testArray, newNumberIndex2, newNumber));
    }

    //В тестах лучше проверять начало, середину и конец. Доработать.
    @Test
    void removeTest() {
        int removeInIndex = 3;
        int removeInIndex2 = 2;
        int removeInIndex3 = 0;
        int[] expectedArray = {10, 7, 12, 13, 3, 14};
        int[] expectedArray2 = {10, 7, -4, 13, 3, 14};
        int[] expectedArray3 = {7, 12, -4, 13, 3, 14};
        assertArrayEquals(expectedArray, remove(testArray, removeInIndex));
        assertArrayEquals(expectedArray2, remove(testArray, removeInIndex2));
        assertArrayEquals(expectedArray3, remove(testArray, removeInIndex3));
    }

    @Test
    void sortBubbleTest() {
        int[] testArrayBubble = {10, 7, 12, -4, 13, 3, 14};
        int[] expectedArray = {-4, 3, 7, 10, 12, 13, 14};
        sortBubble(testArrayBubble);
        assertArrayEquals(expectedArray, testArrayBubble);
    }

    @Test
    void searchBinaryTest() {

        int lookingForIndex_0 = -90;
        int lookingForIndex_10 = 101;
        int lookingForIndex_8 = 69;
        int lookingForWrongNumber = 999;
        int[] testArrayBinary = {-90, -87, -6, 3, 32, 47, 55, 56, 69, 70, 101};

        assertEquals(0, searchBinary(testArrayBinary, lookingForIndex_0));
        assertEquals(10, searchBinary(testArrayBinary, lookingForIndex_10));
        assertEquals(8, searchBinary(testArrayBinary, lookingForIndex_8));
        assertEquals(-1, searchBinary(testArrayBinary, lookingForWrongNumber));

    }

    @Test
    void insertAndKeepSortedTest() {

        int numberToInsert = 4;
        int[] testArrayInsert = {-90, -87, -6, 3, 32, 47, 55, 56, 69, 70, 101};
        int[] expectedArray = {-90, -87, -6, 3, 4, 32, 47, 55, 56, 69, 70, 101};

        assertArrayEquals(expectedArray, insertAndKeepSorted(testArrayInsert, numberToInsert));
    }

    @Test
    // {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    void isOneSwapToSortedTest() {
        // First two swapped, 1 bump
        int[] testArrayTrue1 = {2, 1, 3, 4, 5, 6, 7, 8, 9, 10};
        assertTrue(isOneSwapToSorted(testArrayTrue1));
        // 1 bump first, special case
        int[] testArrayFalse1 = {2, 3, 1, 4, 5, 6, 7, 8, 9, 10};
        assertFalse(isOneSwapToSorted(testArrayFalse1));
        // Last two swapped, 1 bump
        int[] testArrayTrue2 = {1, 2, 3, 4, 5, 6, 7, 8, 10, 9};
        assertTrue(isOneSwapToSorted(testArrayTrue2));
        // 1 bump last, special case
        int[] testArrayFalse2 = {1, 2, 3, 4, 5, 6, 7, 10, 8, 9};
        assertFalse(isOneSwapToSorted(testArrayFalse2));
        // Two neighbours swapped, 1 bump
        int[] testArrayTrue3 = {1, 2, 3, 4, 6, 5, 7, 8, 9, 10};
        assertTrue(isOneSwapToSorted(testArrayTrue3));
        //TODO
    }
}
