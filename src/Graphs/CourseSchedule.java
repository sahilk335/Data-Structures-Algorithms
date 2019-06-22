package Graphs;

import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule {

    /*
     *@Author : Sahil
     * Date : 12 May 2019
     *
     * There are a total of n courses you have to take, labeled from 0 to n-1.
     *
     * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
     * which is expressed as a pair: [0,1]
     *
     * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
     *
     * Example 1:
     *
     * Input: 2, [[1,0]]
     * Output: true
     * Explanation: There are a total of 2 courses to take.
     *              To take course 1 you should have finished course 0. So it is possible.
     * Example 2:
     *
     * Input: 2, [[1,0],[0,1]]
     * Output: false
     * Explanation: There are a total of 2 courses to take.
     *              To take course 1 you should have finished course 0, and to take course 0 you should
     *              also have finished course 1. So it is impossible.
     * Note:
     *
     * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about
     * how a graph is represented.
     * You may assume that there are no duplicate edges in the input prerequisites.
     *
     *
     * Reference :
     * https://leetcode.com/problems/course-schedule/
     *
     * Solution :
     * 1. Topological sort (indegree concept)
     *
     */

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int[][] matrix = new int[numCourses][numCourses];   //i->j

        for (int i = 0; i < prerequisites.length; i++) {
            int pre = prerequisites[i][1];
            int curr = prerequisites[i][0];
            if (matrix[pre][curr] == 0)
                indegree[curr]++;
            matrix[pre][curr] = 1;
        }

        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int removedCourse = queue.poll();
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (matrix[removedCourse][i] != 0) {
                    if (--indegree[i] == 0) {
                        queue.add(i);
                    }
                }
            }
        }

        return count == numCourses;   //else there is a cycle
    }

    public static void main(String[] args) {
        CourseSchedule courseSchedule = new CourseSchedule();
        int[][] matrix = {{1, 0}, {1, 0}};
        System.out.println(courseSchedule.canFinish(2, matrix));
    }
}
