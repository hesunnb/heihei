class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    
   public void sortColors2(int[] colors, int k) {
        // write your code here
        
        //快排模板
        public void sortColors2(int[] colors, int k) {
            // write your code here
            sort(colors, 0, colors.length - 1);
        }
        
        private void sort(int[] nums, int first, int last) {
    		if(first < last) {
    			int pos = partition(nums , first, last); //找第一个数应该在的位置
    			sort(nums, first, pos - 1); //该点左边排
    			sort(nums, pos + 1, last); //该点右边排
    		}
    	}
       
        private int partition(int[] nums, int start, int end) { //快排中的partition方法
            int pivot = nums[end]; //pivot就是支点, 以谁作为参考
            int store = start;
            for (int i = start; i < end; i++) {
                if (nums[i] < pivot) {
                    swap(nums, i, store); //把比pivot小的元素都窜到store前面去; store指的永远是比pivot大的元素的下标
                    store++;
                }
            }
            swap(nums, store, end);
            return store;
        }
        
        
        //木桶排序法, 并不通用(时间O(n), 空间: inspace)
        if (colors == null) {
            return;
        }
        
        int len = colors.length;
        for (int i = 0; i < len; i++) {
            // Means need to deal with A[i]
            while (colors[i] > 0) {
                int num = colors[i];
                if (colors[num - 1] > 0) {    
                    // 1. There is a number in the bucket, 
                    // Store the number in the bucket in position i;
                    colors[i] = colors[num - 1];
                    colors[num - 1] = -1;
                } else if (colors[num - 1] < 0) {
                    // 2. Bucket is using.
                    colors[num - 1]--;
                    // delete the A[i];
                    colors[i] = 0;
                } else if (colors[num - 1] == 0) {
                    // 3. The bucket is empty.
                    colors[num - 1] = -1;
                    // delete the A[i];
                    colors[i] = 0;
                }
            }
        }
        
        int index = len - 1;
        for (int i = k - 1; i >= 0; i--) {
            int cnt = -colors[i];
            
            // Empty number.
            if (cnt == 0) {
                continue;
            }
                                
            while (cnt > 0) {
                colors[index--] = i + 1;
                cnt--;
            }
        }
    }
    

    //这个答案九章的, 不算快排, 应该没有快排好 
    public void sortColors2(int[] colors, int k) {
        int count = 0;
        int start = 0;
        int end = colors.length-1;
        while (count < k) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            
            for (int i = start; i <= end; i++) { //每次start和end都会收缩, 所以要从新计算最大最小值
                min = Math.min(min, colors[i]);
                max = Math.max(max, colors[i]);
            }
            int left = start;
            int right = end;
            int cur = left;
            while(cur <= right) { //和sort1一样, 起的作用就是把每次的最大值挪到end的右面, 最小值移到start的左面, 然后从新的start到end再从新排(每次能搞定两个数, 所以要k / 2次能结束)
                if (colors[cur] == min) {
                    swap(left, cur, colors);
                    cur++;
                    left++;
                } else if (colors[cur] > min && colors[cur] < max) {
                    cur++;
                } else {
                    swap(cur, right, colors);
                    right--;
                }
            }
            count += 2; // 要k / 2次
            start = left;
            end = right;
        }
    }
    
     void swap(int left, int right, int[] colors) {
        int tmp = colors[left];
        colors[left] = colors[right];
        colors[right] = tmp;
    }
    //counting sort和之前的一样, 就是这回数多了, 要开一个数组来记录每个元素出现啦多少回, 所以是O(k)
    
    /*//直接快排, 时间: O(nlogn), 空间: O(logn)因为要排logn次, 递归每次都要开栈
        public void sortColors2(int[] colors, int k) {
            // write your code here
            sort(colors, 0, colors.length - 1);
        }
        
        private void sort(int[] a, int first, int last) {
    		int pos;
    		if(first < last) {
    			pos = FindPos(a , first, last); //找第一个数应该在的位置
    			sort(a, first, pos - 1); //该点左边排
    			sort(a, pos + 1, last); //该点右边排
    		}
    	}
    	
    	private int FindPos(int[] a, int first, int last) {
    		int val = a[first]; //每次都以第一个数为基准
    		while(first < last) {
    			while(first < last && a[last] >= val) { //后指针先开始，比基准数大就留下
    				last--;
    			}
    			a[first] = a[last]; //比基准数小就向前赋值
    			
    			while(first < last && a[first] <= val) { //然后是头指针，比基准数小就留下
    				first++;
    			}
    			a[last] = a[first]; //比基准数大就向后赋值
    		}
    		a[first] = val; //此时first == last, 没有last < first的情况，first和last的左边都比val小，first和last的右边都比val大
    		return first; //返回下标，第一个应该所在的位置
    	}*/
}
