/*All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is 
sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]*/

class Solution {
    
    //solution1: 两个HashSet
    public List<String> findRepeatedDnaSequences(String s) {
        
        if(s == null || s.length() == 0) {
            return new ArrayList<String>();
        }
        //用一个窗口不断向右走
        Set<String> seen = new HashSet<>(), repeated = new HashSet<>();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten)) { //set如果加入重复元素会返回假
                repeated.add(ten);
            }
        }
        return new ArrayList(repeated);
    }
    
    
    //solution2: 自定义map和bits manipulation
    public List<String> findRepeatedDnaSequences(String s) {
        
        if(s == null || s.length() == 0) {
            return new ArrayList<String>();
        }
        
        Set<Integer> words = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();
        List<String> rv = new ArrayList<>();
        char[] map = new char[26];
        //map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        for(int i = 0; i < s.length() - 9; i++) {
            int v = 0;
            for(int j = i; j < i + 10; j++) {
                v <<= 2;
                v |= map[s.charAt(j) - 'A'];
            }
            if(!words.add(v) && doubleWords.add(v)) {
                rv.add(s.substring(i, i + 10));
            }
        }
        return rv;
    }
}
