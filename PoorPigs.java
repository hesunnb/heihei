/*There are 1000 buckets, one and only one of them contains poison, the rest are filled with water. They all look the 
same. If a pig drinks that poison it will die within 15 minutes. What is the minimum amount of pigs you need to 
figure out which bucket contains the poison within one hour.

Answer this question, and write an algorithm for the follow-up general case.

Follow-up:

If there are n buckets and a pig drinking poison will die within m minutes, how many pigs (x) you need to figure out the 
"poison" bucket within p minutes? There is exact one bucket with poison.*/


class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int pigs = 0;
        int checkNum = minutesToTest / minutesToDie + 1; //60分能检测4次, 60~74都只能检测4次, 因为不到15分就不会死, 就无法检测
        while(Math.pow(checkNum, pigs) < buckets) {
            pigs += 1;
        }
        return pigs;
    }
    /*解释:
    With 2 pigs, poison killing in 15 minutes, and having 60 minutes, we can find the poison in up to 25 buckets in the 
    following way. Arrange the buckets in a 5×5 square:

     1  2  3  4  5
     6  7  8  9 10
    11 12 13 14 15
    16 17 18 19 20
    21 22 23 24 25
    Now use one pig to find the row (make it drink from buckets 1, 2, 3, 4, 5, wait 15 minutes, make it drink from 
    buckets 6, 7, 8, 9, 10, wait 15 minutes, etc). Use the second pig to find the column (make it drink 1, 6, 11, 16, 21, 
    then 2, 7, 12, 17, 22, etc).

    Having 60 minutes and tests taking 15 minutes means we can run four tests. If the row pig dies in the third test, 
    the poison is in the third row. If the column pig doesn’t die at all, the poison is in the fifth column (this is 
    why we can cover five rows/columns even though we can only run four tests).

    With 3 pigs, we can similarly use a 5×5×5 cube instead of a 5×5 square and again use one pig to determine the 
    coordinate of one dimension (one pig drinks layers from top to bottom, one drinks layers from left to right, 
    one drinks layers from front to back). So 3 pigs can solve up to 125 buckets.

    In general, we can solve up to (⌊minutesToTest / minutesToDie⌋ + 1)pigs buckets this way, so just find the smallest 
    sufficient number of pigs for example like this:

    def poorPigs(self, buckets, minutesToDie, minutesToTest):
        pigs = 0
        while (minutesToTest / minutesToDie + 1) ** pigs < buckets:
            pigs += 1
        return pigs*/
    
    /*针对这个解释3维以上的思考:
    5个桶的时候一只猪, 那么猪按个数喝水, 喝一桶歇15分, 喝一桶歇15分, 4桶之后便知分晓
    5*5个桶二只猪, 注意喝水不算时间, 所以一只猪按row喝水, 另一只按column喝水, 两只猪同时进行, 交叉点就是poison
    5*5*5个桶三只猪, 每只猪按面喝, 每只都喝25桶一面, 三面同时进行, 交叉点就是poison
    5*5*5*5个桶四只猪, 看成125*5, 那么第四只猪以125桶为单位, 一次喝125桶(因为喝水不算时间), 如果125桶喝完歇15分钟没事儿, 说明
    这125桶没毒, 那么再喝下125桶, 直到喝出poison, 再转为5*5*5三只猪按面进行的过程, 这也就是4维的解法
    同理5*5*5*5*5五维就是看成625*5, 第五只每次喝625桶, 喝出poison, 第四只在625桶中每次再喝125桶, 喝出poison, 然后转为立方体进行*/
}
