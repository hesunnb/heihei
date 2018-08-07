/*Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.*/

class Solution {
    public String frequencySort(String s) {
        
        if(s == null || s.length() == 0) {
            return "";
        }
        
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<Map.Entry<Character, Integer>>(new freComparator());
            
        //向哈希表中加东西
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        
        //向优先级队列中加东西, 这里用优先级队列的最大堆
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.offer(entry);  
        }
        
        //取出结果
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
            Map.Entry<Character, Integer> entry = queue.poll();
            for(int i = 0; i < entry.getValue(); i++) {
                sb.append(entry.getKey()); //用最小堆这里StringBuilder就得用insert方法, insert方法要比append慢很多!
                //append30ms, insert890ms
            }
        }
            
        return sb.toString();
    }
    
    class freComparator implements Comparator<Map.Entry<Character, Integer>> {
        public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
            return e2.getValue() - e1.getValue(); //第一个减第二个就是最小堆, 第二个减第一个就是最大堆
        }
    }
}
