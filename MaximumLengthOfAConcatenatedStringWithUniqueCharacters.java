/*Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.

 

Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
 

Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.*/

class Solution {
    private int result = 0;

    public int maxLength(List<String> arr) {
        if (arr == null || arr.size() == 0) {
            return 0;
        }
        dfs(arr, "", 0);
        return result;
    }

    private void dfs(List<String> arr, String path, int idx) {
        boolean isUniqueChar = isUniqueChars(path);
        
        if (isUniqueChar) {
            result = Math.max(path.length(), result);
        }

        if (idx == arr.size() || !isUniqueChar) {
            return;
        }
        
        for (int i = idx; i < arr.size(); i++) {
            dfs(arr, path + arr.get(i), i + 1);
        }
    }

    private boolean isUniqueChars(String s) {
        Set<Character> set = new HashSet<>();

        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }
 
    
    /*思路:
    0. Initial the result res to include the case of empty string "".
    res include all possible combinations we find during we iterate the input.

    1. Itearte the the input strings,
    but skip the word that have duplicate characters.
    The examples is kind of misleading,
    but the input string can have duplicate characters,
    no need to considerate these strings.

    2. For each string,
    we check if it's conflit with the combination that we found.
    If they have intersection of characters, we skip it.
    If not, we append this new combination to result.

    3. return the maximum length from all combinations.*/
    //循环版
    public int maxLength(List<String> arr) {
        List<String> res = new ArrayList<>();
        res.add("");
        for (String str : arr) {
            if (!isUnique(str)) {
                continue;
            }
            List<String> resList = new ArrayList<>();
            for (String candidate : res) { //在res中加入""就是让这个循环能够启动
                String temp = candidate + str;
                if (isUnique(temp)) {
                    resList.add(temp);
                }
            }
            res.addAll(resList);
        }
        int ans = 0;
        for (String str : res) {
            ans = Math.max(ans, str.length());
        }
        return ans;
    }
    
    private boolean isUnique(String str) { //这段用hashset写也ok
        if (str.length() > 26) {
            return false;
        }
        boolean[] used = new boolean [26];
        char[] arr = str.toCharArray();
        for (char ch : arr) {
            if (used[ch - 'a']) {
                return false; 
            } else {
                used[ch - 'a'] = true;
            }
        }
        return true;
    }
}
