/*Given a list of strings, you need to find the longest uncommon subsequence among them. The longest uncommon subsequence is defined as 
the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.

A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the 
remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon 
subsequence doesn't exist, return -1.

Example 1:
Input: "aba", "cdc", "eae"
Output: 3
Note:

All the given strings' lengths will not exceed 10.
The length of the given list will be in the range of [2, 50].*/

class Solution {
    /*Sort the strings in the reverse order. If there is not duplicates in the array, then the longest string is the answer.

    But if there are duplicates, and if the longest string is not the answer, then we need to check other strings. But the smaller 
    strings can be subsequence of the bigger strings.
    For this reason, we need to check if the string is a subsequence of all the strings bigger than itself. If it's not, that is the 
    answer.*/
    
    //最好O(nlogn), 最差O(n^2)
    public int findLUSlength(String[] strs) {
        
        if(strs == null || strs.length < 2) {
            return -1;
        }
        Arrays.sort(strs, new Comparator<String>() { //把strs数组里面的字符串按照长度从大到小排序
            public int compare(String o1, String o2) {
                return o2.length() - o1.length(); //o1-o2就是从大到小
            }
        });
        
        Set<String> duplicates = getDuplicates(strs);
        for(int i = 0; i < strs.length; i++) {
            if(!duplicates.contains(strs[i])) {
                if(i == 0) {
                    return strs[0].length(); //如果最长的字符串还没有重复, 那么直接返回
                }
                for(int j = 0; j < i; j++) {
                    if(isSubsequence(strs[j], strs[i])) { //对于不重复的字符串要从0到i-1位置判断当前字符串是不是前面长的字符串的子序列
                        /*例子: aaa, aaa, aa
                        aabbcc aabbcc cd
                        aabbcc aabbcc cd cd
                        aabbcc aabbcc bc cc 比如bc和cc的长度相等并且在strs中也没有重复, 但是不能只看bc和cc本身, 因为它俩都是前面aabbcc的子序列
                        所以都不能选, 只能是-1*/
                        break;
                    }
                    if(j == i-1) {
                        return strs[i].length();
                    }
                }
            }
        }
        return -1;
    }
    
    private boolean isSubsequence(String a, String b) { //用于判断b是不是a的subsequence
        int i = 0, j = 0;
        while(i < a.length() && j < b.length()) {
            if(a.charAt(i) == b.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == b.length();
    }
    
    private Set<String> getDuplicates(String[] strs) {
        Set<String> set = new HashSet<String>();
        Set<String> duplicates = new HashSet<String>();
        for(String s : strs) {
            if(!set.add(s)) { //false就是重复
                duplicates.add(s); 
            }
        }
        return duplicates;
    }
}
