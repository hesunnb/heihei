/*Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Clarification
Your algorithm should run in O(n) complexity.

Example
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.*/

public class Solution {
    
    //O(n)时间, O(n)空间
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++) { //用set自动去重
            set.add(nums[i]);
        }
        
        int longest = 0;
        for(int i = 0; i < nums.length; i++) {
            int down = nums[i] - 1; //从num[i]往下找, 找下连续
            while(set.contains(down)) {
                set.remove(down); //要从set中remove(同一个数找过了就不用再找了), 不remove不影响正确性, 但是时间会长
                down--;
            }
            
            int up = nums[i] + 1;
            while(set.contains(up)) { //找上连续
                set.remove(up);
                up++;
            }
            
            longest = Math.max(longest, up - down - 1); //上与下的差就是长度
        }
        
        return longest;
    }
}


public class Solution {
    /**
     * @param num: A list of integers
     * @return an integer
     */
     
    //O(nlogn)时间, O(n)空间
    public int longestConsecutive(int[] num) {
        // write you code here
        if(num == null || num.length == 0) {
            return 0;
        }
        
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < num.length; i++) { //去重复拷贝到集合中
            if(result.contains(num[i])) {
                continue;
            } else {
                result.add(num[i]);
            }
        }
        
        if(result.size() == 1) { //一个就返回, 一个下面循环不了
            return 1;
        }
        
        Collections.sort(result); //排序
        int count = 1;
        int max = 1;
        for(int i = 1; i < result.size(); i++) {
            if(result.get(i) - result.get(i - 1) == 1) { //找连续
                count++;
                max = Math.max(max, count); //找最大连续
            } else {
                count = 1; //count要恢复为1
            }
        }
        return max;
    }
}
