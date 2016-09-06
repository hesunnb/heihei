/*Design a data structure that supports the following two operations: addWord(word) and search(word)

search(word) can search a literal word or a regular expression string containing only letters a-z or ..

A . means it can represent any one letter.

 Notice

You may assume that all words are consist of lowercase letters a-z.

Example
addWord("bad")
addWord("dad")
addWord("mad")
search("pad")  // return false
search("bad")  // return true
search(".ad")  // return true
search("b..")  // return true */

public class WordDictionary {

    Trie trie = new Trie();
    // Adds a word into the data structure.
    public void addWord(String word) {
        // Write your code here
        trie.insert(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        // Write your code here
        return trie.search(word, 0, trie.getRoot());
    }
}

class TrieNode {
    // Initialize your data structure here.
    
    Map<Character, TrieNode> children;
    boolean hasWord;
    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        hasWord = false;
    }
}

class Trie {
    private TrieNode root;

    public TrieNode getRoot() {
        return root;
    }

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) { //跟数组一样, 数组是一个一个开数组, 这个是一个一个开哈希表
        TrieNode now = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!now.children.containsKey(c)) {
                now.children.put(c, new TrieNode());
            }
            now = now.children.get(c);
        }
        now.hasWord = true; //最后一个字母的TrieNode里面的hasword为真
    }
    
    // Returns if the word is in the trie.
    public boolean search(String word, int index, TrieNode root) {
        boolean result = false;
        TrieNode now = root;
        if(index == word.length()) {
            return now.hasWord;
        }
        char c = word.charAt(index);
        if(c == '.') { //如果是'.', 说明这个字符和所有的字符串都匹配
            Set set = now.children.keySet(); //那么就要递归搜索当前节点children里面所有的字符串
            for(Iterator i = set.iterator(); i.hasNext();) {
			    result = search(word, index + 1, now.children.get(i.next()));
			    if(result) { //递归搜索过程中有一个是真, 就说明符合就一直返回真, 不判断了
			        return result;
			    }
		    }
        } else {
            if(!now.children.containsKey(c)) { //不包含相应字母就返回假
                return false;
            }
            result = search(word, index + 1, now.children.get(c)); //这个是正常递归搜索过程
        }
        return result;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
