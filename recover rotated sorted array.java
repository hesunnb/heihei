public class Solution {
    /**
     * @param nums: The rotated sorted array
     * @return: void
     */
    public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
        // write your code
        //[4,5,1,2,3],如果不考虑O[1]的空间，做法就是，开一个数组，找到原数组中的最小值，然后从最小值开始右侧先拷贝到新数组中，然后从头到最小值前一位置再拷贝到新数组中，还得把新数组复制回去，相当费劲
        
       for(int index = 0; index < nums.size() - 1; index++) //减一是为了防止越界
       {
           if(nums.get(index) > nums.get(index + 1)) //找到临界点
           {
               reverse(nums, 0, index); //临界点前翻转
               reverse(nums, index + 1, nums.size() - 1); //临界点后翻转，后面这个要用nums.size() - 1,不能用index + 2
               reverse(nums, 0, nums.size() - 1); //整体翻转一次
           }
       }
    }
    
    public void reverse(ArrayList<Integer> nums, int start, int end) //传过来的是地址, 集合和数组都可以直接交换, 值就不可以啦
    {
        for(int i = start, j = end; i < j; i++, j--) //翻转的具体实现
        {
            int temp = nums.get(i); //就是交换两个数
            nums.set(i, nums.get(j)); //交换
            nums.set(j, temp); //交换
        }
    }
}
