/*Calculate the an % b where a, b and n are all 32bit integers.

Example

For 2^31 % 3 = 2

For 100^1000 % 1000 = 0

*/

class Solution {
    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     */
    public int fastPower(int a, int b, int n) {
        // write your code here
        
        if(n == 1) {
            return a % b;
        }
        
        if(n == 0) { //只有在最开始n = 0的时候才能用到, 其余的时候n / 2除到1的时候就return了
            return 1 % b;
        }
        
        long product = fastPower(a, b, n / 2); 
        product = (product * product) % b;
        if(n % 2 == 1) {
            product = (product * a) % b;
        }
        return (int)product;
    }
    
    /*
    testCase: fastPower(2 ,6 ,6)
    调用fastPower(2, 6, 3), 再调用fastPower(2, 6, 1)
    在fastPower(2, 6, 1)的时候就会返回a % b, 在fastPower(2, 6, 3)的时候会得到2^3%6 = (2^1%6 * 2^1%6)%6, 这时候少了一个2, 所以要product要*2再取余, 最后一步一定是取余
    
    a^n%b = (a^(n/2)%b * a^(n/2)%b)%b (n为偶数, 奇数的话就是2^5%b = ((2^2)%b * (2^3)%b)%b)
    所以要知道a^n%b, 只需知道a(n/2)%b, 要知道a(n/2)%b, 只需知道a(n/4)%b...所以只需要算log(n)次就结束了(比O(n)好)
    因为product每次都是余数, 对b求余的余数一定小于b, 所以long能够装下(Integet.MAX_VALUE - 1) * (Integet.MAX_VALUE - 1)
    */
};
