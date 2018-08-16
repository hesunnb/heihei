/*Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();*/

class Solution {

    private int[] nums;
    private Random random;
    
    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if(nums == null) return null;
        int[] temp = nums.clone();
        for(int i = temp.length - 1; i > 0; i--) { //模拟不放回抽样, 等概率
            int j = random.nextInt(i + 1);
            swap(temp, i, j);
        }
        return temp;
    }
    
    private void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
    /*这道题的意图就是模拟一个不放回抽样, 加一个不放回抽样的工作原理:
    例如有10个球,9个白球1个红球,采用不放回抽样,第一个人和第十个人抽到红球的概率是相等的,概率都是1/10.首先第一个人的概率是1/10；第二个人的概率
    是两部分：在第一个人没抽到的情况概率是9/10,在乘以自己抽到的概率1/9,所以也是1/10,依次类推,第三个人的概率是9/10乘以8/9乘以1/8,最后也等于1/10.
    抽奖也是这样,先抽后抽概率都一样.
    
    在shuffle函数中, 每次都是移除数组的第一个, 移除后抽样(random.nextInt就是对随机抽的模拟), 因为移除第一个, 所以是不放回抽样; 不放回抽样
    的每一轮都要考虑前面的所有轮, 比如计算第七轮, 那么要从第一轮一直乘到第七轮
    
    为什么要swap, 因为要把抽中的值移除, random.nextInt就是随机抽, 抽中的数就要被移除, 交换到第一位是因为移除方便, 因为是对数组操作, 所以每次
    都移除第一个方便
    
    因为每个人被抽中的概率都是1/n, 也就是说在permutation中, 它这次出现在这个permutation中的位置的概率也是1/n, 其它元素也都是1/n, 所以ok*/
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
