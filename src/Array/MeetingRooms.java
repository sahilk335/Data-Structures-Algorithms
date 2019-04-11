package Array;

import java.util.Arrays;

public class MeetingRooms {
    /*
     *@Author : Sahil
     * Date : 11 April 2019
     * Given an array of meeting time intervals consisting of start and end times [s1, e1], [s2, e2], ... , determine if
     * a person could attend all meetings.
     *
     * For example,
     * Given [ [0, 30], [5, 10], [15, 20] ],
     * return false.
     *
     * Refrences :
     * https://www.programcreek.com/2014/07/leetcode-meeting-rooms-java/
     *
     * Solution :
     * If a person can attend all meetings, there must not be any overlaps between any meetings.
     * After sorting the intervals, we can compare the current end and next start.
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

    public boolean findMinNoOfMeetingRooms(Interval[] intervals) {
        //Sort w.r.t start time of interval
        Arrays.sort(intervals, (i1, i2) -> {
            return i1.start - i2.start;
        });
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i + 1].start)
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        MeetingRooms meetingRooms = new MeetingRooms();
        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(1, 2);
        intervals[1] = new Interval(3, 4);
        intervals[2] = new Interval(2, 5);
        System.out.println(meetingRooms.findMinNoOfMeetingRooms(intervals));
    }

}
