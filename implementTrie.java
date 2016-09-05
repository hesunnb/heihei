/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */

// Version 1: Array of TrieNode, 用数组来实现
class TrieNode {
    // Initialize your data structure here.
    
    TrieNode[] children;
    boolean hasword;
    
    public TrieNode() {
        children = new TrieNode[26];
        hasword = false;
    }
    
    public void insert(String word, int index) {
        if(index == word.length()) {
            this.hasword = true;
            return;
        }
        
        int pos = word.charAt(index) - 'a';
        if(children[pos] == null) {
            children[pos] = new TrieNode(); //每一个字母开一个数组放相应的位置
        }
        children[pos].insert(word, index + 1);
    }
    
    public TrieNode find(String word, int index) {
        if(index == word.length()) {
            return this;
        }
        
        int pos = word.charAt(index) - 'a';
        if(children[pos] == null) {
            return null; //证明没有这个字母, 返回空
        }
        
        return children[pos].find(word, index + 1);
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        root.insert(word, 0);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode result = root.find(word, 0);
        return (result != null && result.hasword == true); //node!=null是说有要找的这个字母, 
        //node.hasword表明虽有字母但是找的是不是要找的单词, 比如lintcode, 插入lintcode这个字符串之后, 在e处有hasword = true, 
        //如果要查找lint, 字母都有, node都不为空, 但是t处的hasword不为真, 所以还是没有这个单词
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode result = root.find(prefix, 0);
        return result != null; //只需看字母存不存在即可
    }
}


//version 2: HashMap Version(首选), 用HashMap省空间
class TrieNode {
    // Initialize your data structure here.
    
    Map<Character, TrieNode> children;
    boolean hasWord;
    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        hasWord = false;
    }
}

public class Trie {
    private TrieNode root;

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
    public boolean search(String word) {
        TrieNode now = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(!now.children.containsKey(c)) {
                return false;
            }
            now = now.children.get(c);
        }
        return now.hasWord; //返回最后一个字母的hasword
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode now = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(!now.children.containsKey(c)) {
                return false;
            }
            now = now.children.get(c);
        }
        return true; //能把prefix所对应的字母全部循环完的话就说明prefix对应有所有的字母
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
