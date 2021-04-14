package main.solution.arrays;

import static java.lang.Math.max;

import java.util.Scanner;

public class NewYearChaos {

  // Complete the minimumBribes function below.
  static void minimumBribes(int[] q) {
    int totalNoOfBribes = 0;
    int qLength = q.length;
    for (int i = qLength- 1; i >= 0; i--) {
      if (q[i] > i + 3) {
        System.out.println("Too chaotic");
        break;
      }

      for(int j = max(0, q[i] -2); j < i; j++) {
        if(q[j] > q[i]) {
          totalNoOfBribes++;
        }
      }

      System.out.println(totalNoOfBribes);
    }
  }

  private static final Scanner scanner = new Scanner(System.in);

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

