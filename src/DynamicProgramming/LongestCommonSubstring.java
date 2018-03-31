package DynamicProgramming;

public class LongestCommonSubstring {
    /*
     *@Author : Sahil
     *Date : 31 March 2018
     * Given two strings ‘X’ and ‘Y’, find the length of the longest common substring.
     * nput : X = "GeeksforGeeks", y = "GeeksQuiz"
     * Output : 5
     * The longest common substring is "Geeks" and is of
     * length 5.
     *
     * Input : X = "abcdxyz", y = "xyzabcd"
     * Output : 4
     * The longest common substring is "abcd" and is of
     * length 4.
     *
     * Input : X = "zxabcdezy", y = "yzabcdezx"
     * Output : 6
     * The longest common substring is "abcdez" and is of
     * length 6.
     *
     * References :
     * https://www.geeksforgeeks.org/longest-common-substring/
     * https://www.youtube.com/watch?v=hGwHQP9wbiM
     */

    public int longesCommonSubstring(String str1, String str2) {
        int answer = 0;
        //Make row  for str1 and columns for str2
        //Initialize 0th row and col to 0
        int row = str1.length() + 1;
        int col = str2.length() + 1;
        int T[][] = new int[row][col];

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    T[i][j] = 1 + T[i - 1][j - 1];
                    answer = Math.max(answer, T[i][j]);
                }
            }
        }


        return answer;
    }

    public static void main(String args[]) {
        LongestCommonSubstring lcs = new LongestCommonSubstring();
        String str1 = "sahilkhurana";
        String str2 = "hilk";
        System.out.print(lcs.longesCommonSubstring(str1, str2));
    }
}
