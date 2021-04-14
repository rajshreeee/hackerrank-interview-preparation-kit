package main.solution.dictionariesandhashmaps;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FrequencyQueries {

  // Complete the freqQuery function below.
  static List<Integer> freqQuery(List<List<Integer>> queries) {
    // Records number for keys and the frequency of that number as value
    HashMap<Integer, Integer> numberToFreqMap = new HashMap<>();
    // Records frequency for keys and the set of numbers with that frequency for value
    HashMap<Integer, HashSet<Integer>> freqToNumbersMap = new HashMap<>();
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < queries.size(); i++) {
      List<Integer> query = queries.get(i);
      Integer queryNum = query.get(1);
      Integer queryCommand = query.get(0);
      switch (queryCommand) {
        case 1:
          if (numberToFreqMap.containsKey(queryNum)) {
            Integer currentCount = numberToFreqMap.get(queryNum);
            numberToFreqMap.put(queryNum, currentCount + 1);
            freqToNumbersMap.get(currentCount).remove(queryNum);
            addQueryNumToMap(freqToNumbersMap, queryNum, currentCount + 1);
          } else {
            numberToFreqMap.put(queryNum, 1);
            addQueryNumToMap(freqToNumbersMap, queryNum, 1);
          }
          break;
        case 2:
          if (numberToFreqMap.containsKey(queryNum)) {
            int currentCount = numberToFreqMap.get(queryNum) - 1;
            if (currentCount == 0) {
              numberToFreqMap.remove(queryNum);
            } else {
              numberToFreqMap.put(queryNum, currentCount);
              HashSet<Integer> set = freqToNumbersMap.getOrDefault(currentCount, new HashSet<>());
              set.add(queryNum);
              freqToNumbersMap.put(currentCount, set);
            }
            freqToNumbersMap.get(currentCount + 1).remove(queryNum);
          }
          break;
        case 3:
          result.add(freqToNumbersMap.containsKey(queryNum)
              && freqToNumbersMap.get(queryNum).size() != 0
              ? 1
              : 0);
          break;
      }
    }
    return result;
  }

  static void addQueryNumToMap(HashMap<Integer, HashSet<Integer>> map, Integer queryNum,
      Integer count) {
    HashSet set = map.getOrDefault(count, new HashSet<>());
    set.add(queryNum);
    map.put(count, set);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = Integer.parseInt(bufferedReader.readLine().trim());

    List<List<Integer>> queries = new ArrayList<>();

    IntStream.range(0, q).forEach(i -> {
      try {
        queries.add(
            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList())
        );
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    List<Integer> ans = freqQuery(queries);

    bufferedWriter.write(
        ans.stream()
            .map(Object::toString)
            .collect(joining("\n"))
            + "\n"
    );

    bufferedReader.close();
    bufferedWriter.close();
  }
}

