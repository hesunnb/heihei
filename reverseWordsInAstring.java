
public class Solution {
    /**
     * @param s : A string
     * @return : A string
     */
    public String reverseWords(String s) {
        // write your code

        //用Java的函数库棒棒哒
        if(s == null || s.length() == 0)
        {
            return s; //如果要是"",它翻转完了也是"",所以不能返回null
        }
        
        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = array.length - 1; i >= 0; --i) {
        
            if (!array[i].equals("")) { //这个得加上,split函数的一个特点是字符串前面的分隔符删掉之后会留下一个"",即空字符串(里面没有空格)，
            //所以加的时候要把这个空字符串给去掉。字符串后面的分隔符没有影响，会正常去掉; 中间也是, 比如中间有两个空格, 去掉一个空格, 
            //然后剩下的那个空格变为"", 然后占一个位置; 尾部的空格全都去掉, 不变成"", 不占位置
            
                sb.append(array[i]).append(" ");
            }
        }
        
         return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1); //==0是输入全是空格的情况:" "，这种情况按照题意应返回"",就是把里面的空格去掉，
        //因为这个空格算作leading or trailing spaces; split(" ")对于输入全是空格的情况会把所有的空格都删掉, 有一个"", 不加进去, sb长度就是0
        //所以在这里要处理返回"", 否则下面的substring取值就是(0, -1), 该报错啦
        
        //剩下的就是把最后一个空格给去掉
    }
    
    
    //针对不让用split的情况
    public String reverseWords(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        
        s = s.trim();
        if(s.length() == 0) {
            return s;
        }
        
        StringBuilder sb = new StringBuilder();
        int left = 0;
        for(int i = 0; i < s.length(); i++) { //现在开头结尾的肯定都是字母了
            if(s.charAt(i) != ' ') { //是字母都跳过去
                continue;
            }
            sb.insert(0, " " + s.substring(left, i)); //插入截取出来的单词
            while(s.charAt(i) == ' ') { //跳过中间的空格
                i++;
            }
            left = i; //left到了新的单词的头部
        }
        sb.insert(0, s.substring(left)); //插入最后一个单词
        return sb.toString(); //返回结果
    }
}

