package DynamicProgramming;

public class RodCutting {
    /*
     *@Author :Sahil
     * Date: 16 March 2018
     *
     * Cut a rod in a way , it maximizes the profit. Return the maximum profit
     * We are given rod of lenght say 6;
     * we have to cut a rod into given pieces,such that profit maximizes
     *
     * Given cuts can be ->say 1,2,3,4 ->with values ->2,4,1,5
     *Answer : 24 [picking 2 cuts 6 times, or picking 4 cuts 2 times and 2 cuts 1 times]
     * that means if you cut the rod in one peice profit can be 2 and so on
     * we have to maximize this profit, by cutting and selling accordingly
     *
     *
     * References :https://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
     *
     */

    public int rodCutDP(int cutsPrice[], int rodLength) {
        //Let the lenghts cuts are 1,2,3,4
        int T[] = new int[rodLength + 1];
        //with 0 cuts ,profits is 0
        T[0] = 0;
        //Since we have to maximize the profit, initialize it with - INF
        for (int i = 1; i < T.length; i++) {
            T[i] = Integer.MIN_VALUE;
        }

        for (int j = 0; j < cutsPrice.length; j++) {
            for (int i = 1; i <= rodLength; i++) {
                //For including the rod length value + rod length value of remainging lenght
                //or not picking that rod cut, which ever is maximum, store that
                if (i >= j + 1)
                    T[i] = Math.max(T[i], cutsPrice[j] + T[i - (j + 1)]);
            }
        }

      /* for (int i = 0; i < T.length; i++)
            System.out.print(T[i] + " ");*/
        return T[rodLength];
    }

    public static void main(String args[]) {
        RodCutting rc = new RodCutting();
        int cutsPrice[] = new int[]{2, 5, 7, 8};
        int RodLength = 5;
        System.out.print(rc.rodCutDP(cutsPrice, RodLength));
    }
}
