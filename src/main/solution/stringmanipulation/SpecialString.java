package main.solution.stringmanipulation;

import java.io.*;

public class SpecialString {

    public static long substrCount(int length, String s) {
        long substrCount = length;
        for (int index = 0; index < length; index++) {
            char character = s.charAt(index);
            // find the last consecutive character's index
            int lastIndex = findLastConsecutiveCharacterIndex(s,
                    index + 1, length, character);
            int gap = lastIndex - index;
            substrCount+=gap;
            // check if the substring contains the same character
            if (gap + lastIndex + 2 < length &&  stringContainsTheSameCharacter(
                    s.substring(lastIndex + 2, gap + lastIndex + 3), character)) {
                substrCount++;
            }
        }
        return  substrCount;
    }

    private static boolean stringContainsTheSameCharacter(String s, Character ch) {
        for (int index = 0; index < s.length(); index++) {
            if (s.charAt(index) != ch) {
                return false;
            }
        }
        return true;
    }

    private static int findLastConsecutiveCharacterIndex(String s, int startingIndex
            , int length, char character) {
        for (int index = startingIndex; index < length; index++) {
            if (s.charAt(index) != character) {
                return index - 1;
            }
        }
        return length - 1;
    }

    public static void main(String[] args) {
        System.out.println(substrCount(4, "aaaa"));
    }

    public class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int n = Integer.parseInt(bufferedReader.readLine().trim());

            String s = bufferedReader.readLine();

            long result = SpecialString.substrCount(n, s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
