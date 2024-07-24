package telran.util.test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import static telran.util.Arrays.add;
import static telran.util.Arrays.binarySearch;
import static telran.util.Arrays.find;
import static telran.util.Arrays.insert;
import static telran.util.Arrays.insertSorted;
import static telran.util.Arrays.isOneSwap;
import static telran.util.Arrays.matchesRules;
import static telran.util.Arrays.remove;
import static telran.util.Arrays.removeIf;
import static telran.util.Arrays.search;
import static telran.util.Arrays.sort;
import telran.util.CharacterRule;

public class ArraysTest {

    private static final int N_ELEMENTS = 20;
    int[] testArray = {10, 7, 12, -4, 13, 3, 14};

    @Test
    void searchTest() {
        assertEquals(0, search(testArray, 10));
        assertEquals(4, search(testArray, 13));
        assertEquals(6, search(testArray, 14));
        assertEquals(-1, search(testArray, 100));
    }

    @Test
    // testArray = {10, 7, 12, -4, 13, 3, 14}
    void addTest() {
        int newNumber = 100;
        int[] expectedArray = {10, 7, 12, -4, 13, 3, 14, newNumber};
        assertArrayEquals(expectedArray, add(testArray, newNumber));
    }

    @Test
    // testArray = {10, 7, 12, -4, 13, 3, 14}
    void insertTest() {
        int newNumber = 69;
        int[] expected_0 = {newNumber, 10, 7, 12, -4, 13, 3, 14};
        int[] expected_3 = {10, 7, 12, newNumber, -4, 13, 3, 14};
        int[] expected_last = {10, 7, 12, -4, 13, 3, 14, newNumber};
        assertArrayEquals(expected_0, insert(testArray, 0, newNumber));
        assertArrayEquals(expected_3, insert(testArray, 3, newNumber));
        assertArrayEquals(expected_last, insert(testArray, testArray.length, newNumber));
        // Здесь проверка на вылет за границы массива. Почему даёт предупреждение "Throwable method result is ignored"?
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> insert(testArray, testArray.length + 1, newNumber));
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> insert(testArray, -1, newNumber));
    }

    @Test
    // testArray = {10, 7, 12, -4, 13, 3, 14}
    void removeTest() {
        int[] expected_0 = {7, 12, -4, 13, 3, 14};
        int[] expected_3 = {10, 7, 12, 13, 3, 14};
        int[] expected_last = {10, 7, 12, -4, 13, 3};
        assertArrayEquals(expected_0, remove(testArray, 0));
        assertArrayEquals(expected_3, remove(testArray, 3));
        assertArrayEquals(expected_last, remove(testArray, testArray.length - 1));
        // Здесь проверка на вылет за границы массива. Почему даёт предупреждение "Throwable method result is ignored"?
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> remove(testArray, testArray.length));
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> remove(testArray, -1));
    }

    @Test
    // testArray = {10, 7, 12, -4, 13, 3, 14}
    void sortTest() {
        int[] testNumbers = java.util.Arrays.copyOf(testArray, testArray.length);
        int[] expected = {-4, 3, 7, 10, 12, 13, 14};
        sort(testNumbers);
        assertArrayEquals(expected, testNumbers);
    }

    @Test
    // Here we are testing "sort" with a random array
    void sortTestRandomArray() {
        int[] array = getRandomArray(N_ELEMENTS);
        int limit = array.length - 1;
        sort(array);
        for (int i = 0; i < limit; i++) {
            assertTrue(array[i] <= array[i + 1]);
        }
    }

    private int[] getRandomArray(int nElements) {
        // Here we creating a random array
        int[] res = new int[nElements];
        Random random = new Random();
        for (int i = 0; i < nElements; i++) {
            res[i] = random.nextInt();
        }
        return res;
    }

    @Test
    void binarySearchTest() {

        int Index_0 = -90;
        int Index_10 = 101;
        int Index_8 = 69;
        int Index_11_Wrong = 999;
        int Index_0_Wrong = -91;
        int Index_6_Wrong = 50;
        int[] testAr = {-90, -87, -6, 3, 32, 47, 55, 56, 69, 70, 101};

        assertEquals(0, binarySearch(testAr, Index_0));
        assertEquals(10, binarySearch(testAr, Index_10));
        assertEquals(8, binarySearch(testAr, Index_8));
        assertEquals(-12, binarySearch(testAr, Index_11_Wrong));
        assertEquals(-1, binarySearch(testAr, Index_0_Wrong));
        assertEquals(-7, binarySearch(testAr, Index_6_Wrong));
    }

    @Test
    void insertSortedTest() {
        int[] expected = {5, 10, 10, 20, 25, 30, 40, 50, 55};
        int[] insertedNumbers = {10, 55, 5, 25};
        int[] actual = {10, 20, 30, 40, 50};
        for (int i = 0; i < insertedNumbers.length; i++) {
            actual = insertSorted(actual, insertedNumbers[i]);
        }
        assertArrayEquals(expected, actual);
    }

    @Test
    void isOneSwapTest() {

        int[] arTrue1 = {1, 2, 10, 4, 7, 3};
        int[] arTrue2 = {1, 2, 10, 4, 4, 20};
        int[] arTrue3 = {1, 2, 10, 4, 20, 30};
        int[] arTrue4 = {10, 2, 1, 10, 20, 30};
        int[] arFalse1 = {20, 3, 3, 10, 20, 30};
        int[] arFalse2 = {1, 2, 10, 4, 7, 5};
        int[] arFalse3 = {1, 2, 3, 4, 5, 10};
        int[][] arraysTrue = {arTrue1, arTrue2, arTrue3, arTrue4};
        int[][] arraysFalse = {arFalse1, arFalse2, arFalse3};

        for (int i = 0; i < arraysTrue.length; i++) {
            assertTrue(isOneSwap(arraysTrue[i]), "" + (i + 1));
        }
        for (int i = 0; i < arraysFalse.length; i++) {
            assertFalse(isOneSwap(arraysFalse[i]), "" + (i + 1));
        }
    }

    @Test
    void sortAnyTypeTest() {
        String[] testStrings = {"lmn", "cfta", "w", "aa"};
        String[] expectedASCII = {"aa", "cfta", "lmn", "w"};
        String[] expectedLength = {"w", "aa", "lmn", "cfta"};
        sort(testStrings, (a, b) -> a.compareTo(b));
        assertArrayEquals(expectedASCII, testStrings);
        sort(testStrings, (a, b) -> Integer.compare(a.length(), b.length()));
        assertArrayEquals(expectedLength, testStrings);
    }

    @Test
    void binarySearchObjectTest() {
        String[] testASCII = {"aa", "cfta", "lmn", "w"};
        assertEquals(0, binarySearch(testASCII, "aa", new ComparatorASCII()));
        assertEquals(3, binarySearch(testASCII, "w", new ComparatorASCII()));
        assertEquals(1, binarySearch(testASCII, "cfta", new ComparatorASCII()));
        assertEquals(-5, binarySearch(testASCII, "z", new ComparatorASCII()));

        String[] testLength = {"w", "aa", "lmn", "cfta"};
        assertEquals(0, binarySearch(testLength, "y", new ComparatorLength()));
        assertEquals(3, binarySearch(testLength, "    ", new ComparatorLength()));
        assertEquals(1, binarySearch(testLength, "xx", new ComparatorLength()));
        assertEquals(2, binarySearch(testLength, "zzz", new ComparatorLength()));
        assertEquals(-5, binarySearch(testLength, "     ", new ComparatorLength()));

        Integer[] testIntegers = {-7, -4, 0, 1, 1, 5, 69};
        assertEquals(0, binarySearch(testIntegers, -7, Integer::compare));
        assertEquals(6, binarySearch(testIntegers, 69, new ComparatorInteger()));
        assertEquals(2, binarySearch(testIntegers, 0, new ComparatorInteger()));
        assertEquals(-6, binarySearch(testIntegers, 2, new ComparatorInteger()));
    }

    @Test
    void findTest() {
        Integer[] array = {7, -8, 10, -100, 13, -10, 99};
        Integer[] expected = {7, 13, 99};
        assertArrayEquals(expected, find(array, n -> n % 2 != 0));
    }

    @Test
    void binarySearchNoComparatorTest() {
        String[] testASCII = {"aa", "cfta", "lmn", "w"};
        Person prs1 = new Person(10, "Vasia");
        Person prs2 = new Person(20, "Itay");
        Person prs3 = new Person(30, "Sara");
        Person[] persons = {
            prs1, prs2, prs3
        };
        assertEquals(1, binarySearch(testASCII, "cfta"));
        assertEquals(-2, binarySearch(testASCII, "aaa"));
        assertEquals(0, binarySearch(persons, prs1));
        assertEquals(1, binarySearch(persons, prs2));
        assertEquals(2, binarySearch(persons, prs3));
        assertEquals(-1, binarySearch(persons, new Person(5, "Serg")));
    }

    @Test
    // Здесь мы тестируем компаратор EvenOdd в методе sort
    void evenOddSortingTest() {
        Integer[] array = {7, -8, 10, -100, 13, -10, 99};
        Integer[] expected = {-100, -10, -8, 10, 99, 13, 7};
        sort(array, (arg0, arg1) -> {
            boolean firstEven = arg0 % 2 == 0;
            boolean secondEven = arg1 % 2 == 0;
            return firstEven ? (secondEven ? arg0.compareTo(arg1) : -1) : (secondEven ? 1 : -(arg0.compareTo(arg1)));
        });
        assertArrayEquals(expected, array);
    }

    @Test
    void removeIfTest() {
        Integer[] array = {7, -8, 10, -100, 13, -10, 99};
        Integer[] expected = {-8, 10, -100, -10};
        assertArrayEquals(expected, removeIf(array, n -> n % 2 != 0));
    }

    @Test
    void matchesRulesTest() {
        CharacterRule rule1_ok = new CharacterRule(
                true,
                a -> Character.isUpperCase(a),
                "at least one capital letter");
        CharacterRule rule2_ok = new CharacterRule(
                true,
                a -> Character.isLowerCase(a),
                "at least one lowercase letter");
        CharacterRule rule3_ok = new CharacterRule(
                true,
                a -> Character.isDigit(a),
                "at least one digit");
        CharacterRule rule4_ok = new CharacterRule(
                true,
                a -> a == '.',
                "at least one dot");
        CharacterRule[] rulesOk = {rule1_ok, rule2_ok, rule3_ok, rule4_ok};

        CharacterRule rule1_notOk = new CharacterRule(
                false,
                a -> Character.isWhitespace(a),
                "spaces are disallowed");

        CharacterRule[] rulesNotOk = {rule1_notOk};

        String fine = "";
        char[] match1 = {'a', 'n', '*', 'G', '.', '.', '1'};
        assertEquals(fine, matchesRules(match1, rulesOk, rulesNotOk));

        String noSpace = "spaces are disallowed. ";
        char[] miss1_noSpace = {'a', 'n', '*', 'G', '.', '.', '1', ' '};
        assertEquals(noSpace, matchesRules(miss1_noSpace, rulesOk, rulesNotOk));

        String noCapital = "at least one capital letter. ";
        char[] miss2_noCapital = {'a', 'n', '*', '.', '.', '1'};
        assertEquals(noCapital, matchesRules(miss2_noCapital, rulesOk, rulesNotOk));

        String noDigit = "at least one digit. ";
        char[] miss3_noDigit = {'a', 'n', '*', 'G', '.', '.'};
        assertEquals(noDigit, matchesRules(miss3_noDigit, rulesOk, rulesNotOk));

        String noDot = "at least one dot. ";
        char[] miss4_noDot = {'a', 'n', '*', 'G', 'u', '1', '1'};
        assertEquals(noDot, matchesRules(miss4_noDot, rulesOk, rulesNotOk));

        String noSpaceNoCapital = "at least one capital letter. spaces are disallowed. ";
        char[] miss5_noSpaceNoCapital = {'a', ' ', 'n', '*', '.', '.', '1'};
        assertEquals(noSpaceNoCapital, matchesRules(miss5_noSpaceNoCapital, rulesOk, rulesNotOk));

        String noDigitNoDot = "at least one digit. at least one dot. ";
        char[] miss6_noDigitNoDot = {'a', 'n', '*', 'G', 't', 'g'};
        assertEquals(noDigitNoDot, matchesRules(miss6_noDigitNoDot, rulesOk, rulesNotOk));

        String balagan = "at least one capital letter. at least one lowercase letter. at least one digit. at least one dot. spaces are disallowed. ";
        char[] miss7_balagan = {'*', '*', '*', ' ', '*', '*', '*'};
        assertEquals(balagan, matchesRules(miss7_balagan, rulesOk, rulesNotOk));
    }
}
