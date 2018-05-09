/*The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence 
must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.*/

class Solution {
    /*My idea is to generate the sequence iteratively. For example, when n=3, we can get the result based on n=2.
    00,01,11,10 -> (000,001,011,010) (110,111,101,100). The middle two numbers only differ at their highest bit, while the rest numbers 
    of part two are exactly symmetric of part one. It is easy to see its correctness.*/
    public List<Integer> grayCode(int n) {
        
        List<Integer> result = new ArrayList<>();
        if(n < 0) {
            return result;
        }
        
        result.add(0); //n至少是1, 先把0加进去, 然后在0的基础之上添加1
        for(int i = 0; i < n; i++){
            int size = result.size(); //因为是对称加入, 所以先拿出原来result里面的大小, 在最高位加入0等于没加, 所以下面只加入1
            for(int k = size - 1; k >= 0; k--) {
                result.add(result.get(k) | 1 << i); //从最后一位到第一位, 取出每一个数, 在它的最高位加入1
            }
        }
        return result;
    }
}
