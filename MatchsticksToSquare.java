/*Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way 
you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick 
must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, 
to represent whether you could make one square using all the matchsticks the little match girl has.

Example 1:
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.
Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.*/

class Solution {
    /*the partition problem (or number partitioning) is the task of deciding whether a given multiset S of positive integers can be 
    partitioned into two subsets S1 and S2 such that the sum of the numbers in S1 equals the sum of the numbers in S2. The partition 
    problem is NP-complete.*/
    
    //This is a NP problem, Time complexity should be O(4^n), n is the length of array, 
    //T(n) = 4 T(n-1) + O(1) = 4^n T(0) + 4^n + 4^(n-1) + ... + 4 + 1 = O(4^n)
    
    //Partition to K Equal Sum Subsets类型题, 这个题目就是这种问题的抽象
    public boolean makesquare(int[] nums) {
    	if (nums == null || nums.length < 4) {
            return false;
        }
        
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 4 != 0) return false;
        
        Arrays.sort(nums);
        /*Sorting the input array DESC will make the DFS process run much faster. Reason behind this is we always try to put 
        the next matchstick in the first subset. If there is no solution, trying a longer matchstick first will get to 
        negative conclusion earlier. Following is the updated code. Runtime is improved from more than 1000ms to around 40ms. 
        A big improvement.*/
    	return dfs(nums, new int[4], nums.length - 1, sum / 4); //为了不reverse数组, 那就倒着遍历数组即可
    }
    
    private boolean dfs(int[] nums, int[] sums, int index, int target) {
    	if (index == -1) { //因为倒着遍历, 所以index == -1与正着的index == nums.length是一个意思
            //there is no way to complete the iteration of all matches if there is no solution.
            return true; 
            //index == nums.length, 说明index已经计算完最后一个nums的元素了, 已经到越界了, 如果最后一个元素加进去了并且满足了
            //那就说明all matches了
    	}
    	
    	for (int i = 0; i < 4; i++) { //典型的暴力解, 记住
    	    if (sums[i] + nums[index] > target) {
                continue;
            }
    	    sums[i] += nums[index];
            if (dfs(nums, sums, index - 1, target)) {
                return true;
            }
    	    sums[i] -= nums[index];
    	}
    	
    	return false;
    }
}
