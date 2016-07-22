public class Solution {
    /**
     * @param str: a string
     * @return: a boolean
     */
     
    //使用额外空间哈希表，时间复杂度O(n)
    public boolean isUnique(String str) {
        // write your code here
        
        Map mp=new HashMap();
        for(int i=0;i<str.length();i++) //字符串的length()都加(),数组的length都没有括号
        {
            if(mp.containsKey(str.charAt(i))) {
                return false;
            }
            else {
                mp.put(str.charAt(i),str.charAt(i));
            }
        }
        return true;
    }
    
    
    
    //使用额外空间数组，时间复杂度O(n)
    public boolean isUnique(String str) {
        // write your code here
        
        int[] result = new int[256]; //int数组
        for(int i = 0; i < str.length(); i++) {
            
            if(++result[str.charAt(i)] > 1) {
                return false;
            }
            
        }
        return true;
    }
    
    public boolean isUnique(String str) {
        // write your code here
        
        boolean[] char_set = new boolean[256]; //boolean数组
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i); //char自动转换为int
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }
    
    
    
    //未用额外空间，时间复杂度O(n^2)
    public boolean isUnique(String str) {
        // write your code here
        
        for(int i=0;i<str.length();i++)
        {
            for(int j=i+1;j<str.length();j++)
            {
                if(str.charAt(i)==str.charAt(j))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
