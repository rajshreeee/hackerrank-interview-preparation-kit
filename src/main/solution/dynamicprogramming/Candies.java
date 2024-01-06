package main.solution.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

class Candies {


    /*
    * Basic logic:
    * Traverse the list from beginning to end giving out 1 more candy to student with higher score.
    * Traverse again in reverse order from beginning to end giving out 1 more candy to student with higher score.
    *
    * Catch: Use long instead of int
    * */
    public static long candies(int n, List<Integer> arr) {
        // Write your code here
        var sweets = new long[n];
        Arrays.fill(sweets, 1);

        for (var i = 1; i < n; i++) {
            if (arr.get(i) > arr.get(i - 1)) {
                sweets[i] = sweets[i-1] + 1;
            }
        }

        for (var i = n - 2; i >= 0; i--) {
            if (arr.get(i) > arr.get(i + 1) && sweets[i] <= sweets[i+1]) {
                sweets[i] = sweets[i + 1] + 1;
            }
        }

        return Arrays.stream(sweets).sum();
    }

}
