/*Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to 
trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are 
being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6*/

class Solution {

    // O(n^2)解法
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        // To store the maximum water
        // that can be stored
        int res = 0;

        // For every element of the array
        for (int i = 1; i < height.length - 1; i++) {

            // Find the maximum element on its left
            int left = height[i];
            for (int j = 0; j < i; j++)
                left = Math.max(left, height[j]);

            // Find the maximum element on its right
            int right = height[i];
            for (int j = i + 1; j < height.length; j++)
                right = Math.max(right, height[j]);

            // Update the maximum water
            res = res + (Math.min(left, right) - height[i]);
        }

        return res;
    }
    
    // Time Complexity: O(n)
    // Auxiliary Space: O(n)
    /*
     * An Efficient Solution is to pre-compute highest bar on left and right of
     * every bar in O(n) time. Then use these pre-computed values to find the amount
     * of water in every array element.
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int n = height.length;

        // left[i] contains height of tallest bar to the
        // left of i'th bar including itself
        int left[] = new int[n];

        // Right [i] contains height of tallest bar to
        // the right of ith bar including itself
        int right[] = new int[n];

        // Initialize result
        int result = 0;

        // Fill left array
        left[0] = height[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        // Fill right array
        right[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        // Calculate the accumulated water element by element
        // consider the amount of water on i'th bar, the
        // amount of water accumulated on this particular
        // bar will be equal to min(left[i], right[i]) - arr[i] .
        for (int i = 0; i < n; i++) {
            result += Math.min(left[i], right[i]) - height[i];
        }

        return result;
    }
    
    //这个双指针动归有待理解
    /* Instead of maintaing two arrays of size n for storing left and right max of each element, we will just maintain 
    two variables to store the maximum till that point. Since water trapped at any element = min( max_left, max_right) – arr[i] 
    we will calculate water trapped on smaller element out of A[lo] and A[hi] first and move the pointers till 
    lo doesn’t cross hi.*/
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        // initialize output
        int result = 0;

        // maximum element on left and right
        int left_max = 0, right_max = 0;

        // indices to traverse the array
        int lo = 0, hi = height.length - 1;

        while (lo <= hi) {
            if (height[lo] < height[hi]) {
                if (height[lo] > left_max) {

                    // update max in left
                    left_max = height[lo];
                } else {
                    // water on curr element =
                    // max - curr
                    result += left_max - height[lo];
                }
                lo++;
            } else {
                if (height[hi] > right_max) {

                    // update right maximum
                    right_max = height[hi];
                } else {
                    result += right_max - height[hi];
                }
                hi--;
            }
        }

        return result;
    }
}
