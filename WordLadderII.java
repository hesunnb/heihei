/*Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to 
endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.*/

class Solution {
    /*Explanation

    The basic idea is:

    1). Use BFS to find the shortest distance between start and end, tracing the distance of crossing nodes from start node to end node, 
    and store node's next level neighbors to HashMap;

    2). Use DFS to output paths with the same distance as the shortest distance from distance HashMap: compare if the distance of the 
    next level node equals the distance of the current node + 1.*/
    public List<List<String>> findLadders(String beginWord, String endWordWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<String>(wordList); //判断集合
	List<List<String>> res = new ArrayList<List<String>>(); //结果集合
	HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<String, ArrayList<String>>();// Neighbors for
																								// every node
	HashMap<String, Integer> distance = new HashMap<String, Integer>();// Distance of every node from the beginWord node, 相当于
//wordLadderⅠ里面的visited, 只不过这回不用set, 用map, 同时把level作为distance加入到了哈希表中
	ArrayList<String> solution = new ArrayList<String>(); //dfs中每一条成功路径都存入到solution中作为一个结果

	dict.add(beginWord); //dict加入beginWord的原因是要向nodeNeighbors中加入每个节点时不要落下beginWord, 否则就得不到beginWord的
	//neighbors了
	bfs(beginWord, endWordWord, dict, nodeNeighbors, distance);
	dfs(beginWord, endWordWord, dict, nodeNeighbors, distance, solution, res);
	return res;
    }

    // BFS: Trace every node's distance from the beginWord node (level by level).
    private void bfs(String beginWord, String endWord, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, 
	    HashMap<String, Integer> distance) {
	for (String str : dict) {
	    nodeNeighbors.put(str, new ArrayList<String>());
	}

	Queue<String> queue = new LinkedList<String>(); //与wordLadderⅠ中的queue功能相同
	queue.offer(beginWord);
	distance.put(beginWord, 0);

        while (!queue.isEmpty()) {
	    int size = queue.size();
	    while (size > 0) {
		String cur = queue.poll();
		if(cur.equals(endWord)) {
		    break;
	    	}

		int curDistance = distance.get(cur);
		char[] curArray = cur.toCharArray();
		for(int i = 0; i < curArray.length; i++) {
		    for(char c = 'a'; c <= 'z'; c++) { //26个字符替换匹配
			if(curArray[i] == c) continue; //用来排除和cur相同的字符串, 就是排除本身
			char old = curArray[i]; //保留该位置原有字符, 一定要在这个for循环里面进行old的替换与还原, 写在for外面就错了
			curArray[i] = c;
			String next = new String(curArray);
			if(dict.contains(next)) {
			    nodeNeighbors.get(cur).add(next); //这样操作的话, nodeNeighbors包含了只要与cur差一个字母变化的都会加入到cur的
			    //neighbors中, 包含上一级, 同级, 下一级(级数就是按照distance算的, 即distance差1步); 如果要是写成
			    //if(!distance.containsKey(next) && dict.contains(next)) 会有问题, 因为这回要的是路径, 比如cog的上一级有
			    //dog和log, 那么queue弹出dog的时候会把cog加入到distance中, 那么queue弹出log的时候, 因为cog已然在distance中,
			    //所以nodeNeighbors.get(cur).add(next)这句话就得不到执行, 那么log的neighbors就会加入不到哈希表中, 从而走log
			    //节点的这条路径就获取不到了, 见图
			}
			curArray[i] = old;
		    }
		}

		for(String neighbor : nodeNeighbors.get(cur)) { //所以把add neighbors与distance.put和queue.offer分开, 这样queue就加入不到
		    //重复的节点了, 然后每个节点的neighbor还都能获取到
		    if(!distance.containsKey(neighbor)) {
		    distance.put(neighbor, curDistance + 1);
		    queue.offer(neighbor);
		    /*System.out.println(cur + " " + neighbor);
		    System.out.println(nodeNeighbors);
		    System.out.println(distance);*/ //用于测试
		    }
		}
		size--;
	    }
        }
    }

	// DFS: output all paths with the shortest distance.
	private void dfs(String cur, String endWord, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
		solution.add(cur);
		if (endWord.equals(cur)) {
			res.add(new ArrayList<String>(solution));
		} else {
			for (String next : nodeNeighbors.get(cur)) {
				if (distance.get(next) == distance.get(cur) + 1) { //正常的dfs, 在neighbors中只找下一级的节点(因为neighbors中有同级的, 也有
                    //上一级的)
					dfs(next, endWord, dict, nodeNeighbors, distance, solution, res);
				}
			}
		}
		solution.remove(solution.size() - 1);
	}
}
