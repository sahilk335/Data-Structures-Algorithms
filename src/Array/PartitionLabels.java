package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartitionLabels {
    /*
     *@Author : Sahil Khurana
     * Date : 25 July 2019
     *
     * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that
     * each letter appears in at most one part, and return a list of integers representing the size of these parts.
     *
     * Example 1:
     * Input: S = "ababcbacadefegdehijhklij"
     * Output: [9,7,8]
     * Explanation:
     * The partition is "ababcbaca", "defegde", "hijhklij".
     * This is a partition so that each letter appears in at most one part.
     * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
     * Note:
     *
     * S will have length in range [1, 500].
     * S will consist of lowercase letters ('a' to 'z') only.
     *
     * Solution 1 : O(N)*O(26) 5% submission rate
     * 1. Take 2 count arrays to store first and last ocurrences of each alphabet
     * 2. create a pair of first and last occurence of each alphabet.
     * 3. sort the pair list with first occurence
     * 4. solve it using merge interval question.
     * 5. number of merged intervals length are the answer
     *
     * Solution 2. O(N)+O(26) 86% submission rate
     * 1. Take count array for last occurences of each alphabet in the input
     * 2. maintain an anchor . position j(max) in certain group
     * 3. if iterator i == j anchor... then that is one partition
     * 4. Repeat step 1 to 2 till iterator reaches end of array
     *
     */


    class Pairs {
        int s;
        int e;

        Pairs(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    //Solution 1
    public List<Integer> partitionLabelsSolution1(String s) {
        List<Integer> res = new ArrayList<>();
        int[] lastCharArr = new int[26];
        int[] firstCharArr = new int[26];
        int[] visiteArr = new int[26];
        Arrays.fill(visiteArr, -1);
        Arrays.fill(firstCharArr, -1);
        for (int i = 0; i < s.length(); i++) {
            if (firstCharArr[s.charAt(i) - 'a'] == -1) {
                firstCharArr[s.charAt(i) - 'a'] = i;
            }
            lastCharArr[s.charAt(i) - 'a'] = i;
        }

        List<Pairs> pairList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int charIdx = s.charAt(i) - 'a';
            if (visiteArr[charIdx] == -1) {
                pairList.add(new Pairs(firstCharArr[charIdx], lastCharArr[charIdx]));
                visiteArr[charIdx] = 0;
            }
        }

        pairList.sort((Pairs x, Pairs y) -> {
            return x.s - y.s;
        });

        for (int i = 0; i < pairList.size(); i++) {
            //System.out.println(pairList.get(i).s+","+pairList.get(i).e);
        }
        for (int i = 0; i < pairList.size() - 1; i++) {
            if (pairList.get(i).e > pairList.get(i + 1).s) {
                pairList.get(i + 1).e = Math.max(pairList.get(i).e, pairList.get(i + 1).e);
                pairList.get(i + 1).s = pairList.get(i).s;
            }
            if (pairList.get(i).e <= pairList.get(i + 1).s) {
                res.add(pairList.get(i).e - pairList.get(i).s + 1);
            }
        }
        if (pairList.get(pairList.size() - 2).e > pairList.get(pairList.size() - 1).s) {
            pairList.get(pairList.size() - 1).e = Math.max(pairList.get(pairList.size() - 1).e, pairList.get(pairList.size() - 2).e);
            pairList.get(pairList.size() - 1).s = pairList.get(pairList.size() - 2).s;
        }
        res.add(pairList.get(pairList.size() - 1).e - pairList.get(pairList.size() - 1).s + 1);

        return res;
    }

    //Solution 2
    public List<Integer> partitionLabelsSolution2(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;

        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }


}
