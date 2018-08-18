/*There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end 
coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and 
end of the diameter suffice. Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow 
shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up 
infinitely. The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 
(bursting the other two balloons).*/

class Solution {
    
    //为什么这么做嘞, 以[[2,4], [1,4], [1,5], [3,6], [7,10]]为例, 那么第一次端点选出来的就是4(比如是[2,4]这个气球), [2,4]这个气球是一定有
    //arrow要穿过的, 又因为排好序了, 所以其他气球都在[2,4]这个气球之后, 所以如果其他气球要是和[2,4]气球有重合的话, 那么4这个end位置就是最好的
    //位置, 就是刚刚重合的那个点, 而这个点就是arrow最好穿过的地方, 假设3处也有重合, 那3重合, 4也一定重合, 所以还是4位置最好; 这也就是为什么
    //所有箭头都要选end的位置, 就是气球的最右边
    public int findMinArrowShots(int[][] points) {
		
		if(points == null || points.length == 0 || points[0].length == 0) {
			return 0;
		}
		
        Arrays.sort(points, new pointsComparator()); //按照每一段的end排
        int arrowPos = points[0][1]; //排完序的第一个end
        int arrowCnt = 1;
        for (int i = 1; i < points.length; i++) {
            if (arrowPos >= points[i][0]) { //跳过就是一口气能穿过的气球
                continue;
            }
            arrowCnt++; //换下一根箭
            arrowPos = points[i][1]; //切换到下一个开始的end
        }
        return arrowCnt;
    }
	
	class pointsComparator implements Comparator<int[]> { //这里必须写int[], 写成Integer[]就错了

		@Override
		public int compare(int[] o1, int[] o2) {
			// TODO Auto-generated method stub
			return o1[1] - o2[1];
		}
	}
    /*Idea:
    We know that eventually we have to shoot down every balloon, so for each ballon there must be an arrow whose position is between
    balloon[0] and balloon[1] inclusively. Given that, we can sort the array of balloons by their ending position. Then we make 
    sure that while we take care of each balloon in order, we can shoot as many following balloons as possible.

    So what position should we pick each time? We should shoot as to the right as possible, because since balloons are sorted, 
    this gives you the best chance to take down more balloons. Therefore the position should always be balloon[i][1] for the ith 
    balloon.

    This is exactly what I do in the for loop: check how many balloons I can shoot down with one shot aiming at the ending 
    position of the current balloon. Then I skip all these balloons and start again from the next one (or the leftmost remaining one) 
    that needs another arrow.

    Example:

    balloons = [[7,10], [1,5], [3,6], [2,4], [1,4]]
    After sorting, it becomes:

    balloons = [[2,4], [1,4], [1,5], [3,6], [7,10]]
    So first of all, we shoot at position 4, we go through the array and see that all first 4 balloons can be taken care of by 
    this single shot. Then we need another shot for one last balloon. So the result should be 2.*/
}
