package Array;

import java.util.Stack;

public class SecretSignature {
    /*
     * Source : https://www.geeksforgeeks.org/form-minimum-number-from-given-sequence/
     * Form minimum number from given sequence
     * 4
     * Given a pattern containing only I’s and D’s. I for increasing and
     * D for decreasing. Devise an algorithm to print the minimum number
     * following that pattern. Digits from 1-9 and digits can’t repeat.
     *
     * Examples:
     *
     *    Input: D        Output: 21
     *    Input: I        Output: 12
     *    Input: DD       Output: 321
     *    Input: II       Output: 123
     *    Input: DIDI     Output: 21435
     *    Input: IIDDD    Output: 126543
     *    Input: DDIDDIID Output: 321654798
     *
     *    References : https://leetcode.com/articles/find-permutation/
     */

    public int[] secretSignatureArrayStack(String s) {
        int sol[] = new int[s.length() + 1];
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 1; i < s.length() + 1; i++) {
            if (s.charAt(i - 1) == 'I') {
                stack.push(i);
                while (!stack.isEmpty()) {
                    sol[j++] = stack.pop();
                }
            } else
                stack.push(i);
        }
        stack.push(s.length() + 1);
        while (!stack.isEmpty()) {
            sol[j++] = stack.pop();
        }
        return sol;
    }

    public int[] secretSignatureArrayPointers(String s) {
        int[] sol = new int[s.length() + 1];
        for (int i = 0; i < sol.length; i++)
            sol[i] = i + 1;
        int i = 1;
        while (i <= s.length()) {
            int j = i;
            while (i <= s.length() && s.charAt(i - 1) == 'D')
                i++;
            reverse(sol, j - 1, i);
            i++;
        }
        return sol;
    }

    public void reverse(int[] a, int start, int end) {
        for (int i = 0; i < (end - start) / 2; i++) {
            int temp = a[i + start];
            a[i + start] = a[end - i - 1];
            a[end - i - 1] = temp;
        }
    }

    public static void main(String args[]) {
        SecretSignature ss = new SecretSignature();
        int sol[];
        String s = "IIDDD";
        sol = ss.secretSignatureArrayPointers(s);
        for (int i = 0; i < sol.length; i++) {
            System.out.print(sol[i]+" ");
        }
    }
}
