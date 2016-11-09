/*Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in 
the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

 Notice

You are not suppose to use the library's sort function for this problem. 
You should do it in-place (sort numbers in the original array).

Example
Given [1, 0, 1, 2], sort it in-place to [0, 1, 1, 2].
test case: 1,0,1,1,2,1,0,2,0,1*/

class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    
    public void sortColors(int[] nums) //这个九章方法只能是3个颜色, 多啦就得快排啦
    {
        if(nums == null || nums.length <= 1)
        {
            return;
        }
        
        int pl = 0;
        int pr = nums.length - 1;
        int i = 0;
        while(i <= pr)
        {
            if(nums[i] == 0) //只有0值才能和左边的pl位置的值交换, 比如从后面刚交换过来一个0, 马上就和前面的值比较交换啦, 因为值为2的时候i不++
            {
                swap(nums, i, pl);
                i++;
                pl++; //pl先后窜
            }
            else if(nums[i] == 1)
            {
                i++; //不用动, 只有0和2需要交换, pl指到1早晚也会被换走
            }
            else
            {
                swap(nums, i, pr); //注意, i不++, 这样换过去的0马上又能接着换, 1的话i又能向前窜, pl没有动
                pr--;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) //数组直接交换, 传的地址, 值不能直接交换
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    /*//直接用快排来写
    public void sortColors(int[] nums) {
        // write your code here
        sort(nums, 0, nums.length - 1);
    }
    
    private void sort(int[] a, int first, int last)
	{
		int pos;
		if(first < last)
		{
			pos = FindPos(a , first, last); //找第一个数应该在的位置
			sort(a, first, pos - 1); //该点左边排
			sort(a, pos + 1, last); //该点右边排
		}
	}
	
	private int FindPos(int[] a, int first, int last)
	{
		int val = a[first]; //每次都以第一个数为基准
		while(first < last)
		{
			while(first < last && a[last] >= val) //后指针先开始，比基准数大就留下
			{
				last--;
			}
			a[first] = a[last]; //比基准数小就向前赋值
			
			while(first < last && a[first] <= val) //然后是头指针，比基准数小就留下
			{
				first++;
			}
			a[last] = a[first]; //比基准数大就向后赋值
		}
		a[first] = val; //此时first == last, 没有last < first的情况，first和last的左边都比val小，first和last的右边都比val大
		return first; //返回下标，第一个应该所在的位置
	}*/
}
