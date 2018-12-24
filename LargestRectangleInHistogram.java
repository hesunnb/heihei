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
    //用单调栈的前提是: 每个数往左/右第一个比他小/大的数是谁, 就为了找到这个数就用单调栈
    /*用九章算法强化班中讲过的单调栈(stack)。维护一个单调递增栈，逐个将元素 push 到栈里。push 进去之前先把 >= 自己的元素 pop 出来。
    每次从栈中 pop 出一个数的时候，就找到了往左数比它小的第一个数（当前栈顶）和往右数比它小的第一个数（即将入栈的数），
    从而可以计算出这两个数中间的部分宽度 * 被pop出的数，就是以这个被pop出来的数为最低的那个直方向两边展开的最大矩阵面积。
    因为要计算两个数中间的宽度，因此放在 stack 里的是每个数的下标。*/
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int maxArea = 0;
        int current = 0;
        for (int i = 0; i <= heights.length; i++) {
            if(i == heights.length) {
                current = -1; //到最后了, 确保current最小, 弹出栈中剩余的所有值进行计算
            } else {
                current = heights[i];
            }
            while (!stack.isEmpty() && current <= heights[stack.peek()]) {
                int h = heights[stack.pop()]; //注意这里会pop出一个元素
                int w = stack.isEmpty() ? i : i - stack.peek() - 1; //然后这里的stack.peek()就会有变化
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }

        return maxArea;
    }
}
