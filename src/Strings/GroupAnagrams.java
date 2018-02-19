package Strings;

import java.util.*;

public class GroupAnagrams {
    /*
     *@Author:Sahil
     * Date : 19 Feb 2018
     *
     * References : https://leetcode.com/problems/group-anagrams/description/
     *
     * Main Hint : Two strings are anagrams if and only if their sorted strings are equal.
     *
     * Solution 1 :
     * 1. Sort the string and put in the hashmap,(Key) -> If it is not present already and then add the unsorted string
     *    as its value also
     * 2. If it is present then add it to its value
     *
     * Solution 2 :
     * Make hashtable key as appended # and count array
     * the hashable representation of our count will be a string delimited with '#' characters.
     * For example, abbccc will be #1#2#3#0#0#0...#0 where there are 26 entries total.
     */

    public List<List<String>> groupAnagramsSol1(String[] strs) {
        //Base case if input given is null
        if (strs.length == 0)
            return new ArrayList<>();
        List solution = new ArrayList<>();

        //Create a hasmmap of (key,value) -> (Sorted String, ArrayListOfSolutions)
        Map<String, List> map = new HashMap<String, List>();
        for (String s : strs) {
            char sortedCharacterArray[] = s.toCharArray();
            Arrays.sort(sortedCharacterArray);
            String key = String.valueOf(sortedCharacterArray);
            //If this is new type of string, store its sorted form as a key
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList());
            }
            map.get(key).add(s);
        }

        /*for (List<String> list : map.values()) {
            System.out.println(list);
        }*/

        return new ArrayList(map.values());
    }

    public List<List<String>> groupAnagramsSol2(String[] strs) {
        if (strs.length == 0)
            return new ArrayList();
        Map<String, List> map = new HashMap<String, List>();
        for(String s : strs){
            int count[]=new int[26];
            for(char c:s.toCharArray()){
                count[c-'a']++;
            }
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<26;i++){
                sb.append('#');
                sb.append(count[i]);
            }
            String key=sb.toString();
            if(!map.containsKey(key)){
                map.put(sb.toString(),new ArrayList());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String args[]) {
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        String str[] = {"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams.groupAnagramsSol2(str);
    }
}
