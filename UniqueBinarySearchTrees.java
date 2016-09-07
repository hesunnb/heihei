/*Given n, how many structurally unique BSTs (binary search trees) that store values 1...n?

Example
Given n = 3, there are a total of 5 unique BST's.

1           3    3       2      1
 \         /    /       / \      \
  3      2     1       1   3      2
 /      /       \                  \
2     1          2                  3
Tags 
Catalan Number, Dynamic Programming*/

public class Solution {
    /**
     * @paramn n: An integer
     * @return: An integer
     */
     
    /*
    The case for 3 elements example
    Count[3] = Count[0]*Count[2]  (1 as root) 1左边0个数组成BST的个数*1右边2个数组成BST的个数
                  + Count[1]*Count[1]  (2 as root) 2左边一个, 右边一个
                  + Count[2]*Count[0]  (3 as root) 3左边两个, 右边0个
    
    Therefore, we can get the equation:
    Count[i] = ∑ Count[0...k] * [ k+1....i]     0<=k<i-1 
    这个递推公式也叫卡特兰数
    */
    public int numTrees(int n) {
        // write your code here
        
        int[] count = new int[n + 2]; //+2保证至少有count[0]和count[1]的位置
        count[0] = 1;
        count[1] = 1;
        
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                count[i] += count[j] * count[i - 1 - j];
            }
        }
        return count[n];
    }
}
