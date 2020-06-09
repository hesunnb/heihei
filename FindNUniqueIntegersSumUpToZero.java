/*Given an integer n, return any array containing n unique integers such that they add up to 0.

 

Example 1:

Input: n = 5
Output: [-7,-1,1,3,4]
Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
Example 2:

Input: n = 3
Output: [-1,0,1]
Example 3:

Input: n = 1
Output: [0]*/

class Solution {

    //一开始会想到分奇偶, 但是最后发现不用分, 奇数的时候数组的中间位没有经过修改, 默认就是0
    public int[] sumZero(int n) {
        
        if(n <= 0) {
            return new int[]{};
        }
        int[] result = new int[n];

        for(int i = 1; i < n/2 + 1; i++) { //i不从0开始就是方便用i和-i
            result[i - 1] = i;
            result[n - i] = -i;
        }
        return result;
    }
    //n=6, 1 2 3 -3 -2 -1
    //n=7, 1 2 3 0 -3 -2 -1
 
    //多开了一个list
    public int[] sumZero(int n) {
        if(n <= 0) {
            return new int[]{};
        }
        
        List<Integer> list = new ArrayList<>();
        int temp = n / 2;
        while(temp > 0) {
            list.add(temp);
            list.add(-temp);
            temp--;
        }
        if(n % 2 != 0) {
            list.add(0);
        }
        int[] result = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);   
        }
        return result;
    }
}
