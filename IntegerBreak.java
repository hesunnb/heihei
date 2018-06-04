/*Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. 
Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58.*/

class Solution {
    
    //solution1: 用动归解, 动归的方法并没有math方法好, 但是解法更通用, 适合面试
    public int integerBreak(int n) {
        
        if(n < 2 || n > 58) {
            return Integer.MIN_VALUE;
        }
        
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i ++) { //n在2到58之间, 所以i从2开始
           for(int j = 1; j < i; j ++) {
               dp[i] = Math.max(dp[i], (Math.max(j,dp[j])) * (Math.max(i - j, dp[i - j]))); 
               //让下标与下标对应值比较, 挑出大的进行相乘
               //比如i=6, 就是输入为6的时候, j和i-j组成对应相加的因子, 而j和i-j这两个因子(下标)分别对应以它们为下标数组中的值, 数组中的值就是输入
               //为i的时候能得到的乘积最大值, 和输入为i-j的时候能得到的乘积最大值; 那么再进行相乘得到新的值的时候, 谁大就挑哪个, 因为一个因子的拆分
               //的乘积就是以它为下标在数组中对应的值, 如果这个值还没有它本身大的时候就不用进行拆分了, 就直接选择因子相乘, 反之就用大的数组中的值
           }
        }
        return dp[n];
    }
    
    
    //solution2: math方法解, 巧妙
    public int integerBreak(int n) {
        
        if(n < 2 || n > 58) {
            return Integer.MIN_VALUE;
        }
        
        if(n==2) return 1;
        if(n==3) return 2;
        int product = 1;
        while(n > 4) { //这道题最终结论就是用最多的3相乘, 剩下的就用2补全
            product *= 3;
            n -= 3;
        }
        product *= n;
        
        return product;
    }
    /*The first thing we should consider is : What is the max product if we break a number N into two factors?

    I use a function to express this product: f=x(N-x)

    When x=N/2, we get the maximum of this function.

    However, factors should be integers. Thus the maximum is (N/2)*(N/2) when N is even or (N-1)/2 *(N+1)/2 when N is odd.

    When the maximum of f is larger than N, we should do the break.

    (N/2)*(N/2)>=N, then N>=4

    (N-1)/2 *(N+1)/2>=N, then N>=5

    These two expressions mean that factors should be less than 4, otherwise we can do the break and get a better product. The factors 
    in last result should be 1, 2 or 3. Obviously, 1 should be abandoned. Thus, the factors of the perfect product should be 2 or 3.

    The reason why we should use 3 as many as possible is

    For 6, 3 * 3>2 * 2 * 2. Thus, the optimal product should contain no more than three 2.*/
    
    /*I think a good way to explain why we need to use 3 as much as possible is: assume a breakdown has 3 twos, those 3 twos have a 
    product of 8 which is less than the product of 2 threes(we can improve it that way). So we can prove the breakdown can have at most 
    2 twos. Thus, the conclusion is we should use 3 as much as possible.*/
}
