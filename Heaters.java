/*Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be 
covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of 
heaters.

Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.

Example 1:
Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.

Example 2:
Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed*/


class Solution {
    /*test case:
    1 2 3 4 5 6 7 8
    4,6
    
    1 2 3 5 15
    2,30
    
    1 2 3 4
    1,4
    
    1
    1,2,3,4*/
    /*The idea is to leverage decent Arrays.binarySearch() function provided by Java.

    1. For each house, find its position between those heaters (thus we need the heaters array to be sorted).
    2. Calculate the distances between this house and left heater and right heater, get a MIN value of those two values. Corner cases 
    are there is no left or right heater.
    3. Get MAX value among distances in step 2. It’s the answer.
    
    Time complexity: max(O(nlogn), O(mlogn)) - m is the length of houses, n is the length of heaters. nlogn是排序的时间, mlogn是二分查找
    的时间*/
    
    //solution1:自己写二分, 就是searchInsertPosition那道题
    public int findRadius(int[] houses, int[] heaters) {
        
        if(houses == null || heaters == null || houses.length == 0 || heaters.length == 0) {
            return -1;
        }
        
        Arrays.sort(heaters);
        int result = Integer.MIN_VALUE;
        
        for (int house : houses) {
            int index = searchInsert(heaters, house); //自己写的就不用处理库中Arrays.binarySearch的返回值了
            int dist1 = 0;
            int dist2 = 0;
            if(index - 1 >= 0) { //如果左边有加热器, 或者右边没有加热器的corner case
                dist1 = house - heaters[index - 1]; //house到左边加热器的距离
            } else {
                dist1 = Integer.MAX_VALUE;
            }
            
            if(index < heaters.length) { //如果右边有加热器, 或者左边没有加热器的corner case
                dist2 = heaters[index] - house; //house到右边加热器的距离
            } else {
                dist2 = Integer.MAX_VALUE;
            }
        
            result = Math.max(result, Math.min(dist1, dist2));
        }
        
        return result;
    }
    
    public int searchInsert(int[] A, int target) {
       
        int start = 0, end = A.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        //退出来以后，start和end的值现在已经固定了，接下来就是判断target相对于start和end的位置
        //==是target存在于数组中的情况
        if(A[start] >= target) { //target在start前返回start的下标
            return start;
        }
        else if(A[end] >= target) { //target在start后end前返回end下标
            return end;
        }
        else { //target在end后返回end下标+1
            return end + 1;
        }
    }
    
    
    //solution2:用库的Arrays.binarySearch
    public int findRadius(int[] houses, int[] heaters) {
        
        if(houses == null || heaters == null || houses.length == 0 || heaters.length == 0) {
            return -1;
        }
        
        Arrays.sort(heaters);
        int result = Integer.MIN_VALUE;
        
        for (int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            if (index < 0) {
        	    index = -(index + 1); //把Arrays.binarySearch返回的值改成对应能够插入到heaters中的下标
            }
            int dist1 = 0;
            int dist2 = 0;
            if(index - 1 >= 0) { //如果左边有加热器, 或者右边没有加热器的corner case
                dist1 = house - heaters[index - 1]; //house到左边加热器的距离
            } else {
                dist1 = Integer.MAX_VALUE;
            }
            
            if(index < heaters.length) { //如果右边有加热器, 或者左边没有加热器的corner case
                dist2 = heaters[index] - house; //house到右边加热器的距离
            } else {
                dist2 = Integer.MAX_VALUE;
            }
        
            result = Math.max(result, Math.min(dist1, dist2));
        }
        
        return result;
    }
    /*
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int result = Integer.MIN_VALUE;
        
        for (int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            if (index < 0) {
        	index = -(index + 1);
            }
            int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE; //discuss原版, 便于看懂, 贴到这里
            int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;
        
            result = Math.max(result, Math.min(dist1, dist2));
        }
        
        return result;
    }*/
}
