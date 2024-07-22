package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer arg0, Integer arg1) {
        boolean firstEven = arg0 % 2 == 0;
        boolean secondEven = arg1 % 2 == 0;
        return firstEven ? (secondEven ? arg0.compareTo(arg1) : -1) : (secondEven ? 1 : -(arg0.compareTo(arg1)));
        // 1) Первый Ч, второй Ч - сравниваем через .compareTo() и БОЛЬШЕЕ идёт наверх.
        // 2) Первый Ч, второй НЧ - возвращаем -1, их не нужно менять местами.
        // 3) Первый НЧ второй Ч - возвращаем 1, чтобы нечётный пошёл выше.
        // 4) Первый НЧ второй НЧ - сравниваем через .compareTo() и МЕНЬШЕЕ идёт наверх.
    }

}
