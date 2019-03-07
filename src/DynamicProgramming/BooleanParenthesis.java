package DynamicProgramming;

public class BooleanParenthesis {
    /*
     *
     *@Author : Sahil
     * Date : 7 Mar 2019
     *
     * Given a boolean expression with following symbols.
     *
     * Symbols
     *    'T' ---> true
     *    'F' ---> false
     * And following operators filled between symbols
     *
     * Operators
     *    &   ---> boolean AND
     *    |   ---> boolean OR
     *    ^   ---> boolean XOR
     * Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
     *
     * Let the input be in form of two arrays one contains the symbols (T and F) in order and other contains operators
     * (&, | and ^}
     *
     *
     * References :
     * https://algorithmsandme.com/boolean-parenthesization-problem/
     * https://www.geeksforgeeks.org/boolean-parenthesization-problem-dp-37/
     */
    public int calculateNumberOfWays(String operators, String operands) {
        int numOperands = operands.length();


        int[][] F = new int[numOperands][numOperands];
        int[][] T = new int[numOperands][numOperands];

        for (int i = 0; i < numOperands; i++) {
            F[i][i] = (operands.charAt(i) == 'F') ? 1 : 0;
            T[i][i] = (operands.charAt(i) == 'T') ? 1 : 0;
        }

        //As operator can not be put on 0th index, as at 0th position always operand would be put
        //hence we start from the second diagonal.
        //i->row, j =col.. remember while travelling diagonally... row -> 0 to length-iteration
        //and col = row + iteration
        for (int iteration = 1; iteration < numOperands; iteration++) {
            for (int i = 0; i < numOperands - iteration; ++i) {
                int j = i + iteration;
                T[i][j] = F[i][j] = 0;
                for (int k = i; k < j; k++) {
                    int totalIK = T[i][k] + F[i][k];
                    int totalKJ = T[k + 1][j] + F[k + 1][j];
                    if (operators.charAt(k) == '&') {
                        T[i][j] += T[i][k] * T[k + 1][j];
                        F[i][j] += (totalIK * totalKJ - T[i][k] * T[k + 1][j]);
                    }
                    if (operators.charAt(k) == '|') {
                        F[i][j] += F[i][k] * F[k + 1][j];
                        T[i][j] += (totalIK * totalKJ - F[i][k] * F[k + 1][j]);
                    }
                    if (operators.charAt(k) == '^') {
                        T[i][j] += F[i][k] * T[k + 1][j] + T[i][k] * F[k + 1][j];
                        F[i][j] += T[i][k] * T[k + 1][j] + F[i][k] * F[k + 1][j];
                    }
                }
            }
        }
        return T[0][numOperands - 1];
    }

    public static void main(String[] args) {
        BooleanParenthesis booleanParenthesis = new BooleanParenthesis();
        String operands = "TTFT";
        String operators = "|&^";

        System.out.println("Number of ways to parenthisize expression : " +
                booleanParenthesis.calculateNumberOfWays(operators, operands));
    }
}
