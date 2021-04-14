package main.solution.dictionariesandhashmaps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class TwoStrings {

  // Complete the twoStrings function below.
  static String twoStrings(String s1, String s2) {
    Set<Character> s1set = new LinkedHashSet<>();
    s1.chars().forEach(e -> s1set.add(Character.toLowerCase((char) e)));
    Set<Character> s2set = new LinkedHashSet<>();
    s2.chars().forEach(e -> s2set.add(Character.toLowerCase((char) e)));
    if(s1set.stream().anyMatch(s2set::contains)
    ) {
      return "YES";
    } else {
      return "NO";
    }

  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int qItr = 0; qItr < q; qItr++) {
      String s1 = scanner.nextLine();

      String s2 = scanner.nextLine();

      String result = twoStrings(s1, s2);

      bufferedWriter.write(result);
      bufferedWriter.newLine();
    }

    bufferedWriter.close();

    scanner.close();
  }
}

