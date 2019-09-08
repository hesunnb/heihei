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
 
    //1,1,2就可以看出递归过程
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
            
            if(!visit[i]) {
                visit[i] = true;
                list.add(nums[i]);
                permuteUniqueHelper(result, list, nums, visit);
                list.remove(list.size() - 1);
                visit[i] = false;
            }
        }
    }
    
    //CrackBook中的方法
	List<String> printPerms(String s) { 
		List<String> result = new ArrayList<String>();
		HashMap<Character, Integer> map = buildFreqTable(s);
		printPerms(map, "", s.length(), result);
		return result;
	}

	HashMap<Character, Integer> buildFreqTable(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : s.toCharArray()) {
			if (!map.containsKey(c)) {
				map.put(c, 0);
			}
			map.put(c, map.get(c) + 1);
		}
		return map;
	}

	void printPerms(HashMap<Character, Integer> map, String prefix, int remaining, List<String> result) {
		/* Base case. Permutation has been completed. */
		if (remaining == 0) {
			result.add(prefix);
			return;
		}

		/* Try remaining letters for next char, and generate remaining permutations. */
		for (Character c : map.keySet()) { //就跟用下标循环一样, 比如aabbc这个例子, c放回哈希表后, 是要找c的后一个
			//字母, 然而没有, 已经到结尾了, 就像要去找i++一样, 所以退出循环, 不会死循环
			int count = map.get(c);
			if (count > 0) {
				map.put(c, count - 1);
				printPerms(map, prefix + c, remaining - 1, result);
				map.put(c, count);
				System.out.println(map);
			}
		}
	}
}
