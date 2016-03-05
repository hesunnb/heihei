class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    
    // Discussion One Pass
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
    
    
    
    
    //直接用快排来写, 也是onepass
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
	}




	
    // Couting Sort(第一遍数数组中有多少个0和1, 第二遍按照0和1的个数覆盖原数组)
    public void sortColors(int[] nums) {
    // 2-pass
    int count0 = 0, count1 = 0, count2 = 0;
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] == 0) {count0++;}
        if (nums[i] == 1) {count1++;}
        if (nums[i] == 2) {count2++;}
    }
    for(int i = 0; i < nums.length; i++) {
        if (i < count0) {nums[i] = 0;}
        else if (i < count0 + count1) {nums[i] = 1;}
        else {nums[i] = 2;}
    }
}
}
