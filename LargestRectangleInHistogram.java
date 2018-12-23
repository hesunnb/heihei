/*Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle 
in the histogram.

 


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

 


The largest rectangle is shown in the shaded area, which has area = 10 unit.

 

Example:

Input: [2,1,5,6,2,3]
Output: 10

*/

class Solution {
    
    //非最优: O(n^2)
    public int largestRectangleArea(int[] heights) {
    
        if(heights == null || heights.length == 0) {
            return 0; //leetcode期待无效是用0表示, 那么这里就返回0
        }
        
        int maxArea = Integer.MIN_VALUE;
        for(int i = 0; i < heights.length; i++) { //思路就是从每个点出发, 定义双指针, 一个向前, 一个向后寻找有效区间(长方形)
            int j = i, k = i;
            while(j >= 0 && heights[j] >= heights[i]) {
                j--;
            }
            while(k < heights.length && heights[k] >= heights[i]) {
                k++;
            }
            j++; //找到之后出来要把j和k归位, 因为归位之后才是有效位置
            k--;
            int distance = k - j + 1; //计算区间距离
            maxArea = Math.max(maxArea, distance * heights[i]); //计算面积
        }
        return maxArea;
    }
    
    
    //单调栈: O(n)
    
}
