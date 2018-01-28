package Strings;

public class LongestPalindromicSubstring {
    /*
     * @Author : Sahil
     * Date : 28 Jan 2018
     *
     * Two Solutions
     * 1. Manacher Algorithm
     * 2. Dynamic Programming IDeserve O(N2) space O(N2)
     *
     * References :
     * https://articles.leetcode.com/longest-palindromic-substring-part-ii/
     *
     * for String " BANANA "
     *
     * Pre-Processed String : " @#B#A#N#A#N#A#$"
     * Palindrome Len P[] :     001010305030100
     *  max palindrome Length = 5
     *
     */

    // Transform S into T.
    // For example, S = "abba", T = "@#a#b#b#a#$".
    String preProcessString(String s) {
        String r = "@#";
        for (int i = 0; i < s.length(); i++) {
            r = r + s.substring(i, i + 1) + "#";
        }
        r = r + "$";
        return r;
    }

    //Manacher's Algorithm
    String longestPalindromeManacher(String S) {
        String T = preProcessString(S);
        //System.out.print(T+"\n");
        int P[] = new int[T.length()];
        int center = 0;
        int rightBoundary = 0;

        for (int i = 1; i < T.length() - 1; i++) {

            //mirror -> i' = C - (i-C) = 2C - i
            int mirror = 2 * center - i;

            //Step 1 : i resides under current Right boundary that means p[i] is not defined yet.. define it
            //
            if (i < rightBoundary) {
                P[i] = Math.min(rightBoundary - i, P[mirror]);
            }

            //Step 2 : Update palindrome length of the i (center)
            while (T.charAt(i + (1 + P[i])) == T.charAt(i - (1 + P[i]))) {
                P[i]++;
            }

            //Step 3 :
            // If palindrome centered at i expand past R,
            // adjust center based on expanded palindrome.
            if (i + P[i] > rightBoundary) {
                center = i;
                rightBoundary = i + P[i];
            }

        }
        //System.out.print("\n");
        //for(int i=0;i<P.length;i++)
        //System.out.print(P[i]+" ");

        //Find the max palindrome center
        int maxLenCenter = 0;
        int centerIndex = 0;
        for (int i = 1; i < P.length - 1; i++) {
            if (maxLenCenter < P[i]) {
                maxLenCenter = P[i];
                centerIndex = i;
            }
        }
        int longestPalindromStartIndex = (centerIndex - 1 - maxLenCenter) / 2;

        return S.substring(longestPalindromStartIndex, longestPalindromStartIndex + maxLenCenter);

    }

    //Dynamic Programing O(N^2)
    public String longestPalindromicDynamic(String s) {

        int palindromeBeginsAtIndex = 0;
        int maxLen = 1;


        boolean palindrome[][] = new boolean[s.length()][s.length()];

        //Single Letter Palindromes are always true
        for (int i = 0; i < s.length(); i++) {
            palindrome[i][i] = true;
        }

        //Two letter Palindrome (means two consecutive same letters)

        for(int i=0;i<s.length()-1;i++){
            if(s.charAt(i)==s.charAt(i+1)){
                palindromeBeginsAtIndex=i;
                maxLen=2;
                palindrome[i][i+1]=true;
            }
        }

        //Finding palindromes of length 3 to n and saving the longest

        for(int curr_len=3;curr_len<=s.length();curr_len++){
            for (int i = 0; i < s.length()-curr_len+1; i++) {
                int j = i+curr_len-1;
                if (s.charAt(i) == s.charAt(j) //1. The first and last characters should match
                        && palindrome[i+1][j-1]) //2. Rest of the substring should be a palindrome
                {
                    palindrome[i][j] = true;
                    palindromeBeginsAtIndex = i;
                    maxLen = curr_len;
                }
            }
        }
        return s.substring(palindromeBeginsAtIndex, maxLen + palindromeBeginsAtIndex);
    }


    public static void main(String args[]) {
        String s = "BANANA";
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
        //System.out.print(lps.longestPalindromeManacher(s));
        System.out.print(lps.longestPalindromicDynamic(s));
    }
}
