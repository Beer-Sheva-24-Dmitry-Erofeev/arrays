package telran.util;

import java.util.Comparator;
import java.util.function.Predicate;

public class Arrays {

    // Method searches for a given number in the array and outputs it's index
    // Complexity of this method is O(n).
    public static int searchLinear(int[] ar, int numberToFind) {
        int index = 0;
        while (index < ar.length && numberToFind != ar[index]) {
            index++;
        }
        return index == ar.length ? -1 : index;
    }

    // Method adds number to the end of an existing array
    public static int[] addElementToTheEnd(int[] ar, int number) {
        int[] result = java.util.Arrays.copyOf(ar, ar.length + 1);
        result[ar.length] = number;
        return result;
    }

    //creates new array with all elements from the given "ar" and
    //puts the given number at the given index
    public static int[] insert(int[] ar, int index, int number) {
        int[] result = java.util.Arrays.copyOf(ar, ar.length + 1);
        System.arraycopy(ar, index, result, index + 1, ar.length - index);
        result[index] = number;
        return result;
    }

    // Creates new array with no element in array "ar" at "index"
    public static int[] remove(int[] ar, int index) {
        int[] result = java.util.Arrays.copyOf(ar, ar.length - 1);
        System.arraycopy(ar, index + 1, result, index, result.length - index);
        return result;
    }

    // This private method is pushing a larger element of an array to the top.
    private static boolean pushLargerElementUp(int[] array, int length) {

        boolean result = true;

        for (int i = 1; i < length; i++) {
            if (array[i] < array[i - 1]) {
                result = false;
                swapElements(array, i, i - 1);
            }
        }
        return result;
    }

    // This private method swaps the 2 given elements in array
    private static void swapElements(int[] array, int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    // This is a method of "bubble sorting" an array
    public static void sortBubble(int[] array) {

        int length = array.length;
        boolean arraySorted = false;

        while (!arraySorted) {
            length--;
            arraySorted = pushLargerElementUp(array, length);
        }
    }

    // Algorithm of a binary search in an array sorted from left to right.
    public static int searchBinary(int[] arraySorted, int elementToFind) {

        int startPoint = 0;
        int endPoint = arraySorted.length - 1;
        int middlePoint;

        while (startPoint <= endPoint) {

            middlePoint = startPoint + (endPoint - startPoint) / 2;

            if (arraySorted[middlePoint] == elementToFind) {
                return middlePoint;

            } else if (arraySorted[middlePoint] > elementToFind) {
                endPoint = middlePoint - 1;

            } else {
                startPoint = middlePoint + 1;
            }
        }

        return -1;
    }

    // Sorted array on input.
    public static int[] insertAndKeepSorted(int[] inputArraySorted, int numberToInsert) {

        // Creating a new array with added space for a new element
        int[] resultingArray = new int[inputArraySorted.length + 1];

        // Filling the new array until we find a place for our new number
        int i = 0;
        while (i < inputArraySorted.length && inputArraySorted[i] < numberToInsert) {
            resultingArray[i] = inputArraySorted[i];
            i++;
        }

        // Inserting the new number
        resultingArray[i] = numberToInsert;

        // Filling up the rest of the new array
        System.arraycopy(inputArraySorted, i, resultingArray, i + 1, inputArraySorted.length - i);

        return resultingArray;
    }

    /*
    public static boolean isOneSwapToSorted(int[] array) {
        boolean result = false;
        int amountOfBumps = 0;

        // Here we are counting the amount of "bumps" in the array.
        // If it's less than 3, it is possible for this array to be 1 swap away from being sorted.
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[i + 1]) {
                amountOfBumps += 1;
            }
        }
        // List of actions if the amount of "bumps" is 1.
        if (amountOfBumps == 1) {
            for (int i = 0; i < array.length; i++) {
                // If the elements are in the corners.
                if ((i == 0 || i == array.length - 1) && array[i] > array[i + 1]) {
                    result = true;
                } // If the elements somewhere in the middle and it's not a specific case, like:
                // {..., 6, 7, 4, 5, ...} for example.
                else {
                    result = !(array[i] > array[i + 1] && array[i - 1] >= array[i + 2]);
                }
            }
            if (amountOfBumps == 2) {
                for (int i = 0; i < array.length; i++) {
                    result = false;
                }
            }
            // If the amount of bumps is equal or more than 3, then this is not the array we are looking for.
            if (amountOfBumps >= 3) {
                result = false;
            }

        }
        return result;

    }
     */
    public static <T> void sort(T[] array, Comparator<T> comparator) {
        int length = array.length;
        boolean flSorted = false;
        do {
            length--;
            flSorted = true;
            for (int i = 0; i < length; i++) {
                if (comparator.compare(array[i], array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                    flSorted = false;
                }
            }
        } while (!flSorted);
    }

    private static <T> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static <T> int binarySearch(T[] array, T key, Comparator<T> comp) {

        int start = 0;
        int end = array.length - 1;
        int mid = (start + end) / 2;

        while (start <= end && comp.compare(key, array[mid]) != 0) {
            if (comp.compare(key, array[mid]) < 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            mid = (start + end) / 2;
        }
        return start > end ? -(start + 1) : mid;
    }

    public static <T> int binarySearch(T[] array, T key) {
        return binarySearch(array, key, (Comparator<T>) Comparator.naturalOrder());
    }

    // Method inserts an object (item) into a generic array of objects
    public static <T> T[] insert(T[] array, int index, T item) {
        T[] res = java.util.Arrays.copyOf(array, array.length + 1);
        System.arraycopy(array, index, res, index + 1, array.length - index);
        res[index] = item;
        return res;
    }

    // Method finds object's in an array using the given predicate
    // and returns all of them in an array
    public static <T> T[] find(T[] array, Predicate<T> predicate) {
        T[] result = java.util.Arrays.copyOf(array, 0);
        for (int i = 0; i < array.length; i++) {
            if (predicate.test(array[i])) {
                result = insert(result, result.length, array[i]);
            }
        }
        return result;
    }

    // Method removes all the object's (items) from the array matching the given predicate
    public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
        return find(array, predicate.negate());
        // .negate() инвертирует результат предиката
    }
}
