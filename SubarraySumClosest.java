/*Description

Given an integer array, find a subarray with sum closest to zero. Return the indexes of the first number and last number.
Example

Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4].
Challenge

O(nlogn) time
*/

public class Solution {
    
    //O(nlogn)的做法, 直接O(n^2)也能做
    public int[] subarraySumClosest(int[] nums) {
       
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        } 
        
        int len = nums.length;
        if(len == 1) {
            res[0] = res[1] = 0;
            return res;
        }
        Pair[] sums = new Pair[len+1];
        int prev = 0;
        sums[0] = new Pair(0, 0);
        for (int i = 1; i <= len; i++) {
            sums[i] = new Pair(prev + nums[i-1], i);
            prev = sums[i].sum;
        }
        Arrays.sort(sums, new Comparator<Pair>() { //从小到大按照前序之和排序
           public int compare(Pair a, Pair b) {
               return a.sum - b.sum;
           } 
        });
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= len; i++) {
            if (ans > sums[i].sum - sums[i-1].sum) { //此处没用绝对值的原因是因为已经排序, 后减前肯定为正, 用绝对值也没问题
                ans = sums[i].sum - sums[i-1].sum; //更新最小间距
                int[] temp = new int[]{sums[i].index - 1, sums[i - 1].index - 1}; //因为是前序和数组, 所以下标都要减1
                Arrays.sort(temp); //得到的下标从小到大排序
                res[0] = temp[0] + 1; //起始位置要+1, 因为前序和数组的原因, 真正的起始位置要+1
                res[1] = temp[1];
            }
        }
        
        return res;
    }
    
    class Pair {
        int sum;
        int index;
        public Pair(int s, int i) {
            sum = s;
            index = i;
        }
    }
    /*比如[-3, 1, 1, -3, 5], 
    它的前序和数组是[0, -3, -2, -1, -4, 1], 
    排序之后就是[-4, -3, -2, -1, 0, 1]
    排序的目的就是要求各个前序和之差, 挑出他们之间最小的差值, 同时除了差值之外, 前序和的本身也要参与比较, 这就是0在这个数组
    中的意义了, 只有-1和1这两个前序和与0做差即前序和本身有意义, 其余的前序和都轮不到与0做差, 因为他们本身与0相距太远, 只有
    临近0两边的前序和才有比较的价值, 所以这个0的存在就间接把所有前序和本身也带到了比较当中*/
}
