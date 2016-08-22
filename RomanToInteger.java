/*Given a roman numeral, convert it to an integer.

The answer is guaranteed to be within the range from 1 to 3999.

Example
IV -> 4

XII -> 12

XXI -> 21

XCIX -> 99*/

public class Solution {
    public int romanToInt(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>(); //用哈希表存值
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        int sum = 0;
        for(int i = 0; i < s.length() - 1; i++) {
            if(map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                sum += map.get(s.charAt(i)) * (-1); //如果数比下一个小就加负的
            } else { //不比下一个小就加正的
                sum += map.get(s.charAt(i));
            }
        }
        sum += map.get(s.charAt(s.length() - 1)); //把最后一个数加上
        return sum;
    }
}

/*any of the letters representing numbers in the Roman numerical system: I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1,000. 
In this system, a letter placed after another of greater value adds (thus XVI or xvi is 16), 
whereas a letter placed before another of greater value subtracts (thus XC or xc is 90).*/
