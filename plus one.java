public class Solution {
    /**
     * @param digits a number represented as an array of digits
     * @return the result
     */
     
    
    //version 1: 九章()
    //The complexity is O(1)
    //f(n) = 9/10 + 1/10 * O(n-1)
    //==>  O(n) =  10 / 9 = 1.1111 = O(1)
    
    //T(n) = 9/10 + 1/10 * T(n-1)
    public int[] plusOne(int[] digits) {
        // Write your code here
        
        int i = digits.length - 1;
        int carries = 1;
        
        while(i >= 0 && carries > 0) // fast break when carries equals zero
        {
            int sum = carries + digits[i];
            digits[i] = sum % 10; //余数放回去
            carries = sum / 10; //商当进位
            i--;
        }
        
        if(carries == 0) //最后数组长度没有变化, 直接返回数组
        {
            return digits;
        }
        
        int result[] = new int[digits.length + 1]; //有进位, 就比原来长1
        result[0] = carries;
        for(int k = 1; k < result.length; k++)
        {
            result[k] = digits[k - 1];
        }
        
        return result;
    }
}
