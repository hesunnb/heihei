class Solution {
    /**
     * @param key: A String you should hash
     * @param HASH_SIZE: An integer
     * @return an integer
     */
    public int hashCode(char[] key,int HASH_SIZE) {
        // write your code here
        if(key == null || key.length == 0 || HASH_SIZE <= 0) {
            return 0;
        }
        
        long sum = 0; //int与int的计算结果中途还是int, 所以中间的临时结果要是大于int也是会越界的, 比如
        //System.out.println(1000000007 * 1000 / 20000); //1000000007 * 1000的计算结果还是int, 中途就截断了
        //正确写法是System.out.println(1000000007L * 1000 / 20000);
        for(int i = 0; i < key.length; i++) {
            sum = (33 * sum + (int)key[i]) % HASH_SIZE;
        }
        
        return (int)sum;
        
        /*
        规律: (背下)
        a       a % b = r //a与它的余数(对b)
        将a与r带到同一个线性式中, 得到的新的结果对b同余(a*33 + 1 与 r*33 + 1对b同余)
        如果继续运算, 将a*33 + 1与(r*33 + 1) % b带到新的线性式中运算, 以此类推
        */
    }
}
