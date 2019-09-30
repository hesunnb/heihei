/*You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child 
nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
*/

class Solution {

    //受subtree的启发, 从每一个节点出发向下走判断有没有和等于sum的某一段路径(注意不用到叶子节点)
    /*Consider that node at depth d will be "touched" (via pathSumHelper) by d nodes above it.
    In a balanced binary tree, d will be no more than approximately log N. Therefore, we know that with N
    nodes in the tree, pathSumHelper will be called O(N log N) times. The runtime is O(N log N).*/
    public int pathSum(TreeNode root, int sum) {
        
        if(root == null) {
            return 0;
        }
        int count = pathSumHelper(root, sum, 0, 0); //根左右的方式, 每一个节点计算自己的count
        count += pathSum(root.left, sum); //然后加入左右子树的count
        count += pathSum(root.right, sum);
        return count;
    }
    
    public int pathSumHelper(TreeNode root, int sum, int tempSum, int count) {
        
        if(tempSum + root.val == sum) { //这里就把之前pathSum, pathSum2的条件给取消了, 因为不需要是叶子节点
            count++;
        }
        
        if(root.left != null) {
            count = pathSumHelper(root.left, sum, tempSum + root.val, count); //这里的count不用+=, 因为pathSumHelper里面传入的
            //count就已经是++过的了, 如果count在后面被++过, 这里就直接覆盖掉, 如果在后面没有被++过, 这里返回的还是最开始传入的count
        }
        
        if(root.right != null) {
            count = pathSumHelper(root.right, sum, tempSum + root.val, count);
        }
        return count;
    }
    
    
    //CrackBook使用一个哈希表空间的更优解法:
    /*The runtime for this algorithm is O(N), where N is the number of nodes in the tree. We know it is O(N)
    because we travel to each node just once, doing 0(1) work each time. In a balanced tree, the space
    complexity is O ( log N) due to the hash table. The space complexity can grow to 0( n) in an unbalanced
    tree.*/
    public int pathSum(TreeNode root, int sum) {
        if(root == null) {
            return 0;
        }
        
        return pathSumHelper(root, sum, 0, new HashMap<Integer, Integer>());
    }
    
    public int pathSumHelper(TreeNode root, int targetSum, int curSum, Map<Integer, Integer> map) {
        
        if(root == null) {
            return 0; // Base case
        }
          
        /* Count paths with sum ending at the current node. */
        curSum += root.val;
        int tmpSum = curSum - targetSum; //思路就是把每一步的sum都存到哈希表里, 然后看当前sum与以前历史的sum做差有没有targetSum
        //有的话就说明找到了一个路径
        int totalPath = map.getOrDefault(tmpSum, 0);
        
        /* If curSum equals targetSum, then one additional path starts at root.
        Add in this path.*/
        if(curSum == targetSum) {
            totalPath++;
        }
        
        /* Increment pathCount, recurse, then decrement pathCount. */
        incrementHashTable(map, curSum, 1);
        totalPath += pathSumHelper(root.left, targetSum, curSum, map);
        totalPath += pathSumHelper(root.right, targetSum, curSum, map);
        incrementHashTable(map, curSum, -1);
        
        return totalPath;
    }
    
    public void incrementHashTable(Map<Integer, Integer> map, int key, int delta) {
        int count = map.getOrDefault(key, 0) + delta;
        if(count == 0) {
            map.remove(key); //这个remove是一定要有的, 因为树的路径要求是从上到下, 回溯之后不存在的路径和就要删除
        } else {
            map.put(key, count);
        }
    }
      
    //也是subtree的方法, discuss, 连加估计现场想不起来
    public int pathSum(TreeNode root, int sum) {
        if(root == null) {
            return 0;
        }
        return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum); //相当于自己方法的count连加
    }
    
    public int findPath(TreeNode root, int sum){
        int res = 0;
        if(root == null) {
            return res;
        }
        if(sum == root.val) {
            res++;
        }
        res += findPath(root.left, sum - root.val); //这里是+=的原因是每个节点自己最开始的res都是0, 所以也是根左右的连加的方法
        res += findPath(root.right, sum - root.val); //而我自己的方法是他们共用一个count, 只要根加1就行了, 左右子树的不用再加入
        return res;
    }
}
