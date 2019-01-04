/*Given a collection of candidate numbers (C) and a target number (T), 
find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]*/

public class Solution {
  
    //对于combination sum总结: 输入有重复就加个prev, 然后每个数可选多次就传i, 即自己本身的下标, 每个数可选一次就传i+1, 即自己的下一个数
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            helper(result, candidates, target - candidates[i], path, i + 1); //target在不断变小, 和第一问就这里变成了i + 1, 每次传
            //下一个数, 不传本身了
            path.remove(path.size() - 1); //把最后一个元素卸掉, 准备添加下一个元素
        }
    }
}
