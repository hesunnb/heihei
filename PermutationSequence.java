/*
Given n and k, return the k-th permutation sequence.
Notice

n will be between 1 and 9 inclusive.

Example

For n = 3, all permutations are listed as follows:

"123"
"132"
"213"
"231"
"312"
"321"

If k = 4, the fourth permutation is "231"
Challenge

O(n*k) in time complexity is easy, can you do it in O(n^2) or less?
*/

class Solution {
    /**
      * @param n: n
      * @param k: the kth permutation
      * @return: return the k-th permutation
      */
      
    // Use Linked List to record usage
    //思路和下面九章的一样, 就是把递归和要找的数存好了, 然后直接去取, 时间不是O(n), 是O(n^2), 费点儿空间
    public String getPermutation(int n, int k) {
        
        List<Integer> num = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) num.add(i);
        int[] fact = new int[n];  // factorial
        fact[0] = 1;
        for (int i = 1; i < n; i++) fact[i] = i*fact[i-1];
        k = k-1;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--){
            int ind = k/fact[i-1];
            k = k%fact[i-1];
            sb.append(num.get(ind));
            num.remove(ind);
        }
        return sb.toString();
    }  
    
    
    //还是这个方法更适合中式思维
    public String getPermutation(int n, int k) {
        
        StringBuilder sb = new StringBuilder();
        boolean[] visit = new boolean[n];
        k = k - 1; //k每次都是余数, 所以从开始就让k-1, 解释见下面
        int factor = 1;
        for(int i = 1; i < n; i++) { //求出第一次循环需要的的全排列
            factor *= i;
        }
        
        for(int i = 0; i < n; i++) {
            int index = k / factor; //index说明从头开始有几个数进行了全排列
            k = k % factor; //剩下的余数, 除了已经挑出来的数, 在剩下的组合中按数序要找第余数+1个数
            for(int j = 0; j < n; j++) {
                if(visit[j] == false) { //visit[i]已经为真的是已经挑出去的数了, 所以每次都要在visit[i]为假的地方判断index, 
                    //visit[i]为假的是有效位
                    if(index == 0) {
                        visit[j] = true;
                        sb.append((char)('0' + j + 1)); //j是目前有效位的坐标, +1就变成了值
                        break;
                    } else {
                        index--; //这个位做完了全排列, 找下一位
                    }
                }
            }
            
            if(i < n - 1) {
                factor = factor / (n - 1 - i); //每次让阶乘缩小到后几个数的全排列
            }
        }
        
        return sb.toString();
    }
    
    /*
    举例: 1234 n=4 k=18
    factor = 3! int index = 17 / 3! = 2 //说明1,2已经做完了全排列
                    k = 17 % 3! = 5 //说明要在以3开头的数中找第k + 1个数(余数 + 1)
    
    factor = 2! int index = 5 / 2! = 2 //除了3以外(3已经是true了, 被跳过), 剩下的3个数完成了两次全排列(1与2)
                    k = 5 % 2! = 1 //以4开头的第二个数
                    
    factor = 1! int index = 1 / 1! = 1 //除3,4以外, 剩下的1做了一次全排列
                    k = 1 % 1! = 0 //找以2开头的第一个数
                    
    factor = 1  int index = 0 / 1 = 0 //最后一个数本身, 就是剩下的1了
                    k = 0 % 1 = 0
                    
    最终是要找的第18个数是3421, 每次都把找到的那个开头加入StringBuilder
    */
}
