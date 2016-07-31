/*Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

public class Solution {
     
     //边算temp边加入
     public List<List<Integer>> generate(int numRows) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(numRows <= 0) {
            return result;
        }

        List<Integer> temp = new ArrayList<Integer>();;
        for(int i = 0; i < numRows; i++) {
            temp.add(0, 1); //在头部+1, 这样每次就能从头部开始往后算了, 不用倒着往回算了(在尾部+1的情况)
            for(int j = 1; j < temp.size() - 1; j++) { //只有temp.size()为3的时候才能进入这个循环, 所以头两个直接加到temp然后放到result中了
                temp.set(j, temp.get(j) + temp.get(j + 1)); //除了头尾之外的值
            }
            
            result.add(new ArrayList(temp)); //此处很重要, 集合可以直接new以别的集合做参数, 要是直接加入temp就错了
        }
        return result;
    }
    
    
     //也是边算temp边加入
     public List<List<Integer>> generate(int numRows) {
          
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numRows <= 0) {
            return res;
        }
        
        for (int i = 1; i <= numRows; i++) {
            List<Integer> temp = new ArrayList<Integer>(); //区别是temp写的到了循环里面
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) { //可以得到收尾的1
                    temp.add(j, 1);
                } else {
                    temp.add(j, res.get(i - 2).get(j - 1) + res.get(i - 2).get(j)); //从result中取出前一个算出的加入的结果,
                    //然后算新的temp, 这点非常好, 否则就得从头算, 就不好了
                }
            }
            res.add(temp);
        }
        return res;
     }
}
