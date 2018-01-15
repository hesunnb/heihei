/*Given an array of integers, find if the array contains any duplicates. Your function should return true if any value 
appears at least twice in the array, and it should return false if every element is distinct.*/

class Solution {
    public boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        
        Set<Integer> set = new HashSet<Integer>();
        for(int i : nums) {
            if(!set.add(i)) { //set的add方法对于没有重复的值添加返回true, 如果set中已有值还添加相同的值返回false
                return true; 
            }
        }
			
		    return false;
    }
}
