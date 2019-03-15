/*Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for 
every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which 
means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.
Note: The length of given array won't exceed 10000.*/

class Solution {

    //值得学习的方法
    public int[] nextGreaterElements(int[] nums) {
        if(nums == null || nums.length == 0) {
            return nums;
        }
        
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < nums.length * 2; i++) {
            int index = i % nums.length; //循环用求余解决
            while(!stack.isEmpty() && nums[stack.peek()] < nums[index]) {
                result[stack.pop()] = nums[index];
            }
            if(index < nums.length) {
                stack.push(index); //放入栈中的是下标
            }
        }
        return result;
    }

    
    //(own), 思路想法和discuss一样, 但是discuss写的更好
    public int[] nextGreaterElements(int[] nums) {
        if(nums == null || nums.length == 0) {
            return nums;
        }
        
        int[] result = new int[nums.length];
        for(int i = 0; i < result.length; i++) { //可以用Arrays.fill替换
            result[i] = -1;
        }
        Stack<Point> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 2; i++) { //循环用求余可以解决
            for(int j = 0; j < nums.length; j++) {
                list.add(nums[j]);
            }
        }
        
        for(int i = 0; i < list.size(); i++) {
            while(!stack.isEmpty() && stack.peek().value < list.get(i)) {
                Point p = stack.pop();
                result[p.index] = list.get(i);
            }
            if(i < nums.length) {
                stack.push(new Point(list.get(i), i)); //放入栈可以放入下标
            }
        }
        return result;
    }
    
    class Point {
        int value;
        int index;
        Point(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
