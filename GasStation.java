/*There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

 Notice

The solution is guaranteed to be unique.

Example
Given 4 gas stations with gas[i]=[1,1,3,1], and the cost[i]=[2,2,1,1]. The starting gas station's index is 2.*/

public class Solution {
    /**
     * @param gas: an array of integers
     * @param cost: an array of integers
     * @return: an integer
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // write your code here
        
        if(gas == null || gas.length == 0 || cost == null || cost.length == 0) {
            return -1;
        }
        
        int total = 0;
        int sum = 0;
        int index = -1;
        for(int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i]; //sum负责算局部的和, 能够出发的点一定是gas与cost差值大于0的点,
            //并且如果局部和小于0的话说明前面这段数组没有一个点可以出发
            /*比如以2, -1....开头的局部和小于0, 那么出去2, -1还是会小于0
            以-1, 2....开头的局部和小于0, 这是不能存在的, -1会被舍掉
            以1, -2....开头的局部和小于0, 1, -2这种开头直接就会被跳过*/
            
            total += gas[i] - cost[i]; //total算总的和
            if(sum < 0) { //总结起来就是sum小于0, 前面就都会被跳过
                index = i;
                sum = 0;
            }
        }
        return total < 0 ? -1 : index + 1; //总的和小于0肯定不行, 大于等于0才有解
    }
    /*
    testCase:
    gas:  2  0  1  2  3  4  0
    cost: 0  1  0  0  0  0  11
    diff: 2 -1  1  2  3  4 -11 //这种直接返回的头, index = -1 + 1 = 0
    
    gas:  1  2  3  4  5
    cost: 3  4  5  1  2
    diff:-2 -2 -2  3  3 //一个正数开头加到最后还是正, 那个开头正数的前面的和如果为负且绝对值小于后面这些数的和就有解, 否则就没解了
    
    gas:  1  2  3  3
    cost: 2  1  5  1
    diff:-1  1 -2  2
    */
}
