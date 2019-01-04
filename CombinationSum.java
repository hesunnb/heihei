/*Given a set of candidate numbers (C) and a target number (T), 
find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]*/

public class Solution {
  
    //testCase里面有输入[1,1,1], target=2这种情况, 也就是给的数组允许有重复, 有重复就用prev, 如果所给数组没有重复数字, 那么有关prev的就都可以去掉
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(candidates == null || candidates.length == 0) {
            return result;
        }
        
        Arrays.sort(candidates); //要保证数组有序
        helper(result, candidates, target, new ArrayList<Integer>(), 0);
        return result;
    }
    
    private void helper(List<List<Integer>> result, int[] candidates, int target, List<Integer> path, int index) {
        if(target == 0) { //找到了有效的组合
            result.add(new ArrayList<Integer>(path)); //加入到result中
            return;
        }
         
        for(int i = index; i < candidates.length; i++) {
            if (i != index && candidates[i] == candidates[i - 1]) {
                continue;
            }
          
            if(candidates[i] > target) { //在递归深入的时候, 能够起到停止一个元素重复添加的情况, 在回溯的时候, 比如2,2,6如果不符合情况
            //那么就break, 后面的情况就都不用再试了
                break;
            }
            
            path.add(candidates[i]);
            helper(result, candidates, target - candidates[i], path, i); //target在不断变小
            path.remove(path.size() - 1); //把最后一个元素卸掉, 准备添加下一个元素
        }
    }
}
