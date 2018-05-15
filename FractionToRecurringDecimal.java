/*Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"*/

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        if(denominator == 0) { //分母是0, 返回""作为非法结果
            return "";
        }
        
        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator); //有一个testcase是输入分子-1, 分母-2147483648, 
        //正确答案应是"0.0000000004656612873077392578125", 分母的绝对值大于Integer.MAX_VALUE, 所以要转成long, note:-1除以-2147483648是可以
        //整除的
        
        // integral part
        res.append(num / den);
        num %= den;
        if (num == 0) { //如果能够整除, 就直接返回
            return res.toString();
        }
        
        // fractional part
        res.append(".");
        Map<Long, Integer> map = new HashMap<>();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10; //这个每次num*10就跟除法的借位是一样的, 把余数变大接着除
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) { //map里面放的是余数, 和得到这个余数时候res的长度, 这个长度正好就是后面要加入左括号的index
                //只要余数一重复, 就说明肯定是有限循环小数了, 那么像0.0000000004656612873077392578125这个里面66,77数字连着的是每次num / den
                //的商, 不是余数, 余数都在哈希表里, 在res里显示不出来的
                int index = map.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            }
            else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }
    //-1, -2147483648的哈希表的打印
    /*843055104=24, 
     *1988100096=25, 
     *553648128=26, 
     *1241513984=27, 
     *1677721600=28, 
     *1744830464=29, 
     *268435456=30, 
     *0=33, 
     *536870912=31, 
     *1073741824=32, 
     *1=2, 
     *1874919424=18, 
     *616988672=17, 
     *10=3, 
     *1569325056=19, 
     *1410065408=12, 
     *1000000=8, 
     *10000=6, 
     *1661992960=22, 
     *10000000=9, 
     *1000000000=11, 
     *1587544064=23, 
     *100000=7, 
     *100=4, 
     *660865024=20, 
     *1420103680=14, 
     *1000=5, 
     *166199296=21, 
     *1316134912=15, 
     *100000000=10, 
     *1215752192=13, 
     *276447232=16}*/
}
