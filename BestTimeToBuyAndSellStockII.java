/*Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of 
the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock 
before you buy again).*/

class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        // write your code here
        //可以多次买卖, 就是当天卖当天买也行, 所以就成了逢低就买, 逢高就卖
        if(prices == null || prices.length == 0) {
            return 0;
        }
        
        int profit = 0;
        for(int i = 0; i < prices.length - 1; i++) {
            if(prices[i + 1] > prices[i]) {
                profit += prices[i + 1] - prices[i];
            }
        }
        return profit;
    }
    //此法不能用于1问, 一个例子就是[2,1,2,0,1], 单次最高只能是1, 而多次就可以是2了
};
