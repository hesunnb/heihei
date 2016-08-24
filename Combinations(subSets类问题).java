/*Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]*/

public class Solution {

    //这个就是找规定长度的子集, 方法是subSets那道题的
    public List<List<Integer>> combine(int n, int k) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>(); //装ArrayList的ArrayList
        if(n <= 0 || k <= 0) {
            return result;
        }
        
        int nums[] = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        List<Integer> list = new ArrayList<Integer>(); //这个list是一直跟着变化的
        
        combineHelper(result, list, nums, 0, k); //调用找出子集函数
        
        return result;
    }
    
    private void combineHelper(List<List<Integer>> result, List<Integer> list, int[] nums, int pos, int k)
    {
        if(list.size() == k) {
            result.add(new ArrayList<Integer>(list)); //每次都要加, 第一次加入的是空集
        }
        
        for (int i = pos; i < nums.length; i++) 
        {
            list.add(nums[i]); //按照顺序先加入1
            combineHelper(result, list, nums, i + 1, k); //把所有开头为1的找出子集
            list.remove(list.size()-1);//把1删掉换成下一个数
            //可以这么理解，具体的运行过程得自己画图
        }
    }
}
