/*You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to 
remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the 
number of stones in the heap.

For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, the last stone 
will always be removed by your friend.*/

class Solution {
    public boolean canWinNim(int n) {
        //注意, determine whether you can win, 重点是说对于n你是否能赢, 并且双方都是尽量让你输的拿法, 不会故意拿少, 所以说要给对手留下4的倍数
        //比如2*4< n < 3*4, 这时我拿n, 那么我一定能给对方拿到8, 接着无论对方拿1,2,3, 我都能给对方拿到4, 然后无论对方拿1,2,3, 我都能拿最后一个, 
        //所以在这里谁拿到4的倍数谁就输了
        
        if(n <= 0) {
            return false;
        }
        return n % 4 != 0;
        //进而扩展, 如果可以拿1,2,3,4, 那么就是谁先拿到5的倍数谁输
        
        /*Proof:
        the base case: when n = 4, as suggested by the hint from the problem, no matter which number that that first player, 
        the second player would always be able to pick the remaining number.

        For 1* 4 < n < 2 * 4, (n = 5, 6, 7), the first player can reduce the initial number into 4 accordingly, which will leave the 
        death number 4 to the second player. i.e. The numbers 5, 6, 7 are winning numbers for any player who got it first.

        Now to the beginning of the next cycle, n = 8, no matter which number that the first player picks, it would always leave the 
        winning numbers (5, 6, 7) to the second player. Therefore, 8 % 4 == 0, again is a death number.

        Following the second case, for numbers between (2*4 = 8) and (3*4=12), which are 9, 10, 11, are winning numbers for the first 
        player again, because the first player can always reduce the number into the death number 8.*/
    }
}
