class Solution {
    /**
     *@param a, b: Two integer
     *return: An integer
     */
    public static int bitSwapRequired(int a, int b) {
        // write your code here
        int result = 0;
        int tempa = 0;
        int tempb = 0;
        for(int i = 0; i < Integer.SIZE; i++)
        {
            tempa = a & 1;
            tempb = b & 1;
            if(tempa != tempb)
            {
                result++;
            }
            a = a >>> 1;
            b = b >>> 1;
        }
        return result;
    }
};
