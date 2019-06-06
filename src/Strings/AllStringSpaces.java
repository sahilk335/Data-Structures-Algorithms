package Strings;

import java.util.ArrayList;
import java.util.List;

public class AllStringSpaces {
    /*
     *@Author : Sahil Khurana
     * Date : 06 June 2019
     *
     * Print all possible strings that can be made by placing spaces
     * Given a string you need to print all possible strings that can be made by placing spaces (zero or one) in between them
     * Examples :
     *
     * Input :  str[] = "ABC"
     * Output : ABC
     *         AB C
     *         A BC
     *         A B C
     *
     * Input : str[] = "ABCD"
     * Output : ABCD
     *         A BCD
     *          AB CD
     *          A B CD
     *         ABC D
     *         A BC D
     *      AB C D
     *       A B C D
     * Solution :
     *
     * Let's take string abcd as an example. There are 3 possible places where space can be put:
     *
     * Between "a" and "b"
     * Between "b" and "c"
     * Between "c" and "d"
     * So generally if length of your string is length then you have length - 1 places for spaces.
     *
     * Assume that each such place is represented with a separate digit in a binary number. This digit is 0 when we don't put space there, and 1 when we do. E.g.:
     *
     * a b c d
     *
     * 0 0 0 means that we don't put any spaces - abcd
     *
     * 0 0 1 means that we put space between "c" and "d" only - abc d
     *
     * 0 1 0 means that we put space between "b" and "c" only - ab cd
     *
     * 0 1 1 means ab c d
     *
     * 1 0 0 means a bcd
     *
     * 1 0 1 means a bc d
     *
     * 1 1 0 means a b cd
     *
     * 1 1 1 means a b c d
     *
     * Converting 000, 001, 010, ..., 111 from binary to decimal will give us values 0, 1, 2, ..., 7.
     *
     * With 3 places for spaces we have 8 options to put them. It's exactly 2^3 or 2^(length - 1).
     *
     * Thus we need to iterate all numbers between 0 (inclusive) and 2^(length - 1) (exclusive).
     *
     * Condition (i & (1 << j)) > 0 in the code you provided just checks whether digit at position j (starting from the end, 0-based) is 0 (and we don't need to insert space) or 1 (and space should be added).
     *
     * Let's take value 6 (110 in binary) for example.
     *
     * (6 & (1 << 0)) = 110 & 001 = 000 = 0 (condition > 0 is not met)
     *
     * (6 & (1 << 1)) = 110 & 010 = 010 = 2 (condition > 0 is met)
     *
     * (6 & (1 << 2)) = 110 & 100 = 100 = 4 (condition > 0 is met)
     *
     * References :
     * https://www.geeksforgeeks.org/print-possible-strings-can-made-placing-spaces-2/
     */

    public List<String> addAllSpaces(String s) {
        List<String> resList = new ArrayList<>();
        char[] charArr = s.toCharArray();
        int len = charArr.length;
        int resSize = (int) Math.pow(2, len - 1);

        for (int i = 0; i < resSize; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                sb.append(charArr[j]);
                if ((i & (1 << j)) > 0) {
                    sb.append(' ');
                }
            }
            resList.add(sb.toString());
        }
        return resList;
    }

    public static void main(String[] args) {
        AllStringSpaces allStringSpaces = new AllStringSpaces();
        String str = "ABCD";
        for (String s : allStringSpaces.addAllSpaces(str)) {
            System.out.println(s);
        }
    }
}
