package checker.old;

import java.util.ArrayList;
import java.util.Collections;

public class SortTestMain {
    public static void main(String[] args)
    {
        MyStringLengthComparator comparator = new MyStringLengthComparator();
        ArrayList<String > list = new ArrayList<String >();
        list.add("23,32");
        list.add("23,32,43");
        list.add("24,35");
        list.add("21,32,43,54");
        list.sort(comparator);
        System.out.println(list);
    }
}
