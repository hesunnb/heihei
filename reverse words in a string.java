
public class Solution {
    /**
     * @param s : A string
     * @return : A string
     */
    public String reverseWords(String s) {
        // write your code
        
        public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        
        

        
            
                
            }
        }

        //remove the last " "
       
    }
        //用Java的函数库棒棒哒
        if(s == null || s.length() == 0)
        {
            return ""; //如果要是"",它翻转完了也是"",所以不能返回null
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
        
    
        
        
        //用两个栈来实现翻转过程
        /*Stack statement=new Stack();
        Stack unit=new Stack();
        
        int i=0;
        while(i<=s.length()) //必须要加等于，因为如果输入为"world",没有空格，为了能够执行到把unit栈中的值放到statement栈中，就需要进入下面的if语句，所以要把=加上来，然后倒一次
        {
            if(i==s.length()||s.charAt(i)==' ') //正式的单词前面或后面或中间又连续的空格都会被直接跳过，到下面的i++，因为遇到一个空格的时候，unit栈中的东西都被倒到statement栈中了，所以unit为空，之后的空格也一直为空，下面的就都不执行，只有i++
            {
                if(!unit.isEmpty())
                {
                    if(!statement.isEmpty()) //只有statement栈中不为空的时候，才加入空格，不会加入多余的空格
                    {
                        statement.push(' ');
                    }
                }
                while(!unit.isEmpty())
                {
                    statement.push(unit.peek()); //倒一下
                    unit.pop();
                }
            }
            else
            {
                unit.push(s.charAt(i));
            }
            i++;
        }
        StringBuffer result=new StringBuffer(); //StringBuffer类的字符串可以在尾部追加值，而string类的不可以
        while(!statement.isEmpty())
        {
            result.append(statement.peek());
            statement.pop();
        }
        return result.toString(); //该题返回string类型，所以要toString转换一下*/
    }
}

