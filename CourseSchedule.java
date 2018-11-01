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
  
    //solution1: bfs方法, 推荐使用, O(m)m为边的个数复杂度, 检测图有没有环最有效的方法
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null) { //numCourses < prerequisites.length并不能说明问题, prerequisites只是描述课程间的关系, 与
            //numCourses的大小无关
            return false;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList();
        int count = 0;
        
        for(int[] coursePair : prerequisites) {
            if(!map.containsKey(coursePair[1])) {
                map.put(coursePair[1], new ArrayList<Integer>()); //前提条件的课作为key, 后续的课程作为值; 这里的哈希表是用来存图的
                //区别于topologicalSorting.java里面的map, 因为topologicalSorting.java中每一个节点已经给了neighbours, 所以哈希表就用来存
                //入度了; 这里用哈希表存图, 邻接点的关系, 用一个数组存入度
            }
            map.get(coursePair[1]).add(coursePair[0]);
            inDegree[coursePair[0]]++; //加入入度
        }
        
        for(int i = 0; i < inDegree.length; i++) {
            if(inDegree[i] == 0) { 
                queue.add(i); //加入入度为0的点
            }
        }
        
        while(queue.size() != 0) {
            int course = queue.poll();
            count++; //弹出课, 加数量
            if(map.containsKey(course)) {
                for(int i : map.get(course)) { //减去邻接点的入度数
                    inDegree[i]--;
                    if(inDegree[i] == 0) { //入度为0加入队列
                        queue.add(i);
                    }
                }
            }
        }
        
        if(count != numCourses) { //不想等就是假
            return false;
        }
        return true;
    }  

  
    //solution2: dfs方法, 会有重复点查询, 开栈也费时间
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
        
        for(int i = 0; i < numCourses; i++) { //numCourses就在这里有用, 从每一节课出发判断有没有环; 递归的过程中是不需要
            //numCourses参与的, 就是说判断有没有环的过程不需要numCourses这个变量
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
