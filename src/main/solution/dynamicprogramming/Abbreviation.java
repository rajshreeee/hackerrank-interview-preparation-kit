package main.solution.dynamicprogramming;

class Abbreviation {

    /*
    * For an elaborate explanation: https://www.youtube.com/watch?v=4WzCFTmjKu4&t=4s
    *
    * Here, we need to use dynamic programming ---duh! So, we basically use the result calculated for earlier letters to
    * find the result for current letters.
    *
    * Step 1: define a result matrix. Let's suppose, a = "AbDeF" and b = "ADF"
    *   ""  A   D   F
    * ""
    * A
    * b
    * D
    * e
    * F
    *
    * #Catch: when you define a boolean array in Java, all the values are initialized to false.
    *
    * So, we have
    *
    *   ""  A   D   F
    * "" F  F   F   F
    * A  F  F   F   F
    * b  F  F   F   F
    * D  F  F   F   F
    * e  F  F   F   F
    * F  F  F   F   F
    *
    * Heck, I'll also add the indices, so my future self can understand better!
    *     0   1   2   3
    *     ""  A   D   F
    *0 "" F  F   F   F
    *1 A  F  F   F   F
    *2 b  F  F   F   F
    *3 D  F  F   F   F
    *4 e  F  F   F   F
    *5 F  F  F   F   F
    *
    * Let's go for [0,0] -> "" == "", so we will set it as true
    *     0   1   2   3
    *     ""  A   D   F
    *0 "" T  F   F   F
    *1 A  F  F   F   F
    *2 b  F  F   F   F
    *3 D  F  F   F   F
    *4 e  F  F   F   F
    *5 F  F  F   F   F
    *
    * for [0,1] -> "" != ""A
    * [0,2] -> "" != ""AD
    * [0,3] -> "" != ""ADF
    *
    * Notice that we don't take the 2nd index as only D but as ""AD, so it's cumulative. So our answer would be in [5,3]
    * as it would cumulate the results, and we'd basically be doing ""AbDeF == ""ADF
    *
    * Now, we need to find a pattern! This pattern is the core logic behind the solution. We want to be able to use this
    * pattern to find the result for the rest of the matrix. This pattern will build the matrix.
    *
    * I recommend you try to calculate the rest of the values in the matrix yourself to find this pattern.
    *
    * but if you're in a hurry:
    *   [1, 0] -> ""A != "" as A is uppercase and cannot be deleted
    *   [1, 1] -> ""A == ""A
    *   [1, 2] -> ""A != ""AD and so on for the 1st letter of a
    *
    * So, our matrix looks like
    *     0   1   2   3
    *     ""  A   D   F
    *0 "" T  F   F   F
    *1 A  F  T   F   F
    *2 b  F  F   F   F
    *3 D  F  F   F   F
    *4 e  F  F   F   F
    *5 F  F  F   F   F
    *
    * Now, for the second letter of A -> ""Ab
    * [2, 0] -> ""Ab != ""
    * [2, 1] -> ""Ab == ""A as b is lowercase and can be deleted. But notice, how we could go to [1,1] and use that T to
    * infer this as we already had the result for ""A == ""A. There's our pattern!
    * [2, 2] -> ""Ab != ""AD
    *
    * Now, similarly we can also infer that we could infer [2,2] using [1,1]
    *
    * Yup, that's it!
    * So all we're doing is:
    * 1. define a matrix
    * 2. find a pattern to fill the matrix
    * 3. Fill the matrix
    * 4. Return the last value of the matrix
     * */
    public static String abbreviation(String a, String b) {
        // Write your code here
        int aLength = a.length();
        int bLength = b.length();
        if (aLength < bLength) {
            return "NO";
        }

        var result = new boolean[aLength + 1][ bLength + 1];
        result[0][0] = true;

        for (int aIndex = 1; aIndex <= aLength; aIndex++) {
            var aChar = a.charAt(aIndex - 1);
            var isLowerCase = Character.isLowerCase(aChar);
            result[aIndex][0] = isLowerCase && result[aIndex - 1][0];
            for (int bIndex = 1; bIndex <= bLength; bIndex++) {
                var bChar = b.charAt(bIndex - 1);
                var matches = Character.toUpperCase(aChar) == bChar;
                result[aIndex][bIndex] = (matches && result[aIndex - 1][bIndex - 1])
                        || (isLowerCase && result[aIndex - 1][bIndex]);
            }
        }
        return result[aLength][bLength] ? "YES": "NO";
    }

}