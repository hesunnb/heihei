/*You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in 
any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]
Note:
The number of given pairs will be in the range [1, 1000].*/

class Solution {
    
    //目标就是找绝对不相交的interval
    public int findLongestChain(int[][] pairs) {
        if(pairs == null || pairs.length == 0 || pairs[0].length == 0) {
            return 0;
        }
        
        Arrays.sort(pairs, (a,b) -> (a[1]-b[1])); //按照尾部大小排序
        int result = 1;
        int curEnd = pairs[0][1];
        
        for(int i = 1; i < pairs.length; i++) {
            if(curEnd < pairs[i][0]) { //尾部已经比前一个元素尾部大, 如果头部同时也比前一个元素尾部大, 则一定不会相交
                result++;
                curEnd = pairs[i][1];
            }
        }
        return result;
    }
}
