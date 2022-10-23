package main.solution.stringmanipulation;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SherlockValidString {

    /*The catch is that there can be multiple test cases unaccounted for*/
    public static String isValid(String s) {
        // Write your code here
        HashMap<Character, Integer> characterFrequency = new HashMap<>();
        for (int index = 0; index < s.length(); index++) {
            characterFrequency.put(s.charAt(index),
                    characterFrequency.getOrDefault(s.charAt(index), 0) + 1);
        }
        HashMap<Integer, List<Character>> frequencyCharacters
                = new HashMap<>();
        characterFrequency.forEach((key, value) ->
                frequencyCharacters.put(value, new ArrayList<>()));

        if (frequencyCharacters.keySet().size() > 2) {
            return "NO";
        }

        List<Integer> frequencyList = new ArrayList<>();
        frequencyCharacters.keySet().stream().forEach(value -> frequencyList.add(value));

        if (frequencyList.size() == 2) {
            characterFrequency.forEach((characterKey, frequency) -> {
                List<Character> list = frequencyCharacters.get(frequency);
                list.add(characterKey);
                frequencyCharacters.put(frequency, list);
            });

            int firstKeyListSize = frequencyCharacters.get(frequencyList.get(0)).size();
            int secondKeyListSize = frequencyCharacters.get(frequencyList.get(1)).size();

            if (firstKeyListSize > 1 && secondKeyListSize > 1) {
                return "NO";
            }
            if (Math.abs(frequencyList.get(0) - frequencyList.get(1)) >1 ) {
                if (firstKeyListSize == 1 && frequencyList.get(0) == 1
                        || secondKeyListSize == 1 && frequencyList.get(1)== 1) {
                    return "YES";
                }
            return "NO";
            }
        }

        return "YES";
    }

    public class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String s = bufferedReader.readLine();

            String result = SherlockValidString.isValid(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
