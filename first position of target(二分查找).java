class Solution {
    /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        //write your code here
      if(nums == null || nums.length == 0)
      {
          return -1;
      }
      int start = 0, end = nums.length - 1;
      while(start + 1 < end)
      {
          int mid = start + (end - start) / 2; //这样写是为了防止溢出（装逼用，就是end+start可能比int的最大值还要大，防止int的溢出）
          if(nums[mid] < target) //这个就能起到找第一个元素的作用，如果要是target是相同的重复元素（555），这样就会执行else，然后end不断的
              //向前窜，就会窜到第一个
          {
              start = mid;
          }
          else
          {
              end = mid;
          }
      }
      
      if(nums[start] == target)
      {
          return start; //找重复元素的第一个元素就要先判断start；要是找重复元素的最有一个元素，要先判断end
      }
      else if(nums[end] == target) //这里的else不能少，否则（5,5,5,5,5）这种情况就出现问题了
      {
          return end;
      }
      
      return -1;
    }
}
