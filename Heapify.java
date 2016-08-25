/*Given an integer array, heapify it into a min-heap array.

For a heap array A, A[0] is the root of heap, 
and for each A[i], A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].

What is heap?

Heap is a data structure, which usually have three methods: push, pop and top. where "push" add a new element the heap, "pop" delete the minimum/maximum element in the heap, "top" return the minimum/maximum element.

What is heapify?
Convert an unordered integer array into a heap array. If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].

What if there is a lot of solutions?
Return any of them.
Example
Given [3,2,1,4,5], return [1,2,3,4,5] or any legal heap array.*/

public class Solution {
    /**
     * @param A: Given an integer array
     * @return: void
     */
     
    //最小堆的建立, 最小堆的第一个数一定是最小的, 因为最小堆满足每个节点的两个子节点都比自己大
    //时间O(n)
    public void heapify(int[] A) {
        // write your code here
        
        if(A == null || A.length == 0) {
            return;
        }
        
        for(int i = A.length / 2 - 1; i >= 0; i--) {
            siftDown(A, i); //因为每个点都要去找它的子节点, A.length / 2 - 1后面的点*2+1, *2+2都越界了, 不需要考虑
        }
    }
    
    private void siftDown(int[] A, int k) {
        while(k < A.length) {
            int smallest = k;
            if(k * 2 + 1 < A.length && A[k * 2 + 1] < A[smallest]) { //左子节点
                smallest = k * 2 + 1; //smallest到左子节点
            }
            if(k * 2 + 2 < A.length && A[k * 2 + 2] < A[smallest]) { //右子节点
                smallest = k * 2 + 2; //smallest到右子节点
            }
            //这样就一定把每一个小部分(3个节点)中最小的那个数给了父节点
            
            if(smallest == k) { //如果smallest和k相等就说明满足该节点的两个子节点都比自己大, 不需要进行交换
                break;
            }
            
            int temp = A[k];
            A[k] = A[smallest];
            A[smallest] = temp;
            
            k = smallest; //每次交换后要继续判断所交换过的节点, 要保证整体都有效才行
        }
    }
}


// Version 2: This cost O(nlogn)
public class Solution {
    /**
     * @param A: Given an integer array
     * @return: void
     */
    private void siftup(int[] A, int k) {
        while (k != 0) {
            int father = (k - 1) / 2;
            if (A[k] > A[father]) {
                break;
            }
            int temp = A[k];
            A[k] = A[father];
            A[father] = temp;
            
            k = father;
        }
    }
    
    public void heapify(int[] A) {
        for (int i = 0; i < A.length; i++) {
            siftup(A, i);
        }
    }
}
