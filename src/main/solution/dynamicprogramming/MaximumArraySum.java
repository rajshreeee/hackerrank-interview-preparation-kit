package main.solution.dynamicprogramming;

public class MaximumArraySum {

    /*
    * The entire concept of dynamic programming is to reuse results of 'sub problems' to solve the entire problem.
    *
    * Everytime we iterate the array, we will replace the current element with the maximum sum.
    *
    * Suppose we have arr = [3 5 -7 8 10]
    *
    * So, we have 5 iterations to find out the max value. In each iteration, we replace the current element of the
    * array with the current max value.
    *
    * Iteration 0 -> max is 3 because we only have 1 element which requires no comparison.
    * Iteration 1 -> max is 5 because 5 > 3. And we only have 2 elements to compare, so it's straight forward till here.
    *
    * For iterations 2 to 4, the max value can be :
    * 1. the current element plus the sum of the value 2 indices prior.
    * 2. the current element (the max value or the sum mentioned in no.1 could be -ve).
    * 3. the max value until now.
    *
    * Iteration 2 -> [3 5 5^ 8 10]
    * Iteration 3 -> [3 5 5 12^ 10]
    * Iteration 4 -> [3 5 5 12 15^]
    *
    * so, the max value is 15.
    * */
    static int maxSubsetSum(int[] arr) {
        arr[1] = arr[0] > arr[1]
                ? arr[0]
                : arr[1];
        for (int i = 2; i < arr.length; i++) {
            int sum = arr[i] + arr[i - 2];
            int max = sum > arr[i-1]
                    ? sum
                    : arr[i-1];
            max = max > arr[i]
                    ? max
                    : arr[i];
            arr[i] = max;
        }
        return arr[arr.length-1];
    }
}
