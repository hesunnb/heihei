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
    
    更清晰的解释:
    First of all, given the above definitions, we can see that the total number of unique BST G(n), is the sum of BST F(i) using each 
    number i as a root.
    i.e.
    G(n) = F(1, n) + F(2, n) + ... + F(n, n). 

    Particularly, the bottom cases, there is only one combination to construct a BST out of a sequence of length 1 (only a root) or 0 
    (empty tree).
    i.e.
    G(0)=1, G(1)=1. 

    Given a sequence 1…n, we pick a number i out of the sequence as the root, then the number of unique BST with the specified root F(i), 
    is the cartesian product of the number of BST for its left and right subtrees. For example, F(3, 7): the number of unique BST tree with 
    number 3 as its root. To construct an unique BST out of the entire sequence [1, 2, 3, 4, 5, 6, 7] with 3 as the root, which is to say, 
    we need to construct an unique BST out of its left subsequence [1, 2] and another BST out of the right subsequence [4, 5, 6, 7], and 
    then combine them together (i.e. cartesian product). The tricky part is that we could consider the number of unique BST out of sequence 
    [1,2] as G(2), and the number of of unique BST out of sequence [4, 5, 6, 7] as G(4). Therefore, F(3,7) = G(2) * G(4).
    i.e.
    F(i, n) = G(i-1) * G(n-i)	1 <= i <= n 

    Combining the above two formulas, we obtain the recursive formula for G(n). i.e.
    G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0) 
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
