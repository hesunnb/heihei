public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here
        
        if(nums == null || nums.length == 0) {
            return null;
        }
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        Map<Integer, Integer> m = new HashMap<Integer, Integer>();
        
        int sum = 0;
        m.put(0, -1); //在-1的位置放置0, 对于和为0的子数组开头的情况(1, -1 ...)
        for(int i = 0; i < nums.length; i++) { //从头开始求和, 如果再后面遇到和与之前一样的, 说明中间夹了一个和为0的子数组, 返回这个子数组的开头和结尾坐标即可
            sum += nums[i];
            if(m.containsKey(sum)) {
                result.add(m.get(sum) + 1); //找到子数组开头的位置
                result.add(i); //子数组结尾的位置
                return result;
            }
            m.put(sum, i);
        }
        return result;
    }
}
