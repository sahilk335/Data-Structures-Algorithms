package Bits;

public class RightMostSetBit {
    /*
     *@Author : Sahil Khurana
     * Date : 11 July 2019
     *
     * Position of rightmost set bit
     * Write a one line function to return position of first 1 from right to left, in binary
     * representation of an Integer.
     *
     * I/P    18,   Binary Representation 010010
     * O/P   2
     * I/P    19,   Binary Representation 010011
     * O/P   1
     *
     * Solution :
     *  log 2 ( n&-n)
     *
     */
    int getFirstSetBitPos(int n) {
        return (int) ((Math.log10(n & -n)) / Math.log10(2)) + 1;
    }

    public static void main(String[] args) {
        RightMostSetBit rightMostSetBit = new RightMostSetBit();
        int n = 12;
        System.out.println(rightMostSetBit.getFirstSetBitPos(n));
    }
}
