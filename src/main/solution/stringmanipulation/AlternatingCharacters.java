package main.solution.stringmanipulation;

import java.io.*;
import java.util.stream.IntStream;

public class AlternatingCharacters {

    public static int alternatingCharacters(String s) {
        // Write your code here
        int charactersToRemove = 0;
        int indexOfCharacterToCompare = 0;

        while (indexOfCharacterToCompare < s.length()) {
            boolean characterToCompareForIsA = s.charAt(indexOfCharacterToCompare) == 'A';
            int firstOccurrenceOfAlternatingCharacter = findFirstOccurrenceOf(s,
                    characterToCompareForIsA ? 'B' : 'A', indexOfCharacterToCompare + 1);
            if (firstOccurrenceOfAlternatingCharacter == -1) {
                charactersToRemove += s.length() - indexOfCharacterToCompare - 1;
                break;
            }

            charactersToRemove += firstOccurrenceOfAlternatingCharacter - indexOfCharacterToCompare - 1;
            indexOfCharacterToCompare = firstOccurrenceOfAlternatingCharacter;
        }
        return charactersToRemove;
    }

    public static int findFirstOccurrenceOf(String s, char character, int indexToCheckFrom) {
        for (int index = indexToCheckFrom; index < s.length(); index++) {
            if (s.charAt(index) == character) {
                return index;
            }
        }
        return -1;
    }

    public class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int q = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, q).forEach(qItr -> {
                try {
                    String s = bufferedReader.readLine();

                    int result = AlternatingCharacters.alternatingCharacters(s);

                    bufferedWriter.write(String.valueOf(result));
                    bufferedWriter.newLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}