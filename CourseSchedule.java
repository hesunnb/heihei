/*There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.*/

class Solution {
    //dfs方法
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null) {
            return false;
        }

	    Map<Integer, List<Integer>> map = new HashMap<>();
	    boolean[] visited = new boolean[numCourses];

        for(int[] course : prerequisites) {
            if(!map.containsKey(course[1])) {
                map.put(course[1], new ArrayList<Integer>());
            }
	        map.get(course[1]).add(course[0]);
        }
        
        for(int i = 0; i < numCourses; i++) {
            if(!dfs(map, i, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> map, int index, boolean[] visited) {
        if(visited[index]) {
            return false;
        }
        visited[index] = true;
        if(map.containsKey(index)) {
        	for(int i : map.get(index)) {
                if(!dfs(map, i, visited)) {
                    return false;
                }
            }
        }
        visited[index] = false;
        return true;
    }
}
