/*Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers 
successfully if one of the following is true for the ith position (1 <= i <= N) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.
 

Now given N, how many beautiful arrangements can you construct?

Example 1:

Input: 2
Output: 2
Explanation: 

The first beautiful arrangement is [1, 2]:

Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:

Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 

Note:

N is a positive integer and will not exceed 15.*/

class Solution {
    
    //不用产生所有序列, pos是当前的位置(题中的ith), i + 1是每次放到pos位置的值, used来标记有没有用过, 扫描就变成O(1)了, 比list快
    //testCase: 用1,2,3想就行
    int count = 0;
    public int countArrangement(int N) {
        if(N <= 0) {
            return 0;
        }
        
        helper(1, N, new int[N]);
        return count;
    }
    
    public void helper(int pos, int N, int[] used) {
        if(pos > N) {
            count++;
            return;
        }
        
        for(int i = 0; i < N; i++) {
            if(used[i] == 0 && ((i + 1) % pos == 0 || pos % (i + 1) == 0)) {
                used[i] = 1;
                helper(pos + 1, N, used);
                used[i] = 0;
            }
        }
    }
    
 
    //(own), 产生了数组判断, permutation的方法
    int count = 0;
    public int countArrangement(int N) {
        if(N <= 0) {
            return 0;
        }
        
        List<Integer> list = new ArrayList<>();
        int[] nums = new int[N];
        for(int i = 0; i < nums.length; i++) {
            nums[i] = i + 1;
        }
        helper(list, nums);
        return count;
    }
    
    public void helper(List<Integer> list, int[] nums) {
        if(list.size() == nums.length) {
            count++;
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(list.contains(nums[i])) {
                continue;
            }
            if(nums[i] % (list.size() + 1) == 0 || (list.size() + 1) % nums[i] == 0) {
                list.add(nums[i]);
                helper(list, nums);
                list.remove(list.size() - 1);
            }
        }
    }
}
