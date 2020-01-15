/*Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, 
you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
All possible ways to reach at index 3 with value 0 are: 
index 5 -> index 4 -> index 1 -> index 3 
index 5 -> index 6 -> index 4 -> index 1 -> index 3 
Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true 
Explanation: 
One possible way to reach at index 3 with value 0 is: 
index 0 -> index 4 -> index 1 -> index 3
Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.
 

Constraints:

1 <= arr.length <= 5 * 10^4
0 <= arr[i] < arr.length
0 <= start < arr.length*/

class Solution {

    //dfs, 思路就是从start出发, 对每个点尝试左右走, 遍历所有点就ok, O(n)空间
    public boolean canReach(int[] arr, int start) {
        if(arr == null || arr.length == 0 || start < 0 || start > arr.length - 1) {
            return false;
        }
        
        return helper(arr, start, new boolean[arr.length]);
    }
    
    //result用来记录哪些点访问过了, 访问过的点再访问到就要返回, 否则[3,0,2,1,2]这种无法到达元素0的例子就会死循环
    private boolean helper(int[] arr, int start, boolean[] result) { 
        if(start < 0 || start > arr.length - 1) {
            return false;
        }
        if(result[start]) { //访问过了就返回
            return false;
        }
        if(arr[start] == 0) {
            return true;
        }
        
        result[start] = true; //此处标识该点访问过了
        if(helper(arr, start - arr[start], result)) {
            return true;
        }
        if(helper(arr, start + arr[start], result)) {
            return true;
        }
        return false;
    }
 
 
    //dfs, 如果不用O(n)空间的话, 就用一个小手段
    public boolean canReach(int[] arr, int start) {
        if(arr == null || arr.length == 0 || start < 0 || start > arr.length - 1) {
            return false;
        }
        
        return helper(arr, start);
    }
    
    private boolean helper(int[] arr, int start) {
        if(start < 0 || start > arr.length - 1 || arr[start] >= arr.length) { //如果start非法, 或者访问过该点
            return false;
        }

        if(arr[start] == 0) {
            return true;
        }

        int jump = arr[start]; //保留要跳的步数
        arr[start] += arr.length; //把arr[start]改为非法值, 标记为访问过该点的意思
        if(helper(arr, start - jump)) {
            return true;
        }
        if(helper(arr, start + jump)) {
            return true;
        }
        return false;
    }
}
