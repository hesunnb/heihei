/*Given a list of numbers with duplicate number in it. Find all unique permutations.
 Example

For numbers [1,2,2] the unique permutations are:

[
  [1,2,2],
  [2,1,2],
  [2,2,1]
]
*/

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        
        List<Integer> list = new ArrayList<Integer>();
        boolean[] visit = new boolean[nums.length];
        
        Arrays.sort(nums); //必须要排序
        permuteUniqueHelper(result, list, nums, visit);
        return result;
    }
    
    private void permuteUniqueHelper(List<List<Integer>> result, List<Integer> list, int[] nums, 
            boolean[] visit) {
        if(list.size() == nums.length) {
            result.add(new ArrayList(list));
            return;
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(i != 0 && nums[i] == nums[i - 1] && !visit[i - 1]) { //这里的条件意思是i不等于0, 并且i处的值和它前面一位的值相等, 
             //并且i处前一位没有访问过, 这个没有访问过用在回朔的时候, 比如1,1,2,2,2,3 当递归回退到1,1,2的时候, 擦除掉了2, 进而要换成下一个2进行
             //递归, 但是下一个2之前的2已经被标记为没有访问过, 所以后面重复的2都被跳过了
                continue;
            }
            
            if(!visit[i]) { //如果i位置的元素没有被访问过, 就向list里面填入值, 比如1,1,2,2,3,2这种情况, 最后一个2是从头开始遍历1,1,2,2都访问
            //过, 到第三个2没有访问过, 所以加入到list里面
                visit[i] = true;
                list.add(nums[i]);
                permuteUniqueHelper(result, list, nums, visit);
                list.remove(list.size() - 1);
                visit[i] = false;
            }
        }
    }
}
