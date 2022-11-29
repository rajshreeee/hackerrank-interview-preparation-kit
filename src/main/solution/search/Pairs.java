package main.solution.search;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Pairs {


    /*
    * For each number in the array, add k to find what is its complementary pair.
    * Then search for it in the hashset.
    * The array is sorted so that we only have to move in a unidirectional way
    * */
    public static int pairs(int k, List<Integer> arr) {
        HashSet<Integer> set = new HashSet<>(arr);
        Collections.sort(arr);
        int count = 0;
        for (Integer number : arr) {
            int complementaryNumber = number + k;
            if (set.contains(complementaryNumber)) {
                count++;
            }
        }
        return count;
    }
}
