/*Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Note: 
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?*/

class Solution {
    class Node {
        int pos;
        int val;
        public Node(int pos, int val) {
            this.pos = pos;
            this.val = val;
        }
    }
    
    //用TreeSet作为媒介进行插入删除
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k < 0 || k > nums.length) {
            return new int[] {};
        }
        
        TreeSet<Node> set = new TreeSet<>((x, y) -> x.val != y.val ? y.val - x.val : x.pos - y.pos); //这里要加入pos判断的原因:
        //这里x.pos - y.pos或y.pos - x.pos都行, pos并不真实影响排序, 主要是如果只考虑值的比较, x.val == y.val就会返回0, 然后
        //TreeSet就会认为两个对象相等, 从而1,3,1这种加入两个1就会被认为相等而踢出重复的1
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            Node node = new Node(i, nums[i]); //用node类的原因是TreeSet会判断重复, 考虑[1,3,1,2,0,5], 如果只加入值的话, 1,3,1加入的时候
            //重复的1就被踢出了, 而对象不会踢出
            set.add(node); //加入也是O(logN)
            
            if (set.size() > k) {
                Node last = new Node(i - k, nums[i - k]); //set的remove方法是O(logN), 比用PriorityQueue的remove(O(n))要快
                set.remove(last);
            }
            
            if (set.size() == k) {
                result.add(set.first().val); //first()方法是TreeSet独有的, 所以要TreeSet<Node> set
            }
        }
        
        int[] array = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            array[i] = result.get(i);
        }
        return array;
    }
}
