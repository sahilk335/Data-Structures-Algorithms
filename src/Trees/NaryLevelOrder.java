package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NaryLevelOrder {
    /*
     *@Aurhor : Sahil Khurana
     * Date : 06 June 2019
     *
     * Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
     *
     * References : https://leetcode.com/problems/n-ary-tree-level-order-traversal/
     *
     * Solution 1 : Iterative
     * Solution 2 : recursive
     *
     */
    public List<List<Integer>> levelOrderIterative(Node root) {
        List<List<Integer>> ret = new LinkedList<>();

        if (root == null) return ret;

        Queue<Node> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> curLevel = new LinkedList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node curr = queue.poll();
                curLevel.add(curr.data);
                for (Node c : curr.children)
                    queue.offer(c);
            }
            ret.add(curLevel);
        }

        return ret;
    }

    public List<List<Integer>> levelOrderRecursive(Node root) {

        List<List<Integer>> res = new ArrayList<>();
        levelMaker(res, root, 0);
        return res;
    }

    public void levelMaker(List<List<Integer>> res, Node root, int level) {
        if (root == null)
            return;

        if (level >= res.size()) {
            List<Integer> createLevellist = new ArrayList<>();
            createLevellist.add(root.data);
            res.add(createLevellist);
        } else {
            List<Integer> alreadyCreatedList = res.get(level);
            alreadyCreatedList.add(root.data);
        }

        for (Node node : root.children) {
            levelMaker(res, node, level + 1);
        }
    }
}
