/*You are given an array of positive and negative integers. If a number n at an index is positive, then move forward n steps. 
Conversely, if it's negative (-n), move backward n steps. Assume the first element of the array is forward next to the last element, 
and the last element is backward next to the first element. Determine if there is a loop in this array. A loop starts and ends at a 
particular index with more than 1 element along the loop. The loop must be "forward" or "backward'.

Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 -> 2 -> 3 -> 0.

Example 2: Given the array [-1, 2], there is no loop.

Note: The given array is guaranteed to contain no element "0".

Can you do it in O(n) time complexity and O(1) space complexity?*/

class Solution {
    
    /*[-2, 1, -1, -2, -2]这是一个无效的loop, For example, starting at index 1, nums[1] is 1, move 1 step forward to index 2. 
    Then nums[2] is -1, move back 1 step to index 1. The loop contains indices 1 and 2. Is this a valid loop?
    解释: The question said that it can be only backward loop or forward loop. 1 -> 2, 2 -> 1 is a forward - backward loop.*/
    
    //这个模板也不是单纯的时间O(n), 毕竟要把nums里面所有的元素都要当做loop的开头走一遍, 只不过无效loop的位置都置为0, 缩小了无效的loop的搜索时间
    public boolean circularArrayLoop(int[] nums) {
        
        if(nums == null || nums.length <= 1) { //[1,1]就是有效的loop
            return false;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) { //0就是无效的开始, 所以直接跳过
                continue;
            }
            // slow/fast pointer
            int j = i, k = getIndex(i, nums); //k在j的下一个位置
            while (nums[k] * nums[i] > 0 && nums[getIndex(k, nums)] * nums[i] > 0) { //k与k的下一个位置都要判断大于0的原因:
                //首先必须满足相同符号, 只有相同符号才是The loop must be "forward" or "backward', 就是单一的向前或向后; k位置跟i位置相同的
                //原因就是保证j跳一步走到的也是相同符号的位置, k的下一个位置与i相同的原因也是k跳第一步要与i位置符号相同, 才往下跳第二步, 第二步
                //跳完, 跳到了一个不知符号的位置, 那么这个时候while循环就会判断新的k的位置与i符号的关系了; 这样k走的时候会把所有走到的元素符号
                //都判断了, 而j在k的后面, 走的自然都是与i相同符号的位置
                if (j == k) {
                    // check for loop with only one element
                    if (j == getIndex(j, nums)) { //如果下一个位置跳回自己, 那么就是only one element
                        break;
                    }
                    return true;
                }
                j = getIndex(j, nums); //j走一步
                k = getIndex(getIndex(k, nums), nums); //k走两步
            }
            // loop not found, set all element along the way to 0
            //为啥要设成0呢, 举个例子[-2, 1, -1, -2, -2]这是一个无效的loop, i从0出发开始的第一个loop就是无效的, 那么数组变成[0,1,-1,0,-2],
            //那么出现0的位置, 无论其它任何loop走到有一个0的位置, 那么必然会进入这个无效的loop, 因为这些0就是之前那个无效的loop改出来的,
            //所以把无效loop的位置置成0是正确的
            j = i;
            while (nums[j] * nums[i] > 0) { //因为现在是以这个i为基准, 那么跟这个nums[i]相同符号的都置为0
                int next = getIndex(j, nums);
                nums[j] = 0;
                j = next;
            }
        }
        return false;
    }
    
    public int getIndex(int i, int[] nums) {
        int n = nums.length;
        return i + nums[i] >= 0? (i + nums[i]) % n: n + ((i + nums[i]) % n); //[-2,-2,-2]这个例子可以验证getIndex函数往回倒着走的程序
        //计算有环数组下标非常好的函数
    }
}
