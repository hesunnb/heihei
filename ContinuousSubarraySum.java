/*Given an integer array, find a continuous subarray where the sum of numbers is the biggest. 
Your code should return the index of the first number and the index of the last number. 
(If their are duplicate answer, return anyone)

 Example

Give [-3, 1, 3, -3, 4], return [1,4].
*/

public class Solution {

    //根据前缀和方法改编的方法, MaximumSubarray
    public List<Integer> continuousSubarraySum(int[] A) {
        // write your code here
        List<Integer> result = new ArrayList<>();
        if (A == null || A.length == 0) {
            return result;
        }
        
        int max = Integer.MIN_VALUE, sum = 0, minSum = 0;
        int start = 0;
        int end = 0;
        result.add(0);
        result.add(0);
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            if(sum - minSum > max) { //这里不能有等号, 否则[1,2,-2,-100,1,2,-2]会找到第二个[1,2], 题目说有重复的话要返回第一个[1,2]
                max = sum - minSum;
                end = i;
                result.set(0, start);
                result.set(1, end);
            }
            
            if(minSum > sum) { //这里也不能加等号, 否则[-2,0,0,1,-1,-1]过不去
                minSum = sum;
                start = i + 1; //这里只进行了更新, 没有被set到result当中
                end = i + 1;
            }
        }
        
        return result;
    }   
 
    
    //贪心改编的方法
    public ArrayList<Integer> continuousSubarraySum(int[] A) {
        
        ArrayList<Integer> result = new ArrayList<Integer>();
        
        if(A == null || A.length == 0) {
            return result;
        }
        
        result.add(0);
        result.add(0);
        int maxEndHere = A[0]; 
        int maxSoFar = A[0];
        int start = 0;
        int end = 0;
        for(int i = 1; i < A.length; i++) {
            maxEndHere = maxEndHere + A[i];
            if(maxEndHere <= A[i]) { //如果maxEndHere与A[i]相等的话, 也让start和end前窜, 这样能保证子数组最短
                maxEndHere = A[i];
                start = i;
                end = i;
            } else {
                end = i;
            }
            if(maxEndHere > maxSoFar) { //如果maxEndHere比maxSoFar大的话, 就更新maxSoFar的值
                maxSoFar = maxEndHere;
                result.set(0, start); //同时保存当前下标, 这样i在向前窜的时候如果不大于maxSoFar的话之前保存的下标是不会受到影响的
                result.set(1, end);
            }
        }
        
        return result;
    }
}
