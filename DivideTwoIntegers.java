/*Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.*/

public class Solution {
    /*复杂度是log2(diviend / divisor), 就是两个数化成二进制数位数的差(这个是最坏情况, 就是每次diviend只能被减去一位, 比如1111100, 100; 
    100乘以2到头, 只能消下去1111100中的一个1, 然后从新乘还只能消掉一个1; 好点儿的1111100, 111; 这样一次会减下去挺多, 次数就比位数差要小了): 
    log2(diviend) + 1是它二进制数的位数, log2(divisor) + 1是它二进数位数, 两个数相减就是结果*/
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1) { //如果除数是0, 或者被除数是最小值同时除数还是-1的话
            return Integer.MAX_VALUE; //这两种情况都越界, 所以返回最大值
        }
        
        if (dividend == 0) { //被除数是0, 返回结果0
            return 0;
        }
        
        boolean isNegative = (dividend < 0 && divisor > 0) || //被除数与除数异号, 那么结果就为负(15/-3=-5, -15/3=-5)
                             (dividend > 0 && divisor < 0);
                             
        long a = Math.abs((long)dividend); //a必须转成long, 因为被除数如果是-2147483648, 取绝对值后就越界了
        long b = Math.abs((long)divisor); //b也是一样, 并且b移位还总要比a大才结束, 所以b也一定必须是long
        int result = 0;
        while(a >= b){
            int shift = 0;
            while(a >= (b << shift)){ //a向左移位直到比b大
                shift++; //记录移位次数
            }
            a -= b << (shift - 1); //a减去b刚刚比a小的那个数
            result += 1 << (shift - 1); //1左移shift - 1位就是这一小步的商, 加到结果中
        }
        return isNegative? -result: result; //是负数就把负号添上
    }
}

/*In this problem, we are asked to divide two integers. However, we are not allowed to use division, multiplication and mod operations. 
So, what else can we use? Yeah, bit manipulations.

Let's do an example and see how bit manipulations work.

Suppose we want to divide 15 by 3, so 15 is dividend and 3 is divisor. Well, division simply requires us to find how many times we can 
subtract the divisor from the the dividend without making the dividend negative.

Let's get started. We subtract 3 from 15 and we get 12, which is positive. Let's try to subtract more. Well, we shift 3 to the left 
by 1 bit and we get 6. Subtracting 6 from 15 still gives a positive result. Well, we shift again and get 12. We subtract 12 from 15 
and it is still positive. We shift again, obtaining 24 and we know we can at most subtract 12. Well, since 12 is obtained by shifting 
3 to left twice, we know it is 4 times of 3. How do we obtain this 4? Well, we start from 1 and shift it to left twice at the same time.
We add 4 to an answer (initialized to be 0). In fact, the above process is like 15 = 3 * 4 + 3. We now get part of the quotient (4), 
with a remainder 3.

Then we repeat the above process again. We subtract divisor = 3 from the remaining dividend = 3 and obtain 0. We know we are done. 
No shift happens, so we simply add 1 << 0 to the answer.

Now we have the full algorithm to perform division.

According to the problem statement, we need to handle some exceptions, such as overflow.

Well, two cases may cause overflow:

divisor = 0;
dividend = INT_MIN and divisor = -1 (because abs(INT_MIN) = INT_MAX + 1).
Of course, we also need to take the sign into considerations, which is relatively easy.

Putting all these together, we have the following code.*/
