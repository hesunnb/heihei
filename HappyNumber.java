/*Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

    12 + 92 = 82
    82 + 22 = 68
    62 + 82 = 100
    12 + 02 + 02 = 1
*/


public class Solution {
    /**
     * @param n an integer
     * @return true if this is a happy number or false
     */
    
    //开一个哈希表来记录原来得到过的值, 然后从哈希表判断即可
    //两种方法的getNextHappy(int n)是一样的
    public boolean isHappy(int n) {
        HashSet<Integer> m = new HashSet<Integer>();
        while (n != 1) {
            if (m.contains(n)) {
                return false;
            }
            m.add(n);
            n = getNextHappy(n);
        }
        return true;
    }
    
    private int getNextHappy(int n) {
        int sum = 0;
        while (n != 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
    
    
    
    //discuss, 不用额外空间, 类似于链表有环, 一个走的快, 一个走的慢, 重合了就不是快乐数了
    public boolean isHappy(int n) {
        if(n <= 0) {
            return false;
        }
        
        int x = n;
        int y = n;
        while(x != 1) {
            x = getNextHappy(x);
            if(x == 1) {
                return true;
            }
            y = getNextHappy(getNextHappy(y));
            if(y == 1) {
                return true;
            }
            if(x == y) {
                return false;
            }
        }
        return true;
    }
    
    private int getNextHappy(int n) {
        int sum = 0;
        while(n != 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
}
