/*Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.


Follow up:
Could you solve it using only O(1) extra space?


Example 1:
Input:
["a","a","b","b","c","c","c"]

Output:
Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

Explanation:
"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
Example 2:
Input:
["a"]

Output:
Return 1, and the first 1 characters of the input array should be: ["a"]

Explanation:
Nothing is replaced.
Example 3:
Input:
["a","b","b","b","b","b","b","b","b","b","b","b","b"]

Output:
Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

Explanation:
Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
Notice each digit has it's own entry in the array.
Note:
All characters have an ASCII value in [35, 126].
1 <= len(chars) <= 1000.*/


class Solution {
    public int compress(char[] chars) {
        
        //这个方法好在用currentChar来存住要比较的数, 然后用index向后移动每位进行比较, 用j与j+1这种弄不出来
        if(chars == null || chars.length == 0) {
            return 0;
        }
        
        int index = 0, indexAns = 0; //Ans是answer的缩写, index也是Answer的意思
        while(index < chars.length) {
            char currentChar = chars[index];
            int count = 0;
            while(index < chars.length && chars[index] == currentChar) { //向abbbb这种index < chars.length就是防止越界, 不加这个条件
            //chars[index]该越界了
                index++;
                count++;
            }
            chars[indexAns++] = currentChar; //先给字母,只有chars[index]与currentChar不相等的时候才会到这步, 所以用currentChar来保存数的好处
            //就体现了
            if(count != 1) { //再给数
                for(char c : String.valueOf(count).toCharArray()) { //10以上多位的数的处理
                    chars[indexAns++] = c;
                }
            }
        }
        return indexAns;
    }
}
