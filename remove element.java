public class Solution {
    /** 
     *@param A: A list of integers
     *@param elem: An integer
     *@return: The new length after remove
     */
     
     
    //version 1: (首选)一个for
    public int removeElement(int[] A, int elem) {
        // write your code here
        if(A == null || A.length == 0)
        {
            return 0;
        }
        
        int i = 0;
        for(int k = 0; k < A.length; k++) //扫一遍
        {
            if(A[k] != elem) //不想等就赋值
            {
                A[i] = A[k];
                i++;
            }
        }
        return i;
    }
    
    
    
    
    
    //version 2: 一个while
    public int removeElement(int[] A, int elem) {
        int i = 0;
        int pointer = A.length - 1;
        while(i <= pointer)
        {
            if(A[i] == elem)
            {
                A[i] = A[pointer]; //用一个尾指针向回走
                pointer--;
            } 
            else 
            {
                i++; //i指针向前走
            }
        }
        return i; //p和i相等之后肯定还要判断一次, 然后p和i肯定要错开, 如果p和i都是有效值的时候, i向后走一步, 
        //然后i前面都是有效值, 正好返回i, 如果p和i都是无效值的时候, p向前走一步, 此时i前面也都是有效值, 还是返回i
    }
}
