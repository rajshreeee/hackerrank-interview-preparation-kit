package main.solution.dynamicprogramming;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {


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

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        long result = Result.candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
