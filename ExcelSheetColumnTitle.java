/*Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB */
    
class Solution {
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();

        while(n > 0){
            n--;
            result.insert(0, (char)('A' + n % 26)); //巧啊, 每次都能求余取出对应的位
            n /= 26; //26一组为一层
        }

        return result.toString();
    }
}
