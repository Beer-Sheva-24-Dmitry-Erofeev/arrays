package telran.util.test;

import java.util.Comparator;

public class ComparatorInteger implements Comparator<Integer> {

    @Override
    public int compare(Integer int1, Integer int2) {
        return int1 - int2;
    }

}
