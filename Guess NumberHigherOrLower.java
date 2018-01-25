/*We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
Example:
n = 10, I pick 6.

Return 6.*/

/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    
    //Here “My” means the number which is given for you to guess not the number you put into guess(int num).
    public int guessNumber(int n) {
        if(n <= 0) {
            return -1;
        }
        
        int low = 1, high = n; //二分的上下限根据题目给出的范围定
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) > 0) { //My number是给定的要猜的数, 比如guess(5)>0, 表名给定的数比你猜的数要大
                low = mid;
            } else {
                high = mid;
            }
        }
        
        if(guess(low) == 0) {
        	return low;
        }
        if(guess(high) == 0) {
        	return high;
        }
        return -1;
    }
}
