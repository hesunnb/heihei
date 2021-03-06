/*Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we split the array into some number of "chunks" 
(partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.

What is the most number of chunks we could have made?

Example 1:

Input: arr = [4,3,2,1,0]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
Example 2:

Input: arr = [1,0,2,3,4]
Output: 4
Explanation:
We can split into two chunks, such as [1, 0], [2, 3, 4].
However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
Note:

arr will have length in range [1, 10].
arr[i] will be a permutation of [0, 1, ..., arr.length - 1].*/

class Solution {
    
    //O(nlogn)解法
    public int maxChunksToSorted(int[] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }
        
        int[] arrCopy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arrCopy); //排序
        
        int maxA = arr[0];
        int maxB = arrCopy[0];
        int result = 0;
        for(int i = 0; i < arr.length; i++) {
            maxA = Math.max(maxA, arr[i]);
            maxB = Math.max(maxB, arrCopy[i]);
            if(maxA == maxB) { //当前最大值相同的时候
                result++;
            }
        }
        return result;
    }
    
    //也是O(nlogn)解法
    public int maxChunksToSorted(int[] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }
        
        int[] arrCopy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arrCopy);
        
        int sumA = 0;
        int sumB = 0;
        int result = 0;
        for(int i = 0; i < arr.length; i++) {
            sumA += arr[i];
            sumB += arrCopy[i];
            if(sumA == sumB) { //用做和的方式也ok
                result++;
            }
        }
        return result;
    }
    
    /*original: 0, 2, 1, 4, 3, 5, 7, 6
      max:      0, 2, 2, 4, 4, 5, 7, 7
    这个方法是O(n), 但是前提是这道题数组范围是[0, 1, ..., arr.length - 1], 如果不是这个范围, 变成了随意数字, 这个方法就不行了*/
    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        
        int[] max = new int[arr.length];
        max[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max[i] = Math.max(max[i - 1], arr[i]);
        }
        
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max[i] == i) { //仅限于数组范围是[0, 1, ..., arr.length - 1]可以判断值和下标相等
                count++;
            }
        }
        
        return count;
    }
}
