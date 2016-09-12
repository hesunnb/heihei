/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?

Find all unique quadruplets in the array which gives the sum of target.
Notice

Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.

Example

Given array S = {1 0 -1 0 -2 2}, and target = 0. A solution set is:

(-1, 0, 0, 1)
(-2, -1, 1, 2)
(-2, 0, 0, 2)
*/

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i++) { //i要保证后面有3个位置
            if(i != 0 && nums[i] == nums[i - 1]) { //对于i来说是1个待匹配, 3Sum是先取出一个值, 再用left, right指针判断, 
            //所以允许最开始留一个i, 然后是后面的nums[i]和前面的不要重复(减少无用的操作次数)
                continue;
            }
            
            for(int j = i + 1; j < nums.length - 2; j++) { //j要保证后面有2个位置
                if(j != i + 1 && nums[j] == nums[j - 1]) { //对于j来说就是两个待匹配, 4Sum先取出两个值, 所以允许头两个值重复
                //比如-1,-1,-1 此时i,j是待匹配的头两个值, 匹配完后-1,-1的情况就都有了, 这是j再后窜一位还是-1, 那么待匹配的值还是-1,-1,
                //这就重复了, 所以要跳过
                //     i  j   
                    continue; //比如-1,-1,-1,-1,0,0,1,1,1,1,2(target=2) i,j在0,0处, 如果不加j != i + 1, 那么0,0,1,1这种情况就会
                    //continue而得不到
                    //                          i,j
                }
                int left = j + 1;
                int right = nums.length - 1;
                while(left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum == target) {
                        List<Integer> temp = new ArrayList<Integer>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        result.add(temp);
                        left++;
                        right--;
                        while(left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while(left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if(sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
