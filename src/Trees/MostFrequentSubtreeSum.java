package Trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFrequentSubtreeSum {
    /*
     *@Author : Sahil Khurana
     * Date : 05 June 2019
     *
     * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is
     * defined as the sum of all the node values formed by the subtree rooted at that node
     * (including the node itself). So what is the most frequent subtree sum value? If there is a tie,
     * return all the values with the highest frequency in any order.
     *
     * Examples 1
     * Input:
     *
     *   5
     *  /  \
     * 2   -3
     * return [2, -3, 4], since all the values happen only once, return all of them in any order.
     * Examples 2
     * Input:
     *
     *   5
     *  /  \
     * 2   -5
     * return [2], since 2 happens twice, however -5 only occur once.
     *
     * References :
     * https://leetcode.com/problems/subtree-of-another-tree/
     */

    Map<Integer, Integer> countMap = new HashMap<>();
    int maxCount = 0;

    public int[] findFrequentTreeSum(Node root) {
        dfs(root);
        List<Integer> resList = new ArrayList<>();
        for (int sum : countMap.keySet()) {
            if (countMap.get(sum) == maxCount) {
                resList.add(sum);
            }
        }
        int[] ret = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) ret[i] = resList.get(i);
        return ret;
    }

    int dfs(Node root) {
        if (root == null)
            return 0;
        int sum = root.data + dfs(root.left) + dfs(root.right);

        //put currSum in map
        countMap.put(sum, countMap.getOrDefault(sum, 0) + 1);
        maxCount = Math.max(maxCount, countMap.get(sum));

        return sum;
    }

}
