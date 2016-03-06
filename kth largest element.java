class Solution {
    /*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */
     
    //用快速选择, 就是用快排只排一侧, 时间是O(n), 空间是O(1)
    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        //重复的元素也算第几大, 比如[3,5,4,4,4,2,6,8], 第4大是4, 第五大还是4, 第六大也是4, 仅仅是按号排序的意思,所以用下标就可以处理啦
        k = nums.length - k; //要找第k大, 就是倒着找第k个, 那就是正着找下表为nums.length - k的元素
        int start = 0;
        int end = nums.length - 1;
        int pos = 0;
        while(start < end)
        {
            pos = partition(nums, start, end);
            //k现在已经是坐标啦, pos也是返回的快速选择的partition的坐标(pos左边都比pos小, pos右边都比pos大), 
            if(pos < k)  //如果pos在k的左边, 那就从pos到
            {
                start = pos + 1;
            }
            else if(pos > k)
            {
                end = pos - 1;
            }
            else
            {
                break; //说明返回的下标和k相等, 那也不能返回nums[pos], 因为比如[2,1]找2大, 循环走的是上一条pos > k的情况, pos返回的是下标1, 然后退出啦循环, 想找的是下标0, 所以进入啦这个判断说明pos和k相等, 没有进入这个就不一定, 所以最后只能返回nums[k]
            }
        }
        return nums[k]; //一旦循环退出就说明k这个位置一定是已经找好的
    }
    
    private int partition(int[] a, int first, int last) //快排中的partition部分, 一模一样的
    {
        int val = a[first];
        while(first < last)
        {
            while(first < last && a[last] >= val)
            {
                last--;
            }
            a[first] = a[last];
            
            while(first < last && a[first] <= val)
            {
                first++;
            }
            a[last] = a[first];
        }
        a[first] = val; //此时first和last相等, 然后把最开始取出的第一个作为轴的值再放回来
        return first;
    }
};
