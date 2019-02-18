/*Given many words, words[i] has weight i.

Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix). It will return the word with given 
prefix and suffix with maximum weight. If no word exists, return -1.

Examples:

Input:
WordFilter(["apple"])
WordFilter.f("a", "e") // returns 0
WordFilter.f("b", "") // returns -1
 

Note:

words has length in range [1, 15000].
For each test case, up to words.length queries WordFilter.f may be made.
words[i] has length in range [1, 10].
prefix, suffix have lengths in range [0, 10].
words[i] and prefix, suffix queries consist of lowercase letters only.*/

class WordFilter {

    private String[] localWords;
    public WordFilter(String[] words) {
        this.localWords = words;
    }
    
    public int f(String prefix, String suffix) {
        if(localWords == null) {
            return -1;
        }
        for(int i = localWords.length - 1; i >= 0; i--) { //从后往前, 先出现的就是大的
            if(localWords[i].startsWith(prefix) && localWords[i].endsWith(suffix)) { //直接用字符串库函数, 前后判断即可
                return i;
            }
        }
        
        return -1;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
