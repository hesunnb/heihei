/*Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).*/

class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        // write your code here
        //找一个数组中两组最大差之和
        //用前后遍历, 一个正序求最大差值, 当前值-最小值
        //一个倒序求最大值, 最大值-当前值
        //最后每一个位置都有一个正序到这个位置的最大值和倒序到这个位置的最大值, 求和之后就是总的最大值
        
        if(prices == null || prices.length == 0) {
            return 0;
        }
        
        int[] left = new int[prices.length]; //用于保存正序的每步的profit
        int[] right = new int[prices.length]; //用于保存倒序的每步的profit
        
        int min = 0; //正序保存最小值
        int max = 0; //倒序保存最大值
        
        min = prices[0];
        left[0] = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < min) {
                min = prices[i]; //每步的最小值
            }
            left[i] = Math.max(left[i - 1], prices[i] - min); //当前值-最小值
        }
        
        max = prices[prices.length - 1];
        right[right.length - 1] = 0;
        for(int i = prices.length - 2; i >= 0; i--) {
            if(prices[i] > max) {
                max = prices[i];
            }
            right[i] = Math.max(right[i + 1], max - prices[i]); //最大值-当前值
        }
        
        int profit = 0;
        for(int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, left[i] + right[i]); //这个不用right[i + 1]的原因是, 当天卖之后当天还可以买(可以重复使用一次元素)
            //比如在testCase中, 正数第二个1, 2+4=6才对, 2+3=5就不对了, 而在maximumSubarray中用right[i + 1]的原因是数组中的元素
            //不能重复使用(non-overlapping)
        }
        
        return profit;
    }
    /*
    testCase: [4,4,6,1,1,4,2,5]
    left:      0,0,2,2,2,3,3,4
    
    testCase: [4,4,6,1,1,4,2,5]
    right:     4,4,4,4,4,3,3,0
                   比如在4这个位置
    它情面能达到的最大值是3, 它后面能达到的最大值是3, 所以3+3=6, 每一个位置都有自己前面的最大值和后面的最大值
    */
};
