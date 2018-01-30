package Strings;

import java.util.Stack;

public class RemoveDuplicateLetters {
    /*
     * @Author : Sahil
     * Date : 30 Jan 2018
     *
     * References : https://leetcode.com/problems/remove-duplicate-letters/description/#
     *
     * Solution :
     * 1. Make a count array for 26 alphabets and add count of each alphabet peresent in given string
     * 2. Make visited array and intialize it with false;
     * 3. Apply Stack operation
     *
     *   3.1 Decrement the count of the current char
     *   3.2 If char is already visited then continue(skip)
     *   3.3 if current char is smaller than already present top element in stack
     *      3.3.1 then check if peek element count in count array is greater than 0 or not
     *              3.3.1.1 If it is not greater than zero then that means it should not be removed and push curr above
     *                      peek element.
     *              3.3.1.2 If it is greater than zero then pop the element and push the smaller inplace of it
     */

    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        int count[] = new int[26];

        //Make current string to char array
        char arr[] = s.toCharArray();

        // Count array for each alphabet
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - 'a']++;
        }
       /* Count array count check
            for(int i=0;i<count.length;i++){
            if(count[i]!=0)
            System.out.println("ch : "+(char)(i+'a')+" : "+count[i]);
        }*/

        //Visited array for each alphabet present
        boolean[] visited = new boolean[26];

        for (char ch : arr) {
            //Decrement the char count as soon as you visit
            count[ch - 'a']--;

            //If it is already visited then skip this char and loop
            if (visited[ch - 'a']) {
                continue;
            }

            while (!stack.isEmpty() &&
                    stack.peek() > ch &&
                    count[stack.peek() - 'a'] > 0) {
                //Mark the char as unvisited and pop from stack as it can be arranged
                //lexicographically because its count is still > 0
                visited[stack.peek() - 'a'] = false;
                stack.pop();
            }
            //push the current scanned char and mark it as visited
            stack.push(ch);
            visited[ch - 'a'] = true;
        }

        StringBuilder sb= new StringBuilder();

        for(char ch : stack){
            sb.append(ch);
        }

        return sb.toString();
    }

    public static void main(String args[]) {
        String s = "cbacdcbc";
        RemoveDuplicateLetters rdl = new RemoveDuplicateLetters();
        System.out.print(rdl.removeDuplicateLetters(s));
    }
}
