/*Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.

*/

public class Solution {

    //动归O(n^2), 这个方法会在leetcode会超时, 用贪心吧
    //注意这个数组的元素是最大跳跃数, 可以比最大跳跃数少的
    public boolean canJump(int[] A) {
        
        if(A == null || A.length == 0) {
            return false;
        }
        
        boolean[] result = new boolean[A.length];
        result[0] = true;
        for(int i = 1; i < A.length; i++) {
            for(int j = 0; j < i; j++) { //从头每次扫到i
                if(result[j] && j + A[j] >= i) { //j本身这个点是真, 且j能跳到i或者i以后的地方
                    result[i] = true; //能够跳到这个点, 这个点就是真
                    break; //从头开始找，只要中途有一个满足, 就退出不再向下找了, 避免重复运算
                }
            }
        }
        return result[A.length - 1];
    }
    
    //动归必须每一次都要从头开始进行比较, 才能确定新的点是否为true
    //贪心是只走一遍, 每次只找最远能到达的下标, 最终如果这个下标可以达到最后或者超过, 那么就是true
    
    //Greedy
    //只扫一遍, O(n)
    public boolean canJump(int[] A) {
        if(A == null || A.length == 0) {
            return false;
        }
        
        int farest = A[0]; //farest是所能到达的最远下标
        for(int i = 1; i < A.length; i++) {
            if(i <= farest && i + A[i] > farest) { //i <= farest这个条件就保证i所在的位置能被前面的点跳到; i没到最远下标的地方, 
            //这时有比最远下标大的计算的下标值出现, 那就替换
                farest = i + A[i];
            }
        }
        return farest >= A.length - 1; //返回最远下标是不是比最后的下标还要大
    }
}
