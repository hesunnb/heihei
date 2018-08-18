/*Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.*/

class Solution {
    
    //solution1: O(n)
    public List<Integer> lexicalOrder(int n) {
        
        List<Integer> result = new ArrayList<>();
        if(n < 1) {
            return result;
        }
        
        int curr = 1;
        for (int i = 1; i <= n; i++) {
            result.add(curr);
            if (curr * 10 <= n) { //1,10,100等等
                curr *= 10;
            } else if (curr % 10 != 9 && curr + 1 <= n) { //n=123, curr=122; 然后122+1=123, 123+1不小于n, 所以123/10+1=13, 到了下一个13
                curr++;
            } else {
                while ((curr / 10) % 10 == 9) { //n=200, curr=199, 循环除以10, 然后从2开始
                    curr /= 10;
                }
                curr = curr / 10 + 1; //别的109就会走这里, 下一个是11
            }
        }
        return result;
    }
    /*The basic idea is to find the next number to add.
    Take 45 for example: if the current number is 45, the next one will be 450 (450 == 45 * 10)(if 450 <= n), or 46 (46 == 45 + 1) 
    (if 46 <=  n) or 5 (5 == 45 / 10 + 1)(5 is less than 45 so it is for sure less than n).
    We should also consider n = 600, and the current number = 499, the next number is 5 because there are all "9"s after "4" in "499" 
    so we should divide 499 by 10 until the last digit is not "9".
    It is like a tree, and we are easy to get a sibling, a left most child and the parent of any node.*/
    
    
    //solution2: 递归
    public List<Integer> lexicalOrder(int n) {
        
        List<Integer> result = new ArrayList<>();
        if(n < 1) {
            return result;
        }
        
        for(int i = 1; i < 10; i++) { //从1到9的打头
          lexicalOrderhelper(i, n, result); 
        }
        return result;
    }

    public void lexicalOrderhelper(int cur, int n, List<Integer> result){
        if(cur > n)
            return;
        else {
            result.add(cur);
            for(int i = 0; i < 10; i++) { //每个节点的所有可能的9个子节点
                if(10*cur+i > n)
                    return;
                lexicalOrderhelper(10*cur+i, n, result);
            }
        }
    }
    /*The idea is pretty simple. If we look at the order we can find out we just keep adding digit from 0 to 9 to every digit and 
    make it a tree.
    Then we visit every node in pre-order. 
           1        2        3    ...
          /\        /\       /\
       10 ...19  20...29  30...39   ....*/
}
