/*Given an array of strings, return all groups of strings that are anagrams.

 Notice

All inputs will be in lower-case

Example
Given ["lint", "intl", "inlt", "code"], return ["lint", "inlt", "intl"].

Given ["ab", "ba", "cd", "dc", "e"], return ["ab", "ba", "cd", "dc"].*/

//这道题和groupAnagrams的一点点区别就是自己一个元素作为一组的不算, 并且最后是把所有的anagrams都放到一个ArrayList里面
public class Solution {
    /**
     * @param strs: A list of strings
     * @return: A list of strings
     */

    public List<String> anagrams(String[] strs) {
        ArrayList<String> result = new ArrayList<String>();
        HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();

        for (String str : strs) {
            int[] count = new int[26];
            for (int i = 0; i < str.length(); i++) {
                count[str.charAt(i) - 'a']++;
            }

            int hash = getHash(count);
            if (!map.containsKey(hash)) {
                map.put(hash, new ArrayList<String>());
            }

            map.get(hash).add(str);
        }

        for (ArrayList<String> tmp : map.values()) {
            if (tmp.size() > 1) { //自己一个元素一组的不算数
                result.addAll(tmp); //加入该ArrayList中的所有元素
            }
        }

        return result;
    }
    
    private int getHash(int[] count) {
        int hash = 0;
        int a = 378551;
        int b = 63689;
        for (int num : count) {
            hash = hash * a + num;
            a = a * b;
        }
        return hash;
    }
}
