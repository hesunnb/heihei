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
        if(prerequisites == null) { //numCourses < prerequisites.length并不能说明问题, prerequisites只是描述课程间的关系, 与
            //numCourses的大小无关
            return false;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        boolean[] visited = new boolean[numCourses]; //一定要用visited, 减少递归深度

        for(int[] course : prerequisites) {
            if(!map.containsKey(course[1])) {
                map.put(course[1], new ArrayList<Integer>()); //前提条件的课作为key, 后续的课程作为值
            }
            map.get(course[1]).add(course[0]);
        }
        
        for(int i = 0; i < numCourses; i++) { //numCourses就在这里有用, 从每一节出发判断有没有环
            if(!dfs(map, i, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> map, int index, boolean[] visited) {
        if(visited[index]) { //访问过了就有环
            return false;
        }
        visited[index] = true;
        if(map.containsKey(index)) { //有的课没有先决条件
            for(int i : map.get(index)) {
                if(!dfs(map, i, visited)) {
                    return false;
                }
            }
        }
        visited[index] = false; //在这里赋值为false就足够了, 不用在上面的for循环里面再赋值false, 因为最后一门课可能没有先决条件, 
        //最后一门课的上一门的list里面也就只有这最后一门课, 所以在这里赋值false就足够了
        //举例: 4 [3,1],[1,0],[1,2],[2,0]
        return true;
    }
}
