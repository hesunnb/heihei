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
