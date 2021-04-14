package main.solution.dictionariesandhashmaps;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class RansomNote {

  private static final Scanner scanner = new Scanner(System.in);

  // Complete the checkMagazine function below.
  static void checkMagazine(String[] magazine, String[] note) {
    HashMap<String, Integer> wordCountMap = new HashMap<>();
    for (String word : magazine) {
      if (Objects.isNull(wordCountMap.get(word))) {
        wordCountMap.put(word, 1);
      } else {
        wordCountMap.put(word, wordCountMap.get(word) + 1);
      }
    }

    for (String noteWord : note) {
      if (Objects.isNull(wordCountMap.get(noteWord)) || wordCountMap.get(noteWord) == 0) {
        System.out.println("No");
        return;
      } else {
        wordCountMap.put(noteWord, wordCountMap.get(noteWord) - 1);
      }
    }

    System.out.println("Yes");
  }

  public static void main(String[] args) {
    String[] mn = scanner.nextLine().split(" ");

    int m = Integer.parseInt(mn[0]);

    int n = Integer.parseInt(mn[1]);

    String[] magazine = new String[m];

    String[] magazineItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < m; i++) {
      String magazineItem = magazineItems[i];
      magazine[i] = magazineItem;
    }

    String[] note = new String[n];

    String[] noteItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      String noteItem = noteItems[i];
      note[i] = noteItem;
    }

    checkMagazine(magazine, note);

    scanner.close();
  }
}

