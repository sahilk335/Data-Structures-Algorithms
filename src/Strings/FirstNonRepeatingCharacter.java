package Strings;

import java.util.HashMap;

public class FirstNonRepeatingCharacter {
    /*
     *@Author : Sahil
     * Date : 06 March 2019
     *
     *First non-repeating character using one traversal of string
     *
     * Solution :
     * 1 .Get the frequency of each character.
     * 2. Get the first character that has a frequency of one
     *
     * References :
     * https://www.geeksforgeeks.org/first-non-repeating-character-using-one-traversal-of-string-set-2/
     * https://leetcode.com/problems/first-unique-character-in-a-string/discuss/86348/Java-7-lines-solution-29ms
     */

    private static int answer = Integer.MAX_VALUE;

    public int firstNonRepeating(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int n = s.length();
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // find the index
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }


    public static void main(String[] args) {
        FirstNonRepeatingCharacter firstNonRepeatingCharacter = new FirstNonRepeatingCharacter();

        String str = "geeksforgeeks";

            System.out.print("First non-repeating character" +
                    " is " + firstNonRepeatingCharacter.firstNonRepeating(str));
    }

}


