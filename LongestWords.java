/*Given a dictionary, find all of the longest words in the dictionary.
Example

Given

{
  "dog",
  "google",
  "facebook",
  "internationalization",
  "blabla"
}

the longest words are(is) ["internationalization"].

Given

{
  "like",
  "love",
  "hate",
  "yes"
}

the longest words are ["like", "love", "hate"].
*/

class Solution {
    /**
     * @param dictionary: an array of strings
     * @return: an arraylist of strings
     */
    ArrayList<String> longestWords(String[] dictionary) {
        // write your code here
        
        ArrayList<String> result = new ArrayList<String>();
        if(dictionary == null || dictionary.length == 0) {
            return result;
        }
        
        int maxLength = 0;
        for(int i = 0; i < dictionary.length; i++) {
            maxLength = Math.max(maxLength, dictionary[i].length());
        }
        
        for(int i = 0; i < dictionary.length; i++) {
            if(dictionary[i].length() == maxLength) {
                result.add(dictionary[i]);
            }
        }
        return result;
    }
};
