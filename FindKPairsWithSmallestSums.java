/*You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:
Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

Return: [1,1],[1,1]

The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:
Given nums1 = [1,2], nums2 = [3],  k = 3 

Return: [1,3],[2,3]

All possible pairs are returned from the sequence:
[1,3],[2,3]*/

class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        
        List<int[]> result = new ArrayList<>();
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k <= 0) {
            return result;
        }
        
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        int m = nums1.length, n = nums2.length;
        
        for(int j = 0; j <= n-1; j++) {
            pq.offer(new Tuple(0, j, nums1[0]+nums2[j])); //以nums1的第一个元素为基准, nums2的所有元素都与其配对, 这样配对完了以后, 
            //nums2的每个元素都有一列nums1, 所有的nums1都在起点处, 就是nums1的第一个元素
        }
        
        for(int i = 0; i < Math.min(k, m*n); i++) { //按照题意, 因为是边加边出结果, 所以k个就行, 如果k比m*n大, 那就是全数组了
            Tuple t = pq.poll();
            result.add(new int[]{nums1[t.x], nums2[t.y]});
            if(t.x == m - 1) continue;
            pq.offer(new Tuple (t.x + 1, t.y, nums1[t.x + 1] + nums2[t.y])); //每取出一个元素都对应一个nums2的元素, 然后就看它对应的nums1
            //走到哪里了, 然后从之前的x位置再向后走一个加入到队列
        }
        
        return result;
    }
}

class Tuple implements Comparable<Tuple> {
    int x, y, val;
    public Tuple (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
    
    @Override
    public int compareTo (Tuple that) {
        return this.val - that.val;
    }
}
