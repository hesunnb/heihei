/*Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. 
The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.*/


class Solution {

    //solution2:优先级队列和set
    public int thirdMax(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //优先级队列默认是最小堆, inserting element to a heapq takes log(n), 
        //and removingMin from a heapq also takes log(n)
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (!set.contains(i)) { //set不断的添加并且过滤, 相同的数就给过滤掉了
                pq.offer(i);
                set.add(i);
                if (pq.size() > 3) { //一直保持3个
                    set.remove(pq.poll()); //弹掉三个当中的最小值
                }
            }
        }
        if (pq.size() < 3) {
            while (pq.size() > 1) { //弹出到只剩一个返回最大值
                pq.poll();
            }
        }
        return pq.peek(); //返回三个数中的最小值
    }
}
