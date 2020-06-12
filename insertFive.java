/*188. Insert five
Given a number, insert a 5 at any position of the number to make the number after insertion the largest

Example
Example 1:

Input:  a = 234
Output: 5234
Notice
|a|≤10^6*/

public class Solution {
    /**
     * @param a: A number
     * @return: Returns the maximum number after insertion
     */
    
    // testcase: 565 -> 5655; -524 -> -5245
    public int InsertFive(int a) {
        // write your code here
        StringBuilder sb = new StringBuilder(String.valueOf(a));
        int i = 0;
        if(a >= 0) {
            for(i = 0; i < sb.length(); i++) {
                if('5' > sb.charAt(i)) { // 对于上面的testcase只要注意这里不能带等号就行, >=不行
                    sb.insert(i, '5');
                    break;
                }
            }
        } else {
            for(i = 1; i < sb.length(); i++) { //略过了负号
                if('5' < sb.charAt(i)) { // 不能带等号, <=不行
                    sb.insert(i, '5');
                    break;
                }
            }
        }
        if(i == sb.length()) {
            sb.append('5');
        }
        return Integer.parseInt(sb.toString());
    }
}
