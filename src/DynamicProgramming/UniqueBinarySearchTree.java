package DynamicProgramming;

public class UniqueBinarySearchTree {
    /*
     *@Author : Sahil
     * Date : 08 Apr 2018
     *
     *Count number of binary search tree created for array of size n
     *
     * Reference :
     * http://www.geeksforgeeks.org/program-nth-catalan-number/
     */

    public long uniqueTrees(int n) {
        CatalanNumber catalan = new CatalanNumber();
        return catalan.catalanRes(n);
    }

    public static void main(String args[]) {
        UniqueBinarySearchTree ubst = new UniqueBinarySearchTree();
        int n = 5;
        System.out.print(ubst.uniqueTrees(n));
    }
}
