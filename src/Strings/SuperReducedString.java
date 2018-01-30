package Strings;

public class SuperReducedString {
    /*
     * @Author : Sahil
     * Date : 30 Jan 2018
     *
     * Remove adjacent duplicates in unlimited number of moves until string is reduced
     *
     * Example :
     * aaabccddd → abccddd
     * abccddd → abddd
     * abddd → abd
     * References : https://www.hackerrank.com/challenges/reduced-string/problem
     *
     */
    String superReducedString(String str) {

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                //Remove the ith index from current string
                str = str.substring(0, i - 1) + str.substring(i + 1);
                i = 0;
            }
        }
        if (str.length() == 0) {
            return ("Empty String");
        } else {
            return (str);
        }
    }

    public static void main(String args[]) {
        String str = "abba";
        SuperReducedString srs = new SuperReducedString();
        System.out.print(srs.superReducedString(str));
    }
}
