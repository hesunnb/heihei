/*
Given a string which contains only letters. Sort it by lower case first and upper case second.
Notice

It's NOT necessary to keep the original order of lower-case letters and upper case letters.

 Example

For "abAcD", a reasonable answer is "acbAD"
*/

public class Solution {

    //双指针: 同PartitionArray
    public void sortLetters(char[] nums) {
        // write your code here
        if(nums == null || nums.length == 0) {
            return;
        }
        
        char pivot = 'Z';
        
        int left = 0, right = nums.length - 1;
        while (left <= right) {

            while (left <= right && nums[left] > pivot) { //大的排在前
                left++;
            }

            while (left <= right && nums[right] <= pivot) { //小的排在后
                right--;
            }

            if (left <= right) { //进行交换
                char temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                
                left++;
                right--;
            }
        }
    }
 
 
    //version2:
    public void sortLetters(char[] chars) {
        //write your code here
        
        if(chars == null || chars.length == 0) {
            return;
        }
        
        char pivot = 'Z';
        int store = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > pivot) { //跟快排反过来, 把大的排在前, 小的排在后; store与i中间卡的都是小于等于Z的
                swap(chars, i, store);
                store++;
            }
        }
        //这个地方也不用swap
    }
    
    private static void swap(char[] a, int i, int j) {
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
