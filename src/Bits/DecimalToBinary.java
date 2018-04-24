package Bits;

import static Sort.QuickSort.swap;

public class DecimalToBinary {
    /*
     *@Author : Sahil
     * Date : 17 Apr
     *
     * Convert Decimal To Binary
     *
     * Example : Input :4 Output : 0100
     *
     * We divide a number by 2 recursively and store the remainder, and then show the reverse of the stored remainder
     *
     * Recursive division
     * 4/2->2/2->1/2->0/2
     * Remainder
     * 0->0->1->0
     * Reverse Remainder is the binary number
     * 0100
     */

    public int[] decimalToBinary(int input) {
        int[] res = new int[1000];
        int binary_length = 0;
        while (input != 0) {
            res[binary_length++] = input % 2;
            input = input / 2;
        }
        int j = binary_length;
        while (j >= 0) {
            System.out.print(res[j--]);
        }
        return res;
    }

    public int binarytoDecimal(int binary[]) {
        int sum = 0;
        int n = binary.length;
        for (int i = 0; i < n / 2; i++) {
            swap(binary, i, n - i - 1);
        }

        for (int i = 0; i < binary.length; i++) {
            sum += binary[i] * pow(2, i);
        }
        System.out.print("Binary : " + sum);
        return sum;
    }

    public long pow(int i, int j) {
        if (j == 0)
            return 1;
        return i * pow(i, j - 1);
    }

    public static void main(String args[]) {
        int input = 32;
        DecimalToBinary dec = new DecimalToBinary();
        int binary[] = {1, 1, 0, 0, 1, 0};
        dec.binarytoDecimal(binary);
    }
}
