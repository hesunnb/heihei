/*Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 2^31).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.*/

class Solution {
    public int findNthDigit(int n) {
        
        if(n <= 0) {
            return n;
        }
        
        int len = 1;
	long count = 9;
	int start = 1;

	while (n > len * count) {
		n -= len * count;
		len += 1;
		count *= 10;
		start *= 10;
	}

	start += n / len;
	String s = Integer.toString(start);
	return Character.getNumericValue(s.charAt(n % len - 1));
    }
    /*分析
    1,2 … 8,9, 10, 11, 12 … 98, 99, 100, 101, 102 … 998, 999, 1000 …
    observation: 假设我们要找第2886 个位 (就target数 是998，target bit 是8)
    1~9 9*1bit
    10~99 90*2bit
    100~999 900*3bit
    那么2886 - 9 - 9 * 10 * 2 = 2697 < 9*10*10*3 = 2700
    那么target 就落在了区域3中( 100- 999 )

    我们知道区域3 是由3bit的数组成的
    所以target数是以 100 为起始数，(2697 - 1)/3 = 898 为100以后的数
    target 数 = 100 + 898 = 998
    (2697-1) % 3 = 2 就是 998 的target bit 
    target bit = 998.charAt( 2 ) = 8;

    解题思路
    2886 = 9*1 + 90 * 2 + 2697*/
    
    /*对于(n-1)的解释
    The reason why (n-1) is to keep the correct digits finally in number they correspond to. Eg: if we are trying to find the 192th 
    digit, we know range from 1th digit to 9th digit belongs to numbers from 1 to 9 and range from 10th to 189th belongs to numbers 
    from 10 to 99, right? So it is obvious that the next number should be 100 and the 192th digit should be the 3rd digit of 
    100(now n=3). OK, back to the code, if we donot minus 1 from n and then devide the len, the 192th digit would go to the next 
    number which is 101.*/
}
