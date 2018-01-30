/*Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.*/


class Solution {

    //solution1:方法好在用hashset就一遍循环然后还不用设置自己方法的那个flag位
    public int longestPalindrome(String s) {
        
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        Set<Character> hs = new HashSet<Character>();
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if(hs.contains(s.charAt(i))) {
                hs.remove(s.charAt(i)); //如果成对出现就移出
                count++; //count指对数, 对数加1
            } else {
                hs.add(s.charAt(i)); //如果第一次出现, 就加入到set中
            }
        }
        
        if(!hs.isEmpty()) {
            return count * 2 + 1; //扫完字符串如果set中最后还有剩余, 说明有奇数个的存在, 补上一个
        }
        return count * 2; //字符串s都是偶数个字母, 直接返回乘以2
    }
    
    
    //solution2:(own)
    public int longestPalindrome(String s) {
        
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        int[] map = new int[256];
        int result = 0;
        for(int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
        }
        
        boolean flag = false;
        for(int i = 0; i < map.length; i++) {
            if(map[i] != 0) {
                if(map[i] % 2 == 0) { //就查是不是偶数, 就是成对的, 是就加上
                    result += map[i];
                } else {
                    result += (map[i] - 1); //不是的话加上比奇数小的偶数
                    flag = true;
                }
            } 
        }
        if(flag) {
            result += 1; //如果要是出现过奇数的话, 补上一位, 因为最后回文串里只能有一个奇数
        }
        return result;
    }
}
