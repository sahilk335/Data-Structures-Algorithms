package Array;

import java.util.ArrayList;
import java.util.List;

public class MergeIntervals {

    /*
     * Given a collection of intervals, merge all overlapping intervals.
     *
     * Example 1:
     *
     * Input: [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
     * Example 2:
     *
     * Input: [[1,4],[4,5]]
     * Output: [[1,5]]
     * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
     *
     *
     * References :
     * https://leetcode.com/problems/merge-intervals/
     *
     * Solution :
     * 1. First Sort the array according to the start element
     * 2. Simply merge on condition prevElem.end > CurrElem.start
     */
    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {

        if (intervals == null || intervals.size() == 0)
            return null;

        List<Interval> result = new ArrayList<>();
        //Sort the list according to the start index
        intervals.sort((i1, i2) -> {
            return i1.start - i2.start;
        });

        Interval prev = intervals.get(0);
        Interval curr = prev;       // This is to cover test case when there is only 1 element.

        for (int i = 1; i < intervals.size(); i++) {
            curr = intervals.get(i);
            if (prev.end >= curr.start) {
                prev.end = Math.max(prev.end, curr.end);
            } else {
                result.add(prev);
                prev = curr;
            }
            curr = prev;  // Ignore this, this is only useful when there is  only 2 elements [1,4][4,5]
        }

        //add last element
        result.add(curr);
        return result;
    }

    public static void main(String[] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();
        List<Interval> intervalList = new ArrayList<>();
        intervalList.add(new Interval(1, 3));
        intervalList.add(new Interval(2, 6));
        intervalList.add(new Interval(8, 10));
        intervalList.add(new Interval(15, 18));

        List<Interval> asnList = mergeIntervals.merge(intervalList);
        for (Interval intervals : asnList) {
            System.out.println(intervals.start + "," + intervals.end);
        }
    }
}
