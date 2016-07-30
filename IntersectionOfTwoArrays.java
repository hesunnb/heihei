/*
Given two arrays, write a function to compute their intersection.
Notice

    Each element in the result must be unique.
    The result can be in any order.
    
Example

Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
*/

public class Solution {


    //用HashSet的库函数retainAll, 两个Set, 时间O(n)
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null) {
            return null;
        }
        
        HashSet<Integer> hash = new HashSet<Integer>();
        for (int i = 0; i < nums1.length; i++) {
            hash.add(nums1[i]);
        }
        
        HashSet<Integer> resultHash = new HashSet<Integer>();
        for (int i = 0; i < nums2.length; i++) {
            resultHash.add(nums2[i]);
        }
            
        resultHash.retainAll(hash);
        
        int size = resultHash.size();
        int[] result = new int[size];
        int index = 0;
        for (Integer num : resultHash) {
            result[index++] = num;
        }
        
        return result;
    }
    
    
    //用HashSet, 但是没有用库函数retainAll, 两个Set, 时间O(n)
    public int[] intersection(int[] nums1, int[] nums2) {
        // Write your code here
        
        if(nums1 == null || nums2 == null) {
            return null;
        }
        
        HashSet<Integer> hash = new HashSet<Integer>();
        for (int i = 0; i < nums1.length; i++) {
            hash.add(nums1[i]);
        }
        
        HashSet<Integer> resultHash = new HashSet<Integer>();
        for (int i = 0; i < nums2.length; i++) {
            if (hash.contains(nums2[i]) && !resultHash.contains(nums2[i])) { //也可以写成if (hash.contains(nums2[i]))
                resultHash.add(nums2[i]);
            }
        }
        
        int size = resultHash.size();
        int[] result = new int[size];
        int index = 0;
        for (Integer num : resultHash) {
            result[index++] = num;
        }
        
        return result;
    }
    
    
}
