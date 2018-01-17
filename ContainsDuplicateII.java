/*Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such 
that nums[i] = nums[j] and the absolute difference between i and j is at most k.*/

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        
        if(nums == null || nums.length == 0 || k <= 0) { //不用判断k比数组的大小, 比如数组长度50, 那么下标最大宽度k是100也没有
        //问题吖
            return false;
        }
        
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(i > k) {
                set.remove(nums[i-k-1]); //sliding window的大小就是k+1个, 所以要删除nums中开头的
            }
            if(!set.add(nums[i])) { //set的add方法添加的时候有重复返回false
                return true; //比如nums是1,2,3,4,5,6 k是3; 那么set就是一个sliding window, 里面一直装着k+1个元素; 如果没到k+1个
                //就返回true了, 说明k+1个以内就已经遇到了
            }
        }
        return false;
    }
}
