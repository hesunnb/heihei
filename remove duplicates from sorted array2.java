public class Solution {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */
     
    
    //version 1: (首选)用一个for
    public int removeDuplicates(int[] nums) {
    
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int index = 0; //保存有效位置
        for(int i = 0; i < nums.length; i++) {
            if(index < 2 || nums[i] > nums[index - 2]) { //前两个值保留, 后面的值如果大于index处前两个, 就在index处放置新值, 间隔为2
                nums[index++] = nums[i];
            }
        }
        
        return index;
    }    
    
    /*
    此题可以扩展为duplicates are allowed at most 3次, 4次....n次, 只需改动:
    
    重复3次: if(index < 3 || nums[i] > nums[index - 3])
    重复4次: if(index < 4 || nums[i] > nums[index - 4])
    .
    .
    .以此类推
    */
    
    

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
