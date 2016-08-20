/*Find all possible combinations of k numbers that add up to a number n, 
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]


Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]*/

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        int[] candidates = {1,2,3,4,5,6,7,8,9};
        helper(result, candidates, new ArrayList<Integer>(), 0, k, n);
        return result;
    }
    
    private void helper(List<List<Integer>> result, int[] candidates, List<Integer> path, int index, int k, int n) {
        if(n == 0 && path.size() == k) { //找到了有效的组合
            result.add(new ArrayList<Integer>(path)); //加入到result中
            return;
        }
        
        //这里面没有重复元素和自己重复, 所以就不用prev了
        for(int i = index; i < candidates.length; i++) {
            if(candidates[i] > n) { //在递归深入的时候, 能够起到停止一个元素重复添加的情况, 在回溯的时候, 比如2,2,6如果不符合情况
            //那么就break, 后面的情况就都不用再试了
                break;
            }
            
            path.add(candidates[i]);
            helper(result, candidates, path, i + 1, k, n - candidates[i]); //n在不断变小, 和第一问就这里变成了i + 1, 每次传
            //下一个数, 不传本身了
            path.remove(path.size() - 1); //把最后一个元素卸掉, 准备添加下一个元素
        }
    }
}
