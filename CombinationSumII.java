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
        
        int prev = -1; //这个prev只对cnadidates中有重复元素的时候做判断
        for(int i = index; i < candidates.length; i++) {
            if(candidates[i] > target) { //在递归深入的时候, 能够起到停止一个元素重复添加的情况, 在回溯的时候, 比如2,2,6如果不符合情况
            //那么就break, 后面的情况就都不用再试了
                break;
            }
            
            if(prev != -1 && prev == candidates[i]) { //一个testCase是: [2,2,3] 7 当一个2找出2,2,3的组合后, 第二个2就不用再找了, 否则
                //就会找出一个重复的2,2,3; 因为每个元素都会先重复自己然后进行匹配, 数组中再重复的就多余了
                //当从第一个2出发递归回来的时候, 会把第一个2 remove掉, 然后prev就会变成2, 再循环的时候prev就和第二个2相等了, 然后
                //就把第二个2跳过了(continue)
                continue;
            }
            
            path.add(candidates[i]);
            helper(result, candidates, target - candidates[i], path, i + 1); //target在不断变小, 和第一问就这里变成了i + 1, 每次传
            //下一个数, 不传本身了
            path.remove(path.size() - 1); //把最后一个元素卸掉, 准备添加下一个元素
            
            prev = candidates[i];
        }
    }
}
