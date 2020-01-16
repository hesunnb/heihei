/*Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number 
of conference rooms required.

样例
Example1

Input: intervals = [(0,30),(5,10),(15,20)]
Output: 2
Explanation:
We need two meeting rooms
room1: (0,30)
room2: (5,10),(15,20)
Example2

Input: intervals = [(2,7)]
Output: 1
Explanation: 
Only need one meeting room*/

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    public int minMeetingRooms(List<Interval> intervals) {
        // Write your code here
        if (intervals == null || intervals.size() == 0) {
            return 0;
        }

        // Sort the intervals by start time
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        // Use a min heap to track the minimum end time of merged intervals
        PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.size(), new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.end - b.end;
            }
        });

        // start with the first meeting, put it to a meeting room
        heap.offer(intervals.get(0));

        for (int i = 1; i < intervals.size(); i++) {
            // get the meeting room that finishes earliest
            Interval interval = heap.poll();

            if (intervals.get(i).start >= interval.end) {
                // if the current meeting starts right after
                // there's no need for a new room, merge the interval
                interval.end = intervals.get(i).end;
            } else {
                // otherwise, this meeting needs a new room
                heap.offer(intervals.get(i));
            }

            // don't forget to put the meeting room back
            heap.offer(interval);
        }

        return heap.size();
    }
}
