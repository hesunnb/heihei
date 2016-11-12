/*Given number n. Print number from 1 to n. But:

when number is divided by 3, print "fizz".
when number is divided by 5, print "buzz".
when number is divided by both 3 and 5, print "fizz buzz".

Example
If n = 15, you should return:

[
  "1", "2", "fizz",
  "4", "buzz", "fizz",
  "7", "8", "fizz",
  "buzz", "11", "fizz",
  "13", "14", "fizz buzz"
]*/

public class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        if(n < 1) {
            return result;
        }
        for(int i = 1; i <= n; i++) {
            if(i % 15 == 0) {
                result.add("FizzBuzz");
            } else if(i % 5 == 0) {
                result.add("Buzz");
            } else if(i % 3 == 0) {
                result.add("Fizz");
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }
}
