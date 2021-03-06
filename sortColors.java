/*Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the 
colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Follow up:

    A rather straight forward solution is a two-pass algorithm using counting sort.
    First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed 
    by 2's.
    Could you come up with a one-pass algorithm using only constant space?

*/

class Solution {

    // Couting Sort(第一遍数数组中有多少个0和1, 第二遍按照0和1的个数覆盖原数组)
    public void sortColors(int[] nums) {
        // 2-pass
	if(nums == null || nums.length <= 1) {
            return;
        }    
	
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
	
	
    // Discussion One Pass
    public void sortColors(int[] nums) { //这个九章方法只能是3个颜色, 多啦就得快排啦
    
	//一个testCase: [2,0,2,1,0,1,2]涵盖了所有情况
        if(nums == null || nums.length <= 1) { //testCase里确实有[1,0]这种例子, 不一定就是3种颜色全都有, 所以这里用<=1
            return;
        }
        
        int pl = 0;
        int pr = nums.length - 1;
        int i = 0;
        while(i <= pr) {
            if(nums[i] == 0) { //只有0值才能和左边的pl位置的值交换, 比如从后面刚交换过来一个0, 马上就和前面的值比较交换啦, 因为值为2的时候i不++
                swap(nums, i, pl);
                i++;
                pl++; //pl先后窜的原因是, 没有人会和p1处的0再次交换了, 所以p1换为0之后就向后走
            }
            else if(nums[i] == 1) {
                i++; //不用动, 只有0和2需要交换, pl指到1早晚也会被换走
            }
            else {
                swap(nums, i, pr); //注意, i不++, 这样换过去的0马上又能接着换, 1的话i又能向前窜, pl没有动
                pr--;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) { //数组直接交换, 传的地址, 值不能直接交换
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    
    //直接用快排来写, 时间:O(nlogn), 平常空间: O(1), 栈空间: O(logn)
    public void sortColors(int[] nums) {
        // write your code here
	if(nums == null || nums.length <= 1) {
            return;
        }
        sort(nums, 0, nums.length - 1);
    }
    
    private void sort(int[] a, int first, int last) {
	int pos;
	if(first < last) {
	    pos = FindPos(a , first, last); //找第一个数应该在的位置
	    sort(a, first, pos - 1); //该点左边排
	    sort(a, pos + 1, last); //该点右边排
	}
    }
	
    private int FindPos(int[] a, int first, int last) {
	int val = a[first]; //每次都以第一个数为基准
	while(first < last) {
	    while(first < last && a[last] >= val) { //后指针先开始，比基准数大就留下
		last--;
	    }
	    a[first] = a[last]; //比基准数小就向前赋值

	    while(first < last && a[first] <= val) { //然后是头指针，比基准数小就留下
		first++;
	    }
	    a[last] = a[first]; //比基准数大就向后赋值
	}
	a[first] = val; //此时first == last, 没有last < first的情况，first和last的左边都比val小，first和last的右边都比val大
	return first; //返回下标，第一个应该所在的位置
    }
}
