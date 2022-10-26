package main.solution.stringmanipulation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommonChild {

    /*A very popular problem of LCS. Solved by using dynamic programming. Refer to docs*/
    public static int commonChild(String s1, String s2) {
        int length = s1.length();
        int[][] lcs = new int[length + 1][length + 1];
        List<Character> s2Letters = getAllLetters(s2);
        List<Character> s1Letters = getAllLetters(s1);

        for (int i = 0; i < length + 1; i++) {
            lcs[0][i] = 0;
        }
        for (int j = 1; j < length + 1; j++) {
            lcs[j][0] = 0;
        }

        for (int i = 1; i < length + 1; i++) {
            for (int j = 1; j < length + 1; j++) {
                if (s2Letters.get(i-1).equals(s1Letters.get(j-1))) {
                    lcs[i][j] = 1 + lcs[i-1][j-1];
                } else {
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }

        return lcs[length][length];
    }

    private static List<Character> getAllLetters(String s) {
        List<Character> letters = new ArrayList<>();
        for (int index = 0; index < s.length(); index++) {
            letters.add(s.charAt(index));
        }
        return letters;
    }

    public class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String s1 = bufferedReader.readLine();

            String s2 = bufferedReader.readLine();

            int result = CommonChild.commonChild(s1, s2);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
