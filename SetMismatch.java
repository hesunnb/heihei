/*The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got 
duplicated to another number in the set, which results in repetition of one number and loss of another number.

Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and 
then find the number that is missing. Return them in the form of an array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]
Note:
The given array size will in the range [2, 10000].
The given array's numbers won't have any order.*/


class Solution {
    
    //solution1:
    public int[] findErrorNums(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return null;
        }
        
        int[] res = new int[2];
        for (int i : nums) {
            if (nums[Math.abs(i) - 1] < 0) { //如果已经变为负, 说明此数重复了
                res[0] = Math.abs(i);
            } else {
                nums[Math.abs(i) - 1] *= -1; //把元素变成下标, 把下标对应的值变为负
            }
        }
        for (int i=0;i<nums.length;i++) {
            if (nums[i] > 0) {
                res[1] = i+1; //因为数组当中缺少的那个数对应的变成下标的值不是负数, 所以到这个地方把下标+1就是对应的值了
            }
        }
        return res;
    }
    
    
    //solution2:
    public int[] findErrorNums(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return null;
        }
        
        Set<Integer> set = new HashSet<>();
        int duplicate = 0, n = nums.length;
        long sum = (n * (n+1)) / 2; //long的好处是防止越界, 如果题目给的数组长度再大一些就用long了
        for(int i : nums) {
            if(set.contains(i)) duplicate = i;
            sum -= i; //求出原始的sum然后不断减i, 就是原数组与重复数组数的差值(可正可负, 比如[2,2]输出[2,1]这种就是负数), 
            //把差值直接加在duplicate上就行, 简化了判断差值正负
            set.add(i);
        }
        return new int[] {duplicate, (int)sum + duplicate};
    }
    
    
    //solution3:(own)
    public int[] findErrorNums(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return null;
        }
        Arrays.sort(nums);
        
        int sumPre = (1 + nums.length) * nums.length / 2;
        int sumAfter = 0;
        int duplicateNum = 0;
        int[] result = new int[2];
        
        for(int i = 0; i < nums.length; i++) {
            if(i + 1 < nums.length && nums[i] == nums[i+1]) {
                duplicateNum = nums[i]; //找到重复的数
            }
            sumAfter += nums[i];
        }
        
        int diff = Math.abs(sumPre - sumAfter); //比如像[2,2]这种会返回[2,1], 所以要区分sumPre与sumAfter的大小
        if(sumPre > sumAfter) {
            result[0] = duplicateNum; //题目给顺序了, result[0]必须是重复的那个数
            result[1] = duplicateNum + diff; //result[1]是缺失的那个数
        } else {
            result[0] = duplicateNum;
            result[1] = duplicateNum - diff;
        }
        
        return result;
    }
}
