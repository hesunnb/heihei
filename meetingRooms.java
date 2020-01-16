/*Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person 
could attend all meetings.

样例
Example1

Input: intervals = [(0,30),(5,10),(15,20)]
Output: false
Explanation: 
(0,30), (5,10) and (0,30),(15,20) will conflict
Example2

Input: intervals = [(5,8),(9,15)]
Output: true
Explanation: 
Two times will not conflict 
注意事项
(0,8),(8,10) is not conflict at 8*/

/**
 * Definition of Interval:
 * public class Interval {
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
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
		// Write your code here
		if(intervals == null) {
			return false;
		}
		
		Collections.sort(intervals, new meetingComparator());
		for(int i = 1; i < intervals.size(); i++) {
			if(intervals.get(i).start < intervals.get(i - 1).end) {
				return false;
			}
		}
		return true;
	}

	class meetingComparator implements Comparator<Interval> {

		@Override
		public int compare(Interval o1, Interval o2) {
			// TODO Auto-generated method stub
			return o1.start - o2.start;
		}
	}
}
