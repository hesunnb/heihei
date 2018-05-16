/*Given an array of citations in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute 
the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, 
and the other N − h papers have no more than hcitations each."

Example:

Input: citations = [0,1,3,5,6]
Output: 3 
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had 
             received 0, 1, 3, 5, 6 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, his h-index is 3.
Note: If there are several possible values for h, the maximum one is taken as the h-index.*/

class Solution {
    
    //solution2: (own)
    //和H-Index的区别就是给的citations数组是升序的
    //testcase: [0]返回0, [100]返回1, [100,100,100,100]返回4, [0,1,3,5,6]返回3, [2,10,10,10]返回3
    public int hIndex(int[] citations) {
        
        if(citations == null || citations.length == 0) {
            return 0;
        }
        
        int count = 0;
        for(int i = citations.length - 1; i >= 0; i--) { //根据1受到的启发, 因为后面都比前面大, 所以从后向前加即可
            if(count >= citations[i]) {
                return count;
            }
            count++; //count在后面++, 比如[0,1,3,5,6], count=3的时候走到了元素1的地方, 1比3小, 正好返回3, 如果元素变为元素3, 那么3也正好
            //算到n-h篇文章里; count在后面++的原因是因为[0], [100], 这种单个元素的除了[0]全都返回1, 
        }
        
        return count; //像[100,100,100,100]全都放在n+1桶处的元素, 直接返回最后count的加和了
    }
}
