public class Solution {
    /**
     * @param a a number
     * @param b a number
     * @return the result
     */
     
    //思路就是模拟二进制的加法运算，求余得结果，求商得进位
    public String addBinary(String a, String b) {
        // Write your code here
        if(a.length() < b.length()) //这里确保a最大，做和，谁最大都行
        {
            String temp = a;
            a = b;
            b = temp;
        }
        
        int pa = a.length() - 1;
        int pb = b.length() - 1;
        int carries = 0; //进位
        String result = ""; //结果
        
        while(pa >= 0 && pb >= 0)
        {
            //一定要减'0'，不减'0'的话，就成啦'0'或'1'的ASCII值啦
            int sum = (int)(a.charAt(pa) - '0') + (int)(b.charAt(pb) - '0') + carries; //求和带进位
            result = String.valueOf(sum % 2) + result; //把每次得到的余数加到结果里,不能写成+=，因为要把每次的结果加到前面，不是顺次加，所以要把得到的余数写在前，result写在后
            carries = sum / 2; //得到进位
            pa--;
            pb--;
        }
        
        while(pa >= 0)
        {
            int sum = (int)(a.charAt(pa) - '0') + carries;
            result = String.valueOf(sum % 2) + result;
            carries = sum / 2;
            pa--;
        }
        
        if(carries == 1)
        {
            result = "1" + result;
        }
        return result;
    }
}
