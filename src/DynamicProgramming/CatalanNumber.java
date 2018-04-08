package DynamicProgramming;

public class CatalanNumber {
    /*
     *Catalan Number - it is a sequence of number which is used to solve various mathematical problems
     *
     * N th Catalan is defined as C(n) = 2n C n/(n+1)
     *
     * References :
     * https://en.wikipedia.org/wiki/Catalan_number
     * https://www.geeksforgeeks.org/program-nth-catalan-number/
     *
     */

    public long catalanRes(int num) {
        int dp[] = new int[num + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= num; i++) {
            dp[i] = 0;
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[num];
    }

    public static void main(String args[]) {
        CatalanNumber catalan = new CatalanNumber();
        int n = 4;
        System.out.print(catalan.catalanRes(n));
    }
}
