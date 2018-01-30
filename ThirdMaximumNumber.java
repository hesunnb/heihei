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

    //solution1:非常好, 用Integer对象来解决问题
    public int thirdMax(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        
        Integer max1 = null;
        Integer max2 = null;
        Integer max3 = null;
        for (Integer n : nums) {
            if (n.equals(max1) || n.equals(max2) || n.equals(max3)) {
                continue; //出现过的数就不比较了, 如果是比现在三个数小的数重复出现, 比如1已经被替换掉了, 1又出现了, 虽然三个数都不equals1, 
                //但是1不能替换走现在的三个数中的任意一个; 那如果更大数出现就会进来了, 与这三个数相同的数也会直接跳过, 所以ok
            }
            if (max1 == null || n > max1) { //max1最大
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (max2 == null || n > max2) { //max2次大
                max3 = max2;
                max2 = n;
            } else if (max3 == null || n > max3) { //max3第三大
                max3 = n;
            }
        }
        return max3 == null ? max1 : max3; //用Integer的好处就是利用对象类型避开了只有两个数的时候不好判断返回值的情况
    }
    
    
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
