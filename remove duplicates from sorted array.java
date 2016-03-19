public class Solution {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */
     
    
    //version 1: (首选)就是扫一遍, 两个指针(i和k)
    public int removeDuplicates(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int i = 0;
        for(int k = 0; k < nums.length; k++) //k走所有的元素
        {
            if(i == 0 || nums[k] > nums[i - 1]) //i处只放比自己大的元素, 之后再++
            {
                nums[i] = nums[k];
                i++;
            }
        }
        return i;
    }
    
    
    
    
    
    
    //version 2: 和上面同样的思路, 不同的写法
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int size = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != A[size]) {
                A[++size] = A[i];
            }
        }
        return size + 1;
    }
    
    
    
    
    
    
    
    //version 3: 也是一样
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        int result = 1;
        for (int i = 0; i < nums.length - 1; i++) {
        	if (nums[i] == nums[i + 1]) {
        		continue;
        	} else {
        		nums[result] = nums[i + 1];
        		result++;
        	}
        }
        return result;
    }
}
