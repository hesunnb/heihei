/*A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character 
changed in the gene string.

For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene 
string.

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" 
to "end". If there is no such a mutation, return -1.

Note:

Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needed, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.
 

Example 1:

start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

return: 1
 

Example 2:

start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

return: 2
 

Example 3:

start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

return: 3*/

class Solution {

    //类似题目: word ladder, word ladder2
    //BFS解法, O(n), n是bankSet的长度
    public int minMutation(String start, String end, String[] bank) {
        
        if(start.equals(end)) {
            return -1; //题目要求start与end不能相等 
        }
        
        Set<String> bankSet = new HashSet<>();
        for(String str : bank) {
            bankSet.add(str);
        }
        
        char[] charSet = new char[] {'A', 'C', 'G', 'T'};
        
        int level = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start); //bankSet中已经访问过的可能性
        
        while(!queue.isEmpty()) {
            int size = queue.size(); //size记录每层的大小, 变化一个字母bankSet中有几种符合的; 变化两个字母bankSet中有几种符合的...
            while(size > 0) {
                String curr = queue.poll();
                if(curr.equals(end)) return level; //某一种符合了就返回当前层数, 层数就是变化了几个字母
                
                char[] currArray = curr.toCharArray();
                for(int i = 0; i < currArray.length; i++) {
                    char old = currArray[i]; //保留该位置原有字符
                    for(char c: charSet) { //用4种字符替换看有没有匹配的
                        currArray[i] = c;
                        String next = new String(currArray);
                        if(!visited.contains(next) && bankSet.contains(next)) { //!visited.contains(next)访问过的就不要重复加到队列中了,
                            //因为'A', 'C', 'G', 'T'还有自己替换自己的时候, 这样队列中就会加入许多重复的值
                            /*"AACCTTGG", "AATTCCGG" //以这个为例说明!visited.contains(next)的必要性
                            ["AATTCCGG","AACCTGGG","AACCCCGG","AACCTACC"]*/
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                    currArray[i] = old;
                }
                size--;
            }
            level++; //一层一层的加, 变化一个字母为一层, 变化两个字母为第二层...
        }
        return -1;
    }
}
