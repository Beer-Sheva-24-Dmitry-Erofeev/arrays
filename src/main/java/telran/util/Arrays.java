package telran.util;

import java.util.Comparator;
import java.util.function.Predicate;

public class Arrays {

    public static int search(int[] ar, int key) {
        // Method searches for a given number in the array and outputs it's index
        // Complexity of this method is O(n).
        int index = 0;
        while (index < ar.length && key != ar[index]) {
            index++;
        }
        return index == ar.length ? -1 : index;
    }

    public static int[] add(int[] ar, int number) {
        // Method adds number to the end of an existing array
        int[] res = java.util.Arrays.copyOf(ar, ar.length + 1);
        res[ar.length] = number;
        return res;
    }

    public static int[] insert(int[] ar, int index, int number) {
        // Creates new array with all elements from the given array
        // and puts the given number at the given index
        int[] res = java.util.Arrays.copyOf(ar, ar.length + 1);
        System.arraycopy(ar, index, res, index + 1, ar.length - index);
        res[index] = number;
        return res;
    }

    public static int[] remove(int[] ar, int index) {
        // Creates new array with no element in array "ar" at "index"
        int[] res = java.util.Arrays.copyOf(ar, ar.length - 1);
        System.arraycopy(ar, index + 1, res, index, res.length - index);
        return res;
    }

    private static boolean pushMaxAtEnd(int[] ar, int length) {
        boolean res = true;
        for (int i = 0; i < length; i++) {
            if (ar[i] > ar[i + 1]) {
                res = false;
                swap(ar, i, i + 1);
            }
        }
        return res;
    }

    private static void swap(int[] ar, int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }

    public static void sort(int[] ar) {
        int length = ar.length;
        boolean flSorted = false;
        while (!flSorted) {
            length--;
            flSorted = pushMaxAtEnd(ar, length);
        }
    }

    public static int binarySearch(int[] ar, int key) {
        // Algorithm of a binary search in the sorted array
        int left = 0;
        int right = ar.length - 1;
        int mid = (left + right) / 2;

        while (left <= right && ar[mid] != key) {
            if (key < ar[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }
        return left > right ? -(left + 1) : mid;
    }

    public static int[] insertSorted(int[] arSorted, int number) {
        // Insert number into the array and keep it sorted
        int index = binarySearch(arSorted, number);
        if (index < 0) {
            index = -index - 1;
        }
        return insert(arSorted, index, number);
    }

    public static boolean isOneSwap(int[] array) {
        // Return "true" if a given array has exactly one "swap" to get sorted array
        boolean res = false;
        int index1 = -1;
        int index2 = 0;
        index1 = getFirstIndex(array);
        if (index1 > -1) {
            index2 = getSecondIndex(array, index1);
            res = isOneSwapCheck(array, index1, index2);
        }
        return res;
    }

    private static boolean isOneSwapCheck(int[] array, int index1, int index2) {
        swap(array, index1, index2);
        boolean res = isArraySorted(array);
        swap(array, index2, index1); //array restored after the swap
        return res;
    }

    private static boolean isArraySorted(int[] array) {
        int index = 1;
        while (index < array.length && array[index] >= array[index - 1]) {
            index++;
        }
        return index == array.length;
    }

    private static int getSecondIndex(int[] array, int index1) {
        int index = array.length - 1;
        int lowBorder = index1 + 1;
        while (index > lowBorder && array[index] >= array[index1]) {
            index--;
        }
        return index;
    }

    private static int getFirstIndex(int[] array) {
        int index = 0;
        int limit = array.length - 1;
        while (index < limit && array[index] <= array[index + 1]) {
            index++;
        }
        return index == limit ? -1 : index;
    }

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
        int left = 0;
        int right = array.length - 1;
        int mid = (left + right) / 2;

        while (left <= right && comp.compare(key, array[mid]) != 0) {
            if (comp.compare(key, array[mid]) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }
        return left > right ? -(left + 1) : mid;
    }

    @SuppressWarnings("unchecked")
    public static <T> int binarySearch(T[] array, T key) {
        // Binary search with no custom comparator
        return binarySearch(array, key, (Comparator<T>) Comparator.naturalOrder());
    }

    public static <T> T[] insert(T[] array, int index, T item) {
        // Method inserts an object (item) into a generic array of objects
        T[] res = java.util.Arrays.copyOf(array, array.length + 1);
        System.arraycopy(array, index, res, index + 1, array.length - index);
        res[index] = item;
        return res;
    }

    public static <T> T[] find(T[] array, Predicate<T> predicate) {
        // Method finds object's in an array using the given predicate
        // and returns all of them in an array
        T[] result = java.util.Arrays.copyOf(array, 0);
        for (int i = 0; i < array.length; i++) {
            if (predicate.test(array[i])) {
                result = insert(result, result.length, array[i]);
            }
        }
        return result;
    }

    public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
        // Method removes all the object's (items) from the array matching the given predicate
        return find(array, predicate.negate());
        // .negate() инвертирует результат предиката
    }

}
