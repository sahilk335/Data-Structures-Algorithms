package DynamicProgramming;

public class EggDropping {
    /*
     *@Author : Sahil
     * Date : 27 March 2018
     *
     * If you are given 6 floors & 2 eggs. In how many worst attempts, you will be able to find the critical floor,
     * from which if you drop a egg, it will break
     *
     * Conditions :
     *
     * 1. If egg is dropped & it gets break from 2nd floor, it will break from 3 rd and upper floors also
     * 2. if eggg is dropped & it gets break from 2nd floor, then we have to check below floors to find critical floors
     * 3. If you throw an egg from a floor & it does not breaks, then you can reuse the egg
     *
     * Solution :
     *
     * Create egg x floor matrix
     * initialize first row as 1,2,3,4,5,6  --> if there are 6 floors,meaning that, if you have 1 floor and 1 egg
     * then attempts required are 1 for 1 floor, 2 attempts for 2nd floor, 3 attempts for 3 rd floor
     * after that apply dynamic programming for 2 eggs. considering at ith floor -> if egg breaks or not
     *
     * References :
     * http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
     * https://www.youtube.com/watch?v=3hcaVyX00_4
     */

    public int eggDropAttempts(int eggs, int floors) {
        int T[][] = new int[eggs + 1][floors + 1];

        //Initialize the values of attempts on all floors when number of eggs given is 1
        for (int floor = 1; floor <= floors; floor++) {
            T[1][floor] = floor;
        }

        int c = 0;

        for (int egg = 2; egg <= eggs; egg++) {
            for (int floor = 1; floor <= floors; floor++) {

                T[egg][floor] = Integer.MAX_VALUE;
                //With Given floors and eggs, we try to drop from each floor again, and find the min attempts

                for (int currDropFloor = 1; currDropFloor <= floor; currDropFloor++) {

                    c = 1 + Math.max(T[egg - 1][currDropFloor - 1], T[egg][floor - currDropFloor]);

                    if (c < T[egg][floor]) {
                        T[egg][floor] = c;

                    }
                }
            }
        }

        return T[eggs][floors];
    }

    public static void main(String args[]) {
        EggDropping eg = new EggDropping();
        int eggs = 2;
        int floors = 6;
        System.out.print("Number of Attempts to find critical floor : " + eg.eggDropAttempts(eggs, floors));
    }
}
