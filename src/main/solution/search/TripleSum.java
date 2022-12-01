package main.solution.search;

import java.util.*;

public class TripleSum {

    static long triplets(int[] a, int[] b, int[] c) {
        long count = 0;

        // Convert a,b, c to list with distinct elements and sort them
        List<Integer> aList = Arrays.stream(a).sorted().distinct().boxed().toList();
        List<Integer> bList = Arrays.stream(b).sorted().distinct().boxed().toList();
        List<Integer> cList = Arrays.stream(c).sorted().distinct().boxed().toList();

        int aIndex = 0;
        int cIndex = 0;

        for (Integer number : bList) {
            // Step 1:  Find all numbers lesser than current number in aList
            while (aIndex < aList.size() && aList.get(aIndex) <= number) {
                aIndex++;
            }
            // Step 2: Find all numbers lesser than current number in cList
            while (cIndex < cList.size() && cList.get(cIndex) <= number) {
                cIndex++;
            }
            /* Step 3: Multiply and sum, casting to long as an overflow occurs when only multiplying as int.
            * For a basic overview, int can hold upto 10 digits, for more we need to use long
            * But when you multiply two int numbers with 10 digits each (e.g. 1453339084), integer multiplication occurs
            * ,and you get the wrong answer. So casting each as int will perform long multiplication.
            * */
            count += (long)aIndex * (long)cIndex;
        }

        return count;

    }
}
