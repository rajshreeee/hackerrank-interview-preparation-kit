package main.solution.dictionariesandhashmaps;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class CountTriplets {

  // Complete the countTriplets function below.
  static long countTriplets(List<Long> arr, long r) {
    HashMap<Long, Long> leftMap = new HashMap<>();
    HashMap<Long, Long> rightMap = new HashMap<>();
    long total = 0L;
    for (int i = 0; i < arr.size(); i++) {
      rightMap.put(arr.get(i), rightMap.containsKey(arr.get(i))
          ? rightMap.get(arr.get(i)) + 1L
          : 1L);
    }

    for (int i = 0; i < arr.size(); i++) {
      if (i != 0) {
        leftMap.put(arr.get(i - 1), leftMap.containsKey(arr.get(i - 1))
            ? leftMap.get(arr.get(i - 1)) + 1L
            : 1L);
      }
      rightMap.put(arr.get(i), rightMap.get(arr.get(i)) - 1L);

      if (arr.get(i) % r == 0 && leftMap.containsKey(arr.get(i) / r)) {
        long m = leftMap.get(arr.get(i) / r);

        if (rightMap.containsKey(arr.get(i) * r)) {
          long n = rightMap.get(arr.get(i) * r);
          total += m * n;
        }
      }

    }

    return total;
  }


  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(nr[0]);

    long r = Long.parseLong(nr[1]);

    List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Long::parseLong)
        .collect(toList());

    long ans = countTriplets(arr, r);

    bufferedWriter.write(String.valueOf(ans));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}


