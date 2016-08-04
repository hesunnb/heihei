/*
Find K-th largest element in an array.
Notice

You can swap elements in the array

Example

In array [9,3,2,4,8], the 3rd largest element is 4.

In array [1,2,3,4,5], the 1st largest element is 5, 2nd largest element is 4, 3rd largest element is 3 and etc.
*/

class Solution {
    /*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */
     
    //用快速选择, 就是用快排只排一侧, 时间是O(n), 空间是O(1)
    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        //重复的元素也算第几大, 比如[3,5,4,4,4,2,6,8], 第4大是4, 第五大还是4, 第六大也是4, 仅仅是按号排序的意思,所以用下标就可以处理
        k = nums.length - k; //要找第k大, 就是倒着找第k个, 那就是正着找下表为nums.length - k的元素
        int start = 0;
        int end = nums.length - 1;
        while(start < end) //如果条件正常(nums不是空, 长度不为0, k不越界), 一定会进入到break里面, 所以这里是true都可以
        {
            int pos = partition(nums, start, end);
            //k现在已经是正序坐标, pos也是返回的快速选择的partition的坐标(pos左边都比pos小, pos右边都比pos大), 
            if(pos < k)  //如果pos在k的左边, 那就从pos + 1开始到end
            {
                start = pos + 1;
            }
            else if(pos > k) //如果pos在k的右边, 那就从start到pos - 1
            {
                end = pos - 1;
            }
            else
            {
                break; //pos与k相等
            }
        }
        return nums[k]; //一旦循环退出就说明k这个位置一定是已经找好的, 并且通过pos和partition已经把应该在k位置的元素调了过来
    }
    
    private int partition(int[] nums, int start, int end) { //快排中的partition方法
        int pivot = nums[end];
        int store = start;
        for (int i = start; i < end; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, store);
                store++;
            }
        }
        swap(nums, store, end);
        return store;
    }
    
    private void swap(int[] nums, int m, int n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }
};

/*粗略证明:
快选，每次选一部分，扔掉另一部分，所以是O(N)
假设每次扔掉一半.
（2^k=N)
T(N) =n +n/2+n/4+n/8+n/2^k = n*(1-2^-k)/(1-2^-1) =2N */
