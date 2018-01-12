/*Determine whether an integer is a palindrome. Do this without extra space.*/


class Solution {
    //solution1
    
    //负数不是回文数
    //考虑1000这样的数, 因为x=rev/10这步最后对于1000这种的数会因满足条件而不对
    public boolean isPalindrome(int x) {
        
        if (x < 0 || (x!=0 && x%10==0)){ //排除1000这类的数
            return false;
        } 

        int rev = 0;
        while (x > rev){ //比较数的一般即可, compare half of the digits in x, so don’t need to deal with overflow.
            rev = rev*10 + x%10; //跟reverseInteger那道题不同的是, 这里rev变完值自己给了自己, 而那道题是rst变完给了next_rst, 
            //为的就是用next_rst/10 != rst来判断越界, 而这里只是比较一半的数不用考虑越界的情况, 所以rev给了rev自己
            x = x/10;
        }

        return (x==rev || x==rev/10); //12321就是x==rev/10, 123321就是x==rev
    }
    
    
    
    //solution2
    public boolean isPalindrome(int x) {
        
        String s = String.valueOf(x);
        
        if (s.isEmpty()) {
            return true;
        }
        int start = 0, end = s.length() - 1;

        while(start < end) { //判断起来比ValidPalindrome那道题还简单, 直接转换成字符串从头到尾走一遍就行, 但是在这里新建一个字符串都算extra space
            if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }
        
        return true;
    }
}
