package main.solution.greedyalgorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MaxMin {

    public static int maxMin(int k, List<Integer> arr) {
        Collections.sort(arr);
        int minDiff = Integer.MAX_VALUE;

        for (int index = 0; index <= arr.size() - k; index++) {
            int diff = arr.get(index + k - 1) - arr.get(index);
            if (diff < minDiff) {
                minDiff = diff;
            }
        }
        return minDiff;
    }
}
