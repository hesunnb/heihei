/*Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie. 
Each child i has a greed factor gi, which is the minimum size of a cookie that the child will be content with; and each cookie j has a 
size sj. If sj >= gi, we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of 
your content children and output the maximum number.

Note:
You may assume the greed factor is always positive. 
You cannot assign more than one cookie to one child.

Example 1:
Input: [1,2,3], [1,1]

Output: 1

Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.
Example 2:
Input: [1,2], [1,2,3]

Output: 2

Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
You have 3 cookies and their sizes are big enough to gratify all of the children, 
You need to output 2.*/


class Solution {
    
    //solution1:greedy
    public int findContentChildren(int[] g, int[] s) {
        if(g == null || s == null || g.length == 0 || s.length == 0) {
            return 0;
        }
        
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        for(int j = 0; i < g.length && j < s.length; j++) { //正好j往后走刚刚满足i处的元素
            if(g[i] <= s[j]) { //i被满足
                i++;
            }
        }
        return i;
    }
    
    
    //solution2:(own)把s数组装到list中, 然后二分查找list满足g, 每次从list里面移除找到的结果
    public int findContentChildren(int[] g, int[] s) {
        if(g == null || s == null || g.length == 0 || s.length == 0) {
            return 0;
        }
        
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        
        for(int i = 0; i < s.length; i++) {
            list.add(s[i]);
        }
        Collections.sort(list);
        for(int i = 0; i < g.length; i++) {
            int index = searchValue(list, g[i]);
            if(index >= 0) {
                sum += 1;
            } else {
                continue;
            }
            list.remove(index);
            if(list.size() == 0) { //这个要加, list里面没东西也就结束了
                break;
            }
        }
        return sum;
    }
    
    private int searchValue(List<Integer> list, int target) {
        
        int start = 0, end = list.size() - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(list.get(mid) == target) {
                return mid;
            } else if (list.get(mid) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if(list.get(start) >= target) {
            return start;
        }
        else if(list.get(end) >= target) { 
            return end;
        }
        else { 
            return -1;
        }
    }
}
