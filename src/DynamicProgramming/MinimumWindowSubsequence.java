package DynamicProgramming;

public class MinimumWindowSubsequence {

    /*
     *@Author : Sahil
     * Date : 15 May 2019
     *
     * Minimum Window Subsequence
     * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
     * If there is no such window in S that covers all characters in T, return the empty string "".
     * If there are multiple such minimum-length windows, return the one with the left-most starting index.
     * Example 1:
     * Input:
     * S = "abcdebdde", T = "bde"
     * Output: "bcde"
     * Explanation:
     * "bcde" is the answer because it occurs before "bdde" which has the same length.
     * "deb" is not a smaller window because the elements of T in the window must occur in order.
     *
     * Note:
     * All the strings in the input will only contain lowercase letters.
     * The length of S will be in the range [1, 20000].
     * The length of T will be in the range [1, 100].
     * ------------------------
     *
     * Example: alphabet: [abcd]
     * babdcdaabdbcd
     *  abdcd             <- 5 characters long (the best)
     *       a b  cd          7 chars long
     *        ab  cd          6 chars long
     *  a      b  cd          11 characters long
     *
     *
     * Reference : Asked in Google interview
     *
     *
     * Solution :
     * ==========================================================
     *    |  0   1   2   3   4   5   6   7   8   9   10  11  12
     *===========================================================
     *    |  B   A   B   D   C   D   A   A   B   D   B   C   D
     * ----------------------------------------------------------
     * A  | -1   1   1   1   1   1   6   7   7   7   7   7   7
     * B  | -1  -1   1   1   1   1   1   1   7   7   7   7   7
     * C  | -1  -1  -1  -1   1   1   1   1   1   1   1   7   7
     * D  | -1  -1  -1  -1  -1   1   1   1   1   1   1   1   7
     *
     *
     *               ABDCD            <------Answer (length 5)
     *           =================
     *               ABDCDA
     *           =====================
     *               ABDCDAA
     *           =========================
     *               ABDCDAAB
     *           =============================
     *               ABDCDAABD
     *           =================================
     *               ABDCDAABDB
     *           =====================================
     *               ABDCDAABDBC
     *           =========================================
     *                                          ABDBCD
     *                          ========================
     */

    public String minWindow(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == t.charAt(0)) dp[0][i] = i;
            else if (i > 0) dp[0][i] = dp[0][i - 1];
            else dp[0][i] = -1;
        }

        for (int i = 1; i < m; i++) {
            dp[i][0] = -1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (i > j) dp[i][j] = -1;
                else {
                    if (t.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

            }
        }

        //MatrixPrint.print(dp);

        int min = n;
        String rt = "";
        for (int i = 0; i < n; i++) {
            if (dp[m - 1][i] == -1) continue;
            if (min > i - dp[m - 1][i] + 1) {
                rt = s.substring(dp[m - 1][i], i + 1);
                min = i - dp[m - 1][i] + 1;
            }
        }

        return rt;


    }

    public static void main(String[] args) {
        MinimumWindowSubsequence minimumWindowSubsequence = new MinimumWindowSubsequence();
        System.out.println(minimumWindowSubsequence.minWindow("babdcdaabdbcd", "abcd"));

    }
}
