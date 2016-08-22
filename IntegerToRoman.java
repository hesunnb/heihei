/*Given an integer, convert it to a roman numeral.

The number is guaranteed to be within the range from 1 to 3999.

Example
4 -> IV

12 -> XII

21 -> XXI

99 -> XCIX*/

public class Solution {
    public String intToRoman(int num) {
        if(num <= 0) {
			return "";
		}
		//罗马数字里面没有0, 题目给的是1到3999
		//按照计数规则, 写在左边减的只能有用一个, 写在右边加的最多3个, 所以这样一个对照表能够覆盖所有的数
		//(比如大于等于900小于1000的就用多少个900表示, 大于500小于900的就用D+多少个C(肯定不超过3个)表示, 这样就能覆盖所有数)
		int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	    
	    StringBuilder sb = new StringBuilder();
	    int digit = 0;
	    while(num > 0) {
	        int times = num / nums[digit];
	        num -= nums[digit] * times;
	        while(times > 0) {
	            sb.append(symbols[digit]);
	            times--;
	        }
	        digit++;
	    }
	    return sb.toString();
    }
    
    /*
    基本字符:               I V X  L  C   D   M
    相应的阿拉伯数字表示为:	1 5 10 50 100 500 1000
    
    计数规则:
    相同的数字连写、所表示的数等于这些数字相加得到的数、如：Ⅲ=3；
    小的数字在大的数字的右边、所表示的数等于这些数字相加得到的数、 如：Ⅷ=8、Ⅻ=12；
    小的数字、（限于 Ⅰ、X 和 C）在大的数字的左边、所表示的数等于大数减小数得到的数、如：Ⅳ=4、Ⅸ=9；
    正常使用时、连写的数字重复不得超过三次；
    在一个数的上面画一条横线、表示这个数扩大 1000 倍。
    
    组数规则:

    基本数字 Ⅰ、X 、C 中的任何一个、自身连用构成数目、或者放在大数的右边连用构成数目、都不能超过三个；放在大数的左边只能用一个；
    不能把基本数字 V 、L 、D 中的任何一个作为小数放在大数的左边采用相减的方法构成数目；放在大数的右边采用相加的方式构成数目、只能使用一个;
    I只能用在V和X左边；
    X只能用在L和C左边；
    C只能用在D和M左边。
    */
}
