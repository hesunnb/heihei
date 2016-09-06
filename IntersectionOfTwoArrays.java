/*
Given two arrays, write a function to compute their intersection.
Notice

    Each element in the result must be unique.
    The result can be in any order.
    
Example

Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
*/

public class Solution {


    //用HashSet的库函数retainAll, 两个Set, 时间O(n), retainAll的开销比较大, 所以还是用下面的那个Set方法
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
    
    
    
    //用两个指针指向两个数组的头, 比较大小相等, 然后后移判断, 除了要返回的result, 多开了一个数组temp, 时间是O(nlogn)
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
            if(nums1[i] == nums2[j]) {
                if(index == 0 || temp[index - 1] != nums1[i]) { //因为已经排好序了, 要保证前一个和要进入的值不等, 否则重复了
                    temp[index++] = nums1[i];
                }
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
    
    
    
    //sort & binary search
    //开了一个HashSet, O(nlogn)
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        
        HashSet<Integer> set = new HashSet<>();
        
        Arrays.sort(nums1);
        for (int i = 0; i < nums2.length; i++) {
            if (binarySearch(nums1, nums2[i])) { //用二分法判断nums2的值在nums1里面有没有
                set.add(nums2[i]);
            }
        }
        
        int[] result = new int[set.size()];
        int index = 0;
        for (Integer num : set) {
            result[index++] = num;
        }
        
        return result;
    }
    
    private boolean binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (nums[start] == target) {
            return true;
        }
        if (nums[end] == target) {
            return true;
        }
        
        return false;
    }
}
