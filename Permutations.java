/* Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:

[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/

public class Solution {
    
    //此题没有强调字典序, 所以直接按给的数组得到结果就可以了, 复杂度: O(n!) //应该大于O(n!), O(n * n!), 实际上还要更大
    //如要要按字典序给数组排下序就可以了, O(nlogn)对于O(n!)也不多
    public List<List<Integer>> permute(int[] nums) {
         
         List<List<Integer>> result = new ArrayList<List<Integer>>();
         if(nums == null || nums.length == 0) {
             return result;
         }
         
         List<Integer> list = new ArrayList<Integer>();
         permuteHelper(result, list, nums);
         return result;
    }
    
    private void permuteHelper(List<List<Integer>> result, List<Integer> list, int[] nums) {
        if(list.size() == nums.length) { //得到了每步最后的结果
            result.add(new ArrayList(list));
            return;
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(list.contains(nums[i])) { //包含就跳过包含的元素, 这个contains的方法要学习
                continue;
            }
            list.add(nums[i]);
            permuteHelper(result, list, nums);
            list.remove(list.size() - 1); //移除每步的末尾元素
        }
    }
}
