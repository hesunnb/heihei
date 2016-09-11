/*Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.*/

//题意就是在一个数组中找到后面减前面差值最大的两个数
public class Solution {
    
    /*O(n)扫一遍就可以了*/
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }
        
        int min = Integer.MAX_VALUE; //min保存访问过的最小值
        int profit = 0;
        for(int i = 0; i < prices.length; i++) {
            if(prices[i] < min) { //小于最小值就要更新最小值
                min = prices[i];
            }
            if(profit < prices[i] - min) { //大于profit就更新profit
                profit = prices[i] - min;
            }
        }
        
        return profit;
    }
}


public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
     
    /*O(n^2), 与longestIncreasingSubsequence的O(n^2)解法一样, 超时*/
    public int maxProfit(int[] prices) {
        // write your code here
        
        if(prices == null || prices.length == 0) {
            return 0;
        }
        
        int[] count = new int[prices.length];
        int max = 0;
        for(int i = 0; i < prices.length; i++) {
            count[i] = 0;
            for(int j = 0; j < i; j++) {
                if(prices[i] > prices[j]) {
                    count[i] = Math.max(count[i], prices[i] - prices[j]);
                }
                max = Math.max(max, count[i]);
            }
        }
        
        return max;
    }
}
