/*Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed 
intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection 
of [1, 3] and [2, 4] is [2, 3].)

 

Example 1:



Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 

Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {

    //(own)(On^2的解法)
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        if(A == null || A.length == 0 || B == null || B.length == 0) {
            return new Interval[] {};
        }
        
        List<Interval> result = new ArrayList<>();
        for(Interval interA : A) {
            for(Interval interB : B) { //每一个Interval都扫一遍
                if((interA.start >= interB.start && interA.start <= interB.end) || //区间有交集
                   (interA.end >= interB.start && interA.end <= interB.end) || 
                   (interA.start <= interB.start && interA.end >= interB.end) || //区间完全包含
                   (interA.start >= interB.start && interA.end <= interB.end)) {
                    result.add(new Interval(Math.max(interA.start, interB.start), Math.min(interA.end, interB.end)));
                }
            }
        }
        return result.toArray(new Interval[result.size()]);
    }
}
