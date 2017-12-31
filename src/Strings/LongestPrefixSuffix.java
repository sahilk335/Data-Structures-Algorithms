package Strings;

/*
Longest prefix which is also a longest suffix

 * The LPS computation can determine, at any given index i in a string S, the maximum suffix length that
         * make up a suffix equal to the prefix. For example: S = "acexxxaceyyy": at S[6], S[7], and S[8] will be
         * marked with "1", "2", and "3" respectively because "a", "ac", and "ace" at this points in the string
         * make up substrings whose suffixes equal to the string's prefix. This computation can be done in one
         * linear scan of the string in O(N) time, using a secondary integer array in O(N) space.

References : https://www.youtube.com/watch?v=tWDUjkMv6Lc&list=PLnzNcWHcxAGNP939HXjwgnJrK5At5P780&index=1
 */

public class LongestPrefixSuffix {

    int[] computeLPSArray(String pattern, int lps[]) {
        int j = 0; //length of previous lonngest prefix and suffix
        int i = 1;
        lps[0] = 0;   //first element is itself suffix and prefix

        //loop calculates lps[i] for 1 to length-1
        while (i < pattern.length()) {
            if (pattern.charAt(j) == pattern.charAt(i)) {
                lps[i] = j + 1;
                i++;
                j++;
            } else {  //patter[j]!=pattern[i]
                if (j == 0) {  //no need to look for another suffix prefix
                    lps[i] = 0;
                    i++;
                } else { //look at other prefix suffix in the same string, as it might be possible that it contains other suffix prefix
                    j = lps[j - 1];
                }
            }
        }
        return lps;
    }

/*    void printArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String args[]) {
        String pattern = "aabaabc";
        int lps[]=new int[pattern.length()];
        LongestPrefixSuffix longestPrefixSuffix = new LongestPrefixSuffix();
        longestPrefixSuffix.printArray(longestPrefixSuffix.computeLPSArray(pattern,lps));
    }*/
}
