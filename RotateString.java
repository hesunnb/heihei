public class Solution {
    /**
     * @param str: an array of char
     * @param offset: an integer
     * @return: nothing
     */
    public void rotateString(char[] str, int offset) {
        // write your code here
        
        //复杂度O(n):三步翻转法
        if(str != null && str.length > 0) //防止null以及空数组
        {
            int len = str.length;
            int offsetfinal = offset % len; //offset可能很大,像10000这种，所以求余数组长判断移位数
            if(offsetfinal != 0) //当移位不是0就移动，否则就不移动（移动0位或者len个位都不移动）
            {
                int index = len - offsetfinal; //index指向要翻转的地方
                if(index <= len - 1) //以防万一，保证index不越界
                {
                    rotate(str, index, len - 1); //三步翻转法，index到尾
                    rotate(str, 0, index - 1); //头到index
                    rotate(str, 0, len - 1); //整体翻转
                }
            }
        }
    }
    
    public void rotate(char[] str, int start, int end) //翻转具体实现，和recover rotated sorted array的这个函数一样
    {
        for(int i = start, j = end; i < j; i++, j--)
        {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
    }
}
