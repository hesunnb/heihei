/*You are given a string representing an attendance record for a student. The record only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False*/


class Solution {

    //solution1:
    public boolean checkRecord(String s) {
        
        if(s == null || s.length() == 0) {
            return false;
        }
        
        int countA = 0;
        int countL = 0;
       
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'A'){ //A的情况
                countA++;
                countL = 0;
            }
            else if(s.charAt(i) == 'L') { //L的情况, 对于连续L的情况处理方式就是第一个是L就++, 第二个不是就把计数清0
                countL++;
            }
            else{ //"LPLPLPLPLPL"这种情况
                countL = 0;
            }
            if(countA>1 || countL>2){
                return false;
            }
        }
        return true;
    }
    
    
    //solution2:
    public boolean checkRecord(String s) {
        
        if(s == null || s.length() == 0) {
            return false;
        }
        
        if(s.indexOf("A") != s.lastIndexOf("A") || s.contains("LLL")) { //这个方法很巧妙
            return false;
        }
        return true;
    }
    
    //solution3:
}
