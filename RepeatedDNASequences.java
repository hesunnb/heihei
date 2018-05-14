/*All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is 
sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]*/

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        
        if(s == null || s.length() == 0) {
            return new ArrayList<String>();
        }
        
        Set<String> seen = new HashSet<>(), repeated = new HashSet<>();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten)) { //set如果加入重复元素会返回假
                repeated.add(ten);
            }
        }
        return new ArrayList(repeated);
    }
}
