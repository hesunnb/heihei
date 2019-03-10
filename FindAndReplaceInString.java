/*To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same 
size).

Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is that if x starts at 
position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.

For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because "cd" starts at 
position 2 in the original string S, we will replace it with "ffff".

Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well as another 
replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the original string S[2] = 'c', 
which doesn't match x[0] = 'e'.

All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for example, S = "abc", 
indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.

Example 1:

Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
Output: "eeebffff"
Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
"cd" starts at index 2 in S, so it's replaced by "ffff".
Example 2:

Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
Output: "eeecd"
Explanation: "ab" starts at index 0 in S, so it's replaced by "eee". 
"ec" doesn't starts at index 2 in the original S, so we do nothing.
Notes:

0 <= indexes.length = sources.length = targets.length <= 100
0 < indexes[i] < S.length <= 1000
All characters in given inputs are lowercase letters.*/

class Solution {
    /*testcase:
    用indexOf不行的原因: 
    Input:
    "wreorttvosuidhrxvmvo"
    [14,12,10,5,0,18]
    ["rxv","dh","ui","ttv","wreor","vo"]
    ["frs","c","ql","qpir","gwbeve","n"]
    
    Output:
    "gwbeveqpirosqlcfrsmvo" //用indexOf找到vo所在的位置7和indexes中的18不符, 所以就没有进行替换, vo就留下来了, 所以必须用startWith, 
    因为startWith可以指定从哪个位置开始进行字符串比较
    
    Expected:
    "gwbeveqpirosqlcfrsmn"
    
    i必须加sources[map.get(i)].length()的原因:
    Input:
    "abcd"
    [0, 2]
    ["a", "cd"]
    ["eee", "ffff"]
    
    Output:
    "eeebffffd" //d位置就是3没有在哈希表中, 所以如果不加, d就会被append到结果当中
    
    Expected:
    "eeebffff"*/
    
    //这个就是批量替换的写法, 模板
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        if(S == null || S.length() == 0 || indexes == null || indexes.length == 0 || sources == null || sources.length == 0 || 
           targets == null || targets.length == 0 || sources.length != targets.length) {
            return S;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < indexes.length; i++) {
            // if a match is found in the original string, record it
            if (S.startsWith(sources[i], indexes[i])) { //此处不能用indexOf
                map.put(indexes[i], i); //是indexes中的值和indexes的下标做成了哈希表
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < S.length()) {
            if (map.containsKey(i)) {
                // if a replacement was recorded before
                sb.append(targets[map.get(i)]);
                i += sources[map.get(i)].length(); //此处i必须加sources[map.get(i)].length()
            } else {
                // if no replacement happened at this index
                sb.append(S.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
}
