/*Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth 
distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from 
the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8

Note:
You may assume that nums' length ≥ k-1 and k ≥ 1.
*/

class KthLargest {

    PriorityQueue<Integer> pq;
    int size;
    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>();
        size = k;
        for(int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
        }
        while(pq.size() > size) { //维持k个大小
            pq.poll();
        }
    }
    
    public int add(int val) {
        if(pq.size() < size) { //没有值要先加入值, ["KthLargest","add","add","add","add","add"], [[1,[]],[-3],[-2],[-4],[0],[4]]
            pq.add(val);
        }
        else if(pq.peek() < val) { //只要k个, 比peek小的自然在k外, 所以直接抛弃, 比peek大的就加入队列, 弹出目前最小, 再返回peek
            pq.offer(val);
            pq.poll();
        }
        return pq.peek();
    }
}

class KthLargest {

    PriorityQueue<Integer> pq;
    int size;
    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>();
        size = k;
        for(int i = 0; i < nums.length; i++) {
            add(nums[i]); //这个就是写的稍微巧妙一些, 直接调用了自己的add方法, 也就直接控制了k个队列长度, 没有用队列的add方法
        }
    }
    
    public int add(int val) {
        if(pq.size() < size) { //没有值要先加入值, ["KthLargest","add","add","add","add","add"], [[1,[]],[-3],[-2],[-4],[0],[4]]
            pq.add(val);
        }
        else if(pq.peek() < val) {
            pq.offer(val);
            pq.poll();
        }
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
