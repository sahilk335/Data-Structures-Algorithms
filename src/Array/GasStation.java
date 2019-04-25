package Array;

public class GasStation {
    /*
     *@Author : Sahil
     * Date : 25 Apr 2019
     *
     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
     *
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next
     * station (i+1). You begin the journey with an empty tank at one of the gas stations.
     *
     * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction,
     * otherwise return -1.
     *
     * Note:
     *
     * If there exists a solution, it is guaranteed to be unique.
     * Both input arrays are non-empty and have the same length.
     * Each element in the input arrays is a non-negative integer.
     * Example 1:
     *
     * Input:
     * gas  = [1,2,3,4,5]
     * cost = [3,4,5,1,2]
     *
     * Output: 3
     *
     * Explanation:
     * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
     * Travel to station 4. Your tank = 4 - 1 + 5 = 8
     * Travel to station 0. Your tank = 8 - 2 + 1 = 7
     * Travel to station 1. Your tank = 7 - 3 + 2 = 6
     * Travel to station 2. Your tank = 6 - 4 + 3 = 5
     * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
     * Therefore, return 3 as the starting index.
     *
     * Refrences :
     * https://leetcode.com/problems/gas-station/discuss/215701/Simple-Java-solution-beats-100-with-detailed-explain
     *
     *
     * Solution :
     * It has 2 cases to check only
     *
     * Case 1 : if total sum of gas[i]-cost[i] >= 0 then solution exist else return -1
     *
     * Why ?
     * because to go to station i+1 , it is possible only if
     * gas[i]-cost[i] > = 0
     *
     * Case 2 : if car starts at A and can not reach B. Any station between A and B, then can not reach B.so check from pos B
     *
     * so at any point of time sum of gas[i]-cost[i] reaches negative,then i+1 is the start index
     *
     * based on case to return the value
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int totalCost = 0;
        int startPos = 0;
        int currTotalCost = 0;

        for (int i = 0; i < gas.length; i++) {
            totalCost += gas[i] - cost[i];
            currTotalCost += gas[i] - cost[i];
            if (currTotalCost < 0) {        //If car starts at A and can not reach B. Any station between A and B
                //  can not reach B.(B is the first station that A can not reach.)
                currTotalCost = 0;
                startPos = (i + 1);
            }
        }

        return totalCost >= 0 ? startPos : -1;
    }

    public static void main(String[] args) {
        GasStation gasStation = new GasStation();
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println(gasStation.canCompleteCircuit(gas, cost));
    }
}
