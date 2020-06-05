/*Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1*/

public class Solution {

    public int search(int[] A, int target) {
        // write your code here
        //九章给的思路是画图，分红线和绿线分别在一四象限递增，一象限是红色，四象限是绿色
        //因为还是二分方法，所以是O(logn)复杂度
        
        //O(logn):
        if(A == null || A.length == 0) {
            return -1;
        }
        
        int start = 0, end = A.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(A[start] < A[mid]) { //判断mid在红线上还是在绿线上,小于红线，大于绿线
                if(A[start] <= target && target <= A[mid]) { //target在s到m之间的区域内
                    end = mid;
                }
                else { 
                    start = mid;
                }
            } else {
                if(A[mid] <= target && target <= A[end]) { //target在m到e之间的区域内
                    start = mid;
                }
                else { 
                    end = mid;
                }
            }
        }
        
         //通过收缩，start和end有一个是最终的结果
        if(A[start] == target) {
            return start;
        }
        else if(A[end] == target) {
            return end;
        }
        
        return -1;
    }
}
