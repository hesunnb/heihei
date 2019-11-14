/*Given an array of integers, find if the array contains any duplicates. Your function should return true if any value 
appears at least twice in the array, and it should return false if every element is distinct.*/

class Solution {
	
    //brute force, Time complexity: O(N^2), memory: O(1)
	public boolean containsDuplicate(int[] nums) {

        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //sort and check, Time complexity: O(N lg N), memory: O(1) - not counting the memory used by sort
    public boolean containsDuplicate(int[] nums) {

        Arrays.sort(nums);
        for(int ind = 1; ind < nums.length; ind++) {
            if(nums[ind] == nums[ind - 1]) {
                return true;
            }
        }
        return false;
    }
    
    //hashtable, Time complexity: O(N), memory: O(N)
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
