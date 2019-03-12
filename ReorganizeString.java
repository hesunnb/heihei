/*Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].*/

class Solution {
    
    /*In problem 358, we asked to rearrange string such that the same characters are at least distance k from each other.
    In this problem, we are asked to rearrange string such that the same characters are at least distance 2 from each other.*/
    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        pq.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            sb.append(entry.getKey());
            entry.setValue(entry.getValue() - 1);
            queue.offer(entry);
            while (queue.size() > 1) { //从2到k
                Map.Entry<Character, Integer> temp = queue.poll();
                if (temp.getValue() > 0) {
                    pq.offer(temp);
                }
            }
        }
        return sb.length() == S.length() ? sb.toString() : "";
    } 
}
