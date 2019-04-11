package Array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms2 {
    /*
     *@Author : Sahil
     * Date : 11 April 2019
     *
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
     * find the minimum number of conference rooms required.
     *
     *
     * References :
     * https://www.programcreek.com/2014/05/leetcode-meeting-rooms-ii-java/
     *
     *
     * Solution :
     * The basic idea of the solution is that we sequentially assign meeting to a room. We use a min heap to track the
     * earliest ending meeting. Whenever an old meeting ends before a new meeting starts, we remove the old meeting.
     * Otherwise, we need an extra room.
     */

    static class Interval {
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

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        Arrays.sort(intervals, (i1, i2) -> {
            return i1.start - i2.start;
        });

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(intervals[0].end);

        int count = 1;
        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i].start >= priorityQueue.peek()) {      //If  overlap between min time and next start then these meetings
                priorityQueue.poll();                                //then these meetings can not be done in same room, so just add in minHeap,remove curr head, and count++
            } else {
                count++;
            }
            priorityQueue.add(intervals[i].end);
        }
        return count;

    }

    public static void main(String[] args) {
        MeetingRooms2 meetingRooms2 = new MeetingRooms2();
        Interval[] intervals = new Interval[5];
        intervals[0] = new Interval(2, 15);
        intervals[1] = new Interval(4, 9);
        intervals[2] = new Interval(16, 23);
        intervals[3] = new Interval(9, 29);
        intervals[4] = new Interval(36, 46);

        System.out.println("Minimum rooms required to complete all Meetings : " + meetingRooms2.minMeetingRooms(intervals));

    }
}
