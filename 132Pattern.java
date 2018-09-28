/*Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. 
Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].*/

class Solution {
    
    //O(n)time, O(n)space用栈的解法
    public boolean find132pattern(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return false;
        }
        
        int s3 = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for(int i = nums.length - 1; i >= 0; i--) { //倒着扫, 栈里的元素从下到上依次变小
            if(nums[i] < s3) { //此时遇到的nums[i]是s1
                return true;
            }
            while(!stack.isEmpty() && nums[i] > stack.peek()) { //遇到大的, 就把栈里小的拿出来给s3, 因为是从小到大给, 所以s3保留了目前扫过比
                //nums[i]刚刚小的值, 此时就得到了目前所能得到的最大的s2,s3的pair, 正好用栈把它俩的顺序给调转了
                s3 = stack.pop();
            }
            stack.push(nums[i]); //栈里面剩的是s2
        }
        return false;
    }
    /*QUESTION: To search for a subsequence (s1,s2,s3) such that s1 < s3 < s2.

    INTUITION: Suppose we want to find a 123 sequence with s1 < s2 < s3, we just need to find s3, followed by s2 and s1. Now if we want 
    to find a 132 sequence with s1 < s3 < s2, we need to switch up the order of searching. we want to first find s2, followed by s3, 
    then s1.

    DETECTION: More precisely, we keep track of highest value of s3 for each valid (s2 > s3) pair while searching for a valid s1 
    candidate to the left. Once we encounter any number on the left that is smaller than the largest s3 we have seen so far, we know 
    we found a valid sequence, since s1 < s3 implies s1 < s2.

    ALGORITHM: We can start from either side but I think starting from the right allow us to finish in a single pass. The idea is to 
    start from end and search for valid (s2,s3) pairs, we just need to remember the largest valid s3 value, using a stack will be 
    effective for this purpose. A number becomes a candidate for s3 if there is any number on the left bigger than it.

    CORRECTNESS: As we scan from right to left, we can easily keep track of the largest s3 value of all (s2,s3) candidates encountered 
    so far. Hence, each time we compare nums[i] with the largest candidate for s3 within the interval nums[i+1]...nums[n-1] we are 
    effectively asking the question: Is there any 132 sequence with s1 = nums[i]? Therefore, if the function returns false, there 
    must be no 132 sequence.

    IMPLEMENTATION:

    Have a stack, each time we store a new number, we first pop out all numbers that are smaller than that number. The numbers that are 
    popped out becomes candidate for s3.
    We keep track of the maximum of such s3 (which is always the most recently popped number from the stack).
    Once we encounter any number smaller than s3, we know we found a valid sequence since s1 < s3 implies s1 < s2.
    RUNTIME: Each item is pushed and popped once at most, the time complexity is therefore O(n).

    EXAMPLE:
    i = 6, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 9, S3 candidate = None, Stack = Empty
    i = 5, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 7, S3 candidate = None, Stack = [9]
    i = 4, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 10, S3 candidate = None, Stack = [9,7]
    i = 3, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 9, S3 candidate = 9, Stack = [10]
    i = 2, nums = [ 9, 11, 8, 9, 10, 7, 9 ], S1 candidate = 8, S3 candidate = 9, Stack = [10,9] We have 8<9, sequence (8,10,9) found!*/
}
