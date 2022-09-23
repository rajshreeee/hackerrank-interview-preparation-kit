package main.solution.arrays;

import static java.lang.Math.max;

import java.util.*;

public class NewYearChaos {

  private static final Scanner scanner = new Scanner(System.in);

  static void minimumBribes(int[] q) {
    int totalBribes = 0;

    int expectedFirst = 1;
    int expectedSecond = 2;
    int expectedThird = 3;

    for (int i = 0; i < q.length; ++i) {
      if (q[i] == expectedFirst) {
        expectedFirst = expectedSecond;
        expectedSecond = expectedThird;
        ++expectedThird;
      } else if (q[i] == expectedSecond) {
        ++totalBribes;
        expectedSecond = expectedThird;
        ++expectedThird;
      } else if (q[i] == expectedThird) {
        totalBribes += 2;
        ++expectedThird;
      } else {
        System.out.println("Too chaotic");
        return;
      }
    }

    System.out.println(totalBribes);
  }

  public static void main(String[] args) {
    int t = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int tItr = 0; tItr < t; tItr++) {
      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] q = new int[n];

      String[] qItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < n; i++) {
        int qItem = Integer.parseInt(qItems[i]);
        q[i] = qItem;
      }

      minimumBribes(q);
    }

    scanner.close();
  }
}

