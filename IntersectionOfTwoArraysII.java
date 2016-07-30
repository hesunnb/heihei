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
    
    //开了一个HashMap, 一个ArrayList, 然后是O(n)
    public int[] intersect(int[] nums1, int[] nums2) {
        
        if(nums1 == null || nums2 == null) {
            return null;
        }
        
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        List<Integer> l = new ArrayList<Integer>();
        
        for(int i = 0; i < nums1.length; i++) {
            if(m.containsKey(nums1[i])) {
                m.put(nums1[i], m.get(nums1[i]) + 1); //有就+1
            } else {
                m.put(nums1[i], 1); //没有就放进去
            }
        }
        
        for(int i = 0; i < nums2.length; i++) {
            if(m.containsKey(nums2[i]) && m.get(nums2[i]) > 0) { //包含并且要大于0
                l.add(nums2[i]);
                m.put(nums2[i], m.get(nums2[i]) - 1); //减一
            }
        }
        
        int[] result = new int[l.size()];
        for(int i = 0; i < result.length; i++) { //拷贝
            result[i] = l.get(i);
        }
        return result;
    } 
    
    
    
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
    
    /*Solution to 3rd follow-up question:
    What if elements of nums2 are stored on disk, and the memory islimited such that you cannot load all elements
    into the memory at once?
    
    1. If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, 
    read chunks of array that fit into the memory, and record the intersections.

    2. If both nums1 and nums2 are so huge that neither fit into the memory, 
    sort them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.
    */
}
