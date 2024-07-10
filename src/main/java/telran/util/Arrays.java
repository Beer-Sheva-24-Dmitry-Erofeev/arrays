package telran.util;

public class Arrays {

    // Method searches for a given number in the array and outputs it's index
    public static int search(int[] ar, int key) {
        int index = 0;
        while (index < ar.length && key != ar[index]) {
            index++;
        }
        return index == ar.length ? -1 : index;
    }

    // Method adds number to the end of an existing array
    public static int[] add(int[] ar, int number) {
        int[] result = java.util.Arrays.copyOf(ar, ar.length + 1);
        result[ar.length] = number;
        return result;
    }

    //creates new array with all elements from the given "ar" and
    //the given "number" at the given index
    public static int[] insert(int[] ar, int index, int number) {
        int[] result = java.util.Arrays.copyOf(ar, ar.length + 1);
        System.arraycopy(ar, index, result, index + 1, ar.length - index);
        result[index] = number;
        return result;
    }

    //Creates new array with no element in array "ar" at "index"
    public static int[] remove(int[] ar, int index) {
        int[] result = java.util.Arrays.copyOf(ar, ar.length - 1);
        System.arraycopy(ar, index + 1, result, index, result.length - index);
        return result;
    }
}
