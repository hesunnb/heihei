public class Solution {
     
    //思路就是模拟二进制的加法运算，求余得结果，求商得进位
    public String addBinary(String a, String b) {
        // Write your code here
        //version 1: 1个while
        if(a == null || b == null) {
            return null;
        }
        
        int aLen = a.length() - 1;
        int bLen = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carries = 0;
        while(aLen >= 0 || bLen >= 0 || carries == 1) { //把最后的carries是不是1的判断也合并到这里来, 就只剩一个while啦
            int sum = carries;
            if(aLen >= 0) {
                sum += a.charAt(pa--) - '0';
            }
            if(bLen >= 0) {
                sum += b.charAt(pb--) - '0';
            }
            sb.insert(0, sum % 2); //这里用insert在头部插入就行
            carries = sum / 2;   
        }
        
        return sb.toString();
        //如果要是10进制以内, 就加入一个k代表进制, 然后每次sum % k, sum / k就行啦

        
        //version 2: 1个while还有一个if
        if(a == null || b == null)
        {
            return null;
        }
        
        int pa = a.length() - 1;
        int pb = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carries = 0;
        while(pa >= 0 || pb >= 0) //把最后的carries是不是1的判断也合并到这里来, 就只剩一个while啦
        {
            int sum = carries;
            if(pa >= 0)
            {
                sum += a.charAt(pa--) - '0';
            }
            if(pb >= 0)
            {
                sum += b.charAt(pb--) - '0';
            }
            sb.insert(0, sum % 2); //这里用insert在头部插入就行
            carries = sum / 2;   
        }
        
        if(carries != 0)
        {
            sb.insert(0, carries);
        }
        
        return sb.toString();
        

        //version 3: 2个while
        /*if(a.length() < b.length()) //这里确保a最大，做和，谁最大都行
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
            result = String.valueOf(sum % 2) + result; //把每次得到的余数加到结果里,不能写成+=，因为要把每次的结果加到前面，不是顺次加，
            //所以要把得到的余数写在前，result写在后
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
        return result;*/
    }
}
