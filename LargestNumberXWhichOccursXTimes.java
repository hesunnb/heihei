/*Given an array A consisting of N integers, you should return the biggest value X, which occurs in A exactly X times. If there is no 
such value, you should return 0.

Example
Example 1:
Input: [3, 8, 2, 3, 3, 2]
Output: 3
Explanation: The value 2 occurs exactly two times and the value 3 occurs exactly three times. Meanwhile, the maximum of 2 and 3 is 3 
so the answer is 3.

Example 2:
Input: [3, 1, 4, 1, 5]
Output: 0
Explanation: There is no value which meets the task conditions so the answer is 0.

Notice
N is an integer within the range [1..100,000];
each element of array A is an integer within the range [1..1,000,000,000].*/

public class Solution {
    /**
     * @param arr: an array of integers
     * @return: return the biggest value X, which occurs in A exactly X times.
     */
     
    // 哈希表就完了
    public int findX(int[] arr) {
        // write your code here
        if(arr == null || arr.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        
        int result = 0;
        for(int i : map.keySet()) {
            if(map.get(i) == i) {
                result = Math.max(result, i);
            }
        }
        
        return result;
    }
    
    /* 思路2:
     * Algorithm: 1.Sort the array 2. Start from the end and keep count of same
     * element. 3. If element differs check if the last element and running sum is
     * same if yes return else keep looking.
     */
}
