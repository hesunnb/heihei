/*Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from 
beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.*/

class Solution {
    
    //相似题目: Minimum Genetic Mutation, word ladder2
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		
        if(beginWord == null || endWord == null || beginWord.equals(endWord) || !wordList.contains(endWord)) {
            return 0; //题目要求start与end不能相等 
        }
        
        int level = 0;
        Set<String> dict = new HashSet<>(wordList); //为什么要把List倒入到Set, 因为ArrayList的contains方法是O(n), 扫描一遍来判断存不存在
        //而HashSet底层是HashMap, contains方法是O(1)的; 用wordList.contains会超时
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord); //wordList中已经访问过的元素都放到visited中
	//dict.add(beginWord); //相对于WordLadder2, 这里的意思就是说, WordLadder1加了这句话也没事儿, 但实际上用不到这句话在WordLadder1里
        
        while(!queue.isEmpty()) {
            int size = queue.size(); //size记录每层的大小, 变化一个字母wordList中有几种符合的; 变化两个字母wordList中有几种符合的...
            while(size > 0) {
                String curr = queue.poll();
                if(curr.equals(endWord)) return level+1; //某一种符合了就返回当前层数, 层数就是变化了几个字母; Minimum Genetic Mutation
                //要求返回变化次数, 所以直接返回level, 这道题要返回总的transformation长度, 所以要+1
                char[] currArray = curr.toCharArray();
                for(int i = 0; i < currArray.length; i++) {
                    char old = currArray[i]; //保留该位置原有字符
                    for(char c = 'a'; c <= 'z'; c++) { //用26种字符替换看有没有匹配的
			if(old == c) {
                            continue;
                        }
                        currArray[i] = c;
                        String next = new String(currArray);
                        if(!visited.contains(next) && dict.contains(next)) { //!visited.contains(next)访问过的就不要重复加到队列中了,
                            //在Minimum Genetic Mutation中解释了这句话的必要性
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                    currArray[i] = old;
                }
                size--;
            }
            level++; //一层一层的加, 变化一个字母为一层, 变化两个字母为第二层...
        }
        return 0;
    }
}
