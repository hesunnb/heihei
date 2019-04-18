/*You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

0 represents the obstacle can't be reached.
1 represents the ground can be walked through.
The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
 

You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. 
And after cutting, the original place has the tree will become a grass (value 1).

You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't 
cut off all the trees, output -1 in that situation.

You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

Example 1:

Input: 
[
 [1,2,3],
 [0,0,4],
 [7,6,5]
]
Output: 6
 

Example 2:

Input: 
[
 [1,2,3],
 [0,0,0],
 [7,6,5]
]
Output: -1
 

Example 3:

Input: 
[
 [2,3,4],
 [0,0,5],
 [8,7,6]
]
Output: 6
Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
 

Hint: size of the given matrix will not exceed 50x50.*/

class Solution {
    
    //思路:
    //1. Since we have to cut trees in order of their height, we first put trees (int[] {row, col, height}) into a priority queue and 
    //sort by height.
    //2. Poll each tree from the queue and use BFS to find out steps needed.
    public int cutOffTree(List<List<Integer>> forest) {
        if(forest == null || forest.size() == 0 || forest.get(0).size() == 0) {
            return -1;
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> (x[2] - y[2])); //按照树高排序
        for(int i = 0; i < forest.size(); i++) {
            for(int j = 0; j < forest.get(0).size(); j++) {
                if(forest.get(i).get(j) > 1) {
                    pq.offer(new int[] {i, j, forest.get(i).get(j)});
                }
            }
        }
        
        int[] start = new int[2]; //起点是[0,0]
        int result = 0;
        while(!pq.isEmpty()) {
            int[] tree = pq.poll();
            int step = getMinStep(forest, start, tree); //从start到tree的最短路径
            if(step < 0) {
                return -1;
            }
            result += step;
            start[0] = tree[0]; //更新start
            start[1] = tree[1];
        }
        return result;
    }
    
    int[][] dir = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
    public int getMinStep(List<List<Integer>> forest, int[] start, int[] tree) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        int step = 0;
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
        visited[start[0]][start[1]] = true; //这个重要, 别忘了
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if(cur[0] == tree[0] && cur[1] == tree[1]) {
                    return step;
                }
                for(int[] d : dir) {
                    int nextX = cur[0] + d[0];
                    int nextY = cur[1] + d[1];
                    if(nextX < 0 || nextX >= forest.size() || nextY < 0 || nextY >= forest.get(0).size() || 
                       forest.get(nextX).get(nextY) == 0 || visited[nextX][nextY] == true) {
                        continue;
                    }
                    queue.offer(new int[] {nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
            step++;
        }
        return -1;
    }
    
    //bfs的visited为啥放入队列的时候visited就要马上标记为真:
    //因为bfs就是层扫, 一个点的四周就是下一层, 那么同一层的点互相之间是不需要访问的, 而且下层的点也没必要访问上层的点, 因为上层的点已经通过
    //上上层可以访问了, 无权图(走一步距离都是1)的最短路径就是普通bfs+visited
}
