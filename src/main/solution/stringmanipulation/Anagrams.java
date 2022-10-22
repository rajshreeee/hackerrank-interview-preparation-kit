package main.solution.stringmanipulation;

import java.io.*;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Anagrams {
    public static int makeAnagram(String a, String b) {
        HashMap<Character, Integer> aHashMap = new HashMap<>();
        HashMap<Character, Integer> bHashMap = new HashMap<>();

        for (int i = 0; i < a.length(); i++) {
            Character character = a.charAt(i);
            aHashMap.put(character,
                    aHashMap.containsKey(character) ?
                            aHashMap.get(character) + 1
                            : 1);
        }

        for (int i = 0; i < b.length(); i++) {
            Character character = b.charAt(i);
            bHashMap.put(character,
                    bHashMap.containsKey(character) ?
                            bHashMap.get(character) + 1
                            : 1);
        }

        AtomicInteger commonCharactersCount = new AtomicInteger();
        aHashMap.forEach((character, characterCount) -> {
            if (bHashMap.containsKey(character)) {
                int lesserCount = bHashMap.get(character) <= characterCount
                        ? bHashMap.get(character)
                        : characterCount;
                commonCharactersCount.addAndGet(lesserCount + lesserCount);
            }
        });


        return a.length() + b.length() - commonCharactersCount.get();
    }

    public class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String a = bufferedReader.readLine();

            String b = bufferedReader.readLine();

            int res = Anagrams.makeAnagram(a, b);

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
