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
        
        int[] candidates = {1,2,3,4,5,6,7,8,9}; //和前几问不一样就是自己写一个数组用来求和
        helper(result, candidates, new ArrayList<Integer>(), 0, k, n);
        return result;
    }
    
    private void helper(List<List<Integer>> result, int[] candidates, List<Integer> path, int index, int k, int n) {
        if(n == 0 && path.size() == k) { //找到了有效的组合, 满足n的同时还要满足k大小的要求
            result.add(new ArrayList<Integer>(path)); //加入到result中
            return;
        }
        
        int prev = -1; //这个prev只对cnadidates中有重复元素的时候做判断
        for(int i = index; i < candidates.length; i++) {
            if(candidates[i] > n) { //在递归深入的时候, 能够起到停止一个元素重复添加的情况, 在回溯的时候, 比如2,2,6如果不符合情况
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
            helper(result, candidates, path, i + 1, k, n - candidates[i]); //n在不断变小, 和第一问就这里变成了i + 1, 每次传
            //下一个数, 不传本身了
            path.remove(path.size() - 1); //把最后一个元素卸掉, 准备添加下一个元素
            
            prev = candidates[i];
        }
    }
}
