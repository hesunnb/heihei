/*Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, 
or * between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []*/

class Solution {
    /*This problem has a lot of edge cases to be considered:

    1. overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
    2. 0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
    3. a little trick is that we should save the value that is to be multiplied in the next recursion.*/
    public List<String> addOperators(String num, int target) {

        List<String> result = new ArrayList<>();
            if(num == null || num.length() == 0) {
            return result;
        }

        helper(result, num, "", target, 0, 0, 0);
        return result;
    }

    public void helper(List<String> result, String num, String path, int target, int pos, long sum, long preVal) {
        if(pos == num.length()) {
            if(sum == target) {
                result.add(path);
            }
            return;
        }

        for(int i = pos; i < num.length(); i++) {
            if(i != pos && num.charAt(pos) == '0') { //比如input是"105", target=5, 这时候就要防止出现05这样以0开头的数字变成cur
                break;
            }
            long cur = Long.parseLong(num.substring(pos, i + 1)); //此处要用long, 因为input的num会比Integer.MAX_VALUE要大
            if(pos == 0) {
                helper(result, num, path + cur, target, i + 1, cur, cur);
            } else {
                helper(result, num, path + "+" + cur, target, i + 1, sum + cur, cur);
                helper(result, num, path + "-" + cur, target, i + 1, sum - cur, -cur);
                helper(result, num, path + "*" + cur, target, i + 1, sum - preVal + preVal * cur, preVal * cur);
                //最重要的就是这个乘法, 正常递归1-2*3会变成(1-2)*3, 那么为了1-2*3, 所以先要1-2这个得到的-1减去之前加上的-2, 变回1, 然后
                //1+(-2)*3就可以了, product一直保留了之前那步加减乘的结果
            }
        }
    }
    /*0 0 具体打印过程
    1 1 1
    1+2 3 2
    1+2+3 6 3
    1+2-3 0 -3
    1+2*3 7 6
    1-2 -1 -2
    1-2+3 2 3
    1-2-3 -4 -3
    1-2*3 -5 -6
    1*2 2 2
    1*2+3 5 3
    1*2-3 -1 -3
    1*2*3 6 6
    1 1 1
    1+23 24 23
    1-23 -22 -23
    1*23 23 23
     0 0
    12 12 12
    12+3 15 3
    12-3 9 -3
    12*3 36 36
     0 0
    123 123 123
    */
}
