/*There is a fence with n posts, each post can be painted with one of the k colors.
You have to paint all the posts such that no more than two adjacent fence posts have the same color.
Return the total number of ways you can paint the fence.

Notice

n and k are non-negative integers.

 Example

Given n=3, k=2 return 6

      post 1,   post 2, post 3
way1    0         0       1 
way2    0         1       0
way3    0         1       1
way4    1         0       0
way5    1         0       1
way6    1         1       0
*/

public class Solution {
    /**
     * @param n non-negative integer, n posts
     * @param k non-negative integer, k colors
     * @return an integer, the total number of ways
     */
    public int numWays(int n, int k) {
        // Write your code here
        
        if(n <= 1) {
            return k;
        }
        
        //有两个柱子的情况
        int diffColor = k * (k - 1); //第一根柱子有k种涂法, 第二根柱子有(k - 1)种涂法, 这样会得到第一根和第二根颜色不同的组合数目
        int sameColor = k; //第一根和第二根颜色相同的组合数目
        
        //3根及以上柱子
        for(int i = 2; i < n; i++) {
            int temp = diffColor;
            diffColor = (diffColor + sameColor) * (k - 1); //新加进来的柱子的颜色和它前面那个柱子颜色不同的时候
            //无论前面那几棵柱子怎样, 新柱子和它前面那个挨着的要颜色不一样就有k - 1种
            sameColor = temp; //在上把颜色不同的柱子后面加上相同的颜色, 就有上把不同颜色柱子组合那么多种; 上把已经颜色相同的柱子后面不能再加颜色相同的了
        }
        return sameColor + diffColor;
    }
}
