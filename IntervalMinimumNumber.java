/*Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers 
[start, end]. For each query, calculate the minimum number between index start and end in the given array, return the result list.

Example
For array [1,2,7,8,5], and queries [(1,2),(0,4),(2,4)], return [2,1,5]

Challenge
O(logN) time for each query*/

public class Solution {
    
    
    
    //原始做法, O(mn)
    public List<Integer> intervalMinNumber(int[] A, List<Interval> queries) {
        // write your code here
        if(A == null || A.length == 0) {
            return new ArrayList<>();
        }
        
        List<Integer> result = new ArrayList<>();
        for(Interval inter : queries) {
            int start = inter.start;
            int end = inter.end;
            int min = Integer.MAX_VALUE;
            for(int i = start; i <= end; i++) {
                min = Math.min(min, A[i]);
            }
            result.add(min);
        }
        
        return result;
    }
}
