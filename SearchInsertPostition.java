/*
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were 
inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0 
*/

public class Solution {
    /** 
     * param A : an integer sorted array
     * param target :  an integer to be inserted
     * return : an integer
     */
    public int searchInsert(int[] A, int target) {
        // write your code here
        
        //方法一：find the first position >= target（首选）
        if(A == null || A.length == 0) {
            return 0;
        }
        
        int start = 0, end = A.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        //退出来以后，start和end的值现在已经固定了，接下来就是判断target相对于start和end的位置
        //==是target存在于数组中的情况
        if(A[start] >= target) { //target在start前返回start的下标
            return start;
        }
        else if(A[end] >= target) { //target在start后end前返回end下标
            return end;
        }
        else { //target在end后返回end下标+1
            return end + 1;
        }
        
        //复杂度logn
        
        
        //方法二：find the last position < target, return +1， 要特判一下target小于所有数组里面的元素
        //算了
    }
}
