package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static telran.util.Arrays.add;
import static telran.util.Arrays.insert;
import static telran.util.Arrays.remove;
import static telran.util.Arrays.search;

public class ArraysTest {

    int[] testArray = {10, 7, 12, -4, 13, 3, 14};

    @Test
    void searchTest() {
        assertEquals(0, search(testArray, 10));
        assertEquals(4, search(testArray, 13));
        assertEquals(6, search(testArray, 14));
        assertEquals(-1, search(testArray, 100));
    }

    @Test
    void addTest() {
        int newNumber = 100;
        int[] expectedArray = {10, 7, 12, -4, 13, 3, 14, newNumber};
        assertArrayEquals(expectedArray, add(testArray, newNumber));
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
}
