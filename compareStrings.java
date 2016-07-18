/*Compare two strings A and B, determine whether A contains all of the characters in B.

The characters in string A and B are all Upper Case letters.

Example
For A = "ABCD", B = "ACD", return true.

For A = "ABCD", B = "AABC", return false.

*/

public class Solution {
    /**
     * @param A : A string includes Upper Case letters
     * @param B : A string includes Upper Case letter
     * @return :  if string A contains all of the characters in B return true else return false
     */
    public boolean compareStrings(String A, String B) {
        // write your code here
        //哈希表来解决
        Map<Character,Integer> map=new HashMap<Character,Integer>(); //一定要记得加泛型，key是char，后面的值是int
        for(int i=0;i<A.length();i++)
        {
            char a=A.charAt(i);
            if(map.containsKey(a))
            {
                int value=map.get(a);
                map.put(a,++value);
            }
            else
            {
                map.put(a,1);
            }
        }
        
        for(int i=0;i<B.length();i++)
        {
            char b=B.charAt(i);
            if(map.containsKey(b))
            {
                int value=map.get(b);
                value--;
                if(value<0) //用value来判断重复的字母数，小于0就肯定是B的多余A就不包含啦
                {
                    return false;
                }
                map.put(b,value);
            }
            else
            {
                return false;
            }
        }
        return true;
    }
}
