/*Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is 
incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input:
[1,2,3]

Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]*/

class Solution {
    
    //solution1:
    public int minMoves2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int i = 0, j = nums.length-1;
        int count = 0;
        while(i < j) {
            count += nums[j]-nums[i];
            i++;
            j--;
        }
        return count;
    }
    /*解释:
    Suppose you have two endpoints A and B, when you want to find a point C that has minimum sum of distance between AC and BC, 
    the point C will always between A and B. Draw a graph and you will understand it. Lets keep moving forward. After we locating 
    the point C between A and B, we can define that dis(AC) = c - a; dis(CB) = b - c; sum = dis(AC) + dis(CB) = b - a. b - a will 
    be a constant value, given specific b and a. Thus there will be no difference between points among A and B.

    In this problem, we set two boundaries, saying i and j, and we move the i and j to do the computation.
    比如{1,1,8,100}, 按照计算就是99+7=106, 他这个意思就是说一定要最大与最小配对, 次大与次小配对, 以此类推; 然后1与100不一定非得各自到50, 1到5, 
    100到5和1到50, 100到50走的距离是一样的, 所以{1,1,8,100}就都走到5*/
    
    
    //solution2:
    //如果用快速选择做, 此题就是kthLargestElement的变种; 比如{1,1,8,100}, 那么应该选5, 然后所有元素都向5靠拢, 但是其实1到8之间的所有数都可以, 
    //比如都向6靠拢,7靠拢, 所走的步数都是一样的, 所以最后就成了如果是奇数个那么找到排序后的中位数(因为不整体排序, 所以用快速选择), 如果是偶数个, 
    //那么中间任意两个数都行, 比如{1,1,8,100}, 中间的1和8都可以
    public int minMoves2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int result = 0;
        int median = findMedian(nums, nums.length/2+1, 0, nums.length-1); //在这里nums.length/2+1这个+1是必要的, 因为比如nums的长度是奇数
        //比如5, 5/2+1=3, 要找第3大, 下面k=5-3=2, 就是找下标为2的元素, 正好是第三个, 如果不+1对于奇数个元素的情况就错了; 但是对于偶数个元素
        //的数组, 不+1也可以, 因为就浮动在中间那两个数, 这两个数取谁都可以
        for (int i = 0; i < nums.length; i++) {
            result += Math.abs(nums[i] - median);
        }
        return result;
    }
    
    private int findMedian(int[] nums, int k, int start, int end) { //这里就是kthLargestElement那道题的解法
        
        //重复的元素也算第几大, 比如[3,5,4,4,4,2,6,8], 第4大是4, 第五大还是4, 第六大也是4, 仅仅是按号排序的意思,所以用下标就可以处理
        k = nums.length - k; //要找第k大, 就是倒着找第k个, 那就是正着找下表为nums.length - k的元素
        while(start < end) { //如果条件正常(nums不是空, 长度不为0, k不越界), 一定会进入到break里面, 所以这里是true都可以
            int pos = partition(nums, start, end);
            //k现在已经是正序坐标, pos也是返回的快速选择的partition的坐标(pos左边都比pos小, pos右边都比pos大), 
            if(pos < k) { //如果pos在k的左边, 那就从pos + 1开始到end
                start = pos + 1;
            }
            else if(pos > k) { //如果pos在k的右边, 那就从start到pos - 1
                end = pos - 1;
            }
            else {
                break; //pos与k相等
            }
        }
        return nums[k]; //一旦循环退出就说明k这个位置一定是已经找好的, 并且通过pos和partition已经把应该在k位置的元素调了过来
    }
    
    private int partition(int[] nums, int start, int end) { //快排中的partition方法
        int pivot = nums[end]; //pivot就是支点, 以谁作为参考
        int store = start;
        for (int i = start; i < end; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, store); //把比pivot小的元素都窜到store前面去; store指的永远是比pivot大的元素的下标
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
}
