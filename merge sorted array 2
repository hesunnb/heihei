class Solution {
    /**
     * @param A and B: sorted integer array A and B.
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // Write your code here
        
        //思路就是比较A数组和B数组的开头，谁小就把谁放进result
        //复杂度是O(n)
        int a = A.length;
        int b = B.length;
        int result[] = new int[a+b];
        
        int i = 0, j = 0, k = 0;
        while(i < a && j < b )
        {
            if(A[i] > B[j])
            {
                result[k] = B[j];
                k++;
                j++;
            }
            else
            {
                result[k] = A[i];
                k++;
                i++;
            }
        }
        
        //在其中一个数组到达尾部退出之后，将剩下那个没有走完的数组的剩下的内容接着添加到result中
        while(i < a) 
        {
            result[k] = A[i];
            k++;
            i++;
        }
        
        while(j < b)
        {
            result[k] = B[j];
            k++;
            j++;
        }
        
        return result;
        
        
        //思路就是定义一个数组然后把数都加进去排个序
        
        //复杂度O(n+logn)
      /*  int a = A.length;
        int b = B.length;
        int result[] = new int[a+b];
        
        for(int i = 0; i < a; i++)
        {
            result[i] = A[i];
        }
      
        for(int j = 0; j < b; j++)
        {
            result[j + a] = B[j];
        }
        
        Arrays.sort(result);
        return result;*/
    }
}
