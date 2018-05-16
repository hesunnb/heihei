/*Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's 
h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, 
and the other N − h papers have no more than h citations each."

Example:

Input: citations = [3,0,6,1,5]
Output: 3 
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had 
             received 3, 0, 6, 1, 5 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, his h-index is 3.
Note: If there are several possible values for h, the maximum one is taken as the h-index.*/

class Solution {
    
    //题的意思就是说有h篇文章的引用>=h, n-h篇文章的引用<=h
    //testcase: 注意at least包含等于h, no more than也包含等于h
    /*[3,3,3,3]这个返回3, 3本身就是临界, 既能算到h篇文章里, 也能算到n-h篇文章里, 所以当木桶排完之后, 4>3, 就说明这有3个3算进h篇文章, 
    1个3算进n-h篇文章; [3,3,5,5]也返回3, 3篇文章2个, 5篇文章2个, 那么总共的2+2的4又可以拆成>=3的有3个, 小于等于3的有1个; [1,1]返回1*/
    public int hIndex(int[] citations) {
        
        if(citations == null || citations.length == 0) {
            return 0;
        }
        
        int n = citations.length;
        int[] buckets = new int[n + 1]; //排木桶
        for(int c : citations) {
            if(c >= n) {
                buckets[n]++;
            } else {
                buckets[c]++;
            }
        }
        
        int count = 0;
        for(int i = n; i >= 0; i--) { //从后向前加和count, 原因是要找到最大的H-Index
            count += buckets[i];
            if(count >= i) { //count >= 当前桶数的时候, 就找到了
                return i;
            }
        }
        return 0;
    }
    /*discuss的思路: 假设n是论文总数, 如果我们有n + 1个存储桶, 数量从0到n, 那么对于任何带有与存储桶索引相对应的参考文献, 我们将增加该存储桶
    的计数. 唯一的例外是, 对于参考数量大于n的纸张, 我们放入第n桶,, Then we iterate from the back to the front of the buckets, whenever 
    the total count exceeds the index of the bucket, meaning that we have the index number of papers that have reference greater
    than or equal to the index.*/
}

