/*

Given two arrays, write a function to compute their intersection.
Notice

    Each element in the result should appear as many times as it shows in both arrays.
    The result can be in any order.

 Example

Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

*/

public class Solution {
    /**
     * @param nums1 an integer array
     * @param nums2 an integer array
     * @return an integer array
     */
     
    //跟一问一样, 用了双指针, O(nlogn), 开了一个temp数组
    public int[] intersection(int[] nums1, int[] nums2) {
        // Write your code here
        
        if(nums1 == null || nums2 == null) {
            return null;
        }
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int[] temp = new int[nums1.length];
        int i = 0, j = 0;
        int index = 0;
        while(i < nums1.length && j < nums2.length) {
            if(nums1[i] == nums2[j]) { //这回不判断temp里面的重复了, 相等就加进去
                temp[index++] = nums1[i];
                i++;
                j++;
            } else if(nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        
        int[] result = new int[index]; //index大小
        for(int k = 0; k < index; k++) {
            result[k] = temp[k]; //拷贝
        }
        return result;
    }
}
