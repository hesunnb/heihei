/*
Write a method to replace all spaces in a string with %20. The string is given in a characters array, 
you can assume it has enough space for replacement and you are given the true length of the string.

You code should also return the new length of the string after replacement.

Notice
If you are using Java or Python，please use characters array instead of string.

Example

Given "Mr John Smith", length = 13.

The string after replacement should be "Mr%20John%20Smith", you need to change the string in-place and return the new length 17.
*/

public class Solution {
    /**
     * @param string: An array of Char
     * @param length: The true length of the string
     * @return: The true length of new string
     */
    public int replaceBlank(char[] string, int length) {
        // Write your code here
        //因为每次遇到空格增加两个空位，算上自己3个，前后有多余的空格也会按顺序替换掉
        if(string == null)
        {
            return 0;
        }
        int num = 0;
        for(int i = 0; i < length; i++)
        {
            if(string[i] == ' ') //扫一遍统计空格个数
            {
                num++;
            }
        }
        
        num = num * 2; //要增加的空格数，因为空格自己还占一位，所以加两位
        int newlength = length + num;
        int j = 1; //进行下标递减的步进
        for(int i = 0; i < length; i++)
        {
            if(string[length - i - 1] != ' ')
            {
                string[newlength - j] = string[length - i - 1]; //往后挪
                j++;
            }
            else
            {
                string[newlength - j] = '0';
                j++;
                string[newlength - j] = '2';
                j++;
                string[newlength - j] = '%';
                j++;
            }
        }
        return newlength;
    }
}
