public class Solution {
    /**
     * @param nums a list of integer
     * @return void
     */
     
    /*
    交换法
    复杂度
    时间 O(N) 空间 O(1)
    思路
    题目对摇摆排序的定义有两部分：
    如果i是奇数，nums[i] >= nums[i - 1]
    如果i是偶数，nums[i] <= nums[i - 1]
    所以我们只要遍历一遍数组，把不符合的情况交换一下就行了。具体来说，如果nums[i] > nums[i - 1]， 则交换以后肯定有nums[i] <= nums[i - 1]。
    */
    public void wiggleSort(int[] nums) {
        // Write your code here
        if(nums == null || nums.length <= 1)
        {
            return;
        }
        
        for(int i = 1; i < nums.length; i++)
        {
            //需要交换的情况：奇数时nums[i] < nums[i - 1]或偶数时nums[i] > nums[i - 1]
            if((i % 2 == 0 && nums[i] > nums[i - 1]) || (i % 2 == 1 && nums[i] < nums[i - 1]))
            {
                int temp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = temp;
            }
        }
    }
    
    public void wiggleSort(int[] nums) 
    {
        for (int i=1; i<nums.length; i++) 
        {
            int a = nums[i-1];
            if ((i%2 == 1) == (a > nums[i])) //和上面的判断一个意思, 更简练吧
            {
                nums[i-1] = nums[i];
                nums[i] = a;
            }
        }
    }
}
