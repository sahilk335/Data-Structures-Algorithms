package Strings;

import java.util.HashMap;
import java.util.Map;
 
public class MinimumWindowSubstring {
 
        /*
         *@Author : Sahil
         * Date : 07 Mar 2019
         *
         * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
         *
         * Example:
         *
         * Input: S = "ADOBECODEBANC", T = "ABC"
         * Output: "BANC"
         * Note:
         *
         * If there is no such window in S that covers all characters in T, return the empty string "".
         * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
         *
         * Algorithm
         *
         * We start with two pointers, leftleft and rightright initially pointing to the first element of the string SS.
         *
         * We use the rightright pointer to expand the window until we get a desirable window
         * i.e. a window that contains all of the characters of TT.
         *
         * Once we have a window with all the characters, we can move the left pointer ahead one by one.
         * If the window is still a desirable one we keep on updating the minimum window size.
         *
         * If the window is not desirable any more, we repeat step \; 2step2 onwards.
         *
         * References :
         * https://leetcode.com/problems/minimum-window-substring/solution/
         */
         
    public String minWindow(String str, String pat) {
        if (str.length() == 0 || pat.length() == 0)
            return "";
 
        //Dictionary which keeps a count of all the unique chaacters in pattern
        Map<Character, Integer> patMap = new HashMap<Character, Integer>();
        for (int i = 0; i < pat.length(); i++) {
            Character currChar = pat.charAt(i);
            int count = patMap.getOrDefault(pat.charAt(i), 0);
            patMap.put(currChar, count + 1);
        }
 
        //Number of unique characters in pattern, which needs to be in desired window
        int required = patMap.size();
 
        //Left and right pointer initialized
        int left = 0, right = 0;
 
        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        int formed = 0;
 
        //Dictioanary which keeps a count of all the unique characters in the current window
        Map<Character, Integer> windowMap = new HashMap<Character, Integer>();
 
 
        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};
 
        while (right < str.length()) {
 
            char currChar = str.charAt(right);
            int currCharCount = windowMap.getOrDefault(currChar, 0);
            windowMap.put(currChar, currCharCount + 1);
 
            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (patMap.containsKey(currChar) && windowMap.get(currChar).intValue() == patMap.get(currChar).intValue()) {
                formed++;
            }
 
            // Try and contract the window till the point where it ceases to be 'desirable'.
 
            while (formed == required && left <= right) {
 
                currChar = str.charAt(left);
                //Save the smallest window until now
 
                if (ans[0] == -1 || right - left + 1 < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }
 
                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
 
                windowMap.put(currChar, windowMap.get(currChar) - 1);
 
                if (patMap.containsKey(currChar) && windowMap.get(currChar).intValue() < patMap.get(currChar).intValue()) {
                    formed--;
                }
                left++;
            }
 
            //keep expanding the window
            right++;
        }
 
        return ans[0] == -1 ? "" : str.substring(ans[1], ans[2] + 1);
 
    }
 
         
    public static void main(String[] ags) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        String str = "this is a test string";
        String pat = "tist";
        System.out.println(minimumWindowSubstring.minWindow(str, pat));
    }
}