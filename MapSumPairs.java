/*Implement a MapSum class with insert, and sum methods.

For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. 
If the key already existed, then the original key-value pair will be overridden to the new one.

For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key 
starts with the prefix.

Example 1:
Input: insert("apple", 3), Output: Null
Input: sum("ap"), Output: 3
Input: insert("app", 2), Output: Null
Input: sum("ap"), Output: 5*/

class MapSum {

    //own一遍过
    private Trie trie;
    /** Initialize your data structure here. */
    public MapSum() {
        trie = new Trie();
    }

    public void insert(String key, int val) {
        if(key == null) {
            return;
        }

        trie.insert(key, val);
    }

    public int sum(String prefix) {
        if(prefix == null) {
            return 0;
        }

        return trie.search(prefix, 0, 0, trie.getRoot());
    }

    class TrieNode {
        Map<Character, TrieNode> children;
        boolean hasWord = false;
        int val;
        TrieNode() {
            children = new HashMap<>();
            val = 0;
        }
    }

    class Trie {
        private TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        public TrieNode getRoot() {
            return root;
        }

        public void insert(String key, int val) { //trie正常插入
            TrieNode now = root;
            for(int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                if(!now.children.containsKey(c)) {
                    now.children.put(c, new TrieNode());
                }
                now = now.children.get(c);
            }
            now.val = val;
            now.hasWord = true;
        }

        public int search(String prefix, int sum, int index, TrieNode root) {
            TrieNode now = root;
            if(index < prefix.length()) { //search分为两部分, 这里是走到prefix处
                char c = prefix.charAt(index);
                if(now.children.containsKey(c)) {
                    sum = search(prefix, sum, index + 1, now.children.get(c));
                } else {
                    sum = 0;
                }
            } else { //从prefix处求所有和
                if(now.hasWord) {
                    sum += now.val;
                }
                Set set = now.children.keySet();
                for(Iterator i = set.iterator(); i.hasNext();) {
                    sum = search(prefix, sum, index, now.children.get(i.next()));
                }
            }
            return sum;
        }
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
