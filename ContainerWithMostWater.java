/*Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that 
the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the 
container contains the most water.

Note: You may not slant the container and n is at least 2.
*/

class Solution {

    //注意, 这里面取两条line, left与right, 计算的是以短边为基准的矩形面积, 不是梯形面积
    public int maxArea(int[] height) {
        
        if(height == null || height.length == 0) {
            return 0;
        }
        
        int left = 0, right = height.length - 1; //left与right分别在height的最左右两端
        int maxArea = 0;

        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left)); //挑left与right短的进行计算
            if (height[left] < height[right]) { //left与right谁短移动谁
                left++;
            }   
            else {
                right--;
            }
        }
        return maxArea;
    }
}
