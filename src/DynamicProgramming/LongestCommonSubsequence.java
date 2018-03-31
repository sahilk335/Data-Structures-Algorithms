package DynamicProgramming;

public class LongestCommonSubsequence {
    /*
     *@Author :Sahil
     * Date : 31 March 2018
     * Given two sequences, find the length of longest subsequence present in both of them.
     * A subsequence is a sequence that appears in the same relative order,
     * but not necessarily contiguous. For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc
     * are subsequences of “abcdefg”. So a string of length n has 2^n different possible subsequences.
     *
     * Examples:
     * LCS for input Sequences "SAHIL and XSYAZHPQIRLR" is "SAHIL" of length 5
     * LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
     * LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
     *
     *References :
     * https://www.geeksforgeeks.org/longest-common-subsequence/
     * https://www.youtube.com/watch?v=NnD96abizww
     *
     */

    public int longestCommonSubsequence(String str1, String str2) {
        int answer = 0;
        int row = str1.length() + 1;
        int col = str2.length() + 1;
        //Initialize row and column with string 1 and string 2 respectively
        int T[][] = new int[row][col];

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    T[i][j] = 1 + T[i - 1][j - 1]; //equal to diagonal left +1
                    answer = Math.max(answer, T[i][j]);
                } else {
                    T[i][j] = Math.max(T[i][j - 1], T[i - 1][j]);
                }
            }
        }

        System.out.println(findMaxSequence(T, str1, str2));
        return answer;
    }

    String findMaxSequence(int T[][], String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        for (int i = str1.length(); i > 0; i--) {
            for (int j = str2.length(); j > 0; j--) {
                //character is included only when it is coming from diagonal and not from its top or left
                if (T[i][j - 1] == T[i - 1][j] && T[i][j - 1] != T[i][j] && T[i][j] == 1 + T[i - 1][j - 1]) {
                    sb.append(str1.charAt(i - 1));
                }
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String args[]) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String str1 = "SAHIL";
        String str2 = "XSYAZHPQIRLR";
        System.out.println(lcs.longestCommonSubsequence(str1, str2));
    }
}
