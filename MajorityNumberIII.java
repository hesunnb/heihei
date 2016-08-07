/*Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array.
Find it.

Notice

There is only one majority number in the array.

Example

Given [3,1,2,3,2,3,3,4,4,4] and k=3, return 3.
*/

public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: As described
     * @return: The majority number
     */
     
    //这个方法适用于整个数组里只有唯一一个最大数
    //比如1,1,1,2,2,2,3,4 最大数是1与2, 只能返回其中一个
    //    1,1,1,2,2,2,2,3 最大数是1与2, 但是2是更大的, 这个就不一定了
    //    1,1,2,2,3,3,4,4,2,2 最大数是2, 最后剩余的4不是, 返回2
    public int majorityNumber(ArrayList<Integer> nums, int k) {
        // write your code
        
        if(nums == null || nums.size() == 0) {
            return 0;
        }
        
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.size(); i++) {
            if(m.containsKey(nums.get(i))) {
                m.put(nums.get(i), m.get(nums.get(i)) + 1);
                if(m.get(nums.get(i)) > nums.size() / k) {
                    return nums.get(i);
                }
            } else {
                m.put(nums.get(i), 1);
            }
        }
        return 0;
    }
    
    
    //完整的话就得遍历一次哈希表, 1,1,1,2,2,2,2,3这种情况就会返回2了
    public int majorityNumber(ArrayList<Integer> nums, int k) {
        // write your code
        
        if(nums == null || nums.size() == 0) {
            return 0;
        }
        
        int count = 1;
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.size(); i++) {
            if(m.containsKey(nums.get(i))) {
                m.put(nums.get(i), m.get(nums.get(i)) + 1);
                if(m.get(nums.get(i)) > count) { //如果要找到所有就在这个地方比较m.get(nums.get(i)) > nums.size() / k, 大于则加入到集合中
                    count = m.get(nums.get(i)); //这个版本就是找出现次数最多的了
                }
            } else {
                m.put(nums.get(i), 1);
            }
        }
        
        for(int i = 0; i < nums.size(); i++) {
            if(m.get(nums.get(i)) == count) {
                return nums.get(i);
            }
        }
        return 0;
    }
}
