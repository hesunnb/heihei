/*Construct minimum number by reordering a given non-negative integer array. Arrange them such that they form the minimum number.

 Notice

The result may be very large, so you need to return a string instead of an integer.

Example
Given [3, 32, 321], there are 6 possible numbers can be constructed by reordering the array:

3+32+321=332321
3+321+32=332132
32+3+321=323321
32+321+3=323213
321+3+32=321332
321+32+3=321323
So after reordering, the minimum number is 321323, and return it.*/

public class Solution {
    /**
     * @param nums n non-negative integer array
     * @return a string
     */
     
    //O(nlogn)时间
    public String minNumber(int[] nums) {
        // Write your code here
        
        if(nums == null || nums.length == 0) {
            return "";
        }
        
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++) { //把nums中的整数都转成字符串
            strs[i] = Integer.toString(nums[i]);
        }
        
        Arrays.sort(strs, new StringComparator()); //按照比较器规定排序, sort的时候每次都是倒着取值, 所以也是为什么s1 = 30, s2 = 3
        StringBuilder sb = new StringBuilder();
        
        int index = 0;
        for(int i = strs.length - 1; i >= 0; i--) {
            sb.append(strs[i]);
            if(strs[i].equals("0")) { //如果要都是0就只能返回一个0, 返回一堆0是不对滴
                index++;
            }
        }
        if(index == strs.length) {
            return "0";
        }
        
        while (sb.length() > 1 && sb.charAt(0) == '0') { //出来之后统一处理开头0
            sb.delete(0,1);
        }
        return sb.toString();
    }
    
    class StringComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return (s2 + s1).compareTo(s1 + s2);
        }
    }
}
