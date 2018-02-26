/*Given an integer array with even length, where different numbers in this array represent different kinds of candies. 
Each number means one candy of the corresponding kind. You need to distribute these candies equally in number to brother and sister. 
Return the maximum number of kinds of candies the sister could gain.
Example 1:
Input: candies = [1,1,2,2,3,3]
Output: 3
Explanation:
There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too. 
The sister has three different kinds of candies. 
Example 2:
Input: candies = [1,1,2,3]
Output: 2
Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1]. 
The sister has two different kinds of candies, the brother has only one kind of candies. 
Note:

The length of the given array is in range [2, 10,000], and will be even.
The number in given array is in range [-100,000, 100,000].*/


class Solution {

    //solution1:用HashSet
    public int distributeCandies(int[] candies) {
        
        if(candies == null || candies.length == 0) {
            return 0;
        }
        
        Set<Integer> kinds = new HashSet<>();
        for (int candy : candies) {
            kinds.add(candy);
        }
        if(kinds.size() >= candies.length / 2) { //思路差不多, 种类数大于candies的一半, 比如1,2,3,4,5,6,7,8 这个就是kinds和candies一样,
            //完全大于, -> 1,1,2,2,3,3,4,4 kinds是1,2,3,4 正好等于candies的一半, 这些情况就是每种糖只拿出一个就够了; 1,1,1,1,2,2,2,3,3,4 kinds
            //是1,2,3,4少于candies的一半, 因为每种都已经拿过了, 剩下的也就是徒增数目, 对种类没有影响
            return candies.length / 2;
        } else {
            return kinds.size();
        }
    }
	
	
    //solution2:(own)
    public int distributeCandies(int[] candies) {
        
        if(candies == null || candies.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < candies.length; i++) { //把每种糖果出现的次数统计出来
            map.put(candies[i], map.getOrDefault(candies[i], 0) + 1);
        }
        
        List<Integer> list = new ArrayList<>(map.values()); //把次数拿出来做成list, map.values返回Collection, 但Collection本身不支持直接
        //排序, 如果不排序的话本题直接用Collection也可以(但是leetcode没提供这个环境), 所以还是弄成list
        int len = candies.length / 2;
        int kind = 0;
        
    	for(int i = 0; i < list.size(); i++) { //因为只统计糖果种类, 所以做一个循环, 从每种里面拿出一个给kind, 如果还没循环完一遍kind
            //的长度就已经是candies的一半了那说明每种给一个就足够了; 如果全部循环完了kind比candies的一半还要小, 那剩下的无异于就是增加数目来达到
            //candies的一半, 对种类没有影响
            if(kind < len) {
                kind += 1;
            } else {
                break;
            }
        }

	return kind;
    }
}
