/*You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the 
number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret 
number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong 
position (called "cows"). 
Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate 
the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.

Example 1:

Input: secret = "1807", guess = "7810"

Output: "1A3B"

Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
Example 2:

Input: secret = "1123", guess = "0111"

Output: "1A1B"

Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.*/

class Solution {

    //solution1: one-pass, 用一个数组就行
    public String getHint(String secret, String guess) {
        
        if(secret == null || guess == null) {
            return "";
        }
        
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10]; //用来装0~9
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            }
            else {
                if (numbers[secret.charAt(i)-'0'] < 0) { //secret来判断guess在这个位置上是否出现过
                    cows++;
                }
                if (numbers[guess.charAt(i)-'0'] > 0) { //guess来判断secret在这个位置上是否出现过
                    cows++;
                }
                numbers[secret.charAt(i)-'0']++; //secret用++来表示这个数出现过
                numbers[guess.charAt(i)-'0']--; //guess用--来表示这个数出现过
            }
        }
        return bulls + "A" + cows + "B";
    }
    
    
    //solution2: (own), 用哈希表解决
    public String getHint(String secret, String guess) {
        
        if(secret == null || guess == null) {
            return "";
        }
        
        Map<Character, Integer> map = new HashMap<>();
        int bulls = 0;
        int cows = 0;
        for(int i = 0; i < secret.length(); i++) {
            if(i < guess.length()) { //注意不能超过guess的长度, 否则越界了
                if(secret.charAt(i) == guess.charAt(i)) { //扫一遍找出bulls
                    bulls++;
                }
            }
            map.put(secret.charAt(i), map.getOrDefault(secret.charAt(i), 0) + 1);
        }
        
        for(int i = 0; i < guess.length(); i++) {
            if(map.containsKey(guess.charAt(i))) {
                map.put(guess.charAt(i), map.get(guess.charAt(i)) - 1);
                if(map.get(guess.charAt(i)) >= 0) {
                    cows++; //根据map扫出总数
                }
            }
        }
        cows -= bulls; //更新cows为真正的cows值
        return bulls + "A" + cows + "B"; //返回结果
    }
}
