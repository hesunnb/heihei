/*Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.*/

//类似题目: 451. Sort Characters By Frequency
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hashmap = new HashMap<Integer, Integer>();
        //优先级队列, 比较的类型是Map.Entry<Integer, Integer>
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<Map.Entry<Integer, Integer>>(new topKcomparator());
            
        //向哈希表中加东西
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        
        //向优先级队列中加东西, 优先级队列是最小堆(大小为k)
        for (Map.Entry<Integer, Integer> entry : hashmap.entrySet()) {
            if (queue.size() < k) {
                queue.offer(entry);
            } else if (queue.peek().getValue() < entry.getValue()) {
                queue.poll();
                queue.offer(entry);
            }
        }
        
        //取出结果
        List<Integer> ans = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> entry : queue)
            ans.add(entry.getKey());
        return ans;
    }
    
    class topKcomparator implements Comparator<Map.Entry<Integer, Integer>> {
        public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
            return e1.getValue() - e2.getValue(); //第一个减第二个就是最小堆, 第二个减第一个就是最大堆
        }
    }
}
