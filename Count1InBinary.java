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
            num = num & (num - 1); //与自己减1按位与, 会使每次num中1的个数少一个
            count++;
        }
        return count;
    }
};
