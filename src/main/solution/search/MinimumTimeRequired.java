package main.solution.search;

import java.util.Arrays;

public class MinimumTimeRequired {

    /*
    * Explanation:
    * Wouldn't it be great if we had the number of products produced for each day so, we could select the MINIMUM day
    * which would produce our goal count?
    * But we don't. So, do we go ahead and start calculating the number of products produced each day?
    * No, because time complexity would suffer.
    * Instead, we use binary search.
    *
    * The basic logic is that we set a minimum day (lower bound) and a maximum day (upper bound, which would be the time
    * taken by the slowest machine to produce the goal count). Then, we find the "mid" value between these 2 days and
    * see the number of product produced in the "mid" day. If the value is lesser than the goal count, we search for our
    * day between "mid" to maximum day. If the value is greater than the goal count, we search for our day between
    * minimum to "mid" day.
    *
    * Example: (because I got stuck a lot because of small implementation details)
    * machines[] = [4L, 5L, 6L]
    * goal = 12
    *
    * ----begin execution------------
    *
    * minimumDays = 0
    * maximumDays = 6 * 12 = 72
    *
    * So, our search bound is
    * 0(minimumDays)-----------------------72(maximumDays)
    *
    * Iteration 1:
    * 0(minimumDays)---------36(mid)--------------72(maximumDays)
    * prodCount = 22 > goal
    *
    * Iteration 2:
    * 0(minimumDays)-------------18(mid)-----------36(maximumDays)
    * prodCount = 10 < goal
    *
    * Iteration 3:
    * 19(minimumDays)-------------27(mid)-----------36(maximumDays)
    * prodCount = 15 > goal
    *
    * Iteration 4:
    * 19(minimumDays)-------------23(mid)-----------27(maximumDays)
    * prodCount = 12 == goal (Yay, we found the day that produces the goal! But wait. Could this be the actual MINIMUM
    * day?)
    *
    * Iteration 5:
    * 19(minimumDays)-------------21(mid)-----------23(maximumDays)
    * prodCount = 12 == goal (Huh, so this one is lesser than the day we found in the last iteration)
    *
    * Iteration 6:
    * 19(minimumDays)-------------20(mid)-----------21(maximumDays)
    * prodCount = 12 == goal (even lesser!)
    *
    * Iteration 7:
    * 19(minimumDays, mid)-------------------------20(maximumDays)
    * prodCount = 10 < goal
    *
    * Iteration 8:
    * 20(minimumDays, maximumDays)
    * minimumDays == maximumDays, so loop ends. So we return the value from iteration 6!
    *
    * */
    static long minTime(long[] machines, long goal) {
        Arrays.sort(machines);
        long slowestMachine = machines[machines.length - 1];
        long prodCount = 0;
        long minimumDays = 0;
        long maximumDays = goal * slowestMachine; // time taken by the slowest machine to produce goal count
        while (minimumDays < maximumDays) {
            long mid = (maximumDays + minimumDays) / 2;
            prodCount = calcProdCount(mid, machines);
            if (prodCount < goal) {
                minimumDays = mid + 1;// forgetting to add 1 would cause an unbreakable loop as mid would always come to be the same value.
            } else {
                maximumDays = mid;
            }
        }
        return minimumDays;
    }

    static long calcProdCount(long testDays, long[] machines) {
        long result = 0;
        for (long machine: machines) {
            result += testDays / machine;
        }
        return result;
    }
}
