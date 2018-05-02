package Array;

public class NonRepeatingRange {
    /*
     *@Author : Sahil
     *
     * Date 3 May 2018
     * Given a number x , and its range y to z
     *
     * Find the count of number x multiply by the numbers in range y to z , that does not contains the digit
     * in number y to z
     *
     * Example : x=2,y=4,y=10
     *
     * 2*4 =8
     * 2*5=10
     * 2*6=12
     * 2*7=14
     * 2*8=16
     * 2*9=18
     * 2*10=20  -> Invalid because 20 contains 0 and 10 contains 0
     *
     * so answer is 6
     *
     * Reference : GoldManSachs Hackerrank Non Repeating Digit Range
     *
     */


    int nonRepeatingDigitProductCount(int x, int y, int z) {
        /*
         * Write your code here.
         */

        int range = z - y + 1;
        int inValidCount = 0;

        for (int i = y; i <= z; i++) {
            int currNum = x * i;
            String currNumString = String.valueOf(currNum);
            String currMultiplierString = String.valueOf(i);
            char[] multiplier = currMultiplierString.toCharArray();
            //System.out.println(x+" X "+i+" = "+currNumString+" -->>> currMultiplier string "+ i);
            for (int j = 0; j < multiplier.length; j++) {
                char charCheck = multiplier[j];
                if (currNumString.indexOf(charCheck) != -1) {
                    inValidCount++;
                    break;
                }
            }
        }

        return range - inValidCount;

    }

    public static void main(String args[]) {
        int x = 3;
        NonRepeatingRange nonRepeatingRange = new NonRepeatingRange();
        System.out.print(nonRepeatingRange.nonRepeatingDigitProductCount(2, 10, 15));

     /* for(int i=1;i<=5;i++){
          if(i==2){
              break;
          }
          System.out.print(i);
      }*/
    }
}
