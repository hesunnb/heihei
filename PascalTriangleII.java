/*Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space? */

public class Solution {
    public List<Integer> getRow(int rowIndex) {
        
        List<Integer> result = new ArrayList<Integer>();
        if(rowIndex < 0) { //注意这里是小于号, 区别余第一问
            return result;
        }

        for(int i = 0; i <= rowIndex; i++) { //这里是小于等于, 区别于第一问
            result.add(0, 1); 
            for(int j = 1; j < result.size() - 1; j++) { 
                result.set(j, result.get(j) + result.get(j + 1)); 
            }
        }
        return result;
    }
}
