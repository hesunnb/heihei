/*Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of 
an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.*/

class Solution {
	public List<List<Integer>> findSubsequences(int[] nums) {

		List<List<Integer>> result = new ArrayList<>();
		if (nums == null) {
			return result;
		}

		findSubsequenceshelper(new ArrayList<Integer>(), 0, nums, result);
		return result;
	}

	private void findSubsequenceshelper(List<Integer> list, int index, int[] nums, List<List<Integer>> result) {

		if (list.size() > 1) { // 用list.size() > 1就不用一个变量count计数了
			result.add(new ArrayList<Integer>(list));
		}

		Set<Integer> used = new HashSet<>();
		for (int i = index; i < nums.length; i++) {
			if (used.contains(nums[i])) {
				continue;
			}
			if (list.size() == 0 || nums[i] >= list.get(list.size() - 1)) {
				used.add(nums[i]); // used记录的是list一个位置所加入过的值, 这样当list remove掉了这个位置的值的时候, 当再加入新值的时候
				// 如果used当中已经出现过了, 说明这个新值在这个位置上出现过, 已经重复了, 所以要跳过然后继续判断接下来的值
				list.add(nums[i]);
				findSubsequenceshelper(list, i + 1, nums, result);
				list.remove(list.size() - 1);
			}
		}
	}
} 
