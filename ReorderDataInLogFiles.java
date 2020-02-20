/*You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its 
identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring 
identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

Return the final order of the logs.

 

Example 1:

Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 

Constraints:

0 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] is guaranteed to have an identifier, and a word after the identifier.*/

class Solution {
 
    //时间: O(nlogn), 空间: O(n)
    public String[] reorderLogFiles(String[] logs) {
        if(logs == null || logs.length == 0) {
            return new String[] {};
        }
        
        List<String> result = new ArrayList<>();
        PriorityQueue<LetterLog> pq = new PriorityQueue<>(new LetterLogComparator());
        
        for(int i = 0; i < logs.length; i++) {
            String[] step = logs[i].split(" ");
            if(Character.isDigit(step[1].charAt(0))) {
                result.add(logs[i]);
            } else {
                pq.offer(new LetterLog(step, i));
            }
        }
        while(!pq.isEmpty()) {
            result.add(0, logs[pq.poll().index]);
        }
        
        return result.toArray(new String[result.size()]);
    }
    
    class LetterLog {
        String strs[];
        int index;
        LetterLog(String strs[], int index) {
            this.strs = strs;
            this.index = index;
        }
    }
    
    //从大到小排序, 因为result要add(0, ...)
    class LetterLogComparator implements Comparator<LetterLog> {
        public int compare(LetterLog l1, LetterLog l2) {
            for (int i = 1; i < Math.min(l1.strs.length, l2.strs.length); i++) {
                if (l2.strs[i].compareTo(l1.strs[i]) == 0) { // 比较内容
                    continue;
                } else {
                    return l2.strs[i].compareTo(l1.strs[i]);
                }
            }
            if (l2.strs.length - l1.strs.length == 0) { // 比较identifier
                return l2.strs[0].compareTo(l1.strs[0]);
            }
            return l2.strs.length - l1.strs.length; // 比较长度
        }
    }
    /*Input:
    tier有个例子就是: 在letter-logs相同的情况下, 要去比较identifier
    ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo","a2 act car"]
    Output:
    ["g1 act car","a2 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
    Expected:
    ["a2 act car","g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]*/
 
    //solution2: 写一个大的Comparator, 直接sort
    public String[] reorderLogFiles(String[] logs) {
        if(logs == null || logs.length == 0) {
            return logs;
        }
        
        Arrays.sort(logs, new LetterLogComparator());
        return logs;
    }
    
    class LetterLogComparator implements Comparator<String> {
        
        public int compare(String s1, String s2) {
            int index1 = s1.indexOf(' ');
            int index2 = s2.indexOf(' ');
            char c1 = s1.charAt(index1 + 1);
            char c2 = s2.charAt(index2 + 1);
            
            if(c1 <= '9') { //ASCII值是空格< 数字 < 大写字母 < 小写字母
                if(c2 <= '9') { //都是数字的话保持原始顺序, 原始顺序就是不排序, 那么0就是都相等
                    return 0;
                }
                return 1; //c2是字母, 那么数字在后
            }
            if(c2 <= '9') { //此时c1是字母, c2是数字
                return -1; //c1在前
            }
            
            //如果上面都没满足, 此时c1和c2都是字母了
            int result = s1.substring(index1 + 1).compareTo(s2.substring(index2 + 1));
            //都不用split空格, 直接比较字符串就行, 如果字符都一样, 直接也就继续比较长度了
            if(result == 0) {
                return s1.substring(0, index1).compareTo(s2.substring(0, index2));
            }
            return result;
        }
    }
}
