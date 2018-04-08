package DynamicProgramming;

public class GivenPreorderSequence {
    /*
     *@Author : Sahil
     * Date : 08 Apr 2018
     *
     * Given a preorder sequence how many unique trees can be created
     * Solution is catalan number. Number of tree is exactly same
     * as number of unique BST create with array of size n
     * The way it works for preorder sequence is as follows
     * Suppose our preorder sequence is 1 2 3 4
     * So we need to compute following things
     * count(3)* 2 (combination of 2,3 and 4 on both side of 1)
     * count(1)*count(2) (combination of 2 on one side and 3, 4 on other side)
     * count(2)*count(1) (combinatino of 2,3 on one side and 4 on other side)
     * count(3)*2 can be broken into count(3)*count(0) + count(0)*count(3)
     * So our final result is
     * count(0)*count(3) + count(1)*count(2) + count(2)*count(1) + count(3)*count(0)
     * which is a catalan number
     */

    public long numberOfBinaryTree(int n) {
        CatalanNumber catalan = new CatalanNumber();
        return catalan.catalanRes(n);
    }

    public static void main(String args[]) {
        GivenPreorderSequence preorder = new GivenPreorderSequence();
        int n = 3;
        System.out.print(preorder.numberOfBinaryTree(n));
    }
}
