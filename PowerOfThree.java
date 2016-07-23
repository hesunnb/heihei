/* Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion? */

//三种方法, 这三种对于多少进制的都可以判断
public class Solution {
    public boolean isPowerOfThree(int n) {
        
        return n <= 0 ? false : Math.pow(3, Math.round(Math.log10(n) / Math.log10(3))) == n; //需要round一下, round四舍五入返回
        //long, 如果要是3的幂, 自然整除, pow回去还是原数, 如果不是3的幂, 就不会整除, 然后会有截断, pow回去就不是原值了
        
        return n <= 0 ? false : (Math.log10(n) / Math.log10(3)) % 1 == 0; //Math.log10(n) / Math.log10(3)是浮点数, 用浮点数对1
        //求余, 如果小数点后有值就不等于0(2.8 % 1 = 0.79999...), 能整除就是0.0 = 0
        
        return Integer.toString(n, 3).matches("10*"); //正则表达式, 把n转化为3进制, 看看是不是满足以10开头(后面都是0)
    }
}

/*
log(243) = 5.493061443340548    log(3) = 1.0986122886681098
   ==> log(243)/log(3) = 4.999999999999999

log10(243) = 2.385606273598312    log10(3) = 0.47712125471966244
   ==> log10(243)/log10(3) = 5.0
一个小注意事项就是log默认是自然对数, 容易出现偏差, 所以用log10()以10为底的更好
*/
