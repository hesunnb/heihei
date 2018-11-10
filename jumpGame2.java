public class Solution {

    //动归
    public int jump(int[] A) {
        if(A == null || A.length == 0) {
            return 0;
        }
        
        int[] result = new int[A.length];
        result[0] = 0;
        for(int i = 1; i < A.length; i++) {
            result[i] = Integer.MAX_VALUE; //这个max起到一个标志位的作用, 如果中间有一个点跳不到, 那么这个点以后的所有值都是Integer.MAX_VALUE,
            //最终的返回值也是Integer.MAX_VALUE; 但是leetcode里假设这个jump是默认就能跳到最后的, 所以这个判断在这里不用加, 
            //但是万一有的题要有什么固定失败返回值的话, 就可以把这个Integer.MAX_VALUE加上然后改成那个要返回的失败值
            for(int j = 0; j < i; j++) {
                if(result[j] != Integer.MAX_VALUE && j + A[j] >= i) { //如果j处这个点能够一步跳到i点, 那么就在j处这个地方加一步, 
                    //所以result中每一个存的都是到当前的最小步数
                    result[i] = result[j] + 1;
                    break; //加完之后退出, 否则后面会覆盖最小步数
                }
            }
        }
        return result[A.length - 1];
    }
    
    
    //贪心(一道题一个贪心方法，所以这题的贪心和１问的贪心就不一样了)
    // version 2: Greedy
    public int jump(int[] A) { //用一个farest来标志最远, jump每次跳到s到e的区间内, 找下一个能跳的最远的地方
        if(A == null || A.length == 0) {
            return 0;
        }
        
        int start = 0, end = 0, jumps = 0; //区域起点, 区域终点, 需要跳跃的次数
        while(end < A.length - 1) {
            jumps++;
            int farest = end; //这里是int farest, 每次循环都新定义一个farest, 让farest为最远并参与下面的判断
            for(int i = start; i <= end; i++) { //从区域头到区域尾
                if(i + A[i] > farest) { //在区域中找能有比farest更远的, 有就改变farest的值
                    farest = i + A[i];
                }
            }
            start = end + 1; //在farest前(包括farest)和当前点之后确定区域, 因为不必每次都要跳到farest, 要跳到这个区域内, 找到下一个能跳到更远的
            //地方的点, 然后跳到区域中的这个点
            end = farest;
        }
        return jumps;
    }
}
