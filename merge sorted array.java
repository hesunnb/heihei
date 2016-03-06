class Solution {
    /**
     * @param A: sorted integer array A which has m elements, 
     *           but size of A is m+n
     * @param B: sorted integer array B which has n elements
     * @return: void
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        
        
        //思路就是因为A是个排序数组，所以那些空的地方的０是不能够插在数组中的，比如1,0,2,0,3这种是不可能出现的
        //所以就倒着往回找，比较A和B的尾巴，然后往A的尾巴那里装,扫一遍就能完成
        
        //复杂度:O(n)
        int i = m - 1, j = n - 1, index = m + n - 1;
        while(i >= 0 && j >= 0)
        {
            if(A[i] > B[j])
            {
                A[index] = A[i];
                index--;
                i--;
            }
            else
            {
                A[index] = B[j];
                index--;
                j--;
            }
        }
        
        while(j >= 0) //这个这么理解, A数组与B数组当中的最小值如果在A中, 循环退出之后B就已经和进A中啦; A数组与B数组当中的最小值在B中, B还没有和完, 所以循环退出之后还要和B; 所以总是要操作B
        {
            A[index] = B[j];
            index--;
            j--;
        }
        /*//自己的方法：见到A里面有0，就把B里面的数替换进去，A里面如果有多个0的话，一定会留下来一个；然后把A排个序
        //复杂度应该是O(n+logn)
      
        int a = A.length;
        int b = B.length;
        
        for(int i = 0, j = 0; i < a; i++)
        {
            if(j < b)
            {
                if(A[i] == 0)
                {
                    A[i] = B[j];
                    j++;
                }
            }
        }
        
        Arrays.sort(A);*/
    }
}
