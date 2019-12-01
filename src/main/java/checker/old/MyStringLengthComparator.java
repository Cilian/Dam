package checker.old;

import java.util.Comparator;

public class MyStringLengthComparator<T> implements Comparator {
    public MyStringLengthComparator(){

    }

    @Override
    public int compare(Object o1, Object o2) {
        String st1 = (String) o1;
        String st2 = (String) o2;
        return st2.length() - st1.length();
    }
}
