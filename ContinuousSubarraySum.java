/*Given an integer array, find a continuous subarray where the sum of numbers is the biggest. 
Your code should return the index of the first number and the index of the last number. 
(If their are duplicate answer, return anyone)

 Example

Give [-3, 1, 3, -3, 4], return [1,4].
*/

public class Solution {
    /**
     * @param A an integer array
     * @return  A list of integers includes the index of the first number and the index of the last number
     */
    public ArrayList<Integer> continuousSubarraySum(int[] A) {
        // Write your code here
        
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
