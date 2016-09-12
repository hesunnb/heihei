/*

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.
Notice

Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)

The solution set must not contain duplicate triplets.

Example

For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:

(-1, 0, 1)
(-1, -1, 2)
*/

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length < 3) {
            return result;
        }
        
        Arrays.sort(nums); //一定要排个序
        for(int i = 0; i < nums.length - 2; i++) { //3个一组
            if(i != 0 && nums[i] == nums[i - 1]) { // to skip duplicate numbers, 比如-4, -1, -1, -1, 0, 1, 2, 
            //第一个-1已经涵盖了所有可能的组合, 后面的-1只能是前面的重复或是子集
                continue;
            }
            
            int left = i + 1; //left在i的后一个
            int right = nums.length - 1;
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0) {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    result.add(temp);
                    left++; //left变大了, i没变, 所以right也一定要变小才能从新计算
                    right--; //也就是sum = 0的时候一定要两个指针都变
                    while(left < right && nums[left] == nums[left - 1]) { // to skip duplicates, 重复的话相当于值没变, 
                    //而一会儿right是要变的, 所以left要跳过重复, 不跳过的话会在result中加入重复的结果, 比如[1,0,-1], [1,0,-1]加入两个
                        left++;
                    }
                    while(left < right && nums[right] == nums[right + 1]) { // to skip duplicates, right也是
                        right--;
                    }
                } else if(sum < 0) {
                    left++;
                    /*while(left < right && nums[left] == nums[left - 1]) { 
                        left++; //在这里跳过重复也会加快速度, 不过不是必须的, 上面那两个跳过重复是必须的, 否则会加入重复元素
                    }*/
                } else {
                    right--;
                    /*while(left < right && nums[right] == nums[right + 1]) { 
                        right--;
                    }*/
                }
            }
        }
        return result;
    }
}
