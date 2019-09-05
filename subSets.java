/*
Given a set of distinct integers, return all possible subsets.

Notice

    Elements in a subset must be in non-descending order.
    The solution set must not contain duplicate subsets.
    
Example

If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

class Solution {
    /**
     * @param S: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
     
    //时间复杂度: O(n * 2^(n - 1)), 每个数会出现2^(n - 1)次, 总共有n个数
    public List<List<Integer>> subsets(int[] nums) {
        // write your code here
        //递归方法：递归树
        List<List<Integer>> result = new ArrayList<List<Integer>>(); //装ArrayList的ArrayList
        if(nums == null || nums.length == 0) {
            return result;
        }
        List<Integer> list = new ArrayList<Integer>(); //这个list是一直跟着变化的
        Arrays.sort(nums); //排个序, 当然这道题不排序也可以, 只有要求字典序输出的时候排个序就行
        
        subsetsHelper(result, list, nums, 0); //调用找出子集函数
        
        return result;
    }
    
    private void subsetsHelper(List<List<Integer>> result, List<Integer> list, int[] nums, int pos) {
        result.add(new ArrayList<Integer>(list)); //每次都要加, 第一次加入的是空集
        
        for (int i = pos; i < nums.length; i++)  {
            list.add(nums[i]); //按照顺序先加入1
            subsetsHelper(result, list, nums, i+1); //把所有开头为1的找出子集
            list.remove(list.size()-1);//把1删掉换成下一个数
            //可以这么理解，具体的运行过程得自己画图
        }
    }
    
    //源自于Crackbook的方法, 比如1和2的子集是[],[1],[2],[1,2]; 此时加入一个3, 只需要在前面这些子集中分别加入3, 然后再与[],[1],[2],[1,2]
    //这4个子集合并, 便会得到新的子集, 从而得到上述的复杂度; 就是这个算法的实现
    public List<List<Integer>> subsets(int[] nums) {
        // write your code here

        if(nums == null || nums.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        
        return subsetsHelper(nums, 0);
    }

    List<List<Integer>> subsetsHelper(int[] nums, int index) {
	List<List<Integer>> allsubsets;
	if (nums.length == index) {// Base case - add empty set
	    allsubsets = new ArrayList<List<Integer>>();
	    allsubsets.add(new ArrayList<Integer>()); // Empty set
	} else {
	    allsubsets = subsetsHelper(nums, index + 1);
	    int item = nums[index]; //每次要新加入的元素
	    List<List<Integer>> moresubsets = new ArrayList<List<Integer>>();
	    for (List<Integer> subset : allsubsets) { //遍历allsubsets
		List<Integer> newsubset = new ArrayList<Integer>();
		newsubset.addAll(subset); //先加入subset的元素
		newsubset.add(item); //再加入新的元素
		moresubsets.add(newsubset); //把本轮产生的新结果保留
	    }
	    allsubsets.addAll(moresubsets); //加入本轮产生的所有新结果
	}
	return allsubsets;
    }
    
    //非递归的方法,共0~7个数
    //0 ->000 -> []
    //1 ->001 -> [3]
    //2 ->100 -> [1]
    //3 ->010 -> [2]
    //4 ->111 -> [1,2,3]
    //复杂度是o(n*2^n),比如n=3，对三个数进行排列，总共有2^n个子集，每个子集都要扩展成n位数(000这样的)
    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        if(nums == null || nums.length == 0) {
            return results;
        }

        int n = nums.length;
        Arrays.sort(nums);
        
        for(int i = 0; i < (1 << n); i++) { //2^n个子集，循环2^n次，把每个数都比较一次, 1 << n是个常数
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int j = 0; j < n; j++) { //把每位数扩展成n位
                if((i & (1 << j)) != 0) { //让i与每种情况都按位与
                    list.add(nums[j]); //把相应的每位的组成加入
                }
            }
            results.add(list); //第一次匹配是什么都没有，新建的list自然为空，然后就把空集的情况加进去了，以后都是加入相应的子集
        }
        return results;
    }
}
