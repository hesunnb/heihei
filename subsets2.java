/*Given a list of numbers that may has duplicate numbers, return all possible subsets
Notice

    Each element in a subset must be in non-descending order.
    The ordering between two subsets is free.
    The solution set must not contain duplicate subsets.
    
Example

If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

class Solution {
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return result;
        }
        
        Arrays.sort(nums);
        helper(result, new ArrayList<>(), nums, 0);
        return result;
    }
    
    public void helper(List<List<Integer>> result, List<Integer> list, int[] nums, int pos) {
        
        result.add(new ArrayList<>(list));
        for(int i = pos; i < nums.length; i++) {
            if(i != pos && nums[i] == nums[i - 1]) { //去个重即可, 注意是continue
                continue;
            }
	        list.add(nums[i]);
            helper(result, list, nums, i + 1);
	        list.remove(list.size() - 1);
        }
    }
    
    
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
        public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> S) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(S == null || S.size() == 0)
        {
            return result;
        }
        
        int[] nums = new int[S.size()];
        for(int i = 0; i < nums.length; i++) //给的arrayList,转化成数组进行排序，把数组传到下面去
        {
            nums[i] = S.get(i);
        }
        Arrays.sort(nums);
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        subsetsWithDupHelper(result, list, nums, 0);
        
        return result;
    }
    
    private void subsetsWithDupHelper(ArrayList<ArrayList<Integer>> result, 
            ArrayList<Integer> list, int[] nums, int pos)
    {
        result.add(new ArrayList<Integer>(list));
        
        for(int i = pos; i < nums.length; i++)
        {
            if(i != pos && nums[i] == nums[i-1]) //比一问就多了这两行，就是递归回朔的时候如果后面的元素与前面的元素
            //相等，就不要后面的元素了，退回到循环处，而要第一个重复的元素【2,2】，要第一个2
            
            /*具体解释: i != pos所起到作用的地方是第一遍依次加入数的时候, [1,2]与[1,2,2],
            最后一个2是i = pos, 所以可以正常加入; 以[1,2,2,3]为例, 返回remove的时候, remove到[1,2]这个时候加入3
            [1,2,3]加入result, 这是针对两个2中第一个2, 当继续remove就会回到[1], 然后它要加入第二个2, 此时i != pos &&
            nums[i] == nums[i-1], 所以不加入第二个2, 因为加入了第二个2, 进行的也是和第一个2重复的工作, 舍掉了*/
            
            {
                continue;
            }
            list.add(nums[i]);
            subsetsWithDupHelper(result, list, nums, i+1);
            list.remove(list.size()-1);
        }
    }
}
