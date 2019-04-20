package Array;

import java.util.ArrayList;
import java.util.List;

public class NumberChecker {

    /*
     *@Author : Sahil Khurana
     * Date : 20 APr 2019
     * Interview : GoldManSachs HackerRank Test
     *
     * Given an array as input, find out the integers which contains digit 1,2,3 numbers in its formation.
     * The order need not be sequential
     *
     * In case multiple number qualifies the condition, return ascending order seperated by commas
     *
     * Example :
     * Input :
     * 1456
     * 345671
     * 43218
     * 123
     *
     * Output : 123,43218
     */


    /*
     * Complete the function below.
     */
    static String findQualifiedNumbers(int[] numberArray) {
        String strArr[] = new String[numberArray.length];
        for (int i = 0; i < numberArray.length; i++) {
            strArr[i] = String.valueOf(numberArray[i]);
        }

        List<String> result = new ArrayList<>();
        for (String strNum : strArr) {
            if (strNum.contains("1") && strNum.contains("2") && strNum.contains("3")) {
                result.add(strNum);
            }
        }

        if (result.size() > 0) {
            result.sort((s1, s2) -> {
                return Integer.valueOf(s1) - Integer.valueOf(s2);
            });


            String resultStr = "";
            for (int i = 0; i < result.size() - 1; i++) {
                resultStr += result.get(i) + ",";
            }
            resultStr += result.get(result.size() - 1);

            return resultStr;
        }
        return String.valueOf(-1);
    }

    public static void main(String[] args) {
        int[] arr = {1456, 345671, 43218, 123};
        System.out.println(findQualifiedNumbers(arr));

    }
}
