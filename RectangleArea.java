/*Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.*/

public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int areaOfSqrA = (C - A) * (D - B); //矩形A面积
        int areaOfSqrB = (G - E) * (H - F); //矩形B面积
        
        int left = Math.max(A, E); //重复部分的左边界
        int right = Math.min(G, C); //重复部分的右边界
        int bottom = Math.max(B, F); //重复部分的下边界
        int top = Math.min(D, H); //重复部分的上边界
        
        int overlap = 0;
        if(right > left && top > bottom) { //如果右大于左并且上大于下
            overlap = (right - left) * (top - bottom); //算出重叠部分面积
        }
        return areaOfSqrA + areaOfSqrB - overlap; //目标是求总面积, 所以是两个矩形面积和减去重复部分面积
    }
}
