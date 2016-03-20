public class Solution {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */
     
    
    //version 1: (首选)用一个for
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int i = 0;
        for(int k = 0; k < nums.length; k++) //可以达到想留几个留几个
        {
            if(i < 2 || nums[k] > nums[i - 2]) //在这里比如i < 3 || nums[k] > nums[i - 3]就是留3个
            {
                nums[i] = nums[k];
                i++;
            }
        }
        return i; //因为每次替换完i都马上++, 所以这里i是最后一次++之后的下标, 和有效数组长度相等
    }
    
    
    
    
    
    
    //version 2: 用两个for
    public int removeDuplicates(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0)
        {
            return 0;
        }
        
        int cur = 0;
        int i, j;
        for(i = 0; i < nums.length;)
        {
            int now = nums[i];
            for(j = i; j < nums.length; j++)
            {
                if(nums[j] != now)
                {
                    break;
                }
                if(j - i < 2)
                {
                    nums[cur++] = now;
                }
            }
            i = j;
        }
        return cur;
    }
    
    
    
    
    
    
    
    //version 3: 用一个while
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len <= 2) {
            return len;
        }
        int res = 0;
        int left = 0;
        int right = 0;
        while (left < len) {
            right = left + 1;
            while (right < len && nums[right] == nums[left]) {
                right++;
            }
            if (right - left >= 2) {
                nums[res++] = nums[right - 1];
                nums[res++] = nums[right - 1];
            } else {
                nums[res++] = nums[right - 1];
            }
            left = right;
        }
        return res;
    }
}
