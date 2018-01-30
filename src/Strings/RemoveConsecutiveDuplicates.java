package Strings;

public class RemoveConsecutiveDuplicates {
    /*
     * @Author : Sahil
     * Date : 30 Jan 2018
     *
     * Remove Consecutive Dupliates in a string in 1 Pass in O(N)
     *
     * Example :
     * AABBCDDAAB -> ABCDAB
     * ABBBCCD -> ABCD
     *
     * Solution:
     *
     * Take 3 variables
     * 1. index -> current char
     * 2. Fast -> fast pointer will scan all adjacent duplicates in a while loop
     * 3. Slow-> stores start of Fast pointer
     */

    public int removeConsecutiveDuplicates(String s) {
        char[]input=s.toCharArray();
        int slow =0;
        int fast=0;
        int index=0;
        while(fast< s.length()){
            while(fast<s.length()&&input[slow]==input[fast]){
                fast++;
            }
            input[index]=input[slow];
            index++;
            slow=fast;
        }
        for(int i=0;i<index;i++){
            System.out.print(input[i]);
        }
        return index;
    }

    public static void main(String args[]) {
        String str = "AABCD";
        RemoveConsecutiveDuplicates rcd = new RemoveConsecutiveDuplicates();
        int index = rcd.removeConsecutiveDuplicates(str);

    }
}
