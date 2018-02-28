/*Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in 
adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n 
new flowers can be planted in it without violating the no-adjacent-flowers rule.

Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: True
Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: False
Note:
The input array won't violate no-adjacent-flowers rule.
The input array size is in the range of [1, 20000].
n is a non-negative integer which won't exceed the input array size.*/


class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        
        if(flowerbed == null || flowerbed.length == 0 || n < 0 || n > flowerbed.length) {
            return false;
        }
        
        int count = 0;
        for(int i = 0; i < flowerbed.length && count < n; i++) { //如果循环还没到头, count就已经等于n了就不用再循环了, 条件已经满足了
            if(flowerbed[i] == 0) {
                int next = 0;
                int prev = 0;
                if(i == flowerbed.length - 1) { //如果i是最后一个, 维持原值, 因为flowerbed[i] == 0才能进入这个判断语句, 所以next = 0
                //就是维持原值
                    next = 0;
                } else {
                    next = flowerbed[i + 1];
                }

                if(i == 0) { //同理维持原值
                    prev = 0;
                } else {
                    prev = flowerbed[i - 1];
                }
            
                if(next == 0 && prev == 0) { //能种花
                    flowerbed[i] = 1;
                    count++;
                }
            }
        }
        
        return count == n;
    }
}
