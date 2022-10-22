package main.solution.sorting;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/* General idea: if you sort the prices then sum them incrementally,
* you will get the largest number of toys. As the prices are sorted, obviously the
* smallest prices will come first and you get the maximum number of toys*/
public class MarkAndToysSolution {
    public static int maximumToys(List<Integer> prices, int k) {
        Collections.sort(prices);
        Integer sum = 0;
        int count = 0;
        for(Integer price: prices) {
            sum += price;
            if (sum > k) {
                break;
            }
            count++;
        }
        return count;
    }
    public class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int k = Integer.parseInt(firstMultipleInput[1]);

            List<Integer> prices = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int result = MarkAndToysSolution.maximumToys(prices, k);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
}
}
