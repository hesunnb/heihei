/*Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 2^31.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.*/

class Solution {
    //方法非常巧妙, O(n)
    public int findMaximumXOR(int[] nums) {
        
        if(nums == null || nums.length == 0) {
            return -1;
        }
        
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--) {
            mask = mask | (1 << i); //mask译为掩蔽, 在这里用来取前31-i位, 看下面的注解可以理解为取从有效位开始的前1,2...一直到最后一位
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask); //让set中的每一个值都与mask按位与, 目的就是首先找出有效位, 就是set当中除了有0之外最先开始有其他有效值出现的
                //位置, 然后mask的用途就是取nums数组中每一个值的从有效值开始的前多少位
            }
            int tmp = max | (1 << i); //tmp是每步假象的最大值, 就是在max的基础上向后再多加一位1, 然后判断这个假象的最大值能不能被异或出来
            //如果能的话就把这个tmp给max
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) { //让tmp跟set中的某个元素就是prefix异或, 然后看这个异或的结果在set中存不存在, 这个道理就是
                    //A^B=C, 那么C^B=A, 也就是异或相等的三个数, 随便两个异或都能得到第三个; 在这里tmp是想要的结果, 就是假象的max, tmp与prefix
                    //异或的结果如果在set中存在, 那么tmp就能被set中的两个元素异或而得到
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
    /*测试程序:
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--) {
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
            System.out.println(i + " mask " + Integer.toBinaryString(mask));
            System.out.println(i + " tmp " + Integer.toBinaryString(tmp));
            System.out.println(i + " max " + Integer.toBinaryString(max));
            for(int prefix : set) {
            	System.out.print(Integer.toBinaryString(prefix) + " ");
            }
            System.out.println();
            System.out.println();
        }
        return max;
    }*/
    /*输出结果: [3, 10, 5, 25, 2, 8]
    31 mask 10000000000000000000000000000000
    31 tmp 10000000000000000000000000000000
    31 max 0
    0 

    30 mask 11000000000000000000000000000000
    30 tmp 1000000000000000000000000000000
    30 max 0
    0 

    29 mask 11100000000000000000000000000000
    29 tmp 100000000000000000000000000000
    29 max 0
    0 

    28 mask 11110000000000000000000000000000
    28 tmp 10000000000000000000000000000
    28 max 0
    0 

    27 mask 11111000000000000000000000000000
    27 tmp 1000000000000000000000000000
    27 max 0
    0 

    26 mask 11111100000000000000000000000000
    26 tmp 100000000000000000000000000
    26 max 0
    0 

    25 mask 11111110000000000000000000000000
    25 tmp 10000000000000000000000000
    25 max 0
    0 

    24 mask 11111111000000000000000000000000
    24 tmp 1000000000000000000000000
    24 max 0
    0 

    23 mask 11111111100000000000000000000000
    23 tmp 100000000000000000000000
    23 max 0
    0 

    22 mask 11111111110000000000000000000000
    22 tmp 10000000000000000000000
    22 max 0
    0 

    21 mask 11111111111000000000000000000000
    21 tmp 1000000000000000000000
    21 max 0
    0 

    20 mask 11111111111100000000000000000000
    20 tmp 100000000000000000000
    20 max 0
    0 

    19 mask 11111111111110000000000000000000
    19 tmp 10000000000000000000
    19 max 0
    0 

    18 mask 11111111111111000000000000000000
    18 tmp 1000000000000000000
    18 max 0
    0 

    17 mask 11111111111111100000000000000000
    17 tmp 100000000000000000
    17 max 0
    0 

    16 mask 11111111111111110000000000000000
    16 tmp 10000000000000000
    16 max 0
    0 

    15 mask 11111111111111111000000000000000
    15 tmp 1000000000000000
    15 max 0
    0 

    14 mask 11111111111111111100000000000000
    14 tmp 100000000000000
    14 max 0
    0 

    13 mask 11111111111111111110000000000000
    13 tmp 10000000000000
    13 max 0
    0 

    12 mask 11111111111111111111000000000000
    12 tmp 1000000000000
    12 max 0
    0 

    11 mask 11111111111111111111100000000000
    11 tmp 100000000000
    11 max 0
    0 

    10 mask 11111111111111111111110000000000
    10 tmp 10000000000
    10 max 0
    0 

    9 mask 11111111111111111111111000000000
    9 tmp 1000000000
    9 max 0
    0 

    8 mask 11111111111111111111111100000000
    8 tmp 100000000
    8 max 0
    0 

    7 mask 11111111111111111111111110000000
    7 tmp 10000000
    7 max 0
    0 

    6 mask 11111111111111111111111111000000
    6 tmp 1000000
    6 max 0
    0 

    5 mask 11111111111111111111111111100000
    5 tmp 100000
    5 max 0
    0 

    4 mask 11111111111111111111111111110000
    4 tmp 10000
    4 max 10000
    0 10000 //针对上面的例子, 有效位从这里开始出现, 就是第5位

    3 mask 11111111111111111111111111111000 //用mask取出第5位, 第4位
    3 tmp 11000
    3 max 11000 //异或成功, 说明第5位, 第4位通过set中元素的第5位, 第4位异或是可以得到的; 然后下一步就再看第5位, 第4位, 第3位都有的时候异或行不行
    0 1000 11000 //相应的取出nums数组中每个元素的第5位, 第4位

    2 mask 11111111111111111111111111111100 //过滤出第5位, 第4位, 第3位
    2 tmp 11100 //又加上一位
    2 max 11100
    0 100 1000 11000 //相应nums中第5位, 第4位, 第3位

    1 mask 11111111111111111111111111111110
    1 tmp 11110 //每步假象的最大值
    1 max 11100 //因为tmp的异或得不到, 所以没有更新max
    10 100 11000 1000 1010 

    0 mask 11111111111111111111111111111111
    0 tmp 11101
    0 max 11100
    10 11 101 1000 11001 1010
    */
}
