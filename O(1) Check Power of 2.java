class Solution {
    /*
     * @param n: An integer
     * @return: True or false
     */
     
     
    //version 1: (首选)用count 1那个题的方法来看, 2的power, 那么这个数二进制里面就只能有一个1(1,10,100,1000)
    public boolean checkPowerOf2(int n) {
        // write your code here
        if(n <= 0)
        {
            return false;
        }
        
        return (n & (n - 1) == 0); //做一次与自己减1的操作后, 如果变成0就是2的power
    }
    
    
    
    
    
    
    //version 2: 用对2求余
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }
};
