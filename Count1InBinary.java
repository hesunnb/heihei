public class Solution {
    /**
     * @param num: an integer
     * @return: an integer, the number of ones in num
     */
     
    
    //version 1:
    public int countOnes(int num) {
        // write your code here
        
        int temp = 0;
        int result = 0;
        for(int i = 0; i < 32; i++)
        {
            temp = num & 1;
            if(temp == 1)
            {
                result++;
            }
            num = num >>> 1; //逻辑右移，正负数高位都补0，所以就按位与统计1的个数就行啦
        }
        return result;
    }
    
    
    
    
    
    
    //version 2:
    public int countOnes(int num) {
        int count = 0;
        while (num != 0) {
            num = num & (num - 1); //与自己减1按位与, 会使每次num中1的个数少一个, 这个操作是去掉num二进制数中倒数第一个1
            count++;
        }
        return count;
        //x - (x - 1) & x可以找出最后一个1的位置
        //example:
        //x:                1xxxx1000
        //x - 1:            1xxxx0111
        //(x - 1) & x:      1xxxx0000
        //x - (x - 1) & x:  000001000 最后一个1的位置找出来了
    }
};
