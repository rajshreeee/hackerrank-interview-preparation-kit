package main.solution.dictionariesandhashmaps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SherlockAndAnagrams {

  static HashMap<String, Integer> getSubStringCountMap(String subString) {
    HashMap<String, Integer> hashMap = new HashMap<>();
    for(int j = 0; j < subString.length(); j++) {
      if(Objects.isNull(hashMap.get(subString.substring(j, j+1)))) {
        hashMap.put(subString.substring(j, j+1), 1);
      } else  {
        hashMap.put(subString.substring(j, j+1), hashMap.get(subString.substring(j, j+1))+1);
      }
    }

    return hashMap;
  }

  // Complete the sherlockAndAnagrams function below.
  static int sherlockAndAnagrams(String word) {
    int wordLength = word.length();
    List<String> subStrings = new ArrayList<>();
    for(int j = 0; j < wordLength; j++) {
      for(int i = j+1; i <= wordLength; i++) {
        subStrings.add(word.substring(j, i));
      }
    }
    HashMap<String, HashMap<String, Integer>> hashMapOfAllSubstrings = new HashMap<>();
    for(int i = 0; i < subStrings.size(); i++) {
      hashMapOfAllSubstrings.put(subStrings.get(i), getSubStringCountMap(subStrings.get(i)));
    }

    int anagramCount = 0;
    for(int i = 0; i < subStrings.size(); i++) {
      int lengthOfI = subStrings.get(i).length();
      HashMap<String, Integer> subStringHM = hashMapOfAllSubstrings.get(subStrings.get(i));
      for(int j = 0 + i +1; j < subStrings.size(); j++) {

        if (lengthOfI == subStrings.get(j).length()) {
          HashMap<String, Integer> hashMap = hashMapOfAllSubstrings.get(subStrings.get(j));
          if(subStringHM.equals(hashMap)) {
            anagramCount++;
          }
        }
      }
    }
    return anagramCount;
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int q = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int qItr = 0; qItr < q; qItr++) {
      String s = scanner.nextLine();

      int result = sherlockAndAnagrams(s);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();
    }

    bufferedWriter.close();

    scanner.close();
  }
}


