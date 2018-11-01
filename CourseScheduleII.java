/*There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty 
array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be 
taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].


Hints:
This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological ordering exists and 
therefore it will be impossible to take all courses.

Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.

Topological sort could also be done via BFS.*/

public class Solution {
    
    //bfs解法:
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(prerequisites == null) { //numCourses < prerequisites.length并不能说明问题, prerequisites只是描述课程间的关系, 与
            //numCourses的大小无关
            return new int[]{};
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList();
        List<Integer> list = new ArrayList<>();
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
            count++;
            list.add(course);
            if(map.containsKey(course)) {
                for(int i : map.get(course)) { //减去邻接点的入度数
                    inDegree[i]--;
                    if(inDegree[i] == 0) { //入度为0加入队列
                        queue.add(i);
                    }
                }
            }
        }
        
        if(count != numCourses) {
            return new int[]{};
        }
        
        int[] result = new int[list.size()]; //把结果拷贝到数组中
        for(int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
