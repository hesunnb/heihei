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
    
    //思路也是拓扑排序
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses]; //开一个二维数组, 用来存每对儿关系的
        int[] indegree = new int[numCourses]; //用来存每个节点的入度的

        for (int i=0; i<prerequisites.length; i++) { //扫一遍这个二维数组
            int ready = prerequisites[i][0]; //准备上的课
            int pre = prerequisites[i][1]; //所需的前提
            if (matrix[pre][ready] == 0) { //等于0这个条件还是要加的, testcase里面会有[1,9]给两遍的情况, 这时候就要忽略
                indegree[ready]++; //增加入度个数
            }
            matrix[pre][ready] = 1;
        }
    
        int count = 0;
        Queue<Integer> queue = new LinkedList();
        List<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<indegree.length; i++) { //扫描入度为0的点
            if (indegree[i] == 0) {
                queue.offer(i); //加入队列
                list.add(i); //加入结果list
            }
        }
        
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++; //计算上过课的个数
            for (int i=0; i<numCourses; i++) {
                if (matrix[course][i] != 0) { //不等于0说明有映射关系, 有入度
                    indegree[i]--; //入度减1
                    if (indegree[i] == 0) { //入度为0, 加入队列和结果集
                        queue.offer(i);
                        list.add(i);
                    }
                }
            }
        }
        
        if(count != numCourses) { //如果上的课与所给课程不等, 说明图有(count会小于numCourses), 返回空数组
            return new int[0];
        }
        
        int[] result = new int[list.size()]; //把结果拷贝到数组中
        for(int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
